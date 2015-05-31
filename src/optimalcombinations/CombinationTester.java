/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;
import java.util.ArrayList;
import java.math.BigInteger;
  
/**
 *
 * @author jackprescott
 */
public class CombinationTester {
    
    ArrayList<Unit> masterList;
    ArrayList<Unit> masterListCopy;
    ArrayList<ArrayList<Group>> listAllCombos;
    ArrayList<BigInteger> listOfNCR = new ArrayList<BigInteger>();

    int max;
    int count;
    int groupSize;
    int numberOfGroups;
    
    int[] depthRepetition;
    
    Factorial f=new Factorial();
    
    
    
    
    CombinationTester(ArrayList<Unit> m){
        
        masterList = m;
        groupSize = m.get(0).pcSize;
        
        numberOfGroups = m.size()/groupSize;
        
        for(int i=0; i<numberOfGroups; i++){
            listOfNCR.add( f.factorial(m.size()-i*groupSize).divide(   (f.factorial(m.get(0).pcSize).multiply(   (  f.factorial(  (m.size()-i*groupSize-m.get(0).pcSize)  )  )   )  )   )  );
        }
        
        depthRepetition = new int[numberOfGroups];
        
        for(int x = 0; x<numberOfGroups; x++){
            depthRepetition[x]=0;
        }

    }
    
    
    
    
    ArrayList<ArrayList<Group>> getBestCombo(){
        
        tryCombos(masterList, 0);
        return listAllCombos;
        
    }

    
    
    
    void tryCombos(ArrayList<Unit> MLC, int depth){
        
        
        for(int x=0; x<MLC.size()-2; x++){
            
            for(int y=x+1; y<MLC.size()-1; y++){
                
                for(int z=x+2; z<MLC.size(); z++){
                    if(depth<6){
                    System.out.println(listOfNCR.get(2));
                    System.out.println();
                    }
                    
                    if(depth==numberOfGroups-1){
                        
                        return;
                        
                    }
                    
                    else{
                        
                        tryCombos(MLC, depth+1);
                    }
                
                }
            
            }
        
        }
    
    }

}
    
    /*Group g = new Group(MLC.get(x), MLC.get(y), MLC.get(z));
                    System.out.println(g.groupUnits.get(0).name);
                    System.out.println(g.groupUnits.get(1).name);
                    System.out.println(g.groupUnits.get(2).name);
                    System.out.println();
                    BigInteger b = BigInteger.valueOf(depthRepetition[depth]);
                    
                    
                    for(BigInteger i=listOfNCR.get(depth).multiply(b); i.compareTo(listOfNCR.get(depth).multiply(b))==-1; i.add(BigInteger.valueOf(1))){
                        listAllCombos.get(x).add(g);
                        System.out.println("Added g to listAllCombos");
                    }
                    MLC.remove(z);
                    MLC.remove(y);
                    MLC.remove(x);
*/