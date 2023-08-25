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
    static Double withdrawAmount;
    static String accountNo;
    static String amount;
    static String nameOfAccount;
    static String fromAccountAmount;
    static String customerNameOfFromAccount;
    static String toAccountAmount;
    static String customerNameOfToAccount;
    static String fromAccountID;
    static String toAccountID;
    static Double transferAmount;

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
                    accountNumberValidation("Enter Account Number: ");
                    if(!valid) continue;
                    currentBalance();
                    //Deposit amount Validation
                    Double amontOfDeposition;

                    depositAmountValidation:
                    do{
                        valid = true;
                        System.out.print("Deposit Amount: ");
                        amontOfDeposition = scanner.nextDouble();
                        scanner.nextLine();

                        if(amontOfDeposition < 500){
                            System.out.printf(ERROR_MSG, "Insufficient Amount!");
                            valid = false;
                        }
                        if(valid == false){
                            System.out.printf(TRY_AGAIN_MSG);
                            if(scanner.nextLine().toUpperCase().strip().equals("Y")) {
                                continue depositAmountValidation;
                        }else{
                            screen = DASHBOARD;
                            continue mainLoop;
                        }
                    }

                    }while(!valid);

                    Double newBalance = Double.valueOf(amount) + amontOfDeposition;
                    System.out.printf("New Balance: %,.2f\n", newBalance);

                    for (int i = 0; i < accountDetails.length; i++) {
                        if(accountDetails[i][0].equals(accountNo)){
                            accountDetails[i][2] = newBalance + "";
                        }    
                    }
                    System.out.print("Do you want to continue depositing (Y/n)?");
                    if(scanner.nextLine().toUpperCase().strip().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;
                }
                case WITHDRAW_MONEY:{
                    accountNumberValidation("Enter account Number: ");
                    if(!valid) continue;

                    currentBalance();
                    withdrawAmountValidation("Withdraw Amount");
                    if(!valid) continue;

                    Double newAccountBalance = Double.valueOf(amount) - withdrawAmount;
                    System.out.printf("New Account Balance: %,.2f\n", newAccountBalance);
                    for (int i = 0; i < accountDetails.length; i++) {
                        if(accountDetails[i][0].equals(accountNo)){
                            accountDetails[i][2] = newAccountBalance + "";
                        }                       
                    }
                    System.out.print("Do you want to withdraw again (Y/n)?");
                    if(scanner.nextLine().toUpperCase().strip().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;
                }

                case CHECK_ACCOUNT_BALANCE:{
                    accountNumberValidation("Enter Account ID: ");
                    if(!valid) continue;

                    for (int i = 0; i < accountDetails.length; i++) {
                        if(accountDetails[i][0].equals(accountNo)){
                            amount = accountDetails[i][2];
                            nameOfAccount = accountDetails[i][1];
                            System.out.printf("%sName: %s%s", COLOR_GREEN_BOLD, nameOfAccount, RESET);
                            System.out.printf("%sCurrent Account Balance: %s%,.2f%s\n", COLOR_GREEN_BOLD, "Rs. ", amount, RESET);
                            System.out.printf("%sAvailable Balance to Withdraw: %s%,.2f%s\n", COLOR_GREEN_BOLD, "Rs. ", (Double.valueOf(amount)-500), RESET);
                            break;
                        }
                        
                    }
                    System.out.println();
                    System.out.print("Do you want to countinue checking (Y/n)?");
                    if(scanner.nextLine().toUpperCase().strip().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;
                }
               
            }
        }while (true); 
    }
    public static void accountNumberValidation(String accountNumberInput){
        do{
            valid = true;
            System.out.print(accountNumberInput);
            accountNo = scanner.nextLine().toUpperCase().strip();

            if(accountNo.isBlank()){
                System.out.printf(ERROR_MSG, "Account number cannot be empty!");
                valid = false;
            }else if(!(accountNo.startsWith("SDB-") && accountNo.length() == 9)){
                System.out.printf(ERROR_MSG, "Invalid account Number!");
                valid = false;
            }else{
                String number = accountNo.substring(4);
                for (int i = 0; i < number.length(); i++) {
                    if(!(Character.isDigit(number.charAt(i)))){
                        System.out.printf(ERROR_MSG, "Invalid account Number!");
                        valid = false;
                        break;
                    }
                    
                }
                boolean exists = false;
                for (int i = 0; i < accountDetails.length; i++) {
                    if(accountDetails[i][0].equals(accountNo)){
                        exists = true;
                        break;
                    }
                    
                }
                if(!exists){
                    valid = false;
                    System.out.printf(ERROR_MSG, "Account ID not found!");
                }
            }
            if(!valid){
                System.out.printf(TRY_AGAIN_MSG);
                if(scanner.nextLine().strip().toUpperCase().equals("Y")){
                    continue;
                }
                screen = DASHBOARD;
                return;
            }

        }while(!valid);

    }

    public static void currentBalance(){
        for (int i = 0; i < accountDetails.length; i++) {
            if(accountDetails[i][0].equals(accountNo)){
                amount = accountDetails[i][2];
                nameOfAccount = accountDetails[i][1];
                System.out.printf("%sCurrent Balance: %s%,.2f%s", COLOR_GREEN_BOLD,"Rs. ", Double.valueOf(amount),RESET);
                break;
            }
            
        }
    }
    public static void withdrawAmountValidation(String input){
        do{
            valid = true;
            System.out.print(input);
            withdrawAmount = scanner.nextDouble();
            scanner.nextLine();

            if(withdrawAmount < 100){
                System.out.printf(ERROR_MSG, "Insufficient Amount!");
                valid = false;
            }
            if((Double.valueOf(amount) - withdrawAmount) < 500){
                System.out.printf(ERROR_MSG, "Insufficient Account Balance, There should be at least Rs. 500.00 balance in your account!");
                valid = false;
            }if(valid == false){
                System.out.print(TRY_AGAIN_MSG);
                if(scanner.nextLine().toUpperCase().strip().equals("Y")){
                    continue;
                }else {
                    screen = DASHBOARD;
                    return;
                }
            }

        }while(!valid);
    }
    
}