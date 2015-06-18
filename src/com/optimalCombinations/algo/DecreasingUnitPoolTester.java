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
import java.util.Arrays;
import java.util.Collections;

public class DecreasingUnitPoolTester
{
	@SuppressWarnings("unused")
	public static void main(String [] args)
	{
		Unit A = new Unit("A");
		Unit B = new Unit("B");
		Unit C = new Unit("C");
		Unit D = new Unit("D");
		Unit E = new Unit("E");
		Unit F = new Unit("F");
		Unit G = new Unit("G");
		Unit H = new Unit("H");
		Unit I = new Unit("I"); // 9
		Unit J = new Unit("J");
		Unit K = new Unit("K");
		Unit L = new Unit("L");
		Unit M = new Unit("M");
		Unit N = new Unit("N");
		Unit O = new Unit("O");
		Unit P = new Unit("P"); // 16
		Unit Q = new Unit("Q");
		Unit R = new Unit("R");
		Unit S = new Unit("S");
		Unit T = new Unit("T");
		Unit U = new Unit("U");
		Unit V = new Unit("V");
		Unit W = new Unit("W");
		Unit X = new Unit("X");
		
		A.setPosConns(B, C, D);
		A.setNegConn(E);
		B.setPosConns(A, C, D);
		B.setNegConn(E);
		C.setPosConns(B, A, D);
		C.setNegConn(E);
		D.setPosConns(E, F, G);
		D.setNegConn(I);
		E.setPosConns(D, F, G);
		E.setNegConn(I);
		F.setPosConns(E, D, G);
		F.setNegConn(I);
		G.setPosConns(H, I, D);
		G.setNegConn(A);
		H.setPosConns(G, I, D);
		H.setNegConn(A);
		I.setPosConns(H, G, D);
		I.setNegConn(A);
		J.setPosConns(G, H, I);
		J.setNegConn(D);

		Unit[] arrayPool = new Unit[]{ A, B, C, D, E, F, G, H, I, J};
		for(int i = 0; i < 10; i++)
		{
			ArrayList<Unit> pool = new ArrayList<Unit>(Arrays.asList(arrayPool));
			Collections.shuffle(pool);
			DecreasingUnitPool test = new DecreasingUnitPool(pool, 3);
			GroupSet strongest = test.findStrongestGroups();
			System.out.println("Groups of size 3: " + strongest.toString());
		}
		System.out.println();
		
		for(int i = 0; i < 10; i++)
		{
			ArrayList<Unit> pool = new ArrayList<Unit>(Arrays.asList(arrayPool));
			Collections.shuffle(pool);
			DecreasingUnitPool test = new DecreasingUnitPool(pool, 4);
			GroupSet strongest = test.findStrongestGroups();
			System.out.println("Groups of size 4: " + strongest.toString());
		}
		System.out.println();
		
		for(int i = 0; i < 10; i++)
		{
			ArrayList<Unit> pool = new ArrayList<Unit>(Arrays.asList(arrayPool));
			Collections.shuffle(pool);
			DecreasingUnitPool test = new DecreasingUnitPool(pool, 5);
			GroupSet strongest = test.findStrongestGroups();
			System.out.println("Groups of size 5: " + strongest.toString());
		}
	}
}
