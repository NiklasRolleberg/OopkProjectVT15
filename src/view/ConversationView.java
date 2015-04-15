package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ConversationView {
	
	JButton myButtonsend;
	JButton myButtonadd;
	JButton myButtonremove;
	
	JFrame myFrame;
	
	JPanel myPanel;
	JPanel myContainer;
	JPanel myContainerchat;
	JPanel myContainerbutton;
	
	JTextField myTextchat;
	JTextField myTextwrite;
	
	
	
	public ConversationView() {
		myTextchat = new JTextField();
		myTextwrite = new JTextField();
		
		
		myPanel = new JPanel();
		myPanel.setLayout(new BorderLayout());
		
		myContainer = new JPanel();
		myContainer.setLayout(new BorderLayout());
		myContainerchat = new JPanel();
		myContainerchat.setLayout(new BorderLayout());
		myContainerbutton = new JPanel();
		myContainerbutton.setLayout(new BorderLayout());
		
		
		myButtonsend = new JButton("Send");
		myButtonremove = new JButton("Remove");
		myButtonadd = new JButton("Add");
		
		myFrame = new JFrame();
		myFrame.setPreferredSize(new Dimension(400,300));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.pack();
		
		// TODO Lägg till actionlistener knapp
		
		myFrame.add(myPanel);
		myPanel.add(myContainer);
		myContainer.add(myContainerchat);
		myContainerchat.add(myContainerbutton);
		
		
		myContainer.add(myTextchat,BorderLayout.CENTER);
		myContainer.add(myContainerchat,BorderLayout.SOUTH);
		myContainerchat.add(myTextwrite,BorderLayout.CENTER);
		myContainerchat.add(myButtonsend,BorderLayout.EAST);
		myContainerchat.add(myContainerbutton,BorderLayout.WEST);
		myContainerbutton.add(myButtonadd,BorderLayout.NORTH);
		myContainerbutton.add(myButtonremove,BorderLayout.SOUTH);
		
		
		
		
	}

	
}
