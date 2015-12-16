package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
			   
			    
			    try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    
			    String request = "Oder version or broken request-tag wants to talk to you";
			    
			    byte[] buffer = new byte[128];
			    if(clientSocket.getInputStream().available()>0) {
			    	int l = clientSocket.getInputStream().read(buffer);
			    	request = new String(buffer);
			    	request = request.substring(0,l);
			    	//System.out.println("Request: " + request);
			    	int start = request.indexOf("<request>");
				    int stop = request.indexOf("</request>");
				    
				    if(start == -1 || stop == -1 || start+9>stop) {
				    	request = "Oder version or broken request-tag wants to talk to you";
				    }
				    else {
				    	start +=9;
				    	request = request.substring(start,stop);
				    }
			    }
			   
			    
			    int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog (null, request,"Would You Like to connect?",dialogButton);
			    if(dialogResult == JOptionPane.YES_OPTION){
			    	controller.addConverstion("hej", clientSocket);	
			    }
			    else{
			    	byte[] sendBuffer = new String("<request> NEEJ JAG VILL INTE PRATA MED DIG! </request>").getBytes("UTF-8");
			    	clientSocket.getOutputStream().write(sendBuffer);
			    	clientSocket.getOutputStream().flush();
			    	
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
