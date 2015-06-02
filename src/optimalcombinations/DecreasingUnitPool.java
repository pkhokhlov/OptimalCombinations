package optimalcombinations;

import java.util.ArrayList;

/**
 * @author Pavel Khokhlov
 * @author jackprescott
 */

public class DecreasingUnitPool
{
	Group best_;
	int groupSize_;
	int iterations_ = 0;
	
	ArrayList<Unit> pool_;
	GroupSet strongestGroups_;
	
	public DecreasingUnitPool(ArrayList<Unit> pool, int groupSize)
	{
		pool_ = pool;
		groupSize_ = groupSize;
	}
	
	/**
	 * @precondition: the pool is not empty
	 * @postcondition: best_ contains the strongest group possible from pool_
	 */
	public void findStrongestGroupSize3() throws inadequatePoolSizeException
	{
		if(pool_.size() < groupSize_)
			throw new inadequatePoolSizeException();
		
		int highestScore = 0;
		for(int a = 0; a < pool_.size() - 2; a++)
		{
			for(int b = a + 1; b < pool_.size() - 1; b++)
			{
				for(int c = b + 1; c < pool_.size(); c++)
				{
					Group temp = new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c)}); // only works for groups of size 3
					int gScore = temp.getGroupScore();
					if(gScore > highestScore)
					{
						highestScore = gScore;
						best_ = temp;
					}
				}
			}
		}
	}
	
	/**
	 * @precondition: the pool is not empty
	 * @postcondition: best_ contains the strongest group possible from pool_
	 */
	public void findStrongestGroupSize4() throws inadequatePoolSizeException
	{
		if(pool_.size() < groupSize_)
			throw new inadequatePoolSizeException();
		
		int highestScore = 0;
		for(int a = 0; a < pool_.size() - 3; a++)
		{
			for(int b = a + 1; b < pool_.size() - 2; b++)
			{
				for(int c = b + 1; c < pool_.size() - 1; c++)
				{
					for(int d = c + 1; d < pool_.size(); d++)
					{
						Group temp = new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d)});
						int gScore = temp.getGroupScore();
						if(gScore > highestScore)
						{
							highestScore = gScore;
							best_ = temp;
						}
					}
				}
			}
		}
	}
	
	/**
	 * @precondition: the pool is not empty
	 * @postcondition: best_ contains the strongest group possible from pool_
	 */
	public void findStrongestGroupSize5() throws inadequatePoolSizeException
	{
		if(pool_.size() < groupSize_)
			throw new inadequatePoolSizeException();
		
		int highestScore = -100000;
		for(int a = 0; a < pool_.size() - 4; a++)
		{
			for(int b = a + 1; b < pool_.size() - 3; b++)
			{
				for(int c = b + 1; c < pool_.size() - 2; c++)
				{
					for(int d = c + 1; d < pool_.size() - 1; d++)
					{
						for(int e = d + 1; e < pool_.size(); e++)
						{
							Group temp = new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d), pool_.get(e)});
							int gScore = temp.getGroupScore();
							if(gScore > highestScore)
							{
								highestScore = gScore;
								best_ = temp;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * pool_.remove(...) works because the pool_ is partly made of the units within best_
	 * @precondition: best_ contains the most recently calculated strongest group
	 * @postcondition: all members of best_ are removed from pool_
	 */
	public void removeFromPool()
	{
		for(int i = 0; i < best_.getMembers().size(); i++)
		{
			pool_.remove(best_.getMembers().get(i));
		}
	}
	
	/**
	 * @precondition: inadequatePoolSizeException is thrown
	 * @postcondition: the remaining units are inserted into the group that would be strongest with the remaining units
	 * @param remaining - the set of units that should be inserted
	 * NOTE: the remaining units may not be all of pool_.
	 */
	public void insertIntoOptimalGroup(ArrayList<Unit> remaining)
	{
		for(int i = 0; i < remaining.size(); i++)
		{
			int strongestIndex = 0;
			int highestScoreDif = Integer.MIN_VALUE;
			
			for(int j = 0; j < strongestGroups_.size(); j++)
			{
				if(((Group) strongestGroups_.get(j)).getSize() == groupSize_)
				{
					int groupScore = ((Group) strongestGroups_.get(j)).getGroupScore();
					strongestGroups_.get(j).addUnit(remaining.get(i)); // adds the unit in question to the group
					int tempScore = strongestGroups_.get(j).getGroupScore();
					int scoreDif = tempScore - groupScore;
					if(scoreDif > highestScoreDif) // tests if the newly formed group has the highest score out of the others
					{
						highestScoreDif = scoreDif; // if it does, the index and score is kept
						strongestIndex = j;
					}
					strongestGroups_.get(j).removeUnit(remaining.get(i)); // the unit in question is removed
				}
			}
			strongestGroups_.get(strongestIndex).addUnit(remaining.get(i)); // the strongest resulting group from adding the unit from remaining is added
		}
		pool_ = new ArrayList<Unit>(); // TODO: make sure that the remaining people that are not included in remaining are not deleted
	}
	
	public GroupSet findStrongestGroups()
	{
		strongestGroups_ = new GroupSet();
		switch(groupSize_)
		{
			case 3:
				while(pool_.size() > 0)
				{
					try
					{
						findStrongestGroupSize3();
					} 
					catch (inadequatePoolSizeException e)
					{
						insertIntoOptimalGroup(pool_);
						//e.printStackTrace();
						break;
					}
					strongestGroups_.add(best_);
					removeFromPool();
				}
				break;
			case 4:
				while(pool_.size() > 0)
				{
					try
					{
						findStrongestGroupSize4();
					} 
					catch (inadequatePoolSizeException e)
					{
						insertIntoOptimalGroup(pool_);
						//e.printStackTrace();
						break;
					}
					strongestGroups_.add(best_);
					removeFromPool();
				}
				break;
			case 5:
				while(pool_.size() > 0)
				{
					try
					{
						findStrongestGroupSize5();
					} 
					catch (inadequatePoolSizeException e)
					{
						insertIntoOptimalGroup(pool_);
						//e.printStackTrace();
						break;
					}
					strongestGroups_.add(best_);
					removeFromPool();
				}
				break;
		}
		return strongestGroups_;
	}
	
	class inadequatePoolSizeException extends Exception
	{
		private static final long serialVersionUID = -8560120916174900757L;
		// ask to either include remaining units in the existing groups 
		// or create new group with the remaining units
	}
}
