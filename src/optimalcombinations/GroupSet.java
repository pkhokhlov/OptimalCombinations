package optimalcombinations;

import java.util.ArrayList;

public class GroupSet 
{
	ArrayList<Group> groupSet_;
	int averageStrengthPerGroup_;
	int minimumStrengthPerGroup_;
	int minimumStrengthPerUnit_;
	int numberOfGroups_;
	
	GroupSet(Group[] groups)
	{
		for (Group g : groups)
		{
			addGroup(g);
		}
	}
	
	void addGroup(Group g)
	{
		groupSet_.add(g);
		numberOfGroups_+=1;
	}
	
	void removeGroup(Group g)
	{
		groupSet_.remove(g);
		numberOfGroups_-=1;
	}
	
	int getAverageGroupStrength()
	{
		int totalStrength=0;
		for(Group g : groupSet_)
		{
			totalStrength+=g.getGroupScore();
		}
		return totalStrength/numberOfGroups_;
	}
	
	int getMinimumGroupStrength()
	{
		minimumStrengthPerGroup_=2147483647;
		for(Group g : groupSet_)
		{
			if(minimumStrengthPerGroup_>g.getGroupScore())
			{
				minimumStrengthPerGroup_=g.getGroupScore();
			}
		}
		return minimumStrengthPerGroup_;
	}
	
}
