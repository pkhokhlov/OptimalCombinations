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

public class GroupSet
{
	public ArrayList<Group> groupSet_;
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
		int totalStrength = 0;
		minimumStrengthPerGroup_ = Integer.MAX_VALUE;
		minimumStrengthPerUnit_ = Integer.MAX_VALUE;

		for(Group g : groupSet_)
		{
			totalStrength += g.getGroupScore();
			
			if(minimumStrengthPerGroup_ > g.getGroupScore())
			{
				minimumStrengthPerGroup_ = g.getGroupScore();
			}
			
			if(minimumStrengthPerUnit_ > g.getMinimumUnitStrength())
			{
				minimumStrengthPerUnit_ = g.getMinimumUnitStrength();
			}
			
		}
		numberOfGroups_ = size();
		averageStrengthPerGroup_ = totalStrength / numberOfGroups_;
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
	
	public void alphabetize()
	{
		for(Group g : groupSet_)
		{
			g.alphabetize();
		}
		
		Collections.sort(groupSet_, new Comparator<Group>() 
		{
	        @Override
	        public int compare(final Group g1, final Group g2) 
	        {
	            return g1.getMembers().get(0).getName().compareTo(g2.getMembers().get(0).getName());
	        }
		});
	}
	
	public String getGroupScores()
	{
		String s = "{";
		for(int i = 0; i < groupSet_.size() - 1; i++)
		{
			s = s + groupSet_.get(i).getGroupScore() + ", ";
		}
		s = s + groupSet_.get(groupSet_.size() - 1).getGroupScore() + "}";
		return s;
	}
}
