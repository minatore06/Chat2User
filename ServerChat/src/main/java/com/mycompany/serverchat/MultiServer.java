package com.mycompany.serverchat;

import java.io.*;
import java.net.*;

/**
 *
 * @author stei2
 */
public class MultiServer {
    
    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(5678);
            System.out.println("Server aperto!");
            System.out.println("Server in attesa...");
            Socket socket = serverSocket.accept();
            System.out.println("Nuovo socket "+socket);
            DataOutputStream outputSocket = new DataOutputStream(socket.getOutputStream());
            outputSocket.writeBytes("Connesso alla chat, attendi l'arrivo di un utente\n");
            
            Socket socket2 = serverSocket.accept();
            System.out.println("Nuovo socket "+socket);
            DataOutputStream outputSocket2 = new DataOutputStream(socket2.getOutputStream());
            outputSocket2.writeBytes("Connesso alla chat\n");

            ServerThread serverThread = new ServerThread(socket, socket2.getOutputStream());
            ServerThread serverThread2 = new ServerThread(socket2, socket.getOutputStream());
            serverThread.start();
            serverThread2.start();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'instanza del server");
            System.exit(1);
        }
    }
    
    public static void connection(){
        
    }
}
