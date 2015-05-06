package model;

import java.util.ArrayList;
import java.util.Observable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Conversation extends Observable implements Runnable  {

	ArrayList<Socket> connections;

	
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
		connections = new ArrayList<Socket>(); 
		connections.add(socketIn);
		
	}
	
	
	
	@Override
	public void run() {
		

        PrintWriter out = null;
        BufferedReader in = null;


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

	stdIn = new BufferedReader(new InputStreamReader(System.in));
                                   

	try {
		while ((userInput = stdIn.readLine()) != null) {
		    out.println(userInput);
		    System.out.println("echo: " + in.readLine());
		}
	} catch (IOException e1) {

		e1.printStackTrace();
	}


	out.close();
	try {
		in.close();
		stdIn.close();
		connections.get(0).close();
	} catch (IOException e) {

		e.printStackTrace();
	}
	

	}
}
