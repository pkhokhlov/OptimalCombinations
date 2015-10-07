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

import com.optimalCombinations.algo.DecreasingUnitPool.inadequatePoolSizeException;

public class StaticDecreasingUnitPool
{
	/**
	 * include more parameters for different algos
	 * @param pool
	 * @param groupSize
	 * @return
	 */
	public static GroupSet generateRooms(ArrayList<Unit> pool, int groupSize)
	{
		GroupSet strongestGroups;
		
		
	}
	
	/**
	 * returns the strongest groups with the remainders as their separate group
	 * @param pool
	 * @return
	 */
	public static GroupSet generateRoomsSize4WithRemainder(ArrayList<Unit> pool)
	{
		GroupSet strongestGroups = new GroupSet();
		while(pool.size() >= 4)
		{
			Group best = findBestGroupSize4(pool);
			strongestGroups.add(best);
			removeGroupFromList(pool, best);
		}
		strongestGroups.add(new Group(pool));
		return strongestGroups;
	}
	
	public static GroupSet generateRoomsSize4NoRemainder(ArrayList<Unit> pool)
	{
		
	}
	
	public static GroupSet generateRoomsSize3(ArrayList<Unit> pool)
	{
		GroupSet strongestGroups = new GroupSet();
		while(pool.size() >= 3)
		{
			Group best = findBestGroupSize3(pool);
			strongestGroups.add(best);
			removeGroupFromList(pool, best);
		}
		return strongestGroups;
	}
	
	public static Group findBestGroupSize3(ArrayList<Unit> pool)
	{
		Group best = new Group();
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool.size() - 2; a++)
		{
			loopB:
			for(int b = a + 1; b < pool.size() - 1; b++)
			{
				loopC:
				for(int c = b + 1; c < pool.size(); c++)
				{
					Group grpTemp = new Group(new Unit[]{pool.get(a), pool.get(b), pool.get(c)});
					int negConnIndex = grpTemp.getIndexNegConn();
					if(negConnIndex != -1)
					{
						switch (negConnIndex)
						{
							case 0: continue loopA;
							case 1: continue loopB;
							case 2: continue loopC;
						}
					}
					int gScore = grpTemp.getGroupScore();
					if(gScore > highestScore)
					{
						highestScore = gScore;
						best = grpTemp;
					}
				}
			}
		}
		return best;
	}
	
	public static Group findBestGroupSize4(ArrayList<Unit> pool)
	{
		Group best = new Group();
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool.size() - 3; a++)
		{
			loopB:
			for(int b = a + 1; b < pool.size() - 2; b++)
			{
				loopC:
				for(int c = b + 1; c < pool.size() - 1; c++)
				{
					loopD:
					for(int d = c + 1; d < pool.size(); d++)
					{
						Group grpTemp = new Group(new Unit[]{pool.get(a), pool.get(b), pool.get(c), pool.get(d)});
						int negConnIndex = grpTemp.getIndexNegConn();
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
						int gScore = grpTemp.getGroupScore();
						if(gScore > highestScore)
						{
							highestScore = gScore;
							best = grpTemp;
						}
					}
				}
			}
		}
		return best;
	}
	
	public static Group findBestGroupSize5(ArrayList<Unit> pool)
	{
		Group best = new Group();
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool.size() - 4; a++)
		{
			loopB:
			for(int b = a + 1; b < pool.size() - 3; b++)
			{
				loopC:
				for(int c = b + 1; c < pool.size() - 2; c++)
				{
					loopD:
					for(int d = c + 1; d < pool.size() - 1; d++)
					{
						loopE:
						for(int e = d + 1; e < pool.size(); e++)
						{
							Group grpTemp = new Group(new Unit[]{pool.get(a), pool.get(b), pool.get(c), pool.get(d), pool.get(e)});
							int negConnIndex = grpTemp.getIndexNegConn();
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
							int gScore = grpTemp.getGroupScore();
							if(gScore > highestScore)
							{
								highestScore = gScore;
								best = grpTemp;
							}
						}
					}
				}
			}
		}
		return best;
	}
	
	public static void resizeAndInsertRemainders(GroupSet grpSet, ArrayList<Unit> remaining)
	{
		GroupSet finalSet = new GroupSet();
		int hiSetStrength = Integer.MIN_VALUE;
		if(remaining.size() == 2)
		{
			// can make this next block a function and elminate any conditions on remainder's size
			for(Group existingGrp: grpSet.groupSet_)
			{
				ArrayList<Unit> tempPool = new ArrayList<Unit>();
				DecreasingUnitPool.putIntoArrayList(remaining, tempPool);
				DecreasingUnitPool.putIntoArrayList(existingGrp, tempPool);
				GroupSet tempSet = generateRoomsSize3(tempPool);
				int avgStrength = tempSet.getAverageStrengthPerGroup();
				if(avgStrength > hiSetStrength)
					finalSet = tempSet;
			}
			
			ArrayList<Group> toRemove = new ArrayList<Group>();	
			for(Group existingGrp: grpSet.groupSet_)
			{
				for(Group finalizedGrp: finalSet.groupSet_)
				{
					if(existingGrp.containsMembersOf(finalizedGrp))
					{
						if(!toRemove.contains(existingGrp))
							toRemove.add(existingGrp);
					}
				}
			}
			
			for(Group removedGrp: toRemove)
			{
				grpSet.remove(removedGrp);
			}
			
			for(Group finalGrp: finalSet.groupSet_)
			{
				grpSet.add(finalGrp);
			}
		}
	}
	
	public static void removeGroupFromList(ArrayList<Unit> list, Group grp)
	{
		for(Unit u: grp.getMembers())
		{
			list.remove(u);
		}
	}
	
}
