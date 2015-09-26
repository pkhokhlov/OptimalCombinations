/**
 * Copyright 2015 Pavel Khokhlov <pkhokhlov@hotmail.com>
 * 				  Jack Prescott <jackbprescott@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.optimalCombinations.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Group
{
	ArrayList<Unit> members_ = new ArrayList<Unit>();
	int groupSize_;
	int minimumUnitStrength_;
	boolean flag_=false;
	
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
	
	/**
	 * This function calculates the group strength.
	 * This function works for any number of positive connections.
	 * This function works if the negative connection is null.
	 * @return groupScore
	 */
	public int getGroupScore()
	{
		int groupScore = 0;
		for (int i = 0; i < members_.size(); i++)
		{
			members_.get(i).getPosConns().remove(null);
			ArrayList<Unit> memPosConnsi = members_.get(i).getPosConns();
			
			middleLoop:
			for (int posConnIndex = 0; posConnIndex < memPosConnsi.size(); posConnIndex++)
			{
				Unit posConnx = memPosConnsi.get(posConnIndex);
				for (int j = 0; j < members_.size(); j++)
				{
					if(posConnx == members_.get(j))
					{
						if(posConnIndex == 0)
							groupScore += 3;
						else if(posConnIndex == 1)
							groupScore += 2;
						else 
							groupScore++;
						
						continue middleLoop; // does not go further in comparing once match is found
					}
				}
			}
		}
		
		outerLoop:
		for(int i = 0; i < members_.size(); i++)
		{
			Unit negConni = members_.get(i).getNegConn();
			for(int j = 0; j < members_.size(); j++)
			{
				if(negConni == members_.get(j))
				{
					groupScore -= 3;
					continue outerLoop;
				}
			}
		}
		
		return groupScore;
	}
	
	public int getMinUnitScore()
	{
			int minUnitScore = Integer.MAX_VALUE;
			for (int i = 0; i < members_.size(); i++)
			{
				members_.get(i).getPosConns().remove(null);
				ArrayList<Unit> memPosConnsi = members_.get(i).getPosConns();
				int tempUnitScore = 0;
				middleLoop:
				for (int x = 0; x < memPosConnsi.size(); x++)
				{
					Unit posConnx = memPosConnsi.get(x);
					for (int j = 0; j < members_.size(); j++)
					{
						if(posConnx == members_.get(j))
						{
							if(x == 0)
								tempUnitScore += 3;
							else if(x == 1)
								tempUnitScore += 2;
							else 
								tempUnitScore++;
							
							continue middleLoop; // does not go further in comparing once match is found
						}
					}
				}
				if(tempUnitScore < minUnitScore)
					minUnitScore = tempUnitScore;
			}
			
			return minUnitScore;
	}
	/**
	 * TODO: fix
	 * @return
	 */
	public int getMinimumUnitStrength()
	{
		minimumUnitStrength_ = Integer.MAX_VALUE;

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
					unitStrength += members_.get(0).getPosConnSize() - tempPoints; // adds the priority of the member to the unitStrength
					                                                         // the higher the priority, the lower the index in posConns_
				}															 
				else if (members_.get(y).getNegConn().getName().equals(members_.get(x).getName())) // else if members_.get(x) is 
				{																				   // a negative connection of members_.get(y)
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

	
	public int getGroupScorePrint()
	{
		int groupScore = 0;
		System.out.println(groupScore);
		for (int i = 0; i < members_.size(); i++)
		{
			members_.get(i).getPosConns().remove(null);
			ArrayList<Unit> memPosConnsi = members_.get(i).getPosConns();
			middleLoop:
			for (int x = 0; x < memPosConnsi.size(); x++)
			{
				Unit posConnx = memPosConnsi.get(x);
				for (int j = 0; j < members_.size(); j++)
				{
					if(posConnx == members_.get(j))
					{
						if(x == 0)
						{
							groupScore += 3;
							System.out.println(groupScore);
						}
						else if(x == 1)
						{
							groupScore += 2;
							System.out.println(groupScore);
						}
						else 
						{
							groupScore++;
							System.out.println(groupScore);
						}
						continue middleLoop; // does not go further in comparing once match is found
					}
				}
			}
		}
		
		outerLoop:
		for(int i = 0; i < members_.size(); i++)
		{
			Unit negConni = members_.get(i).getNegConn();
			for(int j = 0; j < members_.size(); j++)
			{
				if(negConni == members_.get(j))
				{
					groupScore -= 3;
					System.out.println("- 3");
					continue outerLoop;
				}
			}
		}
		
		return groupScore;
	}
	
	/**
	 * This function puts the members of the group in alphabetical order. 
	 */
	public void alphabetize()
	{
		Collections.sort(members_, new Comparator<Unit>() 
		{
	        @Override
	        public int compare(final Unit unit1, final Unit unit2) 
	        {
	            return unit1.getName().compareTo(unit2.getName());
	        }
		});
	}
	
	/**
	 * This function returns the string representation of the group. The group score is included
	 */
	public String toString()
	{
		String result = "{";
		for(int i = 0; i < members_.size() - 1; i++)
		{
			result = result + members_.get(i) + ", ";
		}
		return  result + members_.get(members_.size() - 1) + "}" + "(" + getGroupScore() + ")"; 
	}

	/**
	 * non-functioning
	 */
	public int getGroupScoreObsolete()
	{
		int groupScore = 0;

		for (int i = 0; i < members_.size(); i++)
		{
			for (int x = 0; x < members_.get(i).posConnSize_; x++)
			{	
				ArrayList<Unit> memPosConns = members_.get(i).getPosConns();
				int tempPoints = memPosConns.indexOf(members_.get(x));
				
				if (tempPoints != -1) // if members_.get(x) is present in the positive connections of members_(y)
				{
					groupScore += members_.get(0).getPosConnSize() - tempPoints; // adds the priority of the member to the groupscore
					                                                             // the higher the priority, the lower the index in posConns_
				}															 
				else if (members_.get(i).getNegConn().getName().equals(members_.get(x).getName())) // else if members_.get(x) is 
				{																				   // a negative connection of members_.get(y)
					groupScore -= 3;
				}
			}
		}
		return groupScore;
	}

}
