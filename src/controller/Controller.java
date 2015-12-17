package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import view.AddConversationView;
import view.ConversationView;
import view.MainView;
import view.SettingsView;
import model.Conversation;
import model.Model;
import model.Server;

public class Controller implements Observer, ActionListener, WindowListener{
	
	public Model model;
	Server server;
	MainView mainView;
	
	
	public Controller() {
		model = new Model();
		mainView = new MainView(this, model);
		model.addObserver(mainView);
		mainView.addObserver(this);
		
		//ConversationView cv = new ConversationView();
		
		//testsaker
		/*
		String ip = "130.229.162.162";
		int port = 4444;
		model.AddConversation(ip, port, "Test");
		Conversation c = model.getConversation();
		ConversationView cv = new ConversationView();
		c.setView(cv);
		cv.setConversation(c)
		*/
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		//create newCOnversationView
		if(arg0.getActionCommand() == "New conversation")
		{
			AddConversationView acvm = new AddConversationView(this);
		}
		
		else if(arg0.getActionCommand() == "Settings") {
			SettingsView sv = new SettingsView(this,server!=null);
		}

		
	}
	/**
	 * add new conversation
	 * @param conName
	 * conversation name
	 * @param ip
	 * ip address
	 * @param port
	 * port
	 */
	public void addConverstion(String request,String ip,int port){
		model.AddConversation(ip, port, "");
		Conversation c = model.getConversation();
		ConversationView cv = new ConversationView();
		c.setView(cv);
		cv.setConversation(c);
		c.sendRawMessage("<request>" + request + "</request>");
	}
	
	/**'
	 * add new conversation
	 * @param conName
	 * conversation name
	 * @param 
	 * socket
	 */
	public void addConverstion(String conName, Socket socket){
		model.AddConversation(socket, conName);
		Conversation c = model.getConversation();
		ConversationView cv = new ConversationView();
		c.setView(cv);
		cv.setConversation(c);
	}
	
	public void startServer (int port){
		server = new Server(port , this);
		Thread t = new Thread(server);
		t.start();
		
	}
	
	public void exit() {
		model.exit();
		System.exit(0);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg0 == mainView){

			if (arg1 instanceof Conversation){
				Conversation c = (Conversation)arg1;
				c.cv.setVisible(true);
			}
		}
	}

	
	public static void main(String[] args) {
		//String message  = "<message sender="+ '"' + "Niklas" + '"' + "><text color=" + '"' + "#RRGGBB" + '"' + "> hejsan </text>";

		System.out.println("JAG LEVER idag!");
		Controller c = new Controller();
	}


	@Override
	public void windowActivated(WindowEvent arg0) {}


	@Override
	public void windowClosed(WindowEvent arg0) {}


	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("CLOSING!");
		this.exit();	
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {}


	@Override
	public void windowDeiconified(WindowEvent arg0) {}


	@Override
	public void windowIconified(WindowEvent arg0) {}


	@Override
	public void windowOpened(WindowEvent arg0) {}
}
