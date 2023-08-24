import java.util.Arrays;
import java.util.Scanner;

public class Assignment{
    final static String CLEAR = "\033[H\033[2J";
    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String COLOR_GREEN_BOLD = "\033[32;1m";
    final static String COLOR_YELLOW_BOLD = "\033[33;1m";
    final static String RESET = "\033[0m";

    final static String DASHBOARD = "💰"+" Welcome to Smart Banking";
    final static String OPEN_NEW_ACCOUNT = "💳"+" Open New Account";
    final static String DEPOSIT_MONEY = "💵"+" Deposit Money";
    final static String WITHDRAW_MONEY = "💶"+" Withdraw Money";
    final static String TRANSFER_MONEY = "💸"+" Transfer Money";
    final static String CHECK_ACCOUNT_BALANCE = "🤑"+" Check Account Balance";
    final static String DROP_EXISTING_ACCOUNT = "❌"+" Drop Existing Account";
    final static String EXIT = "👋"+" Exit";        

    final static String TRY_AGAIN_MSG = String.format("%s%s%s", COLOR_YELLOW_BOLD, "Do you want to try again?", RESET);
    final static String ERROR_MSG = String.format("%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
    final static String SUCCESS_MSG = String.format("%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

    static String screen = DASHBOARD;
    static String[][] accountDetails = new String[0][];
    static String accountID;    
    static boolean valid;
    static Double WithdrawAmount;
    static String accountNo;
    static String amount;
    static String nameOfAccount;
    static String fromAccountAmount;
    static String customerNameOfFromAccount;
    static String toAccountAmount;
    static String customerNameOfToAccount;
    static String fromAccountID;
    static String toAccountID;
    static String transferAmount;

    private final static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
             
        mainLoop:
        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);
            System.out.println(CLEAR);
            System.out.println(APP_TITLE + "\n");
            

            switch(screen){
                case DASHBOARD: 
                    System.out.println("[1]. Open New Account");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Check Account Balance");
                    System.out.println("[6]. Drop Existing Account");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter an option to continue: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option){
                        case 1: screen = OPEN_NEW_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE; break;
                        case 6: screen = DROP_EXISTING_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;
                case OPEN_NEW_ACCOUNT:{
                    String id = String.format("SDB-%05d", (accountDetails.length + 1));
                    System.out.printf("Account ID: %s\n", id);

                    //Name Validation
                    boolean valid;
                    String name;
                    double initialDeposit;
                    nameValidation:
                    do{
                        valid = true;
                        System.out.print("Enter customer Name: ");
                        name = scanner.nextLine().strip();

                        if(name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name can't be Empty!");
                            valid = false;
                            continue nameValidation;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if(!(Character.isLetter(name.charAt(i))|| Character.isSpaceChar(name.charAt(i)))){
                                System.out.printf(ERROR_MSG, "Invalid Name!");
                                valid = false;
                                continue nameValidation;
                            }    
                        }

                    }while(!valid);
                    //Initial Deposit Validation
                    initialDepositValidation:
                    do{
                        valid = true;
                        System.out.print("Initial Deposit: ");
                        initialDeposit = scanner.nextDouble();
                        scanner.nextLine();

                        if(!(initialDeposit >= 5000)){
                            System.out.printf(ERROR_MSG, "Insufficient amount to open new account!");
                            valid = false;
                            continue initialDepositValidation;
                        }

                    }while (!valid); 

                    String[][] newAccountDetails = new String[accountDetails.length +1][3];
                    for (int i = 0; i < accountDetails.length; i++) {
                        newAccountDetails[i] = accountDetails[i]; 
                    }
                    newAccountDetails[newAccountDetails.length-1][0] = id;
                    newAccountDetails[newAccountDetails.length-1][1] = name;
                    newAccountDetails[newAccountDetails.length-1][2] = initialDeposit + "";

                    accountDetails = newAccountDetails;

                    System.out.printf(SUCCESS_MSG, String.format("%s:%s has cteated succesfully.", id, name));
                    System.out.print("Do you want to add another account (Y/n)? ");
                    if(scanner.nextLine().toUpperCase().strip().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;
                }
                case DEPOSIT_MONEY:{
                    //Account number Validation.
                    
                }

                

                        
                    
                    
                    
            }
        }while (true); 


    }

    
}