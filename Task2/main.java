package Task2;

public class main {

    public static void main(String args[]) {
        // ArrayList<String> logs = new ArrayList<String>();

        String userID = "008";
        admin ad = new admin("ad", "min", "899");
        userManager.addUser("john", "wick", userID);

        accountMediator.requestNewAccount(userID);
        System.out.println(userManager.listUserAccounts(userID));

        ad.approveRequest(accountMediator.getOldestRequest());

        // userManager.getUser(userID).deposit(userManager.getUser(userID).getAccount(0).getAccountNumber(), 10.0);
        // String accountNo = userManager.getUserAccounts(userID);
        for (String accNo : userManager.getUserAccounts(userID)) {
            System.out.println(accNo);
        }
        // accountMediator.depositToAccount(accountNo,10.0);

        // accountMediator.requestNewAccount(userID);

        System.out.println(userManager.listUserAccounts(userID));


    }
}