package services;

import base.User;
import enumerations.Role;
import interfaces.iStaff;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffImpl implements iStaff {

    public User queryStaffList(HashMap<String , User> customerList , String email){
        User customer;
        customer = customerList.getOrDefault(email, null);
        return customer;
    }
    @Override
    public String registerNewCustomer(HashMap<String , User> customerList,HashMap<String , User> staffList, String staffEmail,  String name,  String email, String password, String mobileNumber, String accountNumber) {
       String message = "";
       User staff = queryStaffList(staffList , staffEmail);

     if (staff != null) {
         if (staff.getRole() != null) {
             if (verifyEmail(email)) {
                 if (verifyAccountNumber(accountNumber)) {
                     // System.out.println("test");
                     if (!customerList.containsKey(email)) {
                         customerList.put(email, new User(name, accountNumber, email, password, mobileNumber));
                         message = "successfully Registered";
                         System.out.println("New Customer Registered");
                     } else {
                         message = "User Already Exists";
                         System.out.println(name + " already exists");
                     }
                 } else {
                     message = "invalid account number";
                     System.out.println("invalid account number");
                 }
             } else {
                 message = "invalid email";
                 System.out.println("invalid email");
             }
         } else {
             message = "Unauthorized Operation";
             System.out.println("Unauthorized Operation");
         }
     }else {
         message = "staff not found";
         System.out.println(message);
     }
        return message;
    }


    @Override
    public String registerStaff( HashMap<String , User> staff, String name, String email, String mobileNumber) {
        String message = "";
        if (verifyEmail(email)){

                if (!staff.containsKey(email)){
                    staff.put(email , new User(name , email , mobileNumber));
                    if(staff.get(email) != null){
                        User newStaff = staff.get(email);
                        newStaff.setRole(Role.STAFF);
                    };
                    message = "successfully Registered";
                    System.out.println("successfully Registered");
                }else{
                    System.out.println(name + " Already Exits" );
                    message = "user Already Exits";
                }
        }else{
            message = "invalid email";
        }
        return message;
    }


    public static boolean  verifyEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[A-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n" +
                ")*@[A-Z0-9-]+(?:\\.[A-Z0-9-]+)*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    @Override
    public boolean verifyAccountNumber(String accountNumber) {

       return accountNumber.length() == 11;

    }
}
