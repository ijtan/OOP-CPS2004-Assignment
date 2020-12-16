package Task2;

import java.util.List;
import java.util.ArrayList;

public class userManager {
    private static List<user> users = new ArrayList<user>();

    
    public static user getUser(String id) {
        for (user u : users)
            if (u.getID() == id)
                return u;
        return null;
    }

    // public List<account>getUserAccounts(String id){
    //     for (user u : users) {
    //         if(u.getID() == id)
    //     }
    // }   
     public static void addUser(String id) {
        user u = new user();
        users.add(u);
    }

    public static String getOwner(int accountNumber){
        for(user u : users){
            if(u.getAccount(accountNumber)!=null)
                return u.getID();
        }
        return null;
    }
}
