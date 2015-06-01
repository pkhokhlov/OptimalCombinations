package optimalcombinations;

import java.util.ArrayList;

/**
 * TODO: Include an exception that puts the remaining units from the pool into the 
 *       happiest groups if pool_ is not divisible by groupSize.
 * @author Pavel Khokhlov
 */

public class DecreasingUnitPool
{
	Group best_;
	int groupSize_;
	int iterations_ = 0;
	
	ArrayList<Unit> pool_;
	
	
	public DecreasingUnitPool(ArrayList<Unit> pool, int groupSize)
	{
		pool_ = pool;
		groupSize_ = groupSize; //TODO change for all group sizes
	}
	
	/**
	 * @precondition: the pool is evenly divisible by the groupSize
	 * @postcondition: best_ contains the happiest group possible from pool_
	 */
	public void findHappiestGroupSize3()
	{
		int highestScore = 0;
		for(int a = 0; a < pool_.size() - 2; a++)
		{
			for(int b = a + 1; b < pool_.size() - 1; b++)
			{
				for(int c = b + 1; c < pool_.size(); c++)
				{
					Group temp = new Group(pool_.get(a), pool_.get(b), pool_.get(c)); // only works for groups of size 3
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
	 * @precondition: the pool is evenly divisible by the groupSize
	 * @postcondition: best_ contains the happiest group possible from pool_
	 */
	public void findHappiestGroupSize4()
	{
		int highestScore = 0;
		for(int a = 0; a < pool_.size() - 3; a++)
		{
			for(int b = a + 1; b < pool_.size() - 2; b++)
			{
				for(int c = b + 1; c < pool_.size() - 1; c++)
				{
					for(int d = c + 1; d < pool_.size(); d++)
					{
						Group temp = new Group(pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d));
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
	 * @precondition: the pool is evenly divisible by the groupSize
	 * @postcondition: best_ contains the happiest group possible from pool_
	 */
	public void findHappiestGroupSize5()
	{
		int highestScore = 0;
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
							Group temp = new Group(pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d), pool_.get(e));
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
	 */
	public void removeFromPool()
	{
		for(int i = 0; i < best_.getMembers().size(); i++)
		{
			pool_.remove(best_.getMembers().get(i));
		}
	}
	
	public ArrayList<Group> findHappiestGroups()
	{
		ArrayList<Group> happiest = new ArrayList<Group>();
		switch(groupSize_)
		{
			case 3:
				while(pool_.size() > 0)
				{
					findHappiestGroupSize3();
					happiest.add(best_);
					removeFromPool();
				}
				break;
			case 4:
				while(pool_.size() > 0)
				{
					findHappiestGroupSize4();
					happiest.add(best_);
					removeFromPool();
				}
				break;
			case 5:
				while(pool_.size() > 0)
				{
					findHappiestGroupSize5();
					happiest.add(best_);
					removeFromPool();
				}
				break;
		}
		return happiest;
	}
}