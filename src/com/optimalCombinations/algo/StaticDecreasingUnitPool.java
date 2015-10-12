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

public class StaticDecreasingUnitPool
{	
	public static GroupSet generateRoomsFromSequence(ArrayList<Unit> pool, ArrayList<Integer> sequence)
	{
		GroupSet strongestGroups = new GroupSet();
		for(int i: sequence)
		{
			Group best = findBestGroup(i, pool);
			strongestGroups.add(best);
			removeGroupFromList(pool, best);
		}
		return strongestGroups;
	}
	
	public static Group findBestGroup(int size, ArrayList<Unit> pool)
	{
		switch(size)
		{
			case 2: return findBestGroupSize2(pool);
			case 3: return findBestGroupSize3(pool);
			case 4: return findBestGroupSize4(pool);
			case 5: return findBestGroupSize5(pool);
			case 6: return findBestGroupSize6(pool);
			case 7: return findBestGroupSize7(pool);
		}	
		return null;
	}
	
	public static Group findBestGroupSize2(ArrayList<Unit> pool)
	{
		Group best = new Group();
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool.size() - 1; a++)
		{
			loopB:
			for(int b = a + 1; b < pool.size(); b++)
			{
				Group grpTemp = new Group(new Unit[]{pool.get(a), pool.get(b)});
				int negConnIndex = grpTemp.getIndexNegConn();
				if(negConnIndex != -1)
				{
					switch (negConnIndex)
					{
						case 0: continue loopA;
						case 1: continue loopB;
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
		return best;
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
	
	public static Group findBestGroupSize6(ArrayList<Unit> pool)
	{
		Group best = new Group();
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool.size() - 5; a++)
		{
			loopB:
			for(int b = a + 1; b < pool.size() - 4; b++)
			{
				loopC:
				for(int c = b + 1; c < pool.size() - 3; c++)
				{
					loopD:
					for(int d = c + 1; d < pool.size() - 2; d++)
					{
						loopE:
						for(int e = d + 1; e < pool.size() - 1; e++)
						{
							loopF:
							for(int f = e + 1; f < pool.size(); f++)
							{
								Group grpTemp = new Group(new Unit[]{pool.get(a), pool.get(b), pool.get(c), pool.get(d), pool.get(e), pool.get(f)});
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
										case 5: continue loopF;
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
		}
		return best;
	}
	
	public static Group findBestGroupSize7(ArrayList<Unit> pool)
	{
		Group best = new Group();
		int highestScore = Integer.MIN_VALUE;
		loopA:
		for(int a = 0; a < pool.size() - 6; a++)
		{
			loopB:
			for(int b = a + 1; b < pool.size() - 5; b++)
			{
				loopC:
				for(int c = b + 1; c < pool.size() - 4; c++)
				{
					loopD:
					for(int d = c + 1; d < pool.size() - 3; d++)
					{
						loopE:
						for(int e = d + 1; e < pool.size() - 2; e++)
						{
							loopF:
							for(int f = e + 1; f < pool.size() - 1; f++)
							{
								loopG:
								for(int g = f + 1; g < pool.size(); g++)
								{
									Group grpTemp = new Group(new Unit[]
											{pool.get(a), pool.get(b), pool.get(c), pool.get(d), pool.get(e), pool.get(f), pool.get(g)});
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
											case 5: continue loopF;
											case 6: continue loopG;
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
			}
		}
		return best;
	}
	
	public static void removeGroupFromList(ArrayList<Unit> list, Group grp)
	{
		for(Unit u: grp.getMembers())
		{
			list.remove(u);
		}
	}
	
}
