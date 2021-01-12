package Task2;

// import java.util.List;
import java.util.HashMap;
// import java.util.ArrayList;

public class userManager {
    // private static List<user> users = new ArrayList<user>();
    private static HashMap<String, user> usersMap = new HashMap<String, user>(); 

    public static user getUser(String id) throws Exception {
        user u = usersMap.get(id);
        if(u==null)
            throw new Exception("User with specified ID not found!");
        return u;
    }

    // public List<account>getUserAccounts(String id){
    // for (user u : users) {
    // if(u.getID() == id)
    // }
    // }

    public static void addUser(String name, String surname, String id) {
        user u = new user(name, surname, id);
        usersMap.put(id, u);
        // users.add(u);
    }

    public static void addAccountToUser(String id, String accountNo){
        user u = usersMap.get(id);
        if(u==null){
            System.err.println("Could not find user to add account to!");
            return;
        }
        // accountMediator.removeAccount(accountNo);
        u.addAccount(accountNo);
    }

    public static void removeAccountFromUser(String id, String accountNo) throws Exception {
        user u = usersMap.get(id);
        if (u == null) 
            throw new Exception("User ID specified not found!");
        // accountMediator.removeAccount(accountNo);
        u.removeAccount(accountNo);
    }
}
