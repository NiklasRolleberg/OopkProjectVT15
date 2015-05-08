package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import view.AddConversationView;
import view.ConversationView;
import view.MainView;
import view.SettingsView;
import model.Conversation;
import model.Model;
import model.Server;

public class Controller implements Observer, ActionListener{
	
	Model model;
	Server server;
	MainView mainView;
	
	public Controller() {
		model = new Model();
		mainView = new MainView(this);
		//ConversationView cv = new ConversationView();
		
		//testsaker
		String ip = "130.229.162.162";
		int port = 4444;
		model.AddConversation(ip, port, "Test");
		Conversation c = model.getConversation();
		ConversationView cv = new ConversationView();
		c.setView(cv);
		cv.setConversation(c);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		//create newCOnversationView
		if(arg0.getActionCommand() == "New conversation")
		{
			AddConversationView acvm = new AddConversationView(this);
		}
		
		else if(arg0.getActionCommand() == "Settings") {
			SettingsView sv = new SettingsView(this);
		}
		
		else {
			ConversationView cv = new ConversationView();
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	
	public static void main(String[] args) {
		System.out.println("JAG LEVER idag!");
		Controller c = new Controller();
	}
}
