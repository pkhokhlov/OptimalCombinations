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

import javax.swing.JTextArea;

import com.optimalCombinations.algo.Unit;

public class AddNewStudent extends JDialog
{
	private static final long serialVersionUID = -221976191149931321L;
	String studentName_ = "";

	public AddNewStudent(final DefaultComboBoxModel<Unit> allStudents, final DefaultListModel<Unit> uneditedStudents)
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblPleaseEnterThe = new JLabel(
				"Please enter the name of the new student.");
		lblPleaseEnterThe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseEnterThe.setBounds(100, 85, 238, 15);
		getContentPane().add(lblPleaseEnterThe);

		final JTextArea textArea = new JTextArea();
		textArea.setBounds(100, 130, 238, 20);
		getContentPane().add(textArea);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 239, 91, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}

		});

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(341, 239, 91, 23);
		getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame = new JFrame();
				studentName_ = textArea.getText();
				if (studentName_.length() < 1 || studentName_ == null)
				{
					JOptionPane.showMessageDialog(frame,
							"The name you entered is too short.");
				} 
				else
				{
					allStudents.addElement(new Unit(studentName_));
					uneditedStudents.addElement(allStudents.getElementAt(allStudents.getSize() - 1));
					setVisible(false);
				}
			}
		});
	}

}
