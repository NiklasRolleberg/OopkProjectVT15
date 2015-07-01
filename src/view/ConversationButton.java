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
	
	
	
	
	
	public ConversationButton(Model model, String name){
		super(name);
		this.model = model;
		removeButton = new JButton("X");
		removeButton.addActionListener(this);
		removeButton.setSize(1, 1);
		endPanel = new JPanel();
		
		//endPanel.setSize(20, 20);
		endPanel.setLayout(new BorderLayout());
		endPanel.add(removeButton,BorderLayout.EAST);
		this.add(endPanel);
		
		
	}
	
	
	
	public ConversationButton(String name){
		super(name);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("jaha");
	
	}
	
	
	
	
	
	
	
	
	
	
}
