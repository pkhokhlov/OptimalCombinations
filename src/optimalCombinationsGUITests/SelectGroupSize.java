package optimalCombinationsGUITests;


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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the dialog.
	 */
	public SelectGroupSize()
	{
		setTitle("DC Trip Room Generator");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JComboBox comboBox = new JComboBox();
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
	}
}
