package com.mycompany.serverchat;
import java.net.*;
import java.io.*;

/**
 *
 * @author stei2
 */
public class ServerThread extends Thread{
    ServerThread server = null;
    Socket client = null;
    String stringaClient = null;
    String stringaMod = null;
    BufferedReader inputClient;
    DataOutputStream outputClient;
    DataOutputStream outputChat;
    
    public void run(){
        try{
            comunica();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    
    public void comunica()throws Exception{
        String nome = null;
        
        outputClient.writeBytes("Inserisci il tuo nickname\n");
        nome = inputClient.readLine();
        System.out.println(nome);
        outputChat.writeBytes(nome+" connected\n");
        for(;;){
            
            stringaClient = inputClient.readLine();
            
            if(stringaClient.equals("FINE")){
                System.out.println(nome+" left the chat!");
                outputClient.writeBytes("7586");
                inputClient.close();
                outputClient.close();
                client.close();
                outputChat.writeBytes(nome+" disconnected\n");
                return;
            }
            try {   
                outputChat.writeBytes(nome+": "+stringaClient+"\n");
            } catch (Exception e) {
                outputClient.writeBytes("Non ci sono utenti rimasti in chat, disconnessione automatica!\n");
                outputClient.writeBytes("7586\n");
                inputClient.close();
                outputClient.close();
                client.close();
                return;
            }
        }
    }
    
    public ServerThread(Socket socket, OutputStream u2)throws Exception{
        this.client = socket;
        outputChat = new DataOutputStream(u2);
        inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputClient = new DataOutputStream(client.getOutputStream());
    }
}
