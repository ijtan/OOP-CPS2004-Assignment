
package Task2;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class accountManager {

    private static List<request> requests = new ArrayList<request>();
    private static HashMap<String, account> accounts = new HashMap<String, account>();
    // private static HashMap<String, account> cards = new HashMap<String,
    // account>();
    // private static HashMap<String, account> pendingAccounts = new HashMap<String,
    // account>();

    public static request requestNewAccount(String userID) {
        request r = new request(userID, (request) -> approveNewAccount(request));
        requests.add(r);
        return r;
    }

    public static request requestAccountDeletion(String userID, String accNo) {
        // Map<String, Object> pms = new HashMap<>();
        // pms.put("accountNumber", accNo);
        request r = new request(userID, (request) -> approveAccDelete(request, accNo));
        requests.add(r);
        return r;
    }

    public static void approveNewAccount(request r) {
        requests.remove(r);
        String accNo = generateNewAccountKey();
        System.out.println("approve new acc has been run");
        account newAcc = new account(accNo, r.getRequester(), '$');
        accounts.put(accNo, newAcc);
        userManager.addAccountToUser(r.getRequester(), accNo);
    }

    public static void approveAccDelete(request r, String accNo) {
        requests.remove(r);

        System.out.println("approve acc delete has been run: " + accNo);
        accounts.remove(accNo);
        try {
            userManager.removeAccountFromUser(r.getRequester(), accNo);
        } catch (Exception e) {
            System.err.println("Deletion approval failed: "+e.getMessage());
            return;
        }
        requests.remove(r);

    }

    public static request requestNewCard(String userID, String accountNumber) {
        request r = new request(userID, (request) -> approveNewCard(request, accountNumber));
        requests.add(r);
        return r;
    }

    public static request requestCardDeletion(String userID, String accNo, String CardNo) {
        request r = new request(userID, (request) -> approveCardDelete(request, accNo, CardNo));
        requests.add(r);
        return r;
    }

    public static String approveNewCard(request r, String accountNumber) {
        requests.remove(r);
        System.out.println("approve new card has been run");
        return accounts.get(accountNumber).addCard();
    }

    public static void approveCardDelete(request r, String accNo, String Cardno) {
        requests.remove(r);
        try {
            getAccount(accNo).removeCard(Cardno);
        } catch (Exception e) {
            System.err.println("Could not approve card deletion: " + e.getMessage());
        }
    }

    public static String listCards(String accountNumber) {
        String printOut = "";
        try {
            printOut = getAccount(accountNumber).listCards();
        } catch (Exception e) {
            System.err.println("Could not list cards: " + e.getMessage());
        }
        return printOut;
    }

    public static String[] getCardNumbers(String accNo) {
        return setToArr(accounts.get(accNo).getCardNos());
    }

    public static String[] setToArr(Set<String> setStrings) {
        String[] ret = new String[setStrings.size()];

        int index = 0;
        for (String str : setStrings)
            ret[index++] = str;

        return ret;
    }

    public static request getOldestRequest() {
        if (requests.size() > 0)
            return requests.get(0);
        return null;
    }

    private static account getAccount(String accountNumber) throws Exception {
        // return userManager.getUser(userID).getAccount(accountNumber);
        account acc = accounts.get(accountNumber);
        if (acc == null)
            throw new Exception("Could not find account!");
        return acc;
    }

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

    public static void deny(request r) {
        if (requests.contains(r))
            removeRequest(r);
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
        try {
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
}