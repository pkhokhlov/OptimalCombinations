package optimalCombinationsGUITests;

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

import optimalcombinations.Unit;

public class AddNewStudent extends JDialog
{
	private static final long serialVersionUID = -221976191149931321L;
	String studentName_ = "";

	/**
	 * Create the dialog.
	 */
	public AddNewStudent(final DefaultComboBoxModel<Unit> allStudents, final DefaultListModel<Unit> uneditedStudents)
	{
		setTitle("DC Trip Room Generator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblPleaseEnterThe = new JLabel(
				"Please enter the name of the new student");
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
