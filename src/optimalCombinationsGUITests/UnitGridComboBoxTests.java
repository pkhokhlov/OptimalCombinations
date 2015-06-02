package optimalCombinationsGUITests;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import optimalcombinations.Unit;

public class UnitGridComboBoxTests
{
	public JFrame mainFrame_;
	public JLabel headerLabel_;
	public JLabel statusLabel1_;
	public JLabel statusLabel2_;
	public JLabel statusLabel3_;
	public JLabel statusLabel4_;
	public JPanel controlPanel_;

	public UnitGridComboBoxTests()
	{
		prepareGUI();
	}

	public static void main(String[] args)
	{
		UnitGridComboBoxTests unitGridComboBoxTests = new UnitGridComboBoxTests();
		unitGridComboBoxTests.showComboboxDemo();
	}

	public void prepareGUI()
	{
		mainFrame_ = new JFrame("Unit Connection Selection");
		mainFrame_.setSize(552, 365);
		mainFrame_.setLayout(new GridLayout(3, 1));
		mainFrame_.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				mainFrame_.dispose();
			}
		});
		headerLabel_ = new JLabel("", JLabel.CENTER);
		
		statusLabel1_ = new JLabel("Positive Connection 1: ", JLabel.CENTER);
		statusLabel1_.setSize(350, 75);

		statusLabel2_ = new JLabel("Positive Connection 2: ", JLabel.CENTER);
		statusLabel2_.setSize(350, 75);
		
		statusLabel3_ = new JLabel("Positive Connection 3: ", JLabel.CENTER);
		statusLabel3_.setSize(350, 75);
		
		statusLabel4_ = new JLabel("Negative Connection: ", JLabel.CENTER);
		statusLabel4_.setSize(350, 75);
		
		controlPanel_ = new JPanel();
		controlPanel_.setLayout(new BoxLayout(controlPanel_, BoxLayout.Y_AXIS));

		mainFrame_.add(headerLabel_);
		mainFrame_.add(controlPanel_);
		mainFrame_.add(statusLabel1_);
		mainFrame_.add(statusLabel2_);
		mainFrame_.add(statusLabel3_);
		mainFrame_.add(statusLabel4_);
		//mainFrame_.pack();
		mainFrame_.setVisible(true);
	}

	public void showComboboxDemo()
	{
		headerLabel_.setText("Control in action: JComboBox");
		
		Unit A = new Unit("A");
		Unit B = new Unit("B");
		Unit C = new Unit("C");
		Unit D = new Unit("D");
		Unit E = new Unit("E");
		Unit F = new Unit("F");
		Unit G = new Unit("G");
		Unit H = new Unit("H");
		Unit I = new Unit("I"); // 9
		
		final DefaultComboBoxModel<Unit> unitNames1 = new DefaultComboBoxModel<Unit>();
		unitNames1.addElement(A);
		unitNames1.addElement(B);
		unitNames1.addElement(C);
		unitNames1.addElement(D);
		unitNames1.addElement(E);
		unitNames1.addElement(F);
		unitNames1.addElement(G);
		unitNames1.addElement(H);
		unitNames1.addElement(I);
		
		final DefaultComboBoxModel<Unit> unitNames2 = new DefaultComboBoxModel<Unit>();
		unitNames2.addElement(A);
		unitNames2.addElement(B);
		unitNames2.addElement(C);
		unitNames2.addElement(D);
		unitNames2.addElement(E);
		unitNames2.addElement(F);
		unitNames2.addElement(G);
		unitNames2.addElement(H);
		unitNames2.addElement(I);
		
		final DefaultComboBoxModel<Unit> unitNames3 = new DefaultComboBoxModel<Unit>();
		unitNames3.addElement(A);
		unitNames3.addElement(B);
		unitNames3.addElement(C);
		unitNames3.addElement(D);
		unitNames3.addElement(E);
		unitNames3.addElement(F);
		unitNames3.addElement(G);
		unitNames3.addElement(H);
		unitNames3.addElement(I);
		
		final DefaultComboBoxModel<Unit> unitNames4 = new DefaultComboBoxModel<Unit>();
		unitNames4.addElement(A);
		unitNames4.addElement(B);
		unitNames4.addElement(C);
		unitNames4.addElement(D);
		unitNames4.addElement(E);
		unitNames4.addElement(F);
		unitNames4.addElement(G);
		unitNames4.addElement(H);
		unitNames4.addElement(I);

		final JComboBox<Unit> posConn1 = new JComboBox<Unit>(unitNames1);
		final JComboBox<Unit> posConn2 = new JComboBox<Unit>(unitNames2);
		final JComboBox<Unit> posConn3 = new JComboBox<Unit>(unitNames3);
		final JComboBox<Unit> negConn = new JComboBox<Unit>(unitNames4);

		posConn1.setSelectedIndex(0);
		posConn2.setSelectedIndex(0);
		posConn3.setSelectedIndex(0);
		negConn.setSelectedIndex(0);
		
		JScrollPane fruitListScrollPane1 = new JScrollPane(posConn1);
		JScrollPane fruitListScrollPane2 = new JScrollPane(posConn2);
		JScrollPane fruitListScrollPane3 = new JScrollPane(posConn3);
		JScrollPane fruitListScrollPane4 = new JScrollPane(negConn);

		JButton showButton = new JButton("Show");

		showButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String data1 = "";
				String data2 = "";
				String data3 = "";
				String data4 = "";
				
				//if (posConn1.getSelectedIndex() != -1 && posConn2.getSelectedIndex() != -1 && posConn3.getSelectedIndex() != -1)
				//{
					data1 =   "Positive Connection 1: "
							+ posConn1.getItemAt(posConn1.getSelectedIndex());
					data2 = "Positive Connection 2: "
							+ posConn2.getItemAt(posConn2.getSelectedIndex());
					data3 = "Positive Connection 3: "
							+ posConn3.getItemAt(posConn3.getSelectedIndex());
					data4 = "Negative Connection: "
							+ negConn.getItemAt(negConn.getSelectedIndex());
				//}
				statusLabel1_.setText(data1);
				statusLabel2_.setText(data2);
				statusLabel3_.setText(data3);
				statusLabel4_.setText(data4);
				System.out.println(mainFrame_.getSize() + "\n" + "hello");
			}
		});
		controlPanel_.add(fruitListScrollPane1);
		controlPanel_.add(fruitListScrollPane2);
		controlPanel_.add(fruitListScrollPane3);
		controlPanel_.add(fruitListScrollPane4);
		controlPanel_.add(showButton);
		mainFrame_.setVisible(true);
	}
}
