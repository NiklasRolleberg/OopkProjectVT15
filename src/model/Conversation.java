package model;

import java.util.ArrayList;
import java.util.Observable;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Conversation extends Observable implements Runnable  {

	ArrayList<Socket> conversations;

	
	/**
	 * create conversation from ip and port
	 * @param ipIn
	 * ip address
	 * @param portIn
	 * port number
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	Conversation(String ipIn, int portIn) throws UnknownHostException, IOException{
		this(new Socket(ipIn,portIn));
		
	}
	
	/**
	 * create conversation from connected socket
	 * @param socketIn connected socket
	 */
	Conversation(Socket socketIn){
		conversations = new ArrayList<Socket>(); 
		conversations.add(socketIn);
		
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
