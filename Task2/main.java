package Task2;

public class main {

    public static void main(String args[]) {
        String userID = "008";
        admin ad = new admin("ad", "min", "899");
        userManager.addUser("john", "wick", userID);

        user u = userManager.getUser(userID);
        System.out.println("accounts pre");
        System.out.println(u.listAccounts());
        
        u.requestNewAccount();
        System.out.println("accounts pre app");        
        System.out.println(u.listAccounts());

        ad.approveRequest(accountManager.getOldestRequest());
        System.out.println("accounts post app");
        System.out.println(u.listAccounts());


        String accountNumber = u.getAccountNos().get(0);
        System.out.println("list cards pre:");
        System.out.println(accountManager.listCards(accountNumber));
        accountManager.requestNewCard(userID, accountNumber);
        System.out.println("list cards pre ap:");
        System.out.println(accountManager.listCards(accountNumber));
        System.out.println("list cards post ap:");
        ad.approveRequest(accountManager.getOldestRequest());
        System.out.println(accountManager.listCards(accountNumber));

        accountManager.requestCardDeletion(userID, accountNumber, accountManager.getCardNumbers(accountNumber)[0]);
        System.out.println("list cards pre del app:");
        System.out.println(accountManager.listCards(accountNumber));


        System.out.println("list cards post del app:");
        ad.approveRequest(accountManager.getOldestRequest());
        System.out.println(accountManager.listCards(accountNumber));


        u.requestAccountDelete(u.getAccountNos().get(0));
        System.out.println("accounts pre del app");
        System.out.println(u.listAccounts());

        ad.approveRequest(accountManager.getOldestRequest());
        System.out.println("accounts post del app");
        System.out.println(u.listAccounts());


       

        // System.out.println("User accounts Pre request");
        // accountMediator.requestNewAccount(userID);
        // System.out.println("User accounts Pre approval");
        // System.out.println(userManager.listUserAccounts(userID));
        // System.out.println("Approving");
        // ad.approveRequest(accountMediator.getOldestRequest());
        // System.out.println("User accounts Post approval");
        // System.out.println(userManager.listUserAccounts(userID));

        // System.out.println("Requesting deletion");
        // accountMediator.requestAccountDeletion(userID,userManager.getUserAccounts(userID).get(0));
        // System.out.println("User accounts Pre approval");
        // System.out.println(userManager.listUserAccounts(userID));
        // System.out.println("Approving");
        // ad.approveRequest(accountMediator.getOldestRequest());
        // System.out.println("User accounts Post approval");
        // System.out.println(userManager.listUserAccounts(userID));




        // System.out.println("User Cards Pre request");
        // accountMediator.requestNewAccount(userID);
        // System.out.println("User Cards Pre approval");
        // System.out.println(userManager.listUserAccounts(userID));
        // System.out.println("Approving");
        // ad.approveRequest(accountMediator.getOldestRequest());
        
        // System.out.println("User Cards Post approval");
        // System.out.println(userManager.listUserAccounts(userID));

        // System.out.println("Requesting Cards deletion");
        // accountMediator.requestAccountDeletion(userID, userManager.getUserAccounts(userID).get(0));
        // System.out.println("User Cards Pre approval");
        // System.out.println(userManager.listUserAccounts(userID));
        // System.out.println("Approving");
        // ad.approveRequest(accountMediator.getOldestRequest());
        // System.out.println("User Cards Post approval");
        // System.out.println(userManager.listUserAccounts(userID));

    }
}