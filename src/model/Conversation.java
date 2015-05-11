package model;

import java.util.ArrayList;
import java.util.Observable;
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
		out.print("<message sender =" + '"' + model.getName() + '"' + "> <text color=" + '"' + "#RRGGBB" + '"' + "> "+s+"</text> </message>");
		out.flush();
		chatHistory += "me" + s + "\n";
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
		while ((inStr = in.readLine()) != null) {
		    //out.println(userInput);
		    System.out.println("echo: " + inStr);
		    chatHistory += inStr + "\n";
		    cv.updateDisplay(chatHistory);
		}
	} catch (IOException e1) {

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
