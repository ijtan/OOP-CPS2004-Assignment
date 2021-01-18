package Bank;

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
        if (accountNumbers.contains(myAccount))
            requestorInterface.transact(myAccount, recipientAccountNumber, value);            
        else
            System.err.println("Account specified not found under user");
    }

    public void requestNewAccount() {
        requestorInterface.requestNewAccount(id);
    }

    public void requestAccountDelete(String accountNo) {
        if(accountNumbers.contains(accountNo))
            requestorInterface.requestAccountDeletion(id,accountNo);
        else
            System.err.println("Account specified not found under user");
    }

    public boolean hasAccountNo(String accountNo){
        if(this.accountNumbers.contains(accountNo))
            return true;
        return false;
    }

    public void deposit(String accountNumber, double amount) {
        if (accountNumbers.contains(accountNumber))
            requestorInterface.deposit(accountNumber, amount);
        else
            System.err.println("Account specified not found under user");
    }

    public void withdraw(String accountNumber, double amount) {
        if (!accountNumbers.contains(accountNumber))
            System.err.println("Account specified not found under user");
        else
            requestorInterface.withdraw(accountNumber, amount);
    }

    public double getBalance(String accNo) {
        if (!accountNumbers.contains(accNo))
            System.err.println("Account specified not found under user");
        else
            return requestorInterface.getBalance(accNo);
        return -1;
    }

    public String listAccounts() {
        return requestorInterface.listAccounts(accountNumbers);
    }

    public ArrayList<String> getAccountNos() {
        return accountNumbers;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public void addAccountToList(String accNo) {
        // System.out.println("adding account!: " + accNo);
        accountNumbers.add(accNo);
    }

    public void removeAccountFromList(String accNo) {
        if (this.accountNumbers.contains(accNo))
            this.accountNumbers.remove(accNo);
        else
            System.err.println("Could not delete account as it was not found!");
    }

    public String getID() {
        return id;
    }
}
