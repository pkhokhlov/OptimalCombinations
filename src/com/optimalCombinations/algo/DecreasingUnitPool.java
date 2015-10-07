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

/**
 *     
 * 
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
	 * This function finds the strongest group of 3 units and sets the best_ member to that group.
	 * @precondition: the pool is not empty
	 * @postcondition: best_ contains the strongest group possible from pool_
	 */
	public void findStrongestGroupSize3() throws inadequatePoolSizeException
	{
		if(pool_.size() < groupSize_)
			throw new inadequatePoolSizeException();
		
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool_.size() - 2; a++)
		{
			loopB:
			for(int b = a + 1; b < pool_.size() - 1; b++)
			{
				loopC:
				for(int c = b + 1; c < pool_.size(); c++)
				{
					Group temp = new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c)});
					int negConnIndex = temp.getIndexNegConn();
					if(negConnIndex != -1)
					{
						switch (negConnIndex)
						{
							case 0: continue loopA;
							case 1: continue loopB;
							case 2: continue loopC;
						}
					}
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
	 * This function finds the strongest group of 4 units and sets the best_ member to that group.
	 * @precondition: the pool is not empty
	 * @postcondition: best_ contains the strongest group possible from pool_
	 */
	public void findStrongestGroupSize4() throws inadequatePoolSizeException
	{
		if(pool_.size() < groupSize_)
			throw new inadequatePoolSizeException();
		
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool_.size() - 3; a++)
		{
			loopB:
			for(int b = a + 1; b < pool_.size() - 2; b++)
			{
				loopC:
				for(int c = b + 1; c < pool_.size() - 1; c++)
				{
					loopD:
					for(int d = c + 1; d < pool_.size(); d++)
					{
						Group temp = new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d)});
						int negConnIndex = temp.getIndexNegConn();
						if(negConnIndex != -1)
						{
							switch (negConnIndex)
							{
								case 0: continue loopA;
								case 1: continue loopB;
								case 2: continue loopC;
								case 3: continue loopD;
							}
						}
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
	 * This function finds the strongest group of 5 units and sets the best_ member to that group.
	 * @precondition: the pool is not empty
	 * @postcondition: best_ contains the strongest group possible from pool_
	 */
	public void findStrongestGroupSize5() throws inadequatePoolSizeException
	{
		if(pool_.size() < groupSize_)
			throw new inadequatePoolSizeException();
		
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool_.size() - 4; a++)
		{
			loopB:
			for(int b = a + 1; b < pool_.size() - 3; b++)
			{
				loopC:
				for(int c = b + 1; c < pool_.size() - 2; c++)
				{
					loopD:
					for(int d = c + 1; d < pool_.size() - 1; d++)
					{
						loopE:
						for(int e = d + 1; e < pool_.size(); e++)
						{
							Group temp = new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d), pool_.get(e)});
							int negConnIndex = temp.getIndexNegConn();
							if(negConnIndex != -1)
							{
								switch (negConnIndex)
								{
									case 0: continue loopA;
									case 1: continue loopB;
									case 2: continue loopC;
									case 3: continue loopD;
									case 4: continue loopE;
								}
							}
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
	 * This function removes the units in best_ from the pool.
	 * @precondition: best_ contains the most recently calculated strongest group
	 * @postcondition: all members of best_ are removed from pool_
	 */
	public void removeBestGroupFromPool()
	{
		System.out.println(best_);
		for(int i = 0; i < best_.getMembers().size(); i++)
		{
			pool_.remove(best_.getMembers().get(i));
		}
	}
	
	/**
	 * Only implemented for groups of size 4.
	 * @precondition strongestGroups_ contains only groups of size groupSize_
	 * @precondition pool_.size() < groupSize_
	 * @precondition groupSize_ == 4
	 * @param remaining
	 */
	public void resizeAndInsertRemaining(ArrayList<Unit> remaining)
	{
		GroupSet finalSet = new GroupSet();
		int hiSetStrength = Integer.MIN_VALUE;
		if(remaining.size() == 3)
		{
			
		}
		else if(remaining.size() == 2)
		{
			for(Group generatedGrp: strongestGroups_.groupSet_)
			{
				ArrayList<Unit> tempPool = new ArrayList<Unit>();
				putIntoArrayList(remaining, tempPool);
				putIntoArrayList(generatedGrp, tempPool);
				DecreasingUnitPool tempAlgo = new DecreasingUnitPool(tempPool, 3);
				GroupSet tempSet = tempAlgo.findStrongestGroups();
				int avgStrength = tempSet.getAverageStrengthPerGroup();
				if(avgStrength > hiSetStrength)
					finalSet = tempSet;
			}
			
			ArrayList<Group> toRemove = new ArrayList<Group>();	
			for(Group gStrongestGroups: strongestGroups_.groupSet_)
			{
				for(Group gFinalSet: finalSet.groupSet_)
				{
					if(gStrongestGroups.containsMembersOf(gFinalSet))
					{
						if(!toRemove.contains(gStrongestGroups))
							toRemove.add(gStrongestGroups);
					}
				}
			}
			
			for(Group g: toRemove)
			{
				strongestGroups_.remove(g);
			}
			
			for(Group g: finalSet.groupSet_)
			{
				strongestGroups_.add(g);
			}
			pool_ = new ArrayList<Unit>(); // TODO: make sure that the remaining people that are not included in remaining are not deleted
		}
		else if(remaining.size() == 1)
		{
			for(int i = 0; i < strongestGroups_.size(); i++)
			{
				for(int j = 0; j < strongestGroups_.size(); j++)
				{
					if(i == j)
						continue;
					
					ArrayList<Unit> tempPool = new ArrayList<Unit>();
					putIntoArrayList(remaining, tempPool);
					putIntoArrayList(strongestGroups_.get(i), tempPool);
					putIntoArrayList(strongestGroups_.get(j), tempPool);
					DecreasingUnitPool tempAlgo = new DecreasingUnitPool(tempPool, 3);
					GroupSet tempSet = tempAlgo.findStrongestGroups();
					int avgStrength = tempSet.getAverageStrengthPerGroup();
					if(avgStrength > hiSetStrength)
						finalSet = tempSet;
				}
			}
			
			ArrayList<Group> toRemove = new ArrayList<Group>();	
			for(Group gStrongestGroups: strongestGroups_.groupSet_)
			{
				for(Group gFinalSet: finalSet.groupSet_)
				{
					if(gStrongestGroups.containsMembersOf(gFinalSet))
					{
						if(!toRemove.contains(gStrongestGroups))
							toRemove.add(gStrongestGroups);
					}
				}
			}
			
			for(Group g: toRemove)
			{
				strongestGroups_.remove(g);
			}
			
			for(Group g: finalSet.groupSet_)
			{
				strongestGroups_.add(g);
			}
			pool_ = new ArrayList<Unit>(); // TODO: make sure that the remaining people that are not included in remaining are not deleted
		}
	}
	
	/**
	 * This function takes the remaining units from the pool that were not assigned into groups and assigns
	 * each one into the group that would increase in happiness the most. 
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
	
	/**
	 * This function finds the most optimal assortment of groups based on strength.
	 * @return strongestGroups_ - the optimal set of groups found by decreasing unit pool. 
	 */
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
						break;
					}
					strongestGroups_.add(best_);
					removeBestGroupFromPool();
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
						resizeAndInsertRemaining(pool_);
						break;
					}
					strongestGroups_.add(best_);
					removeBestGroupFromPool();
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
						break;
					}
					strongestGroups_.add(best_);
					removeBestGroupFromPool();
				}
				break;
		}
		strongestGroups_.alphabetize();
		return strongestGroups_;
	}
	
	public static void putIntoArrayList(Group group, ArrayList<Unit> destination)
	{
		for(Unit u: group.members_)
		{
			if(!destination.contains(u))
				destination.add(u);
		}
	}
	
	public static void putIntoArrayList(ArrayList<Unit> list, ArrayList<Unit> destination)
	{
		for(Unit u: list)
		{
			if(!destination.contains(u))
				destination.add(u);
		}
	}
	
	class inadequatePoolSizeException extends Exception
	{
		private static final long serialVersionUID = -8560120916174900757L;
	}
}
