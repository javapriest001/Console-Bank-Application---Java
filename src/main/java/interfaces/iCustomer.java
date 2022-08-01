package interfaces;

import base.User;

import java.util.HashMap;

public interface iCustomer {
    String withdraw(HashMap<String , User> customerList , String email, double amount);
    String deposit(HashMap<String , User> customerList , String email, double amount);
    String transfer(HashMap<String , User> customerList , String senderEmail, String receiverEmail , double amount);
    String login(HashMap<String , User> customerList, String email, String password);
}
