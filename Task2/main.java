package Task2;

public class main {

    public static void main(String args[]) {
        // ArrayList<String> logs = new ArrayList<String>();

        String userID = "008";
        admin ad = new admin("ad", "min", "899");
        userManager.addUser("john", "wick", userID);

        try {
            userManager.getUser(userID).requestNewAccount();

            System.out.println(userManager.getUser(userID).listAccounts());
        } catch (Exception e) {
            System.err.println("error when getting user in testing");
        }


        ad.approveRequest(accountMediator.getOldestRequest());


        System.out.println("post approve");
        try {
            userManager.getUser(userID).deposit(userManager.getUser(userID).getAccount(0).getAccountNumber(), 10.0);

            userManager.getUser(userID).requestNewAccount();

            System.out.println(userManager.getUser(userID).listAccounts());
        } catch (Exception e) {
            System.err.println("error when getting user in testing");
        }


    }
}