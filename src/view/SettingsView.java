package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class SettingsView {
	
	JFrame frame;
	JPanel container;
	
	JTextField nameField;
	JTextField colorField;
	JButton okButton;
	
	/** In this window you can change your name and color
	 */
	public SettingsView(Controller controller) {
		
		frame = new JFrame();
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		nameField = new JTextField("nameField");
		colorField = new JTextField("ColorField");
		colorField.setBackground(Color.GREEN);
		okButton = new JButton("OK");
		
		container.add(new JLabel("name"));
		container.add(nameField);
		container.add(new JLabel("color"));
		container.add(colorField);
		JPanel c0 = new JPanel();
		c0.setLayout(new BorderLayout());
		c0.add(okButton);
		container.add(c0);
		
		frame.add(container);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
}
