/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;

import java.util.ArrayList;

/**
 * @author jackprescott
 */
public class CreateMasterList
{
	ArrayList<Unit> masterList_ = new ArrayList<Unit>();

	public void add(Unit[] u)
	{
		for (Unit unit : u)
		{
			masterList_.add(unit);
		}
	}
	
	public ArrayList<Unit> getMasterList()
	{
		return masterList_;
	}

}
