package Task2;

import java.util.ArrayList;
import java.util.List;

public class userAccountMediator {

    private static List<request> requests = new ArrayList<request>();

    // public static user getUser(String id) {
    //     return userManager.getUser(id);
    // }

    public static void addAccountToUser(String id, account acc) {
        userManager.getUser(id).addAccount(acc);
    }

    public static void removeAccountFromUser(String id, account acc) {
        try {
            userManager.getUser(id).removeAccount(acc);
        } catch (Exception e) {
            System.err.println("Unable to remove account: "+e.getMessage());
        }
    }

    public static account getAccount(String userID, int accountNumber) {
        return userManager.getUser(userID).getAccount(accountNumber);
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
        var account = getAccount(userID, AccountNumber);
        account.addCard(cr);
    }

    public static void removeCardFromAccount(String userID, int AccountNumber, card cr) {
        var account = getAccount(userID, AccountNumber);
        try {
            account.removeCard(cr);
        } catch (Exception e) {
            System.err.println("Unable to remove card: "+e.getMessage());
        }
        
    }

    public static void transact(String senderID, int senderAccount, int recieverAccount, double amount){
        account sender = getAccount(senderID, senderAccount);
        String recieverId = userManager.getOwner(recieverAccount);
        account reciever = getAccount(recieverId, recieverAccount);
        try{
        sender.subtract(amount);
        reciever.add(amount);
       
        }catch(Exception e){
            System.err.println("Could not transact: "+e.getMessage());
        }
        
    }

    public static void joinAccount(int accountNo, String newUserId){
        account a = getAccount(newUserId, accountNo);
        if(a == null){
            System.err.println("Account not found!");
        }
        addAccountToUser(newUserId, a);
    }

    public static char getCurrency(String userID, int accountNumber){
        return getAccount(userID, accountNumber).getCurrency();
    }
}