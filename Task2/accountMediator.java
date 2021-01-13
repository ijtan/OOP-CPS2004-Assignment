package Task2;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class accountMediator implements approverInterface {

    private static List<request> requests = new ArrayList<request>();
    private static HashMap<String, account> accounts = new HashMap<String, account>();
    private static HashMap<String, account> pendingAccounts = new HashMap<String, account>();


    

    public static request requestNewAccount(String userID){
        Map<String,Object> pms = new HashMap<>();
        pms.put("accountNumber", "test Account number");
        request r = new request(userID, (request) -> approveNewAccount(request),pms);
        return r;
    }

    public void approve(request r){
        // String s = (String)r.getParamNoExcept("accountNumber");
        System.out.println("approve from amed has been run");
    }
    public static void approveNewAccount(request r){
        String s = (String) r.getParamNoExcept("accountNumber");
        System.out.println("approve new acc has been run: "+s);
    }


    public static request getOldestRequest() {
        if (requests.size() > 0)
            return requests.get(0);
        return null;
    }

    // public static void addAccount(String accNo, account acc) {
    //     // account accs = accounts.get(id);
    //     accounts.put(accNo, acc);
    // }

    // public static void removeAccount(String accountNo) {
    //     try {
    //         account done = accounts.remove(accountNo);
    //         if (done == null) {
    //             throw new Exception("could not find account!");
    //         }
    //     } catch (Exception e) {
    //         System.err.println("Unable to remove account: " + e.getMessage());
    //     }
    // }

    private static account getAccount(String accountNumber) throws Exception {
        // return userManager.getUser(userID).getAccount(accountNumber);
        account acc = accounts.get(accountNumber);
        if (acc == null)
            throw new Exception("Could not find account!");
        return acc;
    }

    // public static void requestNewAccount(String userID) {
    //     String accNo = generateNewAccountKey();
    //     account newAcc = new account(accNo, userID);
    //     pendingAccounts.put(accNo, newAcc);

    //     Map<String, Object> params = new HashMap<String, Object>();
    //     params.put("accountNumber", accNo);
    //     request rq = new request(userID,params, accountMediator.class);
    //     requests.add(rq);
    // }

    // public static void requestAccountDeletion(String userID, String acNo) {
    //     account acc = new account(userID);

    //     Map<String, Object> params = new HashMap<String, Object>();
    //     params.put("delete", "true");
    //     params.put("accountNumber", acNo);

    //     request rq = new request(userID,params, accountMediator.class);
    //     requests.add(rq);
    // }

    public static void removeRequest(request r) {
        if (requests.contains(r)) {
            requests.remove(r);
        }
    }

    static private final String possibleChars = "123456789ABC";// using this to allow for
    static private final int max_len = 16;

    private static String generateNewAccountKey() {
        StringBuilder sb = new StringBuilder(max_len);
        String accNo = "";
        SecureRandom secRan = new SecureRandom();
        do {

            for (int i = 0; i < max_len; i++)
                sb.append(possibleChars.charAt(secRan.nextInt(possibleChars.length())));
            accNo = sb.toString();
        } while ((accounts.containsKey(accNo)));

        return accNo;
    }

    // public static void approveNewAccount(request r, String accNo) {
    // if (requests.contains(r)) {
    // account acc = pendingAccounts.get(accNo);
    // if (acc == null) {
    // System.err.println("Pending Account not found in corresponding list!");
    // return;
    // }
    // accounts.put(accNo, acc);
    // userManager.addAccountToUser(r.getRequester(), accNo);

    // pendingAccounts.remove(accNo);
    // requests.remove(r);

    // }
    // }

    // public static void approveAccountDeletion(request r, String accNo) {
    // if (requests.contains(r)) {
    // if (accounts.containsKey(accNo)) {
    // try {
    // userManager.removeAccountFromUser(r.getRequester(), accNo);
    // } catch (Exception e) {
    // System.err.println("Could not Remove account from user: " + e.getMessage());
    // }
    // accounts.remove(accNo);

    // }
    // requests.remove(r);
    // }
    // }

    public static void deny(request r) {
        if (requests.contains(r)) {
            removeRequest(r);
        }
    }

    public static void addCardToAccount(String AccountNumber, card cr) {

        try {
            account acc = accounts.get(AccountNumber);
            if (acc == null) {
                throw new Exception("Account not found!");
            }
            acc.addCard(cr);
            // accounts.replace(AccountNumber, acc, acc);
            // getAccount(userID, AccountNumber).addCard(cr);

        } catch (Exception e) {
            System.err.println("Error while adding card: " + e.getMessage());
        }
    }

    public static void removeCardFromAccount(String AccountNumber, card cr) {
        try {
            account acc = accounts.get(AccountNumber);
            if (acc == null) {
                throw new Exception("Account not found!");
            }
            acc.removeCard(cr);
        } catch (Exception e) {
            System.err.println("Error while removing account" + e.getMessage());
        }

    }

    public static void transact(String senderAccountNo, String recieverAccountNo, double amount) {

        try {
            account senderAccount = accounts.get(senderAccountNo);
            account recieverAccount = accounts.get(recieverAccountNo);
            if (senderAccount == null || recieverAccount == null) {
                throw new Exception("Could not retrieve one of the accounts specified!");
            }
        } catch (Exception e) {
            System.err.println("Could not transact: " + e.getMessage());
        }

    }

    public static void joinAccount(String accountNo, String newUserId) {
        // account accToMerge = accounts.get(accountNo);

        // accaount a;
        try {
            // a = getAccount(newUserId, accountNo);
            // addAccountToUser(newUserId, a);
            if (accounts.containsKey(accountNo))
                userManager.addAccountToUser(newUserId, accountNo);
            else
                throw new Exception("Could not find account number specified!");

        } catch (Exception e) {
            System.err.println("Error while joining accounts!" + e.getMessage());
        }
    }

    public static char getCurrency(String accountNumber) throws Exception {
        account acc = accounts.get(accountNumber);
        if (acc == null)
            throw new Exception("Account could not be found!");
        return acc.getCurrency();
        // return getAccount(userID, accountNumber).getCurrency();
    }

    public static double getAccountBalance(String accNo) {
        account acc;
        try {
            acc = getAccount(accNo);

        } catch (Exception e) {
            System.err.println("Could not fetch account value: " + e.getMessage());
            return -1;
        }
        return acc.getBalance();
    }

    public static void depositToAccount(String accNo, double value) {
        account acc;
        try {
            acc = getAccount(accNo);
            acc.add(value);
        } catch (Exception e) {
            System.err.println("Could not fetch account value: " + e.getMessage());
            return;
        }

    }

    public static void withdrawFromAccount(String accNo, double value) {
        account acc;
        try {
            acc = getAccount(accNo);
            acc.subtract(value);
        } catch (Exception e) {
            System.err.println("Could not fetch account value: " + e.getMessage());
            return;
        }

    }

    public static void approveACC(request r) { //TODO change this
        try {
            if (!requests.contains(r)) {
                throw new Exception("Request not found!");
            }
            String accountNumber = (String) r.getParam("accountNumber");
            if (r.getParamNoExcept("delete") != null) {
                if (accounts.containsKey(accountNumber)) {
                    userManager.removeAccountFromUser(r.getRequester(), accountNumber);
                    accounts.remove(accountNumber);
                }
                requests.remove(r);

            } else {
                account acc = pendingAccounts.get(accountNumber);
                accounts.put(accountNumber, acc);
                userManager.addAccountToUser(r.getRequester(), accountNumber);

                pendingAccounts.remove(accountNumber);
                requests.remove(r);
            }
        } catch (Exception e) {
            System.err.println("Could not approve request: " + e.getMessage());
        }

    }

    public static void decline(request r) {
        requests.remove(r);
    }

}