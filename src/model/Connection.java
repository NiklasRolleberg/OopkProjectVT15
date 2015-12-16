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
    	out.print(message);
    	out.flush();
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public String getName(){
    	return name;
    }
    
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
		BufferedReader stdIn;

		Scanner scanner = null;;
        try {
        	out = new PrintWriter(socket.getOutputStream(), true);
        	in = new BufferedReader(new InputStreamReader(
                                    socket.getInputStream()));
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

		
		stdIn = new BufferedReader(new InputStreamReader(System.in));
        String inStr;                           

		try {
			while ((inStr = scanner.next()) != null) {
			    //out.println(userInput);
			    conversation.receive(inStr,this);
			}
		} catch (Exception e1) {

			//e1.printStackTrace();
		}


		out.close();
		try {
			in.close();
			socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
}
