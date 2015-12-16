package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;

public class AddConversationView  implements ActionListener {
	
	JFrame frame;
	JPanel container;
	JButton okButton;
	JTextField requestField;
	JTextField ipField;
	JTextField portField;
	Controller controller;
	
	/**This window lets you add another conversation
	 */
	public AddConversationView(Controller controller) {
		this.controller = controller;
		frame = new JFrame();
		container = new JPanel();
		requestField = new JTextField("Vill du prata med mig?");
		ipField = new JTextField("192.168.1.100");
		portField = new JTextField("4444");
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
				
		JTextArea name = new JTextArea("Request");
		name.setEditable(false);
		name.setBackground(Color.GRAY.brighter());
		container.add(name);
		container.add(requestField);
		
		JTextArea ip = new JTextArea("ip");
		ip.setEditable(false);
		ip.setBackground(Color.GRAY.brighter());
		container.add(ip);
		container.add(ipField);
		
		JTextArea port = new JTextArea("port");
		port.setBackground(Color.GRAY.brighter());
		port.setEditable(false);
		container.add(port);
		container.add(portField);
		
		JPanel c1 = new JPanel();
		c1.setLayout(new BorderLayout());
		c1.add(okButton);
		container.add(c1);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(200,200));
		frame.add(container);
		frame.pack();
		frame.setVisible(true);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String request = requestField.getText();
		String myIp = ipField.getText();
		int myPort = Integer.parseInt( portField.getText());
		frame.setVisible(false);
		controller.addConverstion(request, myIp, myPort);
		
		
	}
}
