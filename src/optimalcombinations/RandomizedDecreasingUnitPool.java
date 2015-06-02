package optimalcombinations;

import java.util.ArrayList;

import optimalcombinations.DecreasingUnitPool.inadequatePoolSizeException;

public class RandomizedDecreasingUnitPool
{
	ArrayList<Unit> pool_;
	int groupSize_;
	ArrayList<GroupSet> randomizedSets = new ArrayList<GroupSet>();
	ArrayList<Group> allGroups = new ArrayList<Group>();
	
	RandomizedDecreasingUnitPool(ArrayList<Unit> pool, int groupSize)
	{
		pool_ = pool;
		groupSize_ = groupSize;
		if(groupSize==3)
		{
			for(int a = 0; a < pool_.size() - 2; a++)
			{
				for(int b = a + 1; b < pool_.size() - 1; b++)
				{
					for(int c = b + 1; c < pool_.size(); c++)
					{
						allGroups.add(new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c)}));
					}
				}
			}
		}
		if(groupSize==4)
		{
			for(int a = 0; a < pool_.size() - 3; a++)
			{
				for(int b = a + 1; b < pool_.size() - 2; b++)
				{
					for(int c = b + 1; c < pool_.size() - 1; c++)
					{
						for(int d = c + 1; d < pool_.size(); d++)
						{
							allGroups.add(new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d)}));
							
						}
					}
				}
			}
		}
		if(groupSize==5)
		{
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
								allGroups.add(new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d), pool_.get(e)}));
							}
						}
					}
				}
			}
		}
	}

	
	void GenerateGroupSets()
	{
		ArrayList<Unit> poolCopy = new ArrayList<Unit>();
		for(Group g : allGroups)
		{
			for(Unit u : pool_)
			{
				poolCopy.add(u);
			}
			for(Unit u : g.getMembers())
			{
				poolCopy.remove(u);
			}
			DecreasingUnitPool DUP = new DecreasingUnitPool(poolCopy, groupSize_);
			GroupSet temp = DUP.findStrongestGroups();
			temp.add(g);
			randomizedSets.add(temp);
			System.out.println(temp.toString());
		}
	}
	
	GroupSet findStrongestGroupSet(int minPerUnit, int minPerGroup){
		GenerateGroupSets();
		int averageStrengthBest=Integer.MIN_VALUE;
		GroupSet bestSet= new GroupSet();
		for (GroupSet s: randomizedSets )
		{
			if(s.getAverageStrengthPerGroup()>averageStrengthBest && s.getMinimumStrengthPerGroup()> minPerGroup && s.getMinimumStrengthPerUnit()>minPerUnit)
			{
				averageStrengthBest=s.getAverageStrengthPerGroup();
				bestSet=s;
			}
		}
		return bestSet;
	}
}