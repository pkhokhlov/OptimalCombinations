/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;

import java.util.ArrayList;

/**
 *
 * @author jackprescott
 * @author Pavel Khokhlov
 */
public class Group
{
	// TODO: include a constant that controls for how many points what is worth
	// TODO: implement a calculator for an arraylist of negative connections
	ArrayList<Unit> members_ = new ArrayList<Unit>();
	int groupSize_;
	int minimumUnitStrength_;
	
	
	Group()
	{
		
	}
	
	public Group(Unit[] units)
	{
		for (Unit u : units)
		{
			addUnit(u);
		}
		groupSize_ = members_.size();
	}
	
	public ArrayList<Unit> getMembers()
	{
		return members_;
	}
	
	public int getSize()
	{
		return members_.size();
	}

	public void addUnit(Unit u)
	{
		members_.add(u);
		groupSize_ = members_.size();
	}
	
	public void removeUnit(Unit u)
	{
		members_.remove(u);
	}
	
	int getMinimumUnitStrength()
	{
		minimumUnitStrength_=2147483647;

		for (int y = 0; y < members_.size(); y++)
		{
			for (int x = 0; x < members_.get(0).posConnSize_; x++)
			{
				int unitStrength=0;
				if(x == y) // cannot have yourself as a positive connection
					continue;
				
				int tempPoints = members_.get(y).getPosConns().indexOf(members_.get(x));
				
				if (tempPoints != -1) // if members_.get(x) is present in the positive connections of members_(y)
				{
					unitStrength += members_.get(0).getPosConnSize() - tempPoints; // adds the priority of the member to the groupscore
					                                                         // the higher the priority, the lower the index in posConns_
				}															 
				else if (members_.get(y).getNegConn().getName().equals(members_.get(x).getName())) // else if members_.get(x) is a negative connection of members_.get(y)
				{
					unitStrength -= 3;
				}
				if(unitStrength<minimumUnitStrength_)
				{
					minimumUnitStrength_=unitStrength;
				}
				
			}
		}
		return minimumUnitStrength_;
	}
	
	
	/**
	 * This function calculates the group score.
	 * TODO: change so that the first index does not have a value > 3 if groupSize_ > 3
	 * @return groupScore
	 */
	public int getGroupScore()
	{
		int groupScore = 0;

		for (int y = 0; y < members_.size(); y++)
		{
			for (int x = 0; x < members_.get(0).posConnSize_; x++)
			{
				if(x == y) // cannot have yourself as a positive connection
					continue;
				
				int tempPoints = members_.get(y).getPosConns().indexOf(members_.get(x));
				
				if (tempPoints != -1) // if members_.get(x) is present in the positive connections of members_(y)
				{
					groupScore += members_.get(0).getPosConnSize() - tempPoints; // adds the priority of the member to the groupscore
					                                                         // the higher the priority, the lower the index in posConns_
				}															 
				else if (members_.get(y).getNegConn().getName().equals(members_.get(x).getName())) // else if members_.get(x) is a negative connection of members_.get(y)
				{
					groupScore -= 3;
				}
			}
		}
		return groupScore;
	}
	
	public String toString()
	{
		String result = "{";
		for(int i = 0; i < members_.size() - 1; i++)
		{
			result = result + members_.get(i) + ", ";
		}
		return  result + members_.get(members_.size() - 1) + "}"; 
	}


}
