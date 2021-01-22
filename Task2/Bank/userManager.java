package Bank;

// import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class userManager {
    private static HashMap<String, user> usersMap = new HashMap<String, user>(); 

    //used to verify ownership of an account
    public static boolean hasAccount(String uid, String accNo){
        user u =  getUser(uid);
        if(u==null){
            System.err.println("User not found!");
            return false;
        }
        return u.hasAccountNo(accNo);
    }
    //used to veridy existance of user
    public static boolean userExists(String uid){
        if(usersMap.containsKey(uid))
            return true;
        return false;
    }

    private static user getUser(String id) {
        return usersMap.get(id);
    }

    //used to get a list of acount numbers owned by a user
    public static ArrayList<String> getUserAccounts(String ID) {
        user u = usersMap.get(ID);
        if (u == null) {
            System.err.println("Could not fetch user accounts!");
            return null;
        }
        return u.getAccountNos();
    }

    public static String listUserAccounts(String ID) {
        user u = usersMap.get(ID);
        if (u == null) {
            System.err.println("Could not fetch user accounts!");
            return null;
        }
        return u.listAccounts();
    }
    //adds user to the syste,
    public static void addUser(String name, String surname, String id) {
        user u = new user(name, surname, id);
        usersMap.put(id, u);
    }

    //used to add an account to user's account list
    public static void addAccountToUser(String id, String accountNo) {
        user u = usersMap.get(id);
        if (u == null) {
            System.err.println("Could not find user to add account to!");
            return;
        }
        u.addAccountToList(accountNo);
    }

    public static void removeAccountFromUser(String id, String accountNo) throws Exception {
        user u = usersMap.get(id);
        if (u == null)
            throw new Exception("User ID specified not found!");
            
        u.removeAccountFromList(accountNo);
    }
}
