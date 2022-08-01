import models.Bank;
import services.CustomerImpl;
import services.StaffImpl;

public class Main {
    public static void main(String[] args) {
        StaffImpl staff = new StaffImpl();
        CustomerImpl customerService = new CustomerImpl();
        staff.registerStaff(Bank.staff , "Enwere Vincent" , "enwerevincent@gmail.com" , "09055589964");
        System.out.println(Bank.staff);
        staff.verifyAccountNumber("12345678986");
        staff.registerNewCustomer(Bank.customers , Bank.staff ,"enwerevincent@gmail.com" , "John Doe" , "johndoe@gmail.com", "Fbiswats1" , "09055589964" , "23487456876");
        staff.registerNewCustomer(Bank.customers , Bank.staff ,"enwerevincent@gmail.com" , "Jane Doe" , "janedoe@gmail.com", "Fbiswats12" , "07016087515" , "12678496367");
       // customerService.

        customerService.login(Bank.customers , "johndoe@gmail.com" , "Fbiswats1");
        customerService.deposit(Bank.customers , "johndoe@gmail.com" , 15000);
        customerService.withdraw(Bank.customers , "johndoe@gmail.com" , 10000);
        customerService.transfer(Bank.customers , "johndoe@gmail.com"  , "janedoe@gmail.com"  , 3200);
        customerService.printStatementOfAccount(Bank.customers , "johndoe@gmail.com" );

        customerService.login(Bank.customers , "janedoe@gmail.com" , "Fbiswats12");
        customerService.printStatementOfAccount(Bank.customers , "janedoe@gmail.com" );
        customerService.transfer(Bank.customers , "johndoe@gmail.com"  , "janddoe@gmail.com"  , 3200);
        customerService.deposit(Bank.customers , "janddoe@gmail.com" , 15000);

       // System.out.println(staff.verifyEmail("enwerevincent@gmail.com"));
    }
}
