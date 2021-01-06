package Task2;

import java.util.ArrayList;

public class user extends person {
    // private ArrayList<account> accounts = new ArrayList<account>(); TODO we will change this so user does not have access to his account ! he will only bave a list of accountNumbers
    private List<String> accountNumbers = new ArrayList<String>();
    // private ArrayList<String> transactions = new ArrayList<String>();

    public user(String name, String surname, String id){
        super(name, surname,id);
    }
    public void moveMoney(double value, int myAccount, int recipientAccountNumber) {
        // if (bankHoliday)
        // return false;

        accountMediator.transact(this.getID(), myAccount, recipientAccountNumber, value);
    }

    public void requestNewAccount() {
        accountMediator.requestNewAccount(this.id);
    }

    public void deposit(int accountNumber, double value) {
        account acc = null;
        try {
           acc = accountMediator.getAccount(this.id, accountNumber);
        } catch (Exception e) {
            System.err.println("Error while depositing!" + e.getMessage());
            return;
        }
        if(acc!=null)
            acc.add(value);
        
    }

    public void withdraw(int accountNumber, double value) {
        account acc = null;
        try {
            acc = accountMediator.getAccount(this.id, accountNumber);
            acc.subtract(value);
        } catch (Exception e) {
            System.out.println("Error whilst withdrawing: " + e.getMessage());
            return;
        }
    }

    public double getBalance(account a) {
        if (accounts.contains(a))
            return a.getBalance();
        return -1;
    }

    public String listAccounts() {
        String accList = "";
        for (account acc : accounts)
            accList += acc.getAccountNumber() + "\t | " + acc.getCurrency() + acc.getBalance() + '\n';
        return accList;
    }

    public ArrayList<account> getAccounts(){
        return accounts;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public void addAccount(account a) {
        this.accounts.add(a);
    }

    public void removeAccount(account a) throws Exception {
        if (this.accounts.contains(a))
            this.accounts.remove(a);
        else
            throw new Exception("Account not found!");
    }

    public String getID() {
        return id;
    }

    public account getAccount(int accNumber) throws Exception {
        for (account acc : accounts)
            if (acc.getAccountNumber() == accNumber)
                return acc;

        throw new Exception("Account not found");
    }

}
