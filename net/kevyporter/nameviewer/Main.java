package net.kevyporter.nameviewer;

import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static JFrame frame;
	public static TextArea text;
	public static JLabel label;
	
	public static void main(String[] args) {

		Layout layout = new Layout();
		
		frame = layout.createFrame();
		text = layout.createField();
		
		layout.viewName(frame);
		
		frame.add(text);
		
		frame.setVisible(true);
		
	}

}
