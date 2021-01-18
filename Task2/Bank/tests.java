package Bank;

import java.util.ArrayList;

public class tests {
    public static void runTests() {
        System.out.println("\033[1;36mStarting Unit Tests! \033[0m\n");
        String UID = "4802L";
        userCreationTest(UID);
        assert userManager.userExists(UID) : "User does not exist post creation!";
        accountRequestTest(UID);
        assert accountManager.getOldestRequest() != null : "Request does not exist post creation!";
        adminApprovalTest();
        assert userManager.getUserAccounts(UID).size() > 0 : "User Account not found Post Request approval!";

        String accNo = userManager.getUserAccounts(UID).get(0);
        accountOpperationsTest(accNo);

        String UID2 = "1134M";
        userManager.addUser("John", "Wick", UID2);
        accountRequestTest(UID2);
        adminApprovalTest();
        String accNo2 = userManager.getUserAccounts(UID2).get(0);
        testTransact(accNo2, accNo);

        cardRequestTest(UID, accNo);
        assert accountManager.getCardNumbers(accNo).length == 0 : "Card added before approval!";
        adminApprovalTest();
        assert accountManager.getCardNumbers(accNo).length == 1 : "Card not added!";
        String cardNo = accountManager.getCardNumbers(accNo)[0];
        cardDeletionTest(UID, accNo, cardNo);
        assert accountManager.getCardNumbers(accNo).length == 1 : "Card deleted before deletion approval!";
        adminApprovalTest();
        assert accountManager.getCardNumbers(accNo).length == 0 : "Card not deleted!";

        jointAccountCreationTest(UID2,accNo);
        // adminApprovalTest();
        jointAccountAccessTest(UID, UID2, accNo);

        accountDeletionTest(UID, accNo);
        assert accountManager.accountExists(accNo) : "Account deleted pre approval!";
        assert userManager.getUserAccounts(UID).contains(accNo) : "Account deleted pre approval!";

        adminApprovalTest();
        assert !accountManager.accountExists(accNo) : "Account still accesable after deletion!";
        assert !userManager.getUserAccounts(UID).contains(accNo) : "Account still accesable after deletion!";
        assert !userManager.getUserAccounts(UID2).contains(accNo) : "Account still accesable FROM JOINT USERS after deletion!";
        
        accountDeletionTest(UID2, accNo2);
        adminApprovalTest();
       

        
        assert !accountManager.accountExists(accNo2) : "Account still accesable after deletion!";


        System.out.println("\n\033[1;32mAll Unit Tests passed! \033[0m\n");
        // System.out.println("All Unit Tests passed!\n\n");

    }

    public static void userCreationTest(String UID) {
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

    public static void accountOpperationsTest(String accountNumber) {
        double valueBefore = accountManager.getAccountBalance(accountNumber);
        double valueDifference = 10;
        accountManager.depositToAccount(accountNumber, valueDifference);
        assert accountManager.getAccountBalance(accountNumber) == valueBefore + valueDifference
                : "Some value lost whilst depositing!";

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

    public static void testTransact(String accNoA, String accNoB) {
        double aVal = accountManager.getAccountBalance(accNoA);
        double bVal = accountManager.getAccountBalance(accNoB);
        double transferAmount = 20.0;

        // NOT ENOUGH MONEY TEST!
        System.out.print("Transact test when not enough balance: ");
        accountManager.transact(accNoA, accNoB, transferAmount);

        double aValPost = accountManager.getAccountBalance(accNoA);
        double bValPost = accountManager.getAccountBalance(accNoB);

        assert aVal == aValPost : "Transfer error: Money deducted even when not enough balance!";
        assert bVal == bValPost : "Transfer error: Money addded to reciever account even when not enough balance!";

        // ENOUGH MONEY TEST!
        
        accountManager.depositToAccount(accNoA, transferAmount);

        aVal = accountManager.getAccountBalance(accNoA);
        bVal = accountManager.getAccountBalance(accNoB);

        accountManager.transact(accNoA, accNoB, transferAmount);

        aValPost = accountManager.getAccountBalance(accNoA);
        bValPost = accountManager.getAccountBalance(accNoB);

        assert aVal - transferAmount == aValPost : "Transfer error: Money not deducted from sender!";
        assert bVal + transferAmount == bValPost : "Transfer error: Money not addded to reciever!";
    }

    public static void cardRequestTest(String UID, String accountNo) {
        accountManager.requestNewCard(UID, accountNo);
    }

    public static void cardDeletionTest(String UID, String accountNo, String cardNo) {
        accountManager.requestCardDeletion(UID, accountNo, cardNo);
    }

    public static void jointAccountCreationTest(String UID2,String accNo) {
        accountManager.joinAccount(accNo, UID2);
    }

    public static void jointAccountAccessTest(String UID1, String UID2, String accNo) {
        assert userManager.hasAccount(UID2, accNo) : "Joint account un-accesable!";
        assert userManager.getUserAccounts(UID2).contains(accNo) : "Joint account not found in added person!";
        
        // assert userManager.
    }

    public static void accountDeletionTest(String UID, String accountNo) {
        accountManager.requestAccountDeletion(UID, accountNo);
    }

}
