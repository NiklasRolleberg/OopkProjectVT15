package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class ConversationView implements ActionListener  {
	
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
		
		myButtonadd.addActionListener(this);
		myButtonremove.addActionListener(this);
		
		myFrame = new JFrame();
		myFrame.setPreferredSize(new Dimension(400,300));
		myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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



	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand() == "Add"){
		JFrame myFrame2 = new JFrame();
		myFrame2 = new JFrame();
		myFrame2.setPreferredSize(new Dimension(200,200));
		myFrame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		myFrame2.setVisible(true);
		myFrame2.pack();
		
		JButton b1 = new JButton("Ok");
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		
		JTextField t1 = new JTextField("110.1156.625.1");
		JTextField t2 = new JTextField("6666");
		
		JLabel l1 = new JLabel("Ip-address"); 
		JLabel l2 = new JLabel("Port");
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(b1);
		
		myFrame2.add(p1);
	}
	if (e.getActionCommand() == "Remove"){
		JFrame myFrame3 = new JFrame();
		myFrame3 = new JFrame();
		myFrame3.setPreferredSize(new Dimension(200,200));
		myFrame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		myFrame3.setVisible(true);
		myFrame3.pack();
		
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
		JButton b1 = new JButton("Remove");
		myFrame3.add(p1);
		
		for(int i=0;i< 30;i++) {
			JToggleButton conversation = new JToggleButton("Conversation "+(i+1));
			conversation.setBackground(Color.WHITE);
			conversation.setBorderPainted(true);
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.add(conversation,BorderLayout.CENTER);
			p1.add(temp);
		}
		p1.add(b1);
	}
	}

	
}
