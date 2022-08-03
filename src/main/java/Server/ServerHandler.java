package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {
    private Socket socket;
    private ObjectInputStream bufferedReader;
    private ObjectOutputStream bufferedWriter;
    private ServerSocket serverSocket;

    public  ServerHandler(){
//        try {
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }

    public void startServer(int port){
        System.out.println("Waiting to connect to a Customer...");
            try {
                this.serverSocket = new ServerSocket(port);
                this.socket = serverSocket.accept();
                this.bufferedReader = new ObjectInputStream(socket.getInputStream());
                this.bufferedWriter = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("A new Customer Join Chat");
               bufferedWriter.writeObject("Welcome! Dear Customer!, I am Zita How can I help you today");
                String message = "";
               while (!serverSocket.isClosed()){
                   message =  (String) bufferedReader.readObject();
                   messages(message, "deposit" , "Dear valued Customer, In order to make a deposit to your account, Make sure to Login and provide the amount you wish to deposit");
                   messages(message, "withdraw" , "Dear valued Customer, In order to make a Withdrawal from your account, Make sure to Login and provide the amount you wish to withdraw");
                   messages(message, "transfer" , "Dear valued Customer, In order to make a transfer to any account, Make sure to Login and provide the amount you wish to transfer and also a correct account credentials to transfer to");
              }
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
    }

    public  void messages(String messageReceived ,  String keyword, String message){
        if (messageReceived.contains(keyword)){
            try {
                bufferedWriter.writeObject(message);
            }catch(IOException e){
                System.out.println("Some Error Occured!!");
                e.printStackTrace();
            }

        }
    }
}
