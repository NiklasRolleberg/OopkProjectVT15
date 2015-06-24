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
	String myName = "Marcus";
	String myColor = "#0f0f0f";
	int number = 1;
	
	
	
	/**Containes all conversations and data
	 */
	public Model() {
		conversations = new ArrayList<Conversation>();
	}
	
	/** create new conversation from a connected socket
	 * @param s
	 * connected socket
	 * @param conName 
	 */
	public void AddConversation(Socket s, String conName) {
		Conversation c = new Conversation(this, s,"chatt"+ (number ++));
		Thread t = new Thread(c);
		t.start();
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
			Thread t = new Thread(c);
			t.start();
			conversations.add(c);
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	public Conversation getConversation(){
		return conversations.get(conversations.size()-1);
	}
	
	/** remove conversation
	 * @param name
	 * name of conversation, might change
	 */
	public void removeConversation(String name) {
		//TODO close and remove the conversation
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
