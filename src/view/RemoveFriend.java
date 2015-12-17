package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import model.Connection;
import model.Conversation;

public class RemoveFriend implements ActionListener {
	
	Conversation c;
	ArrayList<JToggleButton> buttons;
	JFrame myFrame3;
	
	/**
	 * Let you remove a person from the conversation chat.
	 * Has a own list window to chose which person to remove from chat
	 * @param c
	 */
	public RemoveFriend(Conversation c){
		this.c = c;
		myFrame3 = new JFrame();
		myFrame3 = new JFrame();
		myFrame3.setPreferredSize(new Dimension(200,200));
		myFrame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		myFrame3.setVisible(true);
		myFrame3.pack();
		
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
		JButton b1 = new JButton("Remove");
		myFrame3.add(p1);
		b1.addActionListener(this);
		
		buttons = new ArrayList<JToggleButton>();
		
		for(int i=0;i< c.connections.size();i++) {
			JToggleButton conversation = new JToggleButton(c.connections.get(i).getName());
			conversation.setBackground(Color.WHITE);
			conversation.setBorderPainted(true);
			buttons.add(conversation);
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.add(conversation,BorderLayout.CENTER);
			p1.add(temp);
		}
		p1.add(b1);
	}

	/**
	 * Open the list with all the connected persons to the conversation.
	 * Has a select function and removes from list when clicking on remove.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "Remove"){
			myFrame3.setVisible(false);
			ArrayList<Connection> toRemove = new ArrayList<Connection>();
			for(int i=0;i<buttons.size() && i<c.connections.size();i++)
			{
				if(buttons.get(i).isSelected()){
					toRemove.add(c.connections.get(i));
				}
			}
			for (int i = 0; i<toRemove.size(); i++){
				c.removeConnection(toRemove.get(i));
			}
		}
		
	}
}
