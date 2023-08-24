import java.util.Scanner;
public class Assignment{
     private final static Scanner scanner = new Scanner(System.in);
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

        public static void main(String[] args) {
            mainLoop:
            do{
                final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);
                System.out.println(CLEAR);
                System.out.println(APP_TITLE);

                switch(screen){
                    case DASHBOARD: 
                        System.out.println("[1]. Open New Account");
                        System.out.println("[2]. Deposit Money");
                        System.out.println("[3]. Withdraw Money");
                        System.out.println("[4]. Transfer Money");
                        System.out.println("[5]. Check Account Balance");
                        System.out.println("[6]. Drop Existing Account");
                        System.out.println("[7]. Exit\n");
                        System.out.print("Enter an option to continue > ");
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
                    
                    case OPEN_NEW_ACCOUNT:
                    
                }
            

            }while(valid);
        }
}