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

public class RandomizedDecreasingUnitPool
{
	ArrayList<Unit> pool_;
	int groupSize_;
	ArrayList<GroupSet> randomizedSets_ = new ArrayList<GroupSet>();
	ArrayList<Group> allGroups_ = new ArrayList<Group>();
	
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
						allGroups_.add(new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c)}));
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
							allGroups_.add(new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d)}));
							
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
								allGroups_.add(new Group(new Unit[]{pool_.get(a), pool_.get(b), pool_.get(c), pool_.get(d), pool_.get(e)}));
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
		for(Group g : allGroups_)
		{
			System.out.println(pool_);
			poolCopy = new ArrayList<Unit>();
			System.out.println("Poolcopy before change: " + poolCopy.toString());
			for(Unit u : pool_)
			{
				poolCopy.add(u);
			}
			System.out.println(poolCopy.toString());
			for(Unit u : g.getMembers())
			{
				System.out.println(u);
				poolCopy.remove(u);
			}
			System.out.println("After removal: " + poolCopy.toString());
			DecreasingUnitPool DUP = new DecreasingUnitPool(poolCopy, groupSize_);
			GroupSet temp = DUP.findStrongestGroups();
			temp.add(g);
			randomizedSets_.add(temp);
		}
	}
	
	GroupSet findStrongestGroupSet(int minPerUnit, int minPerGroup){
		GenerateGroupSets();
		int averageStrengthBest=Integer.MIN_VALUE;
		GroupSet bestSet= new GroupSet();
		for (GroupSet s: randomizedSets_ )
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