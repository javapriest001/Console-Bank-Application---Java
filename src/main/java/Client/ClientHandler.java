package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientHandler extends Thread{
    private Socket socket;
    private ObjectOutputStream bufferedWriter;
    private ObjectInputStream bufferedReader;

    public void chatWithCustomerCareAgent(){
        try {
            this.socket = new Socket("localhost" , 3455);
            this.bufferedWriter = new ObjectOutputStream(socket.getOutputStream());
            this.bufferedReader = new ObjectInputStream(socket.getInputStream());

            String message = "";
           while (socket.isConnected()){
               Scanner input = new Scanner(System.in);

               System.out.print("Enter Your Message: ");
               message = input.nextLine();
                bufferedWriter.writeObject(message);
                System.out.println("Customer Care: " +bufferedReader.readObject());
           }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        chatWithCustomerCareAgent();
    }
}
