package Task2;

import java.util.List;
import java.util.ArrayList;

public class userManager {
    private static List<user> users = new ArrayList<user>();

    
    public static user getUser(String id) throws Exception{
        for (user u : users)
            if (u.getID() == id)
                return u;
        throw new Exception("User with specified ID not found!");
    }

    // public List<account>getUserAccounts(String id){
    //     for (user u : users) {
    //         if(u.getID() == id)
    //     }
    // } 
      
     public static void addUser(String name, String surname, String id) {
        user u = new user(name,surname,id);
        users.add(u);
    }

    public static String getOwner(int accountNumber) throws Exception{
        for(user u : users){
            if(u.getAccount(accountNumber)!=null)
                return u.getID();
        }
        return null;
    }
}
