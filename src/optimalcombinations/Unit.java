/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;

import java.util.ArrayList;

/**
 * @author jackprescott
 * @author Pavel Khokhlov
 */
public class Unit
{
	String name_;
	int posConnSize_;
	
	Unit negConn_; // TODO: include an arraylist of negative connections
	ArrayList<Unit> posConns_ = new ArrayList<Unit>();
	
	public Unit(String name)
	{
		name_ = name;
		posConnSize_ = posConns_.size();
	}
	
	public Unit(String name, Unit u1, Unit u2, Unit u3)
	{
		name_ = name;
		setPosConns(u1, u2, u3);
	}
	
	public Unit(String name, Unit[] posConn)
	{
		name_ = name;
		setPosConns(posConn);
	}
	
	public void setPosConns(Unit u1, Unit u2, Unit u3)
	{
		posConns_ = new ArrayList<Unit>();
		posConns_.add(u1);
		posConns_.add(u2);
		posConns_.add(u3);
		posConnSize_ = posConns_.size();
	}
	
	public void setPosConns(Unit[] posConn)
	{
		posConns_ = new ArrayList<Unit>();
		for (Unit unit : posConn)
		{
			posConns_.add(unit);
		}
		posConnSize_ = posConns_.size();
	}

	public void setNegConn(Unit negConn)
	{
		negConn_ = negConn;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public ArrayList<Unit> getPosConns()
	{
		return posConns_;
	}
	
	public int getPosConnSize()
	{
		return posConnSize_;
	}
	
	public Unit getNegConn()
	{
		return negConn_;
	}
	
	public String toString()
	{
		return getName();
	}
}
