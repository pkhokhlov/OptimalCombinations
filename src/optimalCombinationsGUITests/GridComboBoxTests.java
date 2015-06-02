package optimalCombinationsGUITests;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * adapted from http://www.tutorialspoint.com/swing/swing_jcombobox.htm
 *
 */
public class GridComboBoxTests
{
	public JFrame mainFrame_;
	public JLabel headerLabel_;
	public JLabel statusLabel_;
	public JPanel controlPanel_;

	public GridComboBoxTests()
	{
		prepareGUI();
	}

	public static void main(String[] args)
	{
		GridComboBoxTests gridComboBoxTests = new GridComboBoxTests();
		gridComboBoxTests.showComboboxDemo();
	}

	public void prepareGUI()
	{
		mainFrame_ = new JFrame("Java Swing Examples");
		mainFrame_.setSize(236, 133);
		mainFrame_.setLayout(new GridLayout(3, 1));
		mainFrame_.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
		headerLabel_ = new JLabel("", JLabel.CENTER);
		statusLabel_ = new JLabel("", JLabel.CENTER);

		statusLabel_.setSize(350, 100);

		controlPanel_ = new JPanel();
		controlPanel_.setLayout(new FlowLayout());

		mainFrame_.add(headerLabel_);
		mainFrame_.add(controlPanel_);
		mainFrame_.add(statusLabel_);
		//mainFrame_.pack();
		mainFrame_.setVisible(true);
	}

	public void showComboboxDemo()
	{
		headerLabel_.setText("Control in action: JComboBox");

		final DefaultComboBoxModel fruitsName = new DefaultComboBoxModel();

		fruitsName.addElement("Apple");
		fruitsName.addElement("Grapes");
		fruitsName.addElement("Mango");
		fruitsName.addElement("Peer");

		final JComboBox fruitCombo = new JComboBox(fruitsName);
		fruitCombo.setSelectedIndex(0);

		JScrollPane fruitListScrollPane = new JScrollPane(fruitCombo);

		JButton showButton = new JButton("Show");

		showButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String data = "";
				if (fruitCombo.getSelectedIndex() != -1)
				{
					data = "Fruits Selected: "
							+ fruitCombo.getItemAt(fruitCombo
									.getSelectedIndex());
				}
				statusLabel_.setText(data);
				System.out.println(mainFrame_.getSize());
			}
		});
		controlPanel_.add(fruitListScrollPane);
		controlPanel_.add(showButton);
		mainFrame_.setVisible(true);
	}
}
