package optimalCombinationsGUITests;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import optimalcombinations.Unit;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * @author Pavel Khokhlov
 * 
 */
public class MainMenu extends JDialog
{
	private static final long serialVersionUID = -7382474446155096090L;
	final DefaultComboBoxModel<Unit> allStudents_;
	final DefaultListModel<Unit> uneditedStudents_;
	final DefaultListModel<Unit> editedStudents_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			MainMenu dialog = new MainMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MainMenu()
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 548, 429);
		getContentPane().setLayout(null);
		
		allStudents_ = new DefaultComboBoxModel<Unit>();
		uneditedStudents_ = new DefaultListModel<Unit>();
		editedStudents_ = new DefaultListModel<Unit>();

		Unit A = new Unit("ABC");
		Unit B = new Unit("B");
		Unit C = new Unit("C");
		Unit D = new Unit("D");
		Unit E = new Unit("E");
		Unit F = new Unit("F");
		Unit G = new Unit("G");
		Unit H = new Unit("H");
		Unit I = new Unit("I"); // 9
				
		
		uneditedStudents_.addElement(A);
		uneditedStudents_.addElement(B);
		uneditedStudents_.addElement(C);
		uneditedStudents_.addElement(D);
		uneditedStudents_.addElement(E);
		uneditedStudents_.addElement(F);
		uneditedStudents_.addElement(G);
		uneditedStudents_.addElement(H);
		uneditedStudents_.addElement(I);
		
		allStudents_.addElement(A);
		allStudents_.addElement(B);
		allStudents_.addElement(C);
		allStudents_.addElement(D);
		allStudents_.addElement(E);
		allStudents_.addElement(F);
		allStudents_.addElement(G);
		allStudents_.addElement(H);
		allStudents_.addElement(I);
		
		System.out.println(uneditedStudents_);
		final JList<Unit> uneditedStudentList = new JList(uneditedStudents_);
		
		
		MouseListener mouseListener1 = new MouseAdapter()
		{
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

		uneditedStudentList.setBounds(10, 25, 247, 255);
		getContentPane().add(uneditedStudentList);

		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addStudent(allStudents_, uneditedStudents_);
			}
		});
		btnAddStudent.setBounds(10, 291, 111, 23);
		getContentPane().add(btnAddStudent);
		
		final JList<Unit> editedStudentList = new JList<Unit>(editedStudents_);
		editedStudentList.setBounds(283, 25, 247, 255);
		getContentPane().add(editedStudentList);
		
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

		// TODO: fix the focus problem so that you can use btnEditPrefs for both lists
		JButton btnEditPrefs = new JButton("Edit Student Preferences");
		btnEditPrefs.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{
				if(getFocusOwner().getClass().isInstance(uneditedStudentList) || getFocusOwner().getClass().isInstance(editedStudentList))
				{
					editPreferences((Unit)((JList<Unit>) getFocusOwner()).getSelectedValue());
				}
			}
		});
		btnEditPrefs.setBounds(131, 291, 180, 23);
		getContentPane().add(btnEditPrefs);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(10, 368, 91, 23);
		btnExit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}

		});
		getContentPane().add(btnExit);

		JButton btnNext = new JButton("Next >>");
		btnNext.setBounds(419, 368, 111, 23);
		getContentPane().add(btnNext);
		
		
		
		JLabel lblUneditedStudents = new JLabel("Unedited Students");
		lblUneditedStudents.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUneditedStudents.setBounds(80, 7, 104, 15);
		getContentPane().add(lblUneditedStudents);
		
		JLabel lblEditedStudents = new JLabel("Edited Students");
		lblEditedStudents.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEditedStudents.setBounds(353, 7, 104, 15);
		getContentPane().add(lblEditedStudents);
		
		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.setBounds(321, 291, 115, 23);
		getContentPane().add(btnRemoveStudent);

	}

	
	public void editPreferences(Unit u)
	{
		try
		{
			/* parameters: unit, unedited, edited
			 * -1. Open the window
			 * -2. if all choices are selected -> remove from uneditedStudents and put into editedStudents
			 * 3. if not all choices are selected -> popup saying not all are selected -> could continue could redo -> 
			 *  if continue, remove from uneditedStudents and put into editedStudents
			 * 4. if none are selected - popup saying none are selected force to fill or cancel
			 */
			PreferenceSelection prefSelectDialog = new PreferenceSelection(u, uneditedStudents_, editedStudents_, allStudents_);
			prefSelectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			prefSelectDialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addStudent(DefaultComboBoxModel<Unit> allS, DefaultListModel<Unit> unEdited)
	{
		AddNewStudent addStudentDialog = new AddNewStudent(allStudents_, uneditedStudents_);
		addStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStudentDialog.setVisible(true);
		System.out.println("added: " + allStudents_.getElementAt(allStudents_.getSize() - 1));
	}
	
	public static DefaultComboBoxModel<Unit> copyComboBoxModel(DefaultComboBoxModel<Unit> toBeCopied)
	{
		DefaultComboBoxModel<Unit> result = new DefaultComboBoxModel<Unit>();
		for(int i = 0; i < toBeCopied.getSize(); i++)
		{
			result.addElement(toBeCopied.getElementAt(i));
		}
		return result;
	}
}
