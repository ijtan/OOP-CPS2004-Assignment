package Bank;

import java.util.ArrayList;

import Bank.userManager;


public class tests {
    public static void runTests() {
        System.out.println("\033[1;36mStarting Unit Tests! \033[0m\n");
        String UID = "4802L";
        userCreationTest(UID);
        assert userManager.userExists(UID) : "User does not exist post creation!";
        accountRequestTest(UID);
        assert accountManager.getOldestRequest()!=null : "Request does not exist post creation!";
        adminApprovalTest();
        assert userManager.getUserAccounts(UID).size() > 0 : "User Account not found Post Request approval!";

        String accNo = userManager.getUserAccounts(UID).get(0);
        userAccountAccessTest(UID);
        accountOpperationsTest(accNo);



        String UID2 = "1134M";userManager.addUser("John", "Wick", UID2); accountRequestTest(UID2);adminApprovalTest();
        String accNo2 = userManager.getUserAccounts(UID2).get(0);
        testTransact(accNo2, accNo);   
        

        System.out.println("\n\033[1;32mAll Unit Tests passed! \033[0m\n");
        // System.out.println("All Unit Tests passed!\n\n");
    }
    public static void userCreationTest(String UID){
        // String UID = "4802L";
        userManager.addUser("Ethan", "Zammit", UID);     
    }
    
    public static void accountRequestTest(String UID) {
        // String UID = "4802L";
        accountManager.requestNewAccount(UID);
        // return UID;
    }

    public static void adminApprovalTest() {
        admin ad = new admin("Admin", "Man", "3311M");
        request toApprove = accountManager.getOldestRequest();
        ad.approveRequest(toApprove);
    }

    public static void userAccountAccessTest(String UID) {
        ArrayList<String> accs =  userManager.getUserAccounts(UID);
        System.out.println("User Has: "+ accs.size()+" account/s");
        System.out.println(userManager.listUserAccounts(UID));
    }

    public static void accountOpperationsTest(String accountNumber) {
        double valueBefore = accountManager.getAccountBalance(accountNumber);
        double valueDifference = 10;
        accountManager.depositToAccount(accountNumber, valueDifference);
        assert accountManager.getAccountBalance(accountNumber) == valueBefore+valueDifference : "Some value lost whilst depositing!";

        valueBefore = accountManager.getAccountBalance(accountNumber);
        valueDifference = 100;
        accountManager.depositToAccount(accountNumber, valueDifference);
        assert accountManager.getAccountBalance(accountNumber) == valueBefore + valueDifference
                : "Some value lost whilst depositing!";

        valueBefore = accountManager.getAccountBalance(accountNumber);
        valueDifference = 50;
        accountManager.withdrawFromAccount(accountNumber, valueDifference);
        assert accountManager.getAccountBalance(accountNumber) == valueBefore - valueDifference
                : "Some value lost whilst withdrawing!";

    }

    public static void testTransact(String accNoA,String accNoB ){
        double aVal = accountManager.getAccountBalance(accNoA);
        double bVal = accountManager.getAccountBalance(accNoB);
        double transferAmount = 20.0;

        //NOT ENOUGH MONEY TEST!
        accountManager.transact(accNoA, accNoA, transferAmount);

        double aValPost = accountManager.getAccountBalance(accNoA);
        double bValPost = accountManager.getAccountBalance(accNoB);

        assert aVal == aValPost : "Transfer error: Money deducted even when not enough balance!";
        assert bVal == bValPost : "Transfer error: Money addded to reciever account even when not enough balance!";

        //ENOUGH MONEY TEST!
        accountManager.depositToAccount(accNoA, transferAmount);

        aVal = accountManager.getAccountBalance(accNoA);
        bVal = accountManager.getAccountBalance(accNoB);

        accountManager.transact(accNoA, accNoA, transferAmount);
        
        aValPost= accountManager.getAccountBalance(accNoA);
        bValPost= accountManager.getAccountBalance(accNoB);

        System.out.println("Apre: " + aVal);
        System.out.println("Bpre: " + bVal);
        System.out.println("Apost: "+ aValPost);
        System.out.println("Bpost: "+ bValPost);
        assert aVal-transferAmount == aValPost : "Transfer error: Money not deducted from sender!";
        assert bVal+transferAmount == bValPost : "Transfer error: Money not addded to reciever!";
        // assert
    }

    public static void cardCreationTest(String accountNo){

    }

    public static void cardDeletionTest(String accountNo) {

    }

    public static void jointAccountCreationTest() {

    }

    public static void jointAccountAccessTest() {

    }

    public static void accountDeletionTest(String accountNo) {

    }
    
    
}
