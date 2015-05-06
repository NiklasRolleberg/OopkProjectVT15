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
	String myName;
	Color myColor;
	
	
	
	/**Containes all conversations and data
	 */
	public Model() {
		conversations = new ArrayList<Conversation>();
	}
	
	/** create new conversation from a connected socket
	 * @param s
	 * connected socket
	 */
	public void AddConversation(Socket s) {
		// TODO Create conversation object and add to list
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
			Conversation c = new Conversation(ip, port);
			Thread t = new Thread(c);
			t.start();
			conversations.add(c);
		} catch (IOException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	/** remove conversation
	 * @param name
	 * name of conversation, might change
	 */
	public void removeConversation(String name) {
		//TODO close and remove the conversation
	}
	
	

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO something. this method will be called when a conversation recives a message
	}
	
}
