package Task2;

import java.util.ArrayList;
import java.util.List;

public class userAccountMediator {


    private static List<request> requests = new ArrayList<request>();
    private static List<user> users = new ArrayList<user>();

    public static user getUser(String id) {
        for (user u : users)
            if (u.getID() == id)
                return u;
        return null;
    }

    public static void addUser(String id){
        user u = new user();
        users.add(u);
    }

    public static void addAccountToUser(String id, account acc) {
        for (user u : users) {
            if (u.getID() == id) {
                u.addAccount(acc);
                return;
            }
        }
    }

    public static void removeAccountFromUser(String id, account acc) {
        for (user u : users) {
            if (u.getID() == id) {
                try {
                    u.removeAccount(acc);
                } catch (Exception e) {
                    System.err.println("Could not remove account: " + e.getMessage());
                }
                return;
            }
        }
    }

    public static user getUserWithAccount(int accountNumber) {
        for (user u : users) {
            if (u.getAccount(accountNumber) != null) {
                return u;
            }
        }
        return null;
    }

    public static account getAccount(int accountNumber) {
        for (user u : users) {
            if (u.getAccount(accountNumber) != null) {
                return u.getAccount(accountNumber);
            }
        }
        return null;
    }


    public static void requestNewAccount(String userID) {
        account a = new account(userID);
        request r = new request(userID, a,false);
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
        var user = getUser(userID);
        var account = getAccount(AccountNumber);
        user.addCard(cr, account);
    }

    public static void removeCardFromAccount(String userID, int AccountNumber, card cr) {
        var user = getUser(userID);
        var account = getAccount(AccountNumber);
        try {
            account.removeCard(cr);
        } catch (Exception e) {
            System.err.println("Unable to remove card: "+e.getMessage());
        }
        
    }

    public static void transact(int senderAccount, int recieverAccount, double amount){
        account sender = getAccount(senderAccount);
        account reciever = getAccount(recieverAccount);
        try{
        sender.subtract(amount);
        reciever.add(amount);
       
        }catch(Exception e){
            System.err.println("Could not transact: "+e.getMessage());
        }
        
    }

    public static void joinAccount(int accountNo, String newUserId){
        account a = getAccount(accountNo);
        if(a == null){
            System.err.println("Account not found!");
        }
        addAccountToUser(newUserId, a);
    }

    public static char getCurrency(int accountNumber){
        return getAccount(accountNumber).getCurrency();
    }
}