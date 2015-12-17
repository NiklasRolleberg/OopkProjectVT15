package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.awt.Button;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;

import view.ConversationButton;
import view.ConversationView;


public class Conversation extends Observable {

	public ArrayList<Connection> connections;
	public ConversationView cv;
    String chatHistory="<html><head><title></title></head><body bgcolor= '#FFFFFFF'> ";
    Model model;
    Scanner scanner;
    StringBuffer sb1;
    public ConversationButton conversationButton;
    String name;

	
	/**
	 * create conversation from ip and port
	 * @param ipIn
	 * ip address
	 * @param portIn
	 * port number
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	Conversation(Model model, String ipIn, int portIn, String name) throws UnknownHostException, IOException{
		this(model, new Socket(ipIn,portIn), name);
	}
	
	/**
	 * create conversation from connected socket
	 * @param socketIn connected socket
	 */
	Conversation(Model model, Socket socketIn,String name) {
		this.model = model;
		this.name = name;
		connections = new ArrayList<Connection>(); 
		connections.add(new Connection(this, socketIn));
		conversationButton = new ConversationButton(model,name);
		conversationButton.setBackground(Color.WHITE);
		conversationButton.setBorderPainted(true);	
	}
	
	/**
	 * Set the view object to show the messages
	 * @param v
	 * View object
	 */
	public void setView(ConversationView v) {
		cv=v;
	}
	
	/**
	 * Send a message to all connections
	 * @param s
	 * message to be sent
	 */
	public void sendMessage(String s) {
		//System.out.println("sending message");
		StringBuilder t = new StringBuilder(s);
		for (int i=0; i<t.length(); i++) 
		{
			if (t.charAt(i) == '<') {
				t.deleteCharAt(i);
				t.insert(i, "&lt;");
			}
			if (t.charAt(i) == '>'){
				t.deleteCharAt(i);
				t.insert(i, "&gt;");	
			}
			s = t.toString();
		}	
			

		for(Connection c:connections)
			c.send("<message sender=" + '"' + model.getName() + '"' + "> <text color="+'"'+ model.getColor()+'"'+"> "+s+"</text> </message>");
		
		chatHistory += "<p style='font-family:arial;color:" + model.getColor() + ";font-size:10px;'>"+ model.getName() +": "+ s+"</p>";
		System.out.println(model.getColor());
		cv.updateDisplay(chatHistory);
		System.out.println("hej");
	}
	
	/**
	 * Get name of conversation
	 * @return
	 * name of converation
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Close conversation and disconnect
	 */
	public void disConnect() {
		cv.setVisible(false);
		for(int i=0; i<connections.size(); i++){
			connections.get(i).close();
		}	
	}
	
	/**
	 * Add a new connection to the conversation 
	 * @param request
	 * Request to be sent
	 * @param ip
	 * IP-address
	 * @param port
	 * port
	 */
	public void addConnection(String request, String ip, int port ){
		Socket s;
		try {
			s = new Socket(ip,port);
			Connection c = new Connection( this,s);
			connections.add(c);
			c.send("<request>"+request+"</request>");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove a connection from the conversation
	 * @param c
	 * connection to be removed
	 */
	public void removeConnection(Connection c){
		connections.remove(c);
		c.close();
	}
	
	/**
	 * Send a raw message to all connections, this method allows you to send '<' and '>'
	 * @param message
	 * String to be sent
	 */
	public void sendRawMessage(String message){
		for (Connection c:connections){
	 			c.send(message);
		}
	}
	
	/**
	 * Decode a received message and distribute it among the connections
	 * @param message
	 * message received
	 * @param receiver
	 * connection that resceived the message
	 */
	void receive(String message,Connection receiver) {
		 System.out.println("echo: " + message);
	 	if(!message.contains("<request>")) {
		 	for (Connection c:connections) {
		 		if (c != receiver){
		 			c.send(message + "</message>");
		 		}
		 	}
	 	}
	 	
	    String name="";
	    String color="";
	    String msg="";
	    
    	if (message.contains("<disconnect/>")){
    		if (name == ""){
    			name = "Någon";
    		}
    		chatHistory += "<p style='font-family:arial;color:#ff0000;font-size:20px;'>"+ name + " har lämnat konversatinen" + "</p>";
    		cv.updateDisplay(chatHistory); 
			return;
    	}
    	
    	if (message.contains("<request>")) {
    		int s = message.indexOf("<request>");
    		s+=9;
    		chatHistory += "<p style='font-family:arial;color:#ff0000;font-size:20px;'>"+message.substring(s)+"</p>";
			cv.updateDisplay(chatHistory); 
			return;
    	}
    
    	if (message.contains("sender="))  {
    		int k;
    		k = message.indexOf("sender=");
    		k = k+8;
		  
    		name = message.substring(k);
    		int l = name.indexOf('"');
    		name = name.substring(0, l);
    		System.out.println(name);
    		receiver.setName(name);;
		}
		if (message.contains("color=")){
			int k;
			k = message.indexOf("color=")+1;
			k = k+7;
			color = message.substring(k);
			int l = color.indexOf('>');
			color = color.substring(0, l-1);
			System.out.println(color);
		
		}
		if (message.contains("color=")){
			int k;
			k = message.indexOf("color=");
			k = k+16;
			msg = message.substring(k);
			int l = msg.indexOf("</");
			msg = msg.substring(0, l);
			System.out.println(msg);
		}
		
		chatHistory += "<p style='font-family:arial;color:" + color + ";font-size:10px;'>"+ name +": "+ msg +"</p>";	
		cv.updateDisplay(chatHistory);   
	}
}
