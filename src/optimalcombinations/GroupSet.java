package optimalcombinations;

import java.util.ArrayList;

public class GroupSet
{
	ArrayList<Group> groupSet_;
	int averageStrengthPerGroup_;
	int minimumStrengthPerGroup_;
	int minimumStrengthPerUnit_;
	int numberOfGroups_=0;
	
	GroupSet()
	{
		
	}
	
	GroupSet(Group[] groups)
	{
		for (Group g : groups)
		{
			groupSet_.add(g);
		}
	}
	
	void add(Group g)
	{
		groupSet_.add(g);
	}
	
	void remove(Group g)
	{
		groupSet_.remove(g);
	}
	
	int size()
	{
		return groupSet_.size();
	}
	
	Group get(int i)
	{
		return groupSet_.get(i);
	}
	
	void findGroupStatistics()
	{
		int totalStrength=0;
		minimumStrengthPerGroup_=2147483647;
		minimumStrengthPerUnit_=2147483647;

		for(Group g : groupSet_)
		{
			totalStrength+=g.getGroupScore();
			
			if(minimumStrengthPerGroup_>g.getGroupScore())
			{
				minimumStrengthPerGroup_=g.getGroupScore();
			}
			
			if(minimumStrengthPerUnit_>g.getMinimumUnitStrength())
			{
				minimumStrengthPerUnit_=g.getMinimumUnitStrength();
			}
			
		}
		numberOfGroups_=size();
		averageStrengthPerGroup_=totalStrength/numberOfGroups_;
	}
	
	int getAverageStrengthPerGroup()
	{
		return averageStrengthPerGroup_;
	}
	
	int getMinimumStrengthPerGroup()
	{
		return minimumStrengthPerGroup_;
	}
	
	int getMinimumStrengthPerUnit()
	{
		return minimumStrengthPerUnit_;
	}
}
