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


public class Conversation extends Observable  {

	//ArrayList<Socket> connections;
	public ArrayList<Connection> connections;
	public ConversationView cv;
	//PrintWriter out = null;
    //BufferedReader in = null;
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
	Conversation(Model model, Socket socketIn,String name){
		this.model = model;
		this.name = name;
		connections = new ArrayList<Connection>(); 
		connections.add(new Connection(this, socketIn));
		conversationButton = new ConversationButton(model,name);
		conversationButton.setBackground(Color.WHITE);
		conversationButton.setBorderPainted(true);
	

		
	}
	
	public void setView(ConversationView v){
		cv=v;
	}
	
	public void sendMessage(String s){
		System.out.println("sending message");
		//out.print("<message sender=" + '"' + model.getName() + '"' + "> <text color="+ model.getColor() +"> "+s+"</text> </message>");
		//out.flush();
		for(Connection c:connections)
			c.send("<message sender=" + '"' + model.getName() + '"' + "> <text color="+ model.getColor() +"> "+s+"</text> </message>");
		
		chatHistory += "<p style='font-family:arial;color:" + model.getColor() + ";font-size:10px;'>"+ model.getName() +": "+ s+"</p>";
		System.out.println(model.getColor());
		cv.updateDisplay(chatHistory);
		System.out.println("hej");
	}
	
	public String getName(){
		return name;
	}
	
	public void disConnect(){
		cv.setVisible(false);
		//out.print("<message> <disconnect/></message>");
		//out.flush();
		for(int i=0; i<connections.size(); i++){
			connections.get(i).close();
		}	
	}
	
	
	public void addConnection(String ip, int port ){
		Socket s;
		try {
			s = new Socket(ip,port);
			connections.add(new Connection( this,s));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeConnection(Connection c){
		connections.remove(c);
		c.close();
	}
	
	
	
	void receive(String message,Connection receiver){
		 System.out.println("echo: " + message);
		    //chatHistory += message + "\n";
		 	for (Connection c:connections){
		 		if (c != receiver){
		 			c.send(message + "</message>");
		 		}
		 	}
		    String name="";
		    String color="";
		    String msg="";
		    
		    	if (message.contains("<disconnect/>")){
		    		
		    		chatHistory += "<p style='font-family:arial;color:#ff0000;font-size:20px;'>blö</p>";
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
					k = message.indexOf("color=");
					k = k+7;
					color = message.substring(k);
					int l = color.indexOf('>');
					color = color.substring(0, l);
					System.out.println(color);
				
				}
				if (message.contains("color=")){
					int k;
					k = message.indexOf("color=");
					k = k+14;
					msg = message.substring(k);
					int l = msg.indexOf("</");
					msg = msg.substring(0, l);
					System.out.println(msg);
				}
				/*chatHistory += "<p style='font-family:arial;color:"+color
						+";font-size:10px;'>"+name+": "+msg+"</p>";*/
				
				chatHistory += "<p style='font-family:arial;color:" + color + ";font-size:10px;'>"+ name +": "+ msg +"</p>";
				
				
			   cv.updateDisplay(chatHistory);   
	}
}
