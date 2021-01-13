package Task2;

import java.util.ArrayList;

public class main {

    public static void main(String args[]) {
        // ArrayList<String> logs = new ArrayList<String>();
        String userID = "008";
        admin ad = new admin("ad", "min", "899");
        userManager.addUser("john", "wick", userID);

        request r = accountMediator.requestNewAccount(userID);
        System.out.println("Approving:");
        r.approve();


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