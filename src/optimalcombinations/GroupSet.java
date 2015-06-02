package optimalcombinations;

import java.util.ArrayList;

public class GroupSet
{
	ArrayList<Group> groupSet_ = new ArrayList<Group>();
	int averageStrengthPerGroup_;
	int minimumStrengthPerGroup_;
	int minimumStrengthPerUnit_;
	int numberOfGroups_ = 0;
	
	public GroupSet()
	{
		groupSet_ = new ArrayList<Group>();
	}
	
	public GroupSet(Group[] groups)
	{
		groupSet_ = new ArrayList<Group>();
		for (Group g : groups)
		{
			groupSet_.add(g);
		}
	}
	
	public void add(Group g)
	{
		groupSet_.add(g);
	}
	
	public void remove(Group g)
	{
		groupSet_.remove(g);
	}
	
	public int size()
	{
		return groupSet_.size();
	}
	
	public Group get(int i)
	{
		return groupSet_.get(i);
	}
	
	public void findGroupStatistics()
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
	
	public int getAverageStrengthPerGroup()
	{
		return averageStrengthPerGroup_;
	}
	
	public int getMinimumStrengthPerGroup()
	{
		return minimumStrengthPerGroup_;
	}
	
	public int getMinimumStrengthPerUnit()
	{
		return minimumStrengthPerUnit_;
	}
	
	public String toString()
	{
		return groupSet_.toString();
	}
}
