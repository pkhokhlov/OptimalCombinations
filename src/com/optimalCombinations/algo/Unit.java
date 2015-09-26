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

import java.io.Serializable;
import java.util.ArrayList;

public class Unit implements Serializable
{
	private static final long serialVersionUID = 2347103416593796621L;
	
	String name_;
	int posConnSize_;
	
	Unit negConn_; /* Ribaudo noted that he does not ask for negative connections. */
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
	
	public void setPosConns(Unit u1, Unit u2, Unit u3, Unit u4, Unit u5)
	{
		posConns_ = new ArrayList<Unit>();
		posConns_.add(u1);
		posConns_.add(u2);
		posConns_.add(u3);
		posConns_.add(u4);
		posConns_.add(u5);
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
