package optimalcombinations;

public class DecreasingUnitPoolTester
{
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
		J.setNegConn(I);
		
		
		CreateMasterList mList1 = new CreateMasterList();
		CreateMasterList mList2 = new CreateMasterList();
		CreateMasterList mList3 = new CreateMasterList();
		
		mList1.add(new Unit[]{ A, B, C, D, E, F, G, H, I, J});
		mList2.add(new Unit[]{ A, B, C, D, E, F, G, H, I, J});
		mList3.add(new Unit[]{ A, B, C, D, E, F, G, H, I, J});
		
		DecreasingUnitPool test1 = new DecreasingUnitPool(mList1.getMasterList(), 3);
		DecreasingUnitPool test2 = new DecreasingUnitPool(mList2.getMasterList(), 4);
		DecreasingUnitPool test3 = new DecreasingUnitPool(mList3.getMasterList(), 5);

		GroupSet strongest1 = test1.findStrongestGroups();
		System.out.println("Groups of size 3: " + strongest1.toString() + " scores: " + strongest1.getGroupScoresWithNull());

		GroupSet strongest2 = test2.findStrongestGroups();
		System.out.println("Groups of size 4: " + strongest2.toString() + " scores: " + strongest2.getGroupScoresWithNull());
		
		GroupSet strongest3 = test3.findStrongestGroups();
		System.out.println("Groups of size 5: " + strongest3.toString() + " scores: " + strongest3.getGroupScoresWithNull());
		
		Group g1 = new Group(new Unit[]{G, H, I, J});
		System.out.println("G1 With Null: "  + g1.getGroupScoreWithNullPrint());
		/*
		Group g2 = new Group(new Unit[]{A, B, C, D, E});
		System.out.println("G2 With Null: "  + g2.getGroupScoreWithNullPrint());
		*/
	}
}
