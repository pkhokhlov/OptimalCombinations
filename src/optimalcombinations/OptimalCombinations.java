/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;

/**z
 *
 * @author jackprescott
 */
public class OptimalCombinations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CreateMasterList mList=new CreateMasterList();
       
        Unit A=new Unit("A");
        Unit B=new Unit("B");
        Unit C=new Unit("C");
        Unit D=new Unit("D");
        Unit E=new Unit("E");
        Unit F=new Unit("F");
        Unit G=new Unit("G");
        Unit H=new Unit("H");
        Unit I=new Unit("I");
        Unit J=new Unit("J");
        Unit K=new Unit("K");
        Unit L=new Unit("L");
        Unit M=new Unit("M");
        Unit N=new Unit("N");
        Unit O=new Unit("O");
        Unit P=new Unit("P");
        Unit Q=new Unit("Q");
        Unit R=new Unit("R");
        Unit S=new Unit("S");
        Unit T=new Unit("T");
        Unit U=new Unit("U");
        Unit V=new Unit("V");
        Unit W=new Unit("W");
        Unit X=new Unit("X");
        
        mList.add(new Unit[]{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X});
        
        /*A.setPC(new Unit[]{B,D,C});
        A.setNC(E);*/

       
        CombinationTester ct= new CombinationTester(mList.masterList);
        System.out.println(ct.getBestCombo());
        
        
    }
    
}
