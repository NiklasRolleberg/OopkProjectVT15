package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Connection implements Runnable{
	Conversation conversation;
	Socket socket;
	PrintWriter out = null;
    BufferedReader in = null;
    Thread myThread;
    String name;
    
    public Connection(Conversation c, Socket s){
    	conversation = c;
    	socket = s;
    	myThread = new Thread(this);
    	myThread.start();
    }
    
    public void send(String message)
    {
    	System.out.println("Sending message: " + message);
    	
    	if(out == null) {
    		try {
    			//Thread is not started yet.
				Thread.sleep(200);
			} catch (InterruptedException e) {e.printStackTrace();}
    	}
    	
    	out.print(message);
    	out.flush();
    }
    
    /**
     * set name receiver
     * @param name
     */
    public void setName(String name){
    	this.name = name;
    }
    
    /**
     * Get name receiver
     * @return
     * name of receiver
     */
    public String getName(){
    	return name;
    }
    
    
    /**
     * Close connections and send disconnect tag
     */
    public void close()
    {
    	System.out.println("close");
    	send("<message> <disconnect/></message>");
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void run() 
	{
		Scanner scanner = null;
        try {
        	out = new PrintWriter(socket.getOutputStream(), true);
        	in = new BufferedReader(new InputStreamReader(
                                    socket.getInputStream()));
        	
        	scanner = new Scanner(in).useDelimiter("</message>|</request>");
            
	            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.\n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to host.\n" + e);
            System.exit(1);
        }
		
		System.out.println("Connection successful!");
		
        String inStr;                           
		try {
			while ((inStr = scanner.next()) != null) {
			    conversation.receive(inStr,this);
			}
		} catch (Exception e1) {e1.printStackTrace();}

		out.close();
		try {
			in.close();
			socket.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}
