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

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import com.optimalCombinations.algo.Unit;

public class AddNewStudent extends JDialog
{
	private static final long serialVersionUID = -221976191149931321L;
	String studentName_ = "";
	JTextArea textArea_;
	JLabel lblPleaseEnterThe_;
	JButton btnCancel_;
	JButton btnOk_;

	public AddNewStudent(final DefaultComboBoxModel<Unit> allStudents, final DefaultListModel<Unit> uneditedStudents)
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lblPleaseEnterThe_ = new JLabel(
				"Please enter the name of the new student.");
		lblPleaseEnterThe_.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseEnterThe_.setBounds(100, 85, 291, 15);
		getContentPane().add(lblPleaseEnterThe_);

		textArea_ = new JTextArea();
		textArea_.setBounds(100, 130, 238, 20);
		textArea_.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == (KeyEvent.VK_ENTER))
				{
					readFromField(allStudents, uneditedStudents);
				}
			}

			@Override
			public void keyReleased(KeyEvent e){}

			@Override
			public void keyTyped(KeyEvent e){}
			
		});
		getContentPane().add(textArea_);
		

		btnCancel_ = new JButton("Cancel");
		btnCancel_.setBounds(10, 239, 91, 23);
		getContentPane().add(btnCancel_);
		btnCancel_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}

		});

		btnOk_ = new JButton("OK");
		btnOk_.setBounds(341, 239, 91, 23);
		getContentPane().add(btnOk_);
		btnOk_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				readFromField(allStudents, uneditedStudents);
			}
		});
	}
	
	public void readFromField(final DefaultComboBoxModel<Unit> allStudents, final DefaultListModel<Unit> uneditedStudents)
	{
		studentName_ = textArea_.getText();
		if (studentName_.length() < 1 || studentName_ == null)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					"The name you entered is too short.");
		} 
		else
		{
			allStudents.addElement(new Unit(studentName_));
			uneditedStudents.addElement(allStudents.getElementAt(allStudents.getSize() - 1));
			setVisible(false);
		}
	}
}
