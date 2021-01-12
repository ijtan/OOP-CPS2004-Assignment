package Task2;

public class main {

    public static void main(String args[]) {
        // ArrayList<String> logs = new ArrayList<String>();

        String userID = "008";
        admin ad = new admin("ad", "min", "899");
        userManager.addUser("john", "wick", userID);
        
        ad.approveRequest(accountMediator.getOldestRequest());
        System.out.println("post approve");
        


    }
}