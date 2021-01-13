package Task2;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class userManager {
    private static List<user> users = new ArrayList<user>();
    // TODO implement this
    private static HashMap<String, user> usersMap = new HashMap<String, user>();

    // public static user getUser(String id) throws Exception {
    // user u = usersMap.get(id);
    // try{
    // if(u==null)
    // throw new Exception("User with specified ID not found!");
    // }catch(Exception e){
    // System.err.println("Could not get user: "+e.getMessage());
    // }
    // return u;
    // }

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

    public static void addUser(String name, String surname, String id) {
        user u = new user(name, surname, id);
        usersMap.put(id, u);
        users.add(u);
    }

    public static void addAccountToUser(String id, String accountNo) {
        user u = usersMap.get(id);
        if (u == null) {
            System.err.println("Could not find user to add account to!");
            return;
        }
        u.addAccount(accountNo);
    }

    public static void removeAccountFromUser(String id, String accountNo) throws Exception {
        user u = usersMap.get(id);
        if (u == null)
            throw new Exception("User ID specified not found!");

        u.removeAccount(accountNo);
    }
}
