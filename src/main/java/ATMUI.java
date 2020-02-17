import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import java.util.Scanner;

public class ATMUI implements BasicAPI {

    boolean loggedIn = false;

    Scanner sc = new Scanner(System.in);

    String menu = "Please select an option below and enter the number corresponding:" +
            "\n 1: Deposit" +
            "\n 2: Withdraw" +
            "\n 3: Transfer" +
            "\n 4: Logout";

    String availableBalance = "The current balance available in account " + acctId + "is: " + amount;

    String loginPassword = "Please enter your password";

    System.out.print("Welcome to The Bank. Please enter your account ID: ");
    String acctId = sc.nextLine();
    System.out.print("Please enter your password: ");
    String password = sc.nextLine();

    if (confirmCredentials(acctId, password)){
        loggedIn == true;
    }

    while (loggedIn == true){

        System.out.print(availableBalance);
        System.out.print(menu);

        int choice = sc.nextInt();

        if (choice == 1){
            System.out.println("Please enter the amount that you would like to deposit: ");
            double depositAmount = sc.nextDouble();

            if (isValidAmount(depositAmount)){
                deposit(acctId, depositAmount);
            }

        }

        else if (choice == 2){

            System.out.println("Please enter the amount that you would like to deposit: ");
            double withdrawAmount = sc.nextDouble();



        }

        else if (choice == 3){

            System.out.println("Please enter the ID of the account you'd like to transfer to: ");
            String accountToTransfer = sc.nextLine();


        }

        else if (choice == 4){
            System.out.print("Thank you for choosing the bank. You have been succesfully logged out.");
            loggedIn = false;
        }

        else{
            System.out.print("ERROR: Not an accepted value. Please enter one of the numbers listed.");
        }



    }



}
