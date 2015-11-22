package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class SettingsView implements ActionListener  {
	
	JFrame frame;
	JPanel container;
	Controller controller;
	JTextField nameField;
	JTextField colorField;
	JTextField portField;
	JButton serverButton;
	JButton okButton;
	
	/** In this window you can change your name and color
	 */
	public SettingsView(Controller controller,boolean serverOn ) {
		this.controller = controller;
		
		frame = new JFrame();
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		nameField = new JTextField(controller.model.myName);
		colorField = new JTextField(controller.model.myColor);
		portField = new JTextField("");
		//colorField.setBackground(Color.GREEN);
		serverButton = new JButton("Start Server");
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		serverButton.addActionListener(this);
		serverButton.setEnabled(!serverOn);
		portField.setEditable(!serverOn);
		
		container.add(new JLabel("name"));
		container.add(nameField);
		container.add(new JLabel("color"));
		container.add(colorField);
		container.add(new JLabel("Port"));
		container.add(portField);
		JPanel c0 = new JPanel();
		c0.setLayout(new BorderLayout());
		c0.add(okButton,BorderLayout.CENTER);
		c0.add(serverButton,BorderLayout.NORTH);
		container.add(c0);
		
		
		
		frame.add(container);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getActionCommand() == "Start Server")
		{
			serverButton.setEnabled(false);
			int i = Integer.parseInt(portField.getText());
			controller.startServer(i);
			portField.setEditable(false);
		}
		else if (arg0.getActionCommand() == "OK")
		{
			controller.model.myColor = colorField.getText();
			controller.model.myName = nameField.getText();
			frame.setVisible(false);
		}
		
	}
}
