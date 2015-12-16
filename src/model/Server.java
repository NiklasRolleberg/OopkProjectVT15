package model;

import java.io.IOException;
import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

import controller.Controller;





public class Server implements Runnable  {
	Controller controller;
	private ServerSocket serverSocket;
	private Socket clientSocket = null;
	int serverPort;
	
	public Server(int port, Controller c){
		this.controller = c;
		this.serverPort = port;
	}

	@Override
	public void run() {
		try {
		    serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
		    System.out.println("Could not listen on port: 4444");
		    System.exit(0);
		}
		
		while (true){
			
			
			// Lyssna efter en klient
			try {
			    clientSocket = serverSocket.accept();
			    
			    int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to connect?","Message",dialogButton);
			    if(dialogResult == JOptionPane.YES_OPTION){
			    	controller.addConverstion("hej", clientSocket);	

			    }
			    if(dialogResult == JOptionPane.YES_OPTION){
			    	clientSocket.close();	

			    }
			    
			    	
			    

			    	
			    
			    /** TODO
			     * Fixa så att en ruta kommer fram meden fråga om man vill ansluta
			     *
			    */
		
			    
			       
			} catch (IOException e) {
			    System.out.println("Accept failed: 4444");
			    System.exit(-1);
			}
			
			
			
		}
		
		
	}
	
	
	
}
