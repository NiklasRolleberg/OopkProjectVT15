package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import view.ConversationView;


public class Conversation extends Observable implements Runnable  {

	ArrayList<Socket> connections;
	ConversationView cv;
	PrintWriter out = null;
    BufferedReader in = null;
    String chatHistory="";
    Model model;
    Scanner scanner;
    StringBuffer sb1;

	
	/**
	 * create conversation from ip and port
	 * @param ipIn
	 * ip address
	 * @param portIn
	 * port number
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	Conversation(Model model, String ipIn, int portIn) throws UnknownHostException, IOException{
		this(model, new Socket(ipIn,portIn));
	}
	
	/**
	 * create conversation from connected socket
	 * @param socketIn connected socket
	 */
	Conversation(Model model, Socket socketIn){
		this.model = model;
		connections = new ArrayList<Socket>(); 
		connections.add(socketIn);
		
	}
	
	public void setView(ConversationView v){
		cv=v;
	}
	
	public void sendMessage(String s){
		System.out.println("sending message");
		out.print("<message sender=" + '"' + model.getName() + '"' + "> <text color=#00ff00> "+s+"</text> </message>");
		out.flush();
		chatHistory += "me " + s + "\n";
		cv.updateDisplay(chatHistory);
	}
	
	
	private void receive(String message){
		 System.out.println("echo: " + message);
		    //chatHistory += message + "\n";
		    
		    
			   if (message.contains("sender="))  {
				   int k;
				   k = message.indexOf("sender=");
				   k = k+8;
				   
				  String name = message.substring(k);
				  int l = name.indexOf('"');
				    name = name.substring(0, l);
				   System.out.println(name);
				chatHistory += name;
			   }
				if (message.contains("color=")){
					int k;
					k = message.indexOf("color=");
					k = k+7;
					String color = message.substring(k);
					int l = color.indexOf('>');
					color = color.substring(0, l);
					System.out.println(color);
				
				}
				if (message.contains("color=")){
					int k;
					k = message.indexOf("color=");
					k = k+14;
					String msg = message.substring(k);
					int l = msg.indexOf("</");
					msg = msg.substring(0, l);
					System.out.println(msg);
				chatHistory += msg +"\n";
				}
			   cv.updateDisplay(chatHistory);   
	}
	
	
	@Override
	public void run() {
		
        


	BufferedReader stdIn;
	String userInput;

	String hostAddress = "130.229.143.191";


        try {
            out = new PrintWriter(connections.get(0).getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        connections.get(0).getInputStream()));
            scanner = new Scanner(in).useDelimiter("</message>");
            
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.\n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to host.\n" + e);
            System.exit(1);
        }


	System.out.println("Connection successful!");

	
	//stdIn = new BufferedReader(new InputStreamReader(System.in));
        String inStr;                           

	try {
		while ((inStr = scanner.next()) != null) {
		    //out.println(userInput);
		    this.receive(inStr);
		}
	} catch (Exception e1) {

		e1.printStackTrace();
	}


	out.close();
	try {
		in.close();
		//stdIn.close();
		connections.get(0).close();
	} catch (IOException e) {

		e.printStackTrace();
	}
	

	}

	
}
