package Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class accountMediator {

    private static List<request> requests = new ArrayList<request>();
    private static HashMap<String,ArrayList<account>> accounts = new HashMap<String, ArrayList<account>>(); //TODO implement this!!

    public static request getOldestRequest(){
        if(requests.size() > 0)
            return requests.get(0);
        return null;
    }

    public static void addAccountToUser(String id, account acc) {
        try {
            userManager.getUser(id).addAccount(acc);
        } catch (Exception e) {
            System.err.println("Error whilst adding account: " + e.getMessage());
            return;
        }
    }

    public static void removeAccountFromUser(String id, account acc) {
        try {
            userManager.getUser(id).removeAccount(acc);
        } catch (Exception e) {
            System.err.println("Unable to remove account: " + e.getMessage());
        }
    }

    public static account getAccount(String userID, int accountNumber) throws Exception {
        return userManager.getUser(userID).getAccount(accountNumber);
    }

    public static ArrayList<account> getAllAccounts(String userID) throws Exception {
        return userManager.getUser(userID).getAccounts();
    }

    public static void requestNewAccount(String userID) {
        account a = new account(userID);
        request r = new request(userID, a, false);
        requests.add(r);
    }

    public static void removeRequest(request r) {
        if (requests.contains(r)) {
            requests.remove(r);
        }
    }

    public static void approveNewAccount(request r, account a) {
        if (requests.contains(r)) {
            addAccountToUser(r.getRequester(), a);

            removeRequest(r);
        }
    }

    public static void approveAccountDeletion(request r, account a) {
        if (requests.contains(r)) {
            removeAccountFromUser(r.getRequester(), a);
            removeRequest(r);
        }
    }

    public static void deny(request r) {
        if (requests.contains(r)) {

            removeRequest(r);
        }
    }

    public static void addCardToAccount(String userID, int AccountNumber, card cr) {
        try {
            getAccount(userID, AccountNumber).addCard(cr);
            
        } catch (Exception e) {
            System.err.println("Error while adding card: " + e.getMessage());
        }
    }

    public static void removeCardFromAccount(String userID, int AccountNumber, card cr) {
        try {
            getAccount(userID, AccountNumber).removeCard(cr);
            ;
        } catch (Exception e) {
            System.err.println("Error while removing account" + e.getMessage());
        }

    }

    public static void transact(String senderID, int senderAccount, int recieverAccount, double amount) {

        try {
            var recieverID = userManager.getOwner(recieverAccount);
            getAccount(senderID, senderAccount).subtract(amount);
            getAccount(recieverID, recieverAccount).add(amount);

        } catch (Exception e) {
            System.err.println("Could not transact: " + e.getMessage());
        }

    }

    public static void joinAccount(int accountNo, String newUserId) {
        account a;
        try {
            a = getAccount(newUserId, accountNo);
            addAccountToUser(newUserId, a);
        } catch (Exception e) {
            System.err.println("Error while joining accounts!" + e.getMessage());
        }        
    }

    public static char getCurrency(String userID, int accountNumber) throws Exception {
        return getAccount(userID, accountNumber).getCurrency();
    }
}