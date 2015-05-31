/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;
import java.util.ArrayList;

/**
 *
 * @author jackprescott
 */
public class Group {
    
    ArrayList<Unit> groupUnits= new ArrayList<Unit>();
    public static int groupSize;
    
    Group(){
        
    }
    
    Group(Unit A, Unit B, Unit C){
        
        groupUnits.add(A);
        groupUnits.add(B);
        groupUnits.add(C);

    }
    
    int getGroupScore(){
        
        int groupScore=0;
        int y;
        int x;
        
        for(y=0; y<groupUnits.size(); y++){
        
            for(x=0; x<groupUnits.get(0).pcSize; x++){
            
                int tempPoints=groupUnits.get(y).positiveConnections.indexOf(groupUnits.get(x));
                
                if (tempPoints!=-1){
                    
                    groupScore+=groupUnits.get(0).pcSize-tempPoints;
                    
                }
                
                if(groupUnits.get(y).negativeConnection==groupUnits.get(x)){
                    
                    groupScore-=3;
                    
                }
            
            }
            
        }
        
        return groupScore;
        
    }
    
    void setGroupSize(int i){
        groupSize=i;
    }
    
    void addUnit(Unit u){
        groupUnits.add(u);
    }
    
}
