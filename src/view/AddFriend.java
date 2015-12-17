package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Conversation;

public class AddFriend implements ActionListener  {

	Conversation conversation;
	JTextField t0;
	JTextField t1;
	JTextField t2;
	JFrame myFrame2;
	
	/**
	 * The add conversation window with textfield and labels and an ok button
	 */
	
	public AddFriend(Conversation addConversation){
		conversation = addConversation;
		myFrame2 = new JFrame();
		myFrame2.setPreferredSize(new Dimension(200,200));
		myFrame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		myFrame2.setVisible(true);
		myFrame2.pack();
		
		JButton b1 = new JButton("Ok");
		b1.addActionListener(this);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		
		t0 = new JTextField("Vill du vara med?");
		t1 = new JTextField("110.1156.625.1");
		t2 = new JTextField("6666");
		
		JLabel l0 = new JLabel("Request"); 
		JLabel l1 = new JLabel("Ip-address"); 
		JLabel l2 = new JLabel("Port");
		
		p1.add(l0);
		p1.add(t0);
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(b1);
		
		myFrame2.add(p1);
		myFrame2.setVisible(true);
		
	}
	
	/**
	 * sending request to be added to the added person
	 */
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "Ok"){
			myFrame2.setVisible(false);
			conversation.addConnection(t0.getText(), t1.getText(),Integer.parseInt(t2.getText()));
		}
		

		
	}

}
