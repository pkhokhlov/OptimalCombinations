package optimalCombinationsGUITests;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import optimalcombinations.Unit;

/**
 * 
 * @author Pavel Khokhlov
 *
 */
public class PreferenceSelection extends JDialog
{
	/**
	 * Create the dialog.
	 */
	public PreferenceSelection(final Unit u, final DefaultListModel<Unit> uneditedStudents, 
			                           final DefaultListModel<Unit> editedStudents, 
			                           DefaultComboBoxModel<Unit> allStudents)
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		DefaultComboBoxModel<Unit> desiredComboBox1 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> desiredComboBox2 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> desiredComboBox3 = MainMenu.copyComboBoxModel(allStudents);
		DefaultComboBoxModel<Unit> undesiredComboBox1 = MainMenu.copyComboBoxModel(allStudents);
		
		final JComboBox<Unit> desiredBox1 = new JComboBox<Unit>(desiredComboBox1);
		desiredBox1.setBounds(212, 48, 163, 22);
		getContentPane().add(desiredBox1);
		
		final JComboBox<Unit> desiredBox2 = new JComboBox<Unit>(desiredComboBox2);
		desiredBox2.setBounds(212, 78, 163, 22);
		getContentPane().add(desiredBox2);
		
		final JComboBox<Unit> desiredBox3 = new JComboBox<Unit>(desiredComboBox3);
		desiredBox3.setBounds(212, 108, 163, 22);
		getContentPane().add(desiredBox3);
		
		final JComboBox<Unit> undesiredBox1 = new JComboBox<Unit>(undesiredComboBox1);
		undesiredBox1.setBounds(212, 160, 163, 22);
		getContentPane().add(undesiredBox1);
		
		if(u.getPosConns().size() != 0)
		{
			desiredBox1.setSelectedIndex(desiredComboBox1.getIndexOf(u.getPosConns().get(0)));
			desiredBox2.setSelectedIndex(desiredComboBox2.getIndexOf(u.getPosConns().get(1)));
			desiredBox3.setSelectedIndex(desiredComboBox3.getIndexOf(u.getPosConns().get(2)));
			undesiredBox1.setSelectedIndex(desiredComboBox1.getIndexOf(u.getNegConn()));
		}
		
		desiredComboBox1.removeElement(u);
		desiredComboBox2.removeElement(u);
		desiredComboBox3.removeElement(u);
		undesiredComboBox1.removeElement(u);
		
		JLabel lblDesiredGroupMember = new JLabel("Desired Group Member 1 :");
		lblDesiredGroupMember.setBounds(57, 48, 145, 22);
		getContentPane().add(lblDesiredGroupMember);
		
		JLabel lblDesiredGroupMember_1 = new JLabel("Desired Group Member 2 :");
		lblDesiredGroupMember_1.setBounds(57, 78, 145, 22);
		getContentPane().add(lblDesiredGroupMember_1);
		
		JLabel lblDesiredGroupMember_2 = new JLabel("Desired Group Member 3 :");
		lblDesiredGroupMember_2.setBounds(57, 108, 145, 22);
		getContentPane().add(lblDesiredGroupMember_2);
		
		JLabel lblUndesiredGroupMember = new JLabel("Undesired Group Member :");
		lblUndesiredGroupMember.setBounds(57, 164, 145, 22);
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
		/* parameters: unit, unedited, edited
		 * 1. Open the window
		 * 2. if all choices are selected -> remove from uneditedStudents and put into editedStudents
		 * 3. if not all choices are selected -> popup saying not all are selected -> could continue could redo -> 
		 *  if continue, remove from uneditedStudents and put into editedStudents
		 * 4. if none are selected - popup saying none are selected force to fill or cancel
		 * 
		 */
		btnOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Unit desired1 = desiredBox1.getItemAt(desiredBox1.getSelectedIndex());
				Unit desired2 = desiredBox2.getItemAt(desiredBox2.getSelectedIndex());
				Unit desired3 = desiredBox3.getItemAt(desiredBox3.getSelectedIndex());
				Unit undesired1 = undesiredBox1.getItemAt(undesiredBox1.getSelectedIndex());
				if(desired1 != desired2 && desired2 != desired3 && desired3 != undesired1)
				{
					u.setPosConns(desired1, desired2, desired3);
					u.setNegConn(undesired1);
					if(uneditedStudents.contains(u) && !editedStudents.contains(u))
					{
						uneditedStudents.removeElement(u);
						editedStudents.addElement(u);
					}
					dispose();
				}
				else if(!(desired1 != desired2 && desired2 != desired3 && desired3 != undesired1))
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
							"Error: you have duplicate choices selected.");
				}
			}
		});
		
		JLabel lblStudentsPreferences = new JLabel(u + "'s Preferences");
		lblStudentsPreferences.setBounds(155, 23, 113, 14);
		getContentPane().add(lblStudentsPreferences);
	}
}