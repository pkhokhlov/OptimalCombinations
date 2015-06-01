/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;

import java.util.ArrayList;

/**
 * z
 *
 * @author jackprescott
 * @author Pavel Khokhlov
 */
public class OptimalCombinations
{

	public static void main(String[] args)
	{
		// TODO code application logic here
		@SuppressWarnings("unused")
		CreateMasterList mList = new CreateMasterList();
		CreateMasterList mList2 = new CreateMasterList();
		
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
		
		
		
		
		
		mList2.add(new Unit[]{ A, B, C, D, E, F, G, H, I});
		DecreasingUnitPoolMethod test = new DecreasingUnitPoolMethod(mList2.getMasterList());
		ArrayList<Group> happiest = test.findHappiestGroups();
		System.out.println(happiest.toString());
		/* OUTPUT
		 *
		 * [{A, B, C}, {D, E, F}, {G, H, I}]
		 */
		
		
		
		
		/*mList.add(new Unit[]
		{ A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W,
				X });*/
		/*
		 * A.setPC(new Unit[]{B,D,C}); A.setNC(E);
		 */
		// CombinationTester ct= new CombinationTester(mList.masterList_);
		// System.out.println(ct.getBestCombo());

	}

}
