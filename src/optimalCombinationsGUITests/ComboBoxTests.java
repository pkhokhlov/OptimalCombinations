package optimalCombinationsGUITests;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import optimalcombinations.Unit;


public class ComboBoxTests
{
	public static void main(String[] args)
	{
		JFrame comboBoxFrame = new JFrame();

		final JComboBox<String> bookList = new JComboBox<String>();
		bookList.addItem("Effective Java");
		bookList.addItem("Head First Java");
		bookList.addItem("Thinking in Java");
		bookList.addItem("Java for Dummies");
		bookList.remove(0);

		// add to the parent container (e.g. a JFrame):
		
		comboBoxFrame.setLayout(new BorderLayout());
		comboBoxFrame.add(bookList, BorderLayout.WEST);
		JButton ok = new JButton();
		ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String selectedBook = (String) bookList.getSelectedItem();
				System.out.println("You seleted the book: " + selectedBook);
			}
			
		});
		comboBoxFrame.add(ok, BorderLayout.SOUTH);
		comboBoxFrame.pack();
		comboBoxFrame.setVisible(true);

		// get the selected item:
		
		
		Unit A = new Unit("A");
		Unit B = new Unit("B");
		Unit C = new Unit("C");
		Unit D = new Unit("D");
		Unit E = new Unit("E");
		Unit F = new Unit("F");
		Unit G = new Unit("G");
		Unit H = new Unit("H");
		Unit I = new Unit("I"); // 9
		
		JComboBox<Unit> UnitBox = new JComboBox<Unit>();
		UnitBox.addItem(A);
		
	}
}
