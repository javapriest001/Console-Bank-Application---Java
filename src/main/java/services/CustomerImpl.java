package services;

import base.User;
import enumerations.TransactionForm;
import enumerations.TransactionType;
import interfaces.iCustomer;
import models.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomerImpl implements iCustomer {
    public User queryCustomerList(HashMap<String , User> customerList , String email){
        User customer;
        customer = customerList.getOrDefault(email, null);
        return customer;
    }

    @Override
    public String withdraw(HashMap<String , User> customerList , String email, double amount) {
        String message = null;
        User customer = queryCustomerList(customerList , email);
        if (customer != null) {
            if (customer.isLoggedIn()) {
                if (customer.getAccountBalance() >= amount) {
                    double balance = customer.getAccountBalance() - amount;
                    customer.setAccountBalance(balance);
                    customer.getTransactions().put(new Date(), new Transaction(TransactionType.DR, TransactionForm.WITHDRAW, true, "Withdrew " + amount));
                    message = "Withdraw successfull";
                    System.out.println("You withdrew " + amount + " your Balance: " + customer.getAccountBalance());
                } else {
                    message = "insufficient Balance";
                    System.out.println("insufficient Balance");
                }
            } else {
                message = "Unauthorized";
                System.out.println("Unauthorized");
            }
        }else {
            message = "Only Customers Can Withdraw";
            System.out.println(message);
        }
        return message;
    }

    @Override
    public String deposit(HashMap<String , User> customerList , String email, double amount) {
        String message = null;
        User customer = queryCustomerList(customerList , email);

      if (customer != null) {
          if (customer.isLoggedIn()) {
              if (amount > 0) {
                  customer.setAccountBalance(amount);
                  customer.getTransactions().put(new Date(), new Transaction(TransactionType.CR, TransactionForm.DEPOSIT, true, "Deposited " + amount));
                  message = "deposit successfull";
                  System.out.println("You deposited the sum of " + amount);
              }
          } else {
              message = "Unauthorized";
              System.out.println("You have to login to deposit");
          }
      }else {
          message = "Only Customers Can deposit";
          System.out.println(message);
      }
        return message;
    }


    @Override
    public String transfer(HashMap<String , User> customerList , String senderEmail, String receiverEmail, double amount) {
        String message = null;

        User sender = queryCustomerList(customerList , senderEmail);
        User customerTwo = queryCustomerList(customerList , receiverEmail);

        if (sender != null){
            if (sender.isLoggedIn()){
                if (customerTwo != null){
                    if (sender.getAccountBalance() > amount){
                        double balance = sender.getAccountBalance() - amount;
                        sender.setAccountBalance(balance);
                        customerTwo.setAccountBalance(customerTwo.getAccountBalance() + amount);
                        sender.getTransactions().put(new Date() , new Transaction(TransactionType.DR, TransactionForm.TRANSFER , true , "You Transferred " + 5000 + " to " + customerTwo.getName()));
                        customerTwo.getTransactions().put(new Date() , new Transaction(TransactionType.CR, TransactionForm.TRANSFER , true , "You Received " + 5000 + " from " + sender.getName()));
                        System.out.println("Your Transfer of " + amount + " to " + customerTwo.getName() + " was successfull" + " Your Balance is " + sender.getAccountBalance());
                        message = "successfull";
                    }else {
                        message = "Insufficient Funds";
                        System.out.println("Insufficient Funds");
                    }
                }else {
                    message = "Receiver Not Found";
                    System.out.println("Receiver Not Found");
                }
            }else {
                message = "unauthorized";
                System.out.println(senderEmail + " is not logged In"  );
            }
        }else {
            message = "Sender Not Found";
            System.out.println(senderEmail + " is not a customer");
        }
        return message;
    }

    public void printStatementOfAccount(HashMap<String , User> customerList, String email){
        User customer = queryCustomerList(customerList , email);
        if (customer != null){
            if (customer.isLoggedIn()){
                for (Map.Entry<Date , Transaction> record : customer.getTransactions().entrySet()){
                    System.out.println("Date: " + record.getKey() + " Transaction : " + record.getValue());
                }

            }else {
                System.out.println("You are not logged");
            }
        }else {
            System.out.println("User not found");
        }
    }

    @Override
    public String login(HashMap<String , User> customerList,  String email, String password) {
        String message = "";
        if(StaffImpl.verifyEmail(email)){
            if(customerList.containsKey(email)){
                User customer = customerList.get(email);
                if (password.equals(customer.getPassword())){
                    customer.setLoggedIn(true);
                    System.out.println("Successfully Logged In");
                    message = "successfull";
                }else {
                    message = "incorrect password";
                    System.out.println("Incorrect Password");
                }
            }else {
                message = "User Not Found";
                System.out.println("User Not Found");
            }
        }else{
            message = "Incorrect Email";
            System.out.println("Incorrect Email");
        }
        return message;
    }

    private boolean logoutUser(User customer){
        customer.setLoggedIn(false);
        return customer.isLoggedIn();
    }
}
