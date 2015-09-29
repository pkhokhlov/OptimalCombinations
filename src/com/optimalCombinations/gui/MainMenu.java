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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

/*
 * TODO: button add new student to the addstudent dialog
 * TODO: add + button to increase number of preferences
 * TODO: change student name option
 * TODO: search within list
 * TODO: In selecting preferences, remove a student from the other preferences if it is selected in one
 * 			- eliminates duplicate choices dialog
 * TODO: have settings tab for algo parameters
 * TODO: when generating rooms, generate automatic text file and ask to save later
 * TODO: evaluate a group separately
 * TODO: when hitting cancel on save, don't make it so that it loses progress
 */
public class MainMenu extends JDialog implements Serializable
{
	private static final long serialVersionUID = -7382474446155096090L;
	DataModel model_ = new DataModel();
	File savedFile_ = null;
	File savedStudentListFile_ = null;
	
	JMenuBar menuBar_;
	JMenu menuFile_;
	JMenuItem menuItemSave_;
	JMenuItem menuItemOpen_;
	JMenuItem menuItemImportClassList_;
	
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
	private JScrollPane editedStudentScrollPane_;
	private JScrollPane uneditedStudentScrollPane_;
	

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
	
	/**
	 * Constructor for the gui.
	 */
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
		editedStudentList_.setBounds(283, 251, 247, 29);
		
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
		uneditedStudentList_.setBounds(10, 251, 247, 29);
		
		MouseListener mouseListener1 = new MouseAdapter()
		{
			
			/* This function opens the preference editing of an element that is double clicked. */
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
				if(model_.allStudents_.getSize() == 0)
				{
					System.exit(0);
				}
				else
				{
					if(shouldSaveOnExit() == 0)
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
					System.exit(0);
				}
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
		
		editedStudentScrollPane_ = new JScrollPane();
		editedStudentScrollPane_.setViewportView(editedStudentList_);
		editedStudentScrollPane_.setBounds(283, 32, 247, 237);
		getContentPane().add(editedStudentScrollPane_);
		
		uneditedStudentScrollPane_ = new JScrollPane();
		uneditedStudentScrollPane_.setViewportView(uneditedStudentList_);
		uneditedStudentScrollPane_.setBounds(10, 32, 247, 237);
		getContentPane().add(uneditedStudentScrollPane_);
		
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
				DataModel model = extractDataModel(importDataModelFile());
				setDataModel(model);
			}
		});
		
		menuItemImportClassList_ = new JMenuItem("Import Class List...");
		menuItemImportClassList_.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				importStudentListFile();
				try
				{
					importCSVStudentList(savedStudentListFile_);
					csvToDataModel(savedStudentListFile_);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		menuFile_.add(menuItemOpen_);
		menuFile_.add(menuItemSave_);
		menuFile_.add(menuItemImportClassList_);

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
	
	public File importDataModelFile()
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
	 * Imports the csv of the student list and saves it
	 * Specs for CSV: 
	 * column 0  - timestamp
	 * column 1  - name of student
	 * column 2+ - prefs in order
	 */
	public File importStudentListFile()
	{
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textFileOnlyFilter = new FileNameExtensionFilter(".csv", "csv", "csv");
        fileChooser.setFileFilter(textFileOnlyFilter);
        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
            savedStudentListFile_ = file;
        }
        
        return file;
	}
	
	/**
	 * This function adds the students from the csv to the allstudent and uneditedlist
	 * @param csvFile
	 * @throws IOException
	 */
	public void importCSVStudentList(File csvFile) throws IOException
	{
		if(csvFile == null)
			return;
		
		int numberImported = 0;
		String csvSplitBy = ",";
		String line;
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		line = br.readLine(); // skips first line that contains names of columns
		while((line = br.readLine()) != null) 
		{
		    String[] columns = line.split(csvSplitBy); // creates an array of strings that contain entries in each column
		    Unit tempUnit = new Unit(columns[1]);
		    
		    model_.allStudents_.addElement(tempUnit);
		    model_.uneditedStudents_.addElement(tempUnit);
		    numberImported++;
		}
		System.out.println("number of students imported: " + numberImported); // for debugging purposes
		br.close();
	}
	
	/**
	 * @precondition - importCSVStudentList is performed and there is a populated unedited student list
	 * This function takes the input from a csv and loads it into the datamodel
	 * @param csvFile
	 * @throws IOException 
	 */
	public void csvToDataModel(File csvFile) throws IOException
	{
		if(csvFile == null)
			return;
		
		int numberEdited = 0;
		String csvSplitBy = ",";
		String line;
		
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		line = br.readLine(); // skips first line that contains names of columns
		while ((line = br.readLine()) != null) 
		{
			// works because tempUnit is removed each iteration
			Unit tempUnit = model_.uneditedStudents_.getElementAt(0); 
			// creates an array of strings that contain entries in each column
		    String[] columns = line.split(csvSplitBy);
		    // adds the student's preferences to the unit in the uneditedStudents
		    ArrayList<Unit> studentPosCons = tempUnit.getPosConns();
		    // goes through saved string array with names of posconns & adds them to posconn of student
		    outerloop:
		    for(int i = 2; i < columns.length; i++)
		    {
		    	String tempPosConn = columns[i];
		    	for(int j = 0; j < model_.allStudents_.getSize(); j++)
		    	{
		    		if(model_.allStudents_.getElementAt(j).getName().equals(tempPosConn))
		    		{
		    			studentPosCons.add(model_.allStudents_.getElementAt(j));
		    			continue outerloop;
		    		}
		    	}
		    }
		    model_.uneditedStudents_.removeElement(tempUnit);
		    model_.editedStudents_.addElement(tempUnit);
		    numberEdited++;
		}
		System.out.println("number of edited: " + numberEdited); // for debugging purposes, should match numberImported in importCSVStudentList
		br.close();
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
        	return;
        }
        
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
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void generateAndReturnRooms(int size) throws FileNotFoundException, UnsupportedEncodingException
	{
		if(size > model_.editedStudents_.getSize())
		{
			JOptionPane.showMessageDialog(new JFrame(),
					"Error: The class size is larger than the number of students.");
		}
		else
		{
			Unit[] temp = new Unit[model_.editedStudents_.getSize()];
			model_.editedStudents_.copyInto(temp);
			ArrayList<Unit> edited = new ArrayList<Unit>(Arrays.asList(temp));
			
			DecreasingUnitPool result = new DecreasingUnitPool(edited, size);
			finalList_ = result.findStrongestGroups();
			fileChooserIntoTextFile();
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
        	    "Would you like to open the generated rooms?",
        	    "DC Room Generator",
        	    JOptionPane.YES_NO_OPTION,
        	    JOptionPane.QUESTION_MESSAGE,
        	    null,     //do not use a custom Icon
        	    options,  //the titles of buttons
        	    options[0]); //default button title
        		
        		if(n == 0) // if "yes" to continue is selected
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
	
	/**
	 * This displays an option dialog that asks the user if he/she wishes to save before exiting.
	 * @return n - the result from the optionDialog
	 */
	public int shouldSaveOnExit()
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
		
		return n;
	}
}
