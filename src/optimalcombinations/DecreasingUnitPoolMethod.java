package optimalcombinations;

import java.util.ArrayList;

/**
 * @author Pavel Khokhlov
 */
public class DecreasingUnitPoolMethod
{
	// TODO: include static methods for calculation
	Group best_;
	int groupSize_;
	int iterations_ = 0;
	
	ArrayList<Unit> pool_;
	
	
	public DecreasingUnitPoolMethod(ArrayList<Unit> pool)
	{
		pool_ = pool;
		groupSize_ = 3; //TODO change for all group sizes
	}
	
	/**
	 * precondition: the pool is evenly divisible by the groupSize
	 * @return
	 */
	public void findHappiestGroup()
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
		while(pool_.size() > 0)
		{
			findHappiestGroup();
			happiest.add(best_);
			removeFromPool();
		}
		return happiest;
	}
}
/* WORKING */