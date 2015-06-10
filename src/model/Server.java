package model;

import java.io.IOException;
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
			    controller.addConverstion("hej", clientSocket);
			    
			} catch (IOException e) {
			    System.out.println("Accept failed: 4444");
			    System.exit(-1);
			}
			
			
			
		}
		
		
	}
	
	
	
}
