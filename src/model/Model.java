package model;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements Observer{
	
	ArrayList<Conversation> conversations;
	public String myName = "Marcus";
	public String myColor = "#ff0000";
	int number = 1;
	
	
	
	/**Containes all conversations and data
	 */
	public Model() {
		conversations = new ArrayList<Conversation>();
	}
	
	
	/**Close all connections 
	 */
	public void exit() {
		Conversation c = null;
		while((c = getConversation()) != null) {
			this.removeConversation(c.getName());
		}
	}
	
	/** create new conversation from a connected socket
	 * @param s
	 * connected socket
	 * @param conName 
	 */
	public void AddConversation(Socket s, String request) {
		Conversation c = new Conversation(this, s,"chatt"+ (number ++));
		//Thread t = new Thread(c);
		//t.start();
		conversations.add(c);
		setChanged();
		notifyObservers();
	}
	
	/** create new conversation from ip and port
	 * @param ip
	 * ipAddress
	 * @param port
	 * port
	 * @param name
	 * name of conversation
	 */
	public void AddConversation(String ip, int port, String name) {
		try {
			Conversation c = new Conversation(this, ip, port,"chatt"+ (number ++));
			//Thread t = new Thread(c);
			//t.start();
			conversations.add(c);
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	public Conversation getConversation(){
		if(conversations.isEmpty())
			return null;
		return conversations.get(conversations.size()-1);
	}
	
	/** remove conversation
	 * @param name
	 * name of conversation, might change
	 */
	public void removeConversation(String name) {
		System.out.println("conversations.size"+conversations.size());
		 for (int i = 0; i<conversations.size(); i++ ){
			 System.out.println("conversations.get(i).getName(): " + conversations.get(i).getName()+ "\nName: " + name);
			 if (conversations.get(i).getName().equals(name)){
				 conversations.get(i).disConnect();
				 conversations.remove(i);
				 System.out.println("removed");
				 setChanged();
				 notifyObservers();
				 break;
				 
			 }
		
		 }
		 	
		 	
		
	}
	
	/**
	 * @return
	 * name
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * @return
	 * color as a string
	 */
	public String getColor() {
		return myColor;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO something. this method will be called when a conversation recives a message
	}

	/** Get all conversations
	 * @return
	 * conversations
	 */
	public ArrayList<Conversation> getConversations() {
		return conversations;
	}
	
}
