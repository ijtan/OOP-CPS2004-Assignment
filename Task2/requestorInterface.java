package Task2;

import java.util.ArrayList;

public interface requestorInterface {
    public static void requestNewAccount(String uid){
        accountManager.requestNewAccount(uid);
    }

    public static void requestAccountDeletion(String uid,String accNo) {
        accountManager.requestAccountDeletion(uid,accNo);
    }

    public static void transact(String senderAccountNo,String recieverAccountNo,double amount){
        accountManager.transact(senderAccountNo, recieverAccountNo, amount);
    }
    public static void withdraw(String accNo, double amount){
        accountManager.withdrawFromAccount(accNo, amount);
    }
    
    public static void deposit(String accNo, double amount) {
        accountManager.depositToAccount(accNo, amount);
    }
    
    public static double getBalance(String accNo) {
        return accountManager.getAccountBalance(accNo);
    }

    public static String listAccounts(ArrayList<String>accountNumbers){
        String accList = "";
        for (String accNo : accountNumbers)
            try {
                accList += accNo+"\t|\t";
                accList += accountManager.getCurrency(accNo);
                accList += accountManager.getAccountBalance(accNo) + '\n';
            } catch (Exception e) {
                System.err.println(
                        "Could not fetch accounts as there was an issue in one or more accounts: " + e.getMessage());
            }
        return accList;
    }
}