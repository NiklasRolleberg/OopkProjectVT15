package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class AddConversationView {
	
	JFrame frame;
	JPanel container;
	JButton okButton;
	JTextField nameField;
	JTextField ipField;
	JTextField portField;
	
	/**This window lets you add another conversation
	 */
	public AddConversationView(Controller controller) {
		frame = new JFrame();
		container = new JPanel();
		nameField = new JTextField("nameField");
		ipField = new JTextField("ipField");
		portField = new JTextField("portField");
		
		okButton = new JButton("OK");
		
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		JPanel c1 = new JPanel();
		JPanel c2 = new JPanel();
		JPanel c3 = new JPanel();
		JPanel c4 = new JPanel();
		c1.setLayout(new BorderLayout());
		c2.setLayout(new BorderLayout());
		c3.setLayout(new BorderLayout());
		c4.setLayout(new BorderLayout());
		
		
		JTextField t1 = new JTextField("Name");
		JTextField t2 = new JTextField("IP");
		JTextField t3 = new JTextField("port");
		
		c1.add(t1,BorderLayout.WEST);
		c1.add(nameField,BorderLayout.CENTER);
		container.add(c1);
		
		c2.add(t2,BorderLayout.WEST);
		c2.add(ipField,BorderLayout.CENTER);
		container.add(c2);
		
		c3.add(t3,BorderLayout.WEST);
		c3.add(portField,BorderLayout.CENTER);
		container.add(portField);
		
		c4.add(okButton);
		container.add(c4);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(container);
		frame.pack();
		frame.setVisible(true);
	}
}
