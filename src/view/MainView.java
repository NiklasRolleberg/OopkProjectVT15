package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Conversation;
import model.Model;
import controller.Controller;

public class MainView extends Observable implements  Observer, ActionListener {

	JFrame frame;
	JPanel container;
	JPanel conversationList;
	JPanel buttonContainer;
	JScrollPane scroll;
	JButton newConversationButton;
	JButton settingsButton;
	Controller controller;
	Model model;
	
	/**
	 * This view shows all active conversations in a list and buttons for creating new conversations and changing settings. 
	 */
	public MainView(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(220,500));
		frame.setResizable(true);
		
		frame.addWindowListener(controller);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		conversationList = new JPanel(new GridBagLayout());
		
		//conversationList.setLayout(new BoxLayout(conversationList, BoxLayout.PAGE_AXIS));
		/*
		for(int i=0;i< 30;i++) {
			JButton conversation = new JButton("Conversation "+(i+1));
			conversation.setBackground(Color.WHITE);
			conversation.setBorderPainted(true);
			conversation.addActionListener(controller);
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.add(conversation,BorderLayout.CENTER);
			conversationList.add(temp);
		}*/
		
		
		scroll = new JScrollPane(conversationList);		
		
		newConversationButton = new JButton("New conversation");
		settingsButton =  new JButton("Settings");
		
		newConversationButton.addActionListener(controller);
		settingsButton.addActionListener(controller);
		
		buttonContainer = new JPanel();
		buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.LINE_AXIS));
		buttonContainer.add(newConversationButton);
		buttonContainer.add(settingsButton);
		
		JPanel temp = new JPanel();
		temp.add(scroll);
		container.add(temp,BorderLayout.CENTER);
		//container.add(scroll, BorderLayout.CENTER);
		container.add(buttonContainer, BorderLayout.SOUTH);
		
		
		frame.add(container);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println("MainView created");
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Something changed in model");
		System.out.println("Udpading buttons");
		System.out.println("Conversations.size: " + model.getConversations().size());
		 int i=0;
		 conversationList.removeAll();
		for(Conversation c: model.getConversations()) {
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			boolean b = true;
			for (ActionListener a: c.conversationButton.getActionListeners()){
				if (a.equals(this)) 
					b= false;

				
							
			}
			if (b){
				c.conversationButton.addActionListener(this);
			}
			c.conversationButton.setPreferredSize(new Dimension(190,50));
			temp.add(c.conversationButton,BorderLayout.CENTER);
			//conversationList.add(temp); 

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=i;
			
			conversationList.add(temp,gbc);
			i++;
		}
		scroll.validate();
		
		frame.revalidate();
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for (Conversation c: model.getConversations()){
			if (c.conversationButton.getText() == arg0.getActionCommand()){
				this.setChanged();
				this.notifyObservers(c);
			}
			
		}
	
		
	}

}
