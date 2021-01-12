package Task2;

import java.util.ArrayList;
import java.util.HashMap;

public class user extends person {
    // private ArrayList<account> accounts = new ArrayList<account>(); TODO we will
    // change this so user does not have access to his account ! he will only bave a
    // list of accountNumbers
    private ArrayList<String> accountNumbers = new ArrayList<String>();
    // private ArrayList<String> transactions = new ArrayList<String>();

    public user(String name, String surname, String id) {
        super(name, surname, id);
    }

    public void moveMoney(double value, String myAccount, String recipientAccountNumber) {
        // if (bankHoliday)
        // return false;

        accountMediator.transact(myAccount, recipientAccountNumber, value);
    }

    public void requestNewAccount() {
        accountMediator.requestNewAccount(this.id);
    }

    public void deposit(String accountNumber, double value) {
        accountMediator.depositToAccount(accountNumber, value);
    }

    public void withdraw(String accountNumber, double value) {
        accountMediator.withdrawFromAccount(accountNumber, value);
    }

    public double getBalance(String accNo) {
        ;
        return accountMediator.getAccountBalance(accNo);
    }

    public String listAccounts() {
        String accList = "";
        for (String accNo : accountNumbers)
            try {
                accList += accNo + "\t | " + accountMediator.getCurrency(accNo);
                accList += accountMediator.getAccountBalance(accNo) + '\n';
            } catch (Exception e) {
                System.err.println("Could not fetch accounts as there was an issue in one or more accounts: "+e.getMessage());
            }
        return accList;
    }

    public ArrayList<String> getAccountNos(){
        return accountNumbers;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public void addAccount(String accNo) {
        accountNumbers.add(accNo);
    }

    public void removeAccount(String accNo) throws Exception {
        if (this.accountNumbers.contains(accNo))
            this.accountNumbers.remove(accNo);
        else
            throw new Exception("Account not found!");
    }

    public String getID() {
        return id;
    }

    // public account getAccount(int accNumber) throws Exception {
    //     for (account acc : accounts)
    //         if (acc.getAccountNumber() == accNumber)
    //             return acc;

    //     throw new Exception("Account not found");
    // }

}
