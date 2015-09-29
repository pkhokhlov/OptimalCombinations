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

package com.optimalCombinations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.optimalCombinations.algo.Unit;

public class PreferenceSelection extends JDialog
{
	private static final long serialVersionUID = -665010428935262459L;

	public PreferenceSelection(final Unit u, final DefaultListModel<Unit> uneditedStudents, 
		                           final DefaultListModel<Unit> editedStudents, 
		                           DefaultComboBoxModel<Unit> allStudents)
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		// creates all the lists to be used in the combobox
		DefaultComboBoxModel<Unit> desiredComboBox1 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> desiredComboBox2 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> desiredComboBox3 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> desiredComboBox4 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> desiredComboBox5 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> undesiredComboBox1 = MainMenu.copyComboBoxModel(allStudents);
		
		final JComboBox<Unit> desiredBox1 = new JComboBox<Unit>(desiredComboBox1);
		desiredBox1.setBounds(212, 36, 163, 22);
		getContentPane().add(desiredBox1);
		
		final JComboBox<Unit> desiredBox2 = new JComboBox<Unit>(desiredComboBox2);
		desiredBox2.setBounds(212, 66, 163, 22);
		getContentPane().add(desiredBox2);
		
		final JComboBox<Unit> desiredBox3 = new JComboBox<Unit>(desiredComboBox3);
		desiredBox3.setBounds(212, 96, 163, 22);
		getContentPane().add(desiredBox3);
		
		final JComboBox<Unit> desiredBox4 = new JComboBox<Unit>(desiredComboBox4);
		desiredBox4.setBounds(212, 126, 163, 22);
		getContentPane().add(desiredBox4);
		
		final JComboBox<Unit> desiredBox5 = new JComboBox<Unit>(desiredComboBox5);
		desiredBox5.setBounds(212, 156, 163, 22);
		getContentPane().add(desiredBox5);
		
		final JComboBox<Unit> undesiredBox1 = new JComboBox<Unit>(undesiredComboBox1);
		undesiredBox1.setBounds(212, 202, 163, 22);
		getContentPane().add(undesiredBox1);
		// if the preferences are already set, it displays the set preferences
		if(u.getPosConns().size() != 0)
		{
			desiredBox1.setSelectedIndex(desiredComboBox1.getIndexOf(u.getPosConns().get(0)));
			desiredBox2.setSelectedIndex(desiredComboBox2.getIndexOf(u.getPosConns().get(1)));
			desiredBox3.setSelectedIndex(desiredComboBox3.getIndexOf(u.getPosConns().get(2)));
			desiredBox4.setSelectedIndex(desiredComboBox4.getIndexOf(u.getPosConns().get(3)));
			desiredBox5.setSelectedIndex(desiredComboBox5.getIndexOf(u.getPosConns().get(4)));
			undesiredBox1.setSelectedIndex(desiredComboBox1.getIndexOf(u.getNegConn()));
		}
		
		desiredComboBox1.removeElement(u);
		desiredComboBox2.removeElement(u);
		desiredComboBox3.removeElement(u);
		desiredComboBox4.removeElement(u);
		desiredComboBox5.removeElement(u);
		undesiredComboBox1.removeElement(u);
		
		JLabel lblStudentsPreferences = new JLabel(u + "'s Preferences");
		lblStudentsPreferences.setBounds(155, 11, 113, 14);
		getContentPane().add(lblStudentsPreferences);
		
		JLabel lblDesiredGroupMember_1 = new JLabel("Desired Group Member 1:");
		lblDesiredGroupMember_1.setBounds(47, 36, 145, 22);
		getContentPane().add(lblDesiredGroupMember_1);
		
		JLabel lblDesiredGroupMember_2 = new JLabel("Desired Group Member 2:");
		lblDesiredGroupMember_2.setBounds(47, 66, 145, 22);
		getContentPane().add(lblDesiredGroupMember_2);
		
		JLabel lblDesiredGroupMember_3 = new JLabel("Desired Group Member 3:");
		lblDesiredGroupMember_3.setBounds(47, 96, 145, 22);
		getContentPane().add(lblDesiredGroupMember_3);
		
		JLabel lblDesiredGroupMember_4 = new JLabel("Desired Group Member 4:");
		lblDesiredGroupMember_4.setBounds(47, 126, 145, 22);
		getContentPane().add(lblDesiredGroupMember_4);
		
		JLabel lblDesiredGroupMember_5 = new JLabel("Desired Group Member 5:");
		lblDesiredGroupMember_5.setBounds(47, 156, 145, 22);
		getContentPane().add(lblDesiredGroupMember_5);
		
		JLabel lblUndesiredGroupMember = new JLabel("Restricted Group Member:");
		lblUndesiredGroupMember.setVerticalAlignment(SwingConstants.TOP);
		lblUndesiredGroupMember.setBounds(47, 206, 182, 22);
		getContentPane().add(lblUndesiredGroupMember);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 239, 91, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
			
		});
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(341, 239, 91, 23);
		getContentPane().add(btnOK);
		btnOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Unit desired1 = desiredBox1.getItemAt(desiredBox1.getSelectedIndex());
				Unit desired2 = desiredBox2.getItemAt(desiredBox2.getSelectedIndex());
				Unit desired3 = desiredBox3.getItemAt(desiredBox3.getSelectedIndex());
				Unit desired4 = desiredBox4.getItemAt(desiredBox4.getSelectedIndex());
				Unit desired5 = desiredBox5.getItemAt(desiredBox5.getSelectedIndex());
				Unit undesired1 = undesiredBox1.getItemAt(undesiredBox1.getSelectedIndex());	
				
				// look for duplicates of non-nulls
				if(choicesValid(desired1, desired2, desired3, desired4, desired5, undesired1))
				{
					JOptionPane.showMessageDialog(new JFrame(),
							"Error: you have duplicate choices selected.");
				}
				else // there are no duplicates unless they are null
				{   
					if(desired1 == null || desired2 == null || desired3 == null || desired4 == null || desired5 == null)
					{
						int n = showEmptyPrefsDialog();
						if(n != 1) // if "yes" to continue is selected
						{
							u.setPosConns(desired1, desired2, desired3, desired4, desired5);
							u.setNegConn(undesired1);
							if(uneditedStudents.contains(u) && !editedStudents.contains(u))
							{
								uneditedStudents.removeElement(u);
								editedStudents.addElement(u);
							}
							dispose();
						}
				    }
					else
					{
						u.setPosConns(desired1, desired2, desired3, desired4, desired5);
						u.setNegConn(undesired1);
						if(uneditedStudents.contains(u) && !editedStudents.contains(u))
						{
							uneditedStudents.removeElement(u);
							editedStudents.addElement(u);
						}
						dispose();
					}
				}
			}
		});
	}
	
	public int showEmptyPrefsDialog()
	{
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
	    "One or more preferences are not set.\n"
	    + "Do you want to continue with empty preferences?",
	    "DC Room Generator",
	    JOptionPane.YES_NO_OPTION,
	    JOptionPane.QUESTION_MESSAGE,
	    null,     //do not use a custom Icon
	    options,  //the titles of buttons
	    options[1]); //default button title
		
		return n;
	}
	
	/**
	 * TODO: find simpler logic
	 */	
	public boolean choicesValid(Unit desired1, Unit desired2, Unit desired3, Unit desired4, Unit desired5, Unit undesired1)
	{
		return    ((desired1 == desired2 && desired1 != null && desired2 != null) // 1 && 2
				|| (desired1 == desired3 && desired1 != null && desired3 != null) // 1 && 3
				|| (desired1 == desired4 && desired1 != null && desired4 != null) // 1 && 4
				|| (desired1 == desired5 && desired1 != null && desired5 != null) // 1 && 5
				|| (desired1 == undesired1 && desired1 != null && undesired1 != null) // 1 && u
				|| (desired2 == desired3 && desired2 != null && desired3 != null) // 2 && 3
				|| (desired2 == desired4 && desired2 != null && desired4 != null) // 2 && 4
				|| (desired2 == desired5 && desired2 != null && desired5 != null) // 2 && 5
				|| (desired2 == undesired1 && desired2 != null && undesired1 != null) // 2 & u
				|| (desired3 == desired4 && desired3 != null && desired4 != null) // 3 && 4
				|| (desired3 == desired5 && desired3 != null && desired5 != null) // 3 && 5
				|| (desired3 == undesired1 && desired3 != null && undesired1 != null) // 3 & u
				|| (desired4 == desired5 && desired4 != null && desired5 != null) // 4 && 5
				|| (desired4 == undesired1 && desired4 != null && undesired1 != null) // 4 & u
				|| (desired5 == undesired1 && desired5 != null && undesired1 != null)); // 5 & u
				
	}
}
