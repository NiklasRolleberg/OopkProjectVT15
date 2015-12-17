package view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Model;
import model.Server;


public class ConversationButton extends JButton implements ActionListener  {

	
	
	JButton removeButton;
	JPanel endPanel;
	Model model;
	String name;
	
	
	
	/**
	 * let you have conversationbuttons in the mainview
	 */
	
	public ConversationButton(Model model, String name){
		super(name);
		this.model = model;
		removeButton = new JButton("X");
		removeButton.addActionListener(this);
		removeButton.setSize(1, 1);
		endPanel = new JPanel();
		this.name = name;
	
		//endPanel.setSize(20, 20);
		endPanel.setLayout(new BorderLayout());
		endPanel.add(removeButton,BorderLayout.EAST);
		endPanel.add(new JLabel(name),BorderLayout.CENTER);
		this.add(endPanel);
		
		
	}
	
	/**
	 * calling the super construct
	 * @param name
	 */
	
	public ConversationButton(String name){
		super(name);
		
	}

	/**
	 * let you remove conversation with "X" button
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("actionPreformed");
		model.removeConversation(name);
		
	
	}


	
	
	
	
	
	
	
	
}
