package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Controller;

public class MainView {

	JFrame frame;
	JPanel container;
	JPanel conversationList;
	JPanel buttonContainer;
	JScrollPane scroll;
	JButton newConversationButton;
	JButton settingsButton;
	Controller controller;
	
	/**
	 * This view shows all active conversations in a list and buttons for creating new conversations and changing settings. 
	 */
	public MainView(Controller controller) {
		this.controller = controller;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(220,500));
		frame.setResizable(false);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		conversationList = new JPanel();
		conversationList.setLayout(new BoxLayout(conversationList, BoxLayout.PAGE_AXIS));
		
		for(int i=0;i< 30;i++) {
			JButton conversation = new JButton("Conversation "+(i+1));
			conversation.setBackground(Color.WHITE);
			conversation.setBorderPainted(true);
			conversation.addActionListener(controller);
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.add(conversation,BorderLayout.CENTER);
			conversationList.add(temp);
		}
		
		
		scroll = new JScrollPane(conversationList);		
		
		newConversationButton = new JButton("New conversation");
		settingsButton =  new JButton("Settings");
		
		buttonContainer = new JPanel();
		buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.LINE_AXIS));
		buttonContainer.add(newConversationButton);
		buttonContainer.add(settingsButton);
		
		container.add(scroll, BorderLayout.CENTER);
		container.add(buttonContainer, BorderLayout.SOUTH);
		
		
		frame.add(container);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println("MainView created");
		
	}

}
