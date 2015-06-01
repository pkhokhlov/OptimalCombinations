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
	public static int groupSize;
	
	
	Group()
	{
		
	}
	
	public Group(Unit A, Unit B, Unit C)
	{
		members_.add(A);
		members_.add(B);
		members_.add(C);
		groupSize = 3;
	}
	
	public ArrayList<Unit> getMembers()
	{
		return members_;
	}
	
	public void setGroupSize(int gSize)
	{
		groupSize = gSize;
	}
	
	public int getSize()
	{
		return groupSize;
	}

	public void addUnit(Unit u)
	{
		members_.add(u);
	}
	
	/**
	 * Calculates the groupscore.
	 * Changed x to equal y in second for loop for optimization.
	 * @return groupScore
	 */
	public int getGroupScore()
	{
		int groupScore = 0;

		for (int y = 0; y < members_.size(); y++)
		{
			for (int x = y; x < members_.get(0).posConnSize_; x++)
			{
				
				int tempPoints = members_.get(y).getPosConns().indexOf(members_.get(x));
				
				if (tempPoints != -1) // if members_.get(x) is present in the positive connections of members_(y)
				{
					groupScore += members_.get(0).posConnSize_ - tempPoints; // adds the priority of the member to the groupscore
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
