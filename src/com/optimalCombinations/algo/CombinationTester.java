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
import java.math.BigInteger;

public class CombinationTester
{

	ArrayList<Unit> masterList_;
	ArrayList<Unit> masterListCopy_;
	ArrayList<ArrayList<Group>> listAllCombos_;
	ArrayList<BigInteger> listOfNCR_ = new ArrayList<BigInteger>();

	int max_;
	int count_;
	int groupSize_;
	int numberOfGroups_;

	int[] depthRepetition_;

	Factorial f_ = new Factorial();

	@SuppressWarnings("static-access")
	public CombinationTester(ArrayList<Unit> m)
	{

		masterList_ = m;
		groupSize_ = m.get(0).posConnSize_;

		numberOfGroups_ = m.size() / groupSize_;

		for (int i = 0; i < numberOfGroups_; i++)
		{
			listOfNCR_.add(f_.factorial(m.size() - i * groupSize_).divide(
					(f_.factorial(m.get(0).posConnSize_).multiply((f_.factorial((m
							.size() - i * groupSize_ - m.get(0).posConnSize_)))))));
		}

		depthRepetition_ = new int[numberOfGroups_];

		for (int x = 0; x < numberOfGroups_; x++)
		{
			depthRepetition_[x] = 0;
		}

	}

	public ArrayList<ArrayList<Group>> getBestCombo()
	{

		tryCombos(masterList_, 0);
		return listAllCombos_;

	}

	public void tryCombos(ArrayList<Unit> MLC, int depth)
	{
		for (int x = 0; x < MLC.size() - 2; x++)
		{
			for (int y = x + 1; y < MLC.size() - 1; y++)
			{
				for (int z = x + 2; z < MLC.size(); z++)
				{
					if (depth < 6)
					{
						System.out.println(listOfNCR_.get(2));
						System.out.println();
					}

					if (depth == numberOfGroups_ - 1)
					{
						return;
					}
					else
					{
						tryCombos(MLC, depth + 1);
					}
				}
			}
		}
	}
}

/*
 * Group g = new Group(MLC.get(x), MLC.get(y), MLC.get(z));
 * System.out.println(g.groupUnits.get(0).name);
 * System.out.println(g.groupUnits.get(1).name);
 * System.out.println(g.groupUnits.get(2).name); System.out.println();
 * BigInteger b = BigInteger.valueOf(depthRepetition[depth]);
 * 
 * 
 * for(BigInteger i=listOfNCR.get(depth).multiply(b);
 * i.compareTo(listOfNCR.get(depth).multiply(b))==-1;
 * i.add(BigInteger.valueOf(1))){ listAllCombos.get(x).add(g);
 * System.out.println("Added g to listAllCombos"); } MLC.remove(z);
 * MLC.remove(y); MLC.remove(x);
 */