package net.kevyporter.nameviewer;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Layout {

	protected JFrame createFrame(){
		JFrame frame = new JFrame("Name Viewer");

		int sizeX = 325; 
		int sizeY = 520;

		frame.setDefaultCloseOperation(3);
		frame.setBounds(new Rectangle(sizeX, sizeY));
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);

		return frame;
	}

	protected void viewName(JFrame frame){
		JLabel credit = new JLabel("By KevyPorter v1.0");
		credit.setBounds(105, 10, 150, 14);
		credit.setForeground(Color.BLACK);
		credit.setVisible(true);
		
		JTextField field = new JTextField();
		field.setBounds(15, 33, 280, 30);
		field.setForeground(Color.BLUE);
		field.setVisible(true);

		final JButton button = new JButton("Search");
		button.setBounds(50, 73, 200, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (button.isEnabled()){
					new NameViewer().ViewNames(field);
				}
			}
		});
		button.setVisible(true);
		
		frame.add(credit);
		frame.add(button);
		frame.add(field);
	}
	
	protected TextArea createField()
	  {
	    TextArea text = new TextArea();
	    text.setBounds(10, 123, 290, 350);
	    text.setEditable(false);
	    text.setBackground(Color.GRAY);
	    text.setForeground(Color.BLACK);
	    text.setVisible(true);
	    
	    return text;
	  }

}
