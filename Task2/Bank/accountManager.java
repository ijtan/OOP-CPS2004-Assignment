package Bank;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class accountManager {

    private static List<request> requests = new ArrayList<request>();
    private static HashMap<String, account> accounts = new HashMap<String, account>();
    static private final String possibleChars = "123456789ABC";// using this to allow for
    static private final int max_len = 16;

    
    public static request requestNewAccount(String userID) {
        try {

            if (!userManager.userExists(userID)) //check that user exists
                throw new Exception("Could not find specified user!");
            //here we dont simply do an if esle, since we might get an excpetion in the userExists()
        } catch (Exception e) {
            System.err.println("Error whilst requesting: " + e.getMessage());
        }

        request r = new request(userID, (request) -> approveNewAccount(request)); 
        //in the above line we define the approverInterface as the data we will pass to the interface
        // followed by which function to call once approved
        requests.add(r);
        return r;
    }

    public static request requestAccountDeletion(String userID, String accNo) {
        try {
            if (!userManager.hasAccount(userID, accNo)) //check if the account belongs to the user
                throw new Exception("Could not verify ownership of the account specified!");
        } catch (Exception e) {
            System.err.println("Error whilst requesting: " + e.getMessage());
        }
        //we will call the approveAccDelet function passing the given params, once the request is approved
        request r = new request(userID, (request) -> approveAccDelete(request, accNo));
        requests.add(r);
        return r;
    }

    private static void approveNewAccount(request r) {
        requests.remove(r);
        String accNo = generateNewAccountKey(); //randomly generate a new key
        account newAcc = new account(accNo, r.getRequester(), '€'); 
        accounts.put(accNo, newAcc); //link the account to the user in map
        userManager.addAccountToUser(r.getRequester(), accNo); //put accNo in the user's list
    }

    // called when delete request is approved
    private static void approveAccDelete(request r, String accNo) { 
        requests.remove(r);
        
        try {
            //ensure we remove the account from all owners!
            for(String ownerID : getAccount(accNo).getOwnerIDs())
                userManager.removeAccountFromUser(ownerID, accNo);

            accounts.remove(accNo);

        } catch (Exception e) {
            System.err.println("Deletion approval failed: " + e.getMessage());
            return;
        }
        requests.remove(r);

    }

    public static request requestNewCard(String userID, String accountNumber) {
        try {
            if (!userManager.hasAccount(userID, accountNumber)) //ensure accNo belongs to user
                throw new Exception("Could not verify owenership of the account");

        } catch (Exception e) {
            System.err.println("Error whilst requesting: " + e.getMessage());
        }

        request r = new request(userID, (request) -> approveNewCard(request, accountNumber));
        requests.add(r);
        return r;
    }

    public static request requestCardDeletion(String userID, String accNo, String CardNo) {
        try {
            //ensure account belongs to user
            if (!userManager.hasAccount(userID, accNo))
                throw new Exception("Could not verify owenership of the account");
            account acc = getAccount(accNo);
            if (!acc.hasCard(CardNo)) //ensure card belongs to account
                throw new Exception("Card not found in specified account!");

        } catch (Exception e) {
            System.err.println("Error whilst requesting: " + e.getMessage());
        }

        request r = new request(userID, (request) -> approveCardDelete(request, accNo, CardNo));
        requests.add(r);
        return r;
    }

    private static String approveNewCard(request r, String accountNumber) {
        requests.remove(r);
        return accounts.get(accountNumber).addCard(); //return generated accNo
    }

    private static void approveCardDelete(request r, String accNo, String Cardno) {
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
        //we can only get a set of the keys, thus we use a function to convert it to an array
        return setToArr(accounts.get(accNo).getCardNos());
    }

    //helper function used to convert sets to arrays
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
    //for internal use -> private; return account with exception throw
    private static account getAccount(String accountNumber) throws Exception {
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


    //used to generate unique account keys
    private static String generateNewAccountKey() {
        StringBuilder sb = new StringBuilder(max_len); //stringBuilder is ideal for this use case
        String accNo = "";
        SecureRandom secRan = new SecureRandom(); //we use secure random since the standard random is not
                                                  // cryptographically strong
        do {

            for (int i = 0; i < max_len; i++)//choose max_len chars from the possible_chars pool and append to string
                sb.append(possibleChars.charAt(secRan.nextInt(possibleChars.length())));
            accNo = sb.toString();
        } while ((accounts.containsKey(accNo))); //ensure we dont get any duplicate accNos

        return accNo;
    }

    public static void deny(request r) {
        if (requests.contains(r))
            removeRequest(r);
    }

    public static void transact(String senderAccountNo, String recieverAccountNo, double amount) {

        try {//used to send money between any two Accounts
            account senderAccount = getAccount(senderAccountNo);
            account recieverAccount = getAccount(recieverAccountNo);
            senderAccount.subtract(amount);
            recieverAccount.add(amount);
        } catch (Exception e) {
            System.err.println("Could not transact: " + e.getMessage());
        }

    }
    //used to add an owner to an account
    public static void joinAccount(String accountNo, String newUserId) {
        try {
            if (accounts.containsKey(accountNo)) { //ensure account exists
                userManager.addAccountToUser(newUserId, accountNo);
                accounts.get(accountNo).addOwner(newUserId);
            } else
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
            acc = getAccount(accNo); //throws exception if not found
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
    //used to check if an account exists
    public static boolean accountExists(String accNo){
        if(accounts.containsKey(accNo))
            return true;
        return false;
    }
    //used to check if a user is the owner of an account
    public static boolean isOwner(String userID, String accNo){
        try{
        return getAccount(accNo).getOwnerIDs().contains(userID);
        }catch(Exception e){
            System.err.println("User account not found!");
            return false;
        }
    }
}