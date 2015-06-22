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

import com.optimalCombinations.algo.DataModel;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/*
 * TODO: include a save question when closing
 */
public class MainMenu extends JDialog implements Serializable
{
	private static final long serialVersionUID = -7382474446155096090L;
	DataModel model_ = new DataModel();
	File savedFile_ = null;
	
	JMenuBar menuBar_;
	JMenu menuFile_;
	JMenuItem menuItemSave_;
	JMenuItem menuItemOpen_;
	
	final JList<Unit> uneditedStudentList_;
	final JList<Unit> editedStudentList_;
	JList<Unit> lastFocusedStudents_;
	
	JLabel lblUneditedStudents_;
	JLabel lblEditedStudents_;
	
	JButton btnEditPrefs_;
	JButton btnAddStudent_;
	JButton btnRemoveStudent_;
	JButton btnNext_;
	JButton btnExit_;
	
	GroupSet finalList_ = new GroupSet();
	private JMenuItem menuItemSaveAs_;
	

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
		
		uneditedStudentList_ = new JList<Unit>(model_.uneditedStudents_);
		uneditedStudentList_.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lastFocusedStudents_ = uneditedStudentList_;
		
		editedStudentList_ = new JList<Unit>(model_.editedStudents_);
		editedStudentList_.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editedStudentList_.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				lastFocusedStudents_ = editedStudentList_;
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				uneditedStudentList_.clearSelection();
			}
		});
		editedStudentList_.setBounds(283, 25, 247, 255);
		getContentPane().add(editedStudentList_);
		
		uneditedStudentList_.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				lastFocusedStudents_ = uneditedStudentList_;
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				editedStudentList_.clearSelection();
			}
		});
		uneditedStudentList_.setBounds(10, 25, 247, 255);
		
		MouseListener mouseListener1 = new MouseAdapter()
		{
			/*
			 * This function opens the preference editing of an element that is double clicked.
			 */
			public void mouseClicked(MouseEvent mouseEvent)
			{
				@SuppressWarnings("unchecked")
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
		uneditedStudentList_.addMouseListener(mouseListener1);
		
		getContentPane().add(uneditedStudentList_);

		btnAddStudent_ = new JButton("Add Student");
		btnAddStudent_.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addStudent();
			}
		});
		btnAddStudent_.setBounds(10, 291, 111, 23);
		getContentPane().add(btnAddStudent_);
		
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent mouseEvent)
			{
				@SuppressWarnings("unchecked")
				JList<Unit> theList = (JList<Unit>) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2)
				{
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0)
					{
						Unit selectedUnit = (Unit) theList.getModel().getElementAt(index);
						editPreferences(selectedUnit);
					}
				}
			}
		};
		editedStudentList_.addMouseListener(mouseListener);

		btnEditPrefs_ = new JButton("Edit Student Preferences");
		btnEditPrefs_.addActionListener(new ActionListener()
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
		btnEditPrefs_.setBounds(131, 291, 212, 23);
		getContentPane().add(btnEditPrefs_);

		btnExit_ = new JButton("Exit");
		btnExit_.setBounds(10, 347, 91, 23);
		btnExit_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(shouldSaveOnExit())
				{
					if(model_.saved_) 
					{
						saveToFile(savedFile_);
					}
					else
					{
						saveAsDataModel();
					}
					if(savedFile_ == null)
						return;
				}
				System.exit(0);
			}

		});
		getContentPane().add(btnExit_);

		btnNext_ = new JButton("Next >>");
		btnNext_.setBounds(419, 347, 111, 23);
		getContentPane().add(btnNext_);
		btnNext_.addActionListener(new ActionListener()
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
						}
						
				
			}
		});
		
		lblUneditedStudents_ = new JLabel("Students");
		lblUneditedStudents_.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUneditedStudents_.setBounds(107, 7, 50, 15);
		getContentPane().add(lblUneditedStudents_);
		
		lblEditedStudents_ = new JLabel("Student Preferences");
		lblEditedStudents_.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEditedStudents_.setBounds(348, 7, 155, 15);
		getContentPane().add(lblEditedStudents_);
		
		btnRemoveStudent_ = new JButton("Remove Student");
		btnRemoveStudent_.setBounds(353, 291, 131, 23);
		getContentPane().add(btnRemoveStudent_);
		
		menuBar_ = new JMenuBar();
		setJMenuBar(menuBar_);
		
		menuFile_ = new JMenu("File");
		menuBar_.add(menuFile_);
		
		menuItemSave_ = new JMenuItem("Save");
		menuItemSave_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(model_.saved_) 
				{
					saveToFile(savedFile_);
				}
				else
				{
					saveAsDataModel();
				}
			}
		});
		
		menuItemOpen_ = new JMenuItem("Open...");
		menuItemOpen_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DataModel model = extractDataModel(getFile());
				setDataModel(model);
			}
		});
		menuFile_.add(menuItemOpen_);
		menuFile_.add(menuItemSave_);
		
		menuItemSaveAs_ = new JMenuItem("Save As...");
		menuItemSaveAs_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				saveAsDataModel();
			}
		});
		menuFile_.add(menuItemSaveAs_);
		
		btnRemoveStudent_.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(lastFocusedStudents_.getSelectedValue() != null)
				{
					model_.allStudents_.removeElement(lastFocusedStudents_.getSelectedValue());
					model_.editedStudents_.removeElement(lastFocusedStudents_.getSelectedValue());
					model_.uneditedStudents_.removeElement(lastFocusedStudents_.getSelectedValue());
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
	 * This function sets the model_ and all the JLists to display a saved version of the program.
	 * @param model - the model_ that contains all the data that should be displayed
	 */
	public void setDataModel(DataModel model)
	{
		model_ = model;
		editedStudentList_.setModel(model_.editedStudents_);
		uneditedStudentList_.setModel(model_.uneditedStudents_);
	}
	
	/**
	 * @return model - the DataModel from the file being read
	 */
	public DataModel extractDataModel(File file)
	{
		DataModel model = null;
		if(file == null)
			return model_;
		
		try
        {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            model = (DataModel) ois.readObject();
            ois.close();

            // Clean up the file
            new File(file.getName()).delete();
            
            return model;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		return model;
	}
	
	public File getFile()
	{
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textFileOnlyFilter = new FileNameExtensionFilter(".proj", "proj", "project");
        fileChooser.setFileFilter(textFileOnlyFilter);
        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
            savedFile_ = file;
        }
        
        return file;
	}

	/**
	 * This function saves the current DataModel into a file.
	 */
	public void saveAsDataModel()
	{
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textFileOnlyFilter = new FileNameExtensionFilter(".proj", "proj", "project");
        fileChooser.setFileFilter(textFileOnlyFilter);
        int returnVal = fileChooser.showSaveDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
			if (!file.getName().endsWith(".proj")) 
			{
				file = new File(file.toString() + ".proj");
			}
        }
        
        if(file == null)
        {
        	System.out.println("Error: File could not be made");
        	return;
        }
        //
		try
        {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            model_.saved_ = true;
            oos.writeObject(model_);
            oos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	 
	/**
	 * This function saves the current model_ to an existing file.
	 * @param file
	 */
	public void saveToFile(File file)
	{
		try
        {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            model_.saved_ = true;
            oos.writeObject(model_);
            oos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	/**
	 * This function opens a dialog for the user to edit the roommate preferences of a student.
	 * @param selectedUnit - the unit whose preferences will be changed
	 */
	public void editPreferences(Unit selectedUnit)
	{
		try
		{
			PreferenceSelection prefSelectDialog = new PreferenceSelection(selectedUnit, model_.uneditedStudents_, model_.editedStudents_, model_.allStudents_);
			prefSelectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			prefSelectDialog.setVisible(true);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This function opens the AddNewStudent dialog and adds a unit with the name inputed by the user through the dialog
	 * to uneditedStudents_.
	 */
	public void addStudent()
	{
		AddNewStudent addStudentDialog = new AddNewStudent(model_.allStudents_, model_.uneditedStudents_);
		addStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStudentDialog.setVisible(true);
	}
	
	/**
	 * This function opens an option dialog and asks if the user would like to continue editing the class.
	 * @return returns true if yes is selected
	 */
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
		
		return n == 0 ; // if "yes" to continue is selected
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
	 * This function generates rooms from the editedStudents_ using the DecreasingUnitPool algorithm 
	 * and writes the result to a text file.
	 * @param size - the size of the rooms
	 * @return boolean - true if the file with the rooms has been created, false otherwise
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public boolean generateAndReturnRooms(int size) throws FileNotFoundException, UnsupportedEncodingException
	{
		if(size > model_.editedStudents_.getSize())
		{
			JOptionPane.showMessageDialog(new JFrame(),
					"Error: The class size is larger than the number of students.");
			return false;
		}
		else
		{
			Unit[] temp = new Unit[model_.editedStudents_.getSize()];
			model_.editedStudents_.copyInto(temp);
			ArrayList<Unit> edited = new ArrayList<Unit>(Arrays.asList(temp));
			
			DecreasingUnitPool result = new DecreasingUnitPool(edited, size);
			finalList_ = result.findStrongestGroups();
			fileChooserIntoTextFile();
			return true;
		}
	}

	/**
	 * This function brings up a file chooser and the user saves the finalList_ into the text file.
	 */
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
        		
        		Object[] options = {"Yes", "No"};
        		int n = JOptionPane.showOptionDialog(new JFrame(),
        	    "Would you like to open the result?",
        	    "DC Room Generator",
        	    JOptionPane.YES_NO_OPTION,
        	    JOptionPane.QUESTION_MESSAGE,
        	    null,     //do not use a custom Icon
        	    options,  //the titles of buttons
        	    options[0]); //default button title
        		
        		if(n != 1) // if "yes" to continue is selected
        		{
        			openGeneratedFile(file);
        		}
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * This function opens a file with the default text editor on the system.
	 * @param file - the file desired to be opened
	 */
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
			JOptionPane.showMessageDialog(new JFrame(), "Could not find a text editor.");
		}
	}
	
	public boolean shouldSaveOnExit()
	{
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
	    "Would you like to save before exiting?",
	    "DC Room Generator",
	    JOptionPane.YES_NO_OPTION,
	    JOptionPane.QUESTION_MESSAGE,
	    null,     //do not use a custom Icon
	    options,  //the titles of buttons
	    options[0]); //default button title
		
		System.out.println(n);
		
		return n == 0; // "yes" was selected
	}
}
