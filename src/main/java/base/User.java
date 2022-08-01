package base;

import enumerations.Role;
import lombok.Data;
import models.Transaction;

import java.util.Date;
import java.util.HashMap;

@Data
public class User {
    private String name;
    private String accountNumber;
    private  double accountBalance;
    private String email;
    private  String password;
    private String mobileNumber;

    private boolean isLoggedIn;

    private Role role;
    private HashMap<Date , Transaction> transactions;

    //Customers
    public User(String name, String accountNumber , String email , String password,String mobileNumber){
        this.name = name;
        this.accountNumber = accountNumber;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.transactions = new HashMap<>();
    }

    //Staff

    public User(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}
