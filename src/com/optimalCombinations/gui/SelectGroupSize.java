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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGroupSize extends JDialog
{
	private static final long serialVersionUID = 7443413933637988591L;

	public static void main(String[] args)
	{
		try
		{
			SelectGroupSize dialog = new SelectGroupSize();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public SelectGroupSize()
	{
		setTitle("DC Trip Room Generator");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 450, 300);
		
		DefaultComboBoxModel<Integer> sizes = new DefaultComboBoxModel<Integer>();
		sizes.addElement(3);
		sizes.addElement(4);
		sizes.addElement(5);
		
		getContentPane().setLayout(null);
		{
			JComboBox<Integer> comboBox = new JComboBox<Integer>(sizes);
			comboBox.setBounds(195, 122, 56, 22);
			getContentPane().add(comboBox);
		}
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the number of students per room.");
		lblPleaseSelectThe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseSelectThe.setBounds(90, 96, 264, 15);
		getContentPane().add(lblPleaseSelectThe);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
			
		});
		btnCancel.setBounds(10, 239, 91, 23);
		getContentPane().add(btnCancel);
		
		JButton btnBuildRooms = new JButton("Build Rooms");
		btnBuildRooms.setBounds(341, 239, 91, 23);
		getContentPane().add(btnBuildRooms);
		btnBuildRooms.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("Rooms are built");
			}
			
		});
	}
}
