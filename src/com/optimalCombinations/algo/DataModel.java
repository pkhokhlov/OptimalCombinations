package com.optimalCombinations.algo;

import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class DataModel implements Serializable
{
	private static final long serialVersionUID = 4838742432795524455L;
	
	public DefaultComboBoxModel<Unit> allStudents_ = new DefaultComboBoxModel<Unit>();
	public DefaultListModel<Unit> uneditedStudents_ = new DefaultListModel<Unit>();
	public DefaultListModel<Unit> editedStudents_ = new DefaultListModel<Unit>();
}
