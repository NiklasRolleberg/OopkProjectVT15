package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import view.ConversationView;
import view.MainView;
import model.Model;
import model.Server;

public class Controller implements Observer, ActionListener{
	
	Model model;
	Server server;
	MainView mainView;
	
	public Controller() {
		model = new Model();
		mainView = new MainView();
		ConversationView cv = new ConversationView();
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
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
