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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.optimalCombinations.algo.DecreasingUnitPool;
import com.optimalCombinations.algo.GroupSet;
import com.optimalCombinations.algo.Unit;

import java.awt.Desktop;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class MainMenu extends JDialog
{
	private static final long serialVersionUID = -7382474446155096090L;
	final DefaultComboBoxModel<Unit> allStudents_;
	final DefaultListModel<Unit> uneditedStudents_;
	final DefaultListModel<Unit> editedStudents_;
	JList<Unit> lastFocusedStudents_;
	
	GroupSet finalList_ = new GroupSet();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			MainMenu dialog = new MainMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public MainMenu()
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 548, 429);
		getContentPane().setLayout(null);
		
		allStudents_ = new DefaultComboBoxModel<Unit>();
		uneditedStudents_ = new DefaultListModel<Unit>();
		editedStudents_ = new DefaultListModel<Unit>();
		
		final JList<Unit> uneditedStudentList = new JList<Unit>(uneditedStudents_);
		uneditedStudentList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lastFocusedStudents_ = uneditedStudentList;
		
		final JList<Unit> editedStudentList = new JList<Unit>(editedStudents_);
		editedStudentList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editedStudentList.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				lastFocusedStudents_ = editedStudentList;
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				uneditedStudentList.clearSelection();
			}
		});
		editedStudentList.setBounds(283, 25, 247, 255);
		getContentPane().add(editedStudentList);
		
		uneditedStudentList.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				lastFocusedStudents_ = uneditedStudentList;
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				editedStudentList.clearSelection();
			}
		});
		uneditedStudentList.setBounds(10, 25, 247, 255);
		
		MouseListener mouseListener1 = new MouseAdapter()
		{
			/*
			 * This function opens the preference editing of an element that is double clicked.
			 */
			public void mouseClicked(MouseEvent mouseEvent)
			{
				JList<Unit> theList = (JList<Unit>) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2)
				{
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0)
					{
						Unit o = (Unit) theList.getModel().getElementAt(index);
						editPreferences(o);
					}
				}
			}
		};
		uneditedStudentList.addMouseListener(mouseListener1);

		
		getContentPane().add(uneditedStudentList);

		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addStudent();
			}
		});
		btnAddStudent.setBounds(10, 291, 111, 23);
		getContentPane().add(btnAddStudent);
		
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent mouseEvent)
			{
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2)
				{
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0)
					{
						Unit o = (Unit) theList.getModel().getElementAt(index);
						editPreferences(o);
					}
				}
			}
		};
		editedStudentList.addMouseListener(mouseListener);

		JButton btnEditPrefs = new JButton("Edit Student Preferences");
		btnEditPrefs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(lastFocusedStudents_.getSelectedValue() != null)
				{
					editPreferences(lastFocusedStudents_.getSelectedValue());
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(),
							"Error: student not selected.");
				}
			}
		});
		btnEditPrefs.setBounds(131, 291, 212, 23);
		getContentPane().add(btnEditPrefs);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(10, 368, 91, 23);
		btnExit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(shouldClose())
				{
					System.exit(0);
				}
			}

		});
		getContentPane().add(btnExit);

		JButton btnNext = new JButton("Next >>");
		btnNext.setBounds(419, 368, 111, 23);
		getContentPane().add(btnNext);
		btnNext.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int size;
				Object[] possibilities = {"3", "4", "5"};
				String s = (String)JOptionPane.showInputDialog(
	                    new JFrame(),
	                    "Please select the size of the group",
	                    "DC Trip Room Generator", 
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    possibilities,
	                    "4");

						// if a value is set
						if ((s != null) && (s.length() > 0)) 
						{
							size = Integer.parseInt(s);
							
							try
							{
								generateAndReturnRooms(size);
							} 
							catch (FileNotFoundException e)
							{
								e.printStackTrace();
							} 
							catch (UnsupportedEncodingException e)
							{
								e.printStackTrace();
							}
							
							setVisible(false);
							
							if(continueWithEditing())
							{
								setVisible(true);
							}
							else
							{
								System.exit(0);
							}
						}
						
				
			}
		});
		
		JLabel lblUneditedStudents = new JLabel("Students");
		lblUneditedStudents.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUneditedStudents.setBounds(107, 7, 50, 15);
		getContentPane().add(lblUneditedStudents);
		
		JLabel lblEditedStudents = new JLabel("Student Preferences");
		lblEditedStudents.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEditedStudents.setBounds(348, 7, 155, 15);
		getContentPane().add(lblEditedStudents);
		
		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.setBounds(353, 291, 131, 23);
		getContentPane().add(btnRemoveStudent);
		btnRemoveStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(lastFocusedStudents_.getSelectedValue() != null)
				{
					allStudents_.removeElement(lastFocusedStudents_.getSelectedValue());
					editedStudents_.removeElement(lastFocusedStudents_.getSelectedValue());
					uneditedStudents_.removeElement(lastFocusedStudents_.getSelectedValue());
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(),
							"Error: student not selected.");
				}
			}
		});

	}

	/**
	 * This function opens a dialog for the user to edit the roommate preferences of a student.
	 * @param u - the unit whose preferences will be changed
	 */
	public void editPreferences(Unit u)
	{
		try
		{
			PreferenceSelection prefSelectDialog = new PreferenceSelection(u, uneditedStudents_, editedStudents_, allStudents_);
			prefSelectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			prefSelectDialog.setVisible(true);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This function opens the AddNewStudent dialog and adds a unit with the name inputed by the user through the dialog.
	 */
	public void addStudent()
	{
		AddNewStudent addStudentDialog = new AddNewStudent(allStudents_, uneditedStudents_);
		addStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStudentDialog.setVisible(true);
	}
	
	public boolean continueWithEditing()
	{
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
	    "Would you like to continue working with this class? \n"
	    + "Hitting no will close the program.",
	    "DC Room Generator",
	    JOptionPane.YES_NO_OPTION,
	    JOptionPane.QUESTION_MESSAGE,
	    null,     //do not use a custom Icon
	    options,  //the titles of buttons
	    options[0]); //default button title
		
		if(n != 1) // if "yes" to continue is selected
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This function copies one DefaultComboBoxModel's units into another DefaultComboBoxModel.
	 * @param toBeCopied - the DefaultComboBoxModel to be copied into another one
	 * @return - a new DefaultComboBoxModel that contains all the units of toBeCopied
	 */
	public static DefaultComboBoxModel<Unit> copyComboBoxModel(DefaultComboBoxModel<Unit> toBeCopied)
	{
		DefaultComboBoxModel<Unit> result = new DefaultComboBoxModel<Unit>();
		result.addElement(null);
		for(int i = 0; i < toBeCopied.getSize(); i++)
		{
			result.addElement(toBeCopied.getElementAt(i));
		}
		return result;
	}
	
	/**
	 * This function generates rooms using the DecreasingUnitPool algorithm and writes the result to a text file.
	 * @param size - the size of the rooms
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void generateAndReturnRooms(int size) throws FileNotFoundException, UnsupportedEncodingException
	{
		Unit[] temp = new Unit[editedStudents_.getSize()];
		editedStudents_.copyInto(temp);
		ArrayList<Unit> edited = new ArrayList<Unit>(Arrays.asList(temp));
		
		DecreasingUnitPool result = new DecreasingUnitPool(edited, size);
		finalList_ = result.findStrongestGroups();
		fileChooserIntoTextFile();
	}

	
	public void fileChooserIntoTextFile()
	{
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textFileOnlyFilter = new FileNameExtensionFilter(".txt", "txt", "text");
        fileChooser.setFileFilter(textFileOnlyFilter);
        int returnVal = fileChooser.showSaveDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".txt")) 
                {
                	file = new File(file.toString() + ".txt");
                } 

                PrintWriter writer = new PrintWriter(file);
                
        		for(int i = 0; i < finalList_.groupSet_.size(); i++)
        		{
        			writer.println("Group " + (i+1) + ": " + finalList_.groupSet_.get(i).toString());
        		}
        		writer.close();
        		openGeneratedFile(file);
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
	}
	
	public void openGeneratedFile(File file)
	{
		if (Desktop.isDesktopSupported()) 
		{
		    try
			{
				Desktop.getDesktop().edit(file);
			} 
		    catch (IOException e)
			{
				e.printStackTrace();
			}
		} 
		else 
		{
			JOptionPane.showMessageDialog(new JFrame(), "Done.");
		}
	}
	
	public boolean shouldClose()
	{
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
	    "Do you want to exit the program?",
	    "DC Room Generator",
	    JOptionPane.YES_NO_OPTION,
	    JOptionPane.QUESTION_MESSAGE,
	    null,     //do not use a custom Icon
	    options,  //the titles of buttons
	    options[1]); //default button title
		
		if(n != 1) // if "yes" to continue is selected
		{
			return true;
		}
		return false;
	}
}
