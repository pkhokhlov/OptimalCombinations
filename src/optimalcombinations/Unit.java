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
public class Unit {
    int pcSize=3;
    ArrayList<Unit> positiveConnections =  new ArrayList<Unit>();
    Unit negativeConnection;
    String name;
    Unit(String n){
        name=n;
    }
    void setPC(Unit[] u) {
        for(Unit unit:u){
            positiveConnections.add(unit);
        }
    }
    
    void setNC(Unit u) {
        negativeConnection=u;
    }
    
}
