package Task2;

import java.util.ArrayList;

public class user {
    private ArrayList<account> accounts = new ArrayList<account>();
    private ArrayList<String> transactions = new ArrayList<String>();
    private String name, surname, id;

    public boolean moveMoney(double value, int myAccount, int recipient) {
        // if (bankHoliday) 
        //     return false;
        
        userAccountMediator.transact(myAccount, recipient, value);

        transactions.add("Sent money from account ["+myAccount+"] to account [" + recipient+"] a value of" + userAccountMediator.getCurrency(myAccount) + value);
        return true;

    }


    public void requestNewAccount(){
        userAccountMediator.requestNewAccount(this.id);
    }

    

    public double getBalance(account a){
        if(accounts.contains(a))
            return a.getBalance();
        return -1;
    }

    public String listAccounts(){
        String accList = "";
        for (account acc : accounts) 
            accList+=acc.getAccountNumber()+"\t | "+acc.getCurrency()+acc.getBalance()+'\n';
        return accList;
    }

    public String getFullName(){
        return name + " " + surname;
    }

    public void addAccount(account a) {
        this.accounts.add(a);
    }

    public void removeAccount(account a) throws Exception {
        if(this.accounts.contains(a))
            this.accounts.remove(a);
        else
            throw new Exception("Account not found!");
    }
    public String getID(){
        return id;
    }

    public void addCard(card cr, account a){
        if(this.accounts.contains(a)){
            var acc = this.accounts.get(this.accounts.indexOf(a));
            acc.addCard(cr);
            this.accounts.set(this.accounts.indexOf(a), acc);
        }else
            System.err.println("Account not found!");
        
    }

    public account getAccount(int accNumber){
        for (account acc : accounts) {
            if(acc.getAccountNumber() == accNumber)
                return acc;
        }
        System.err.println("Account not found");
        return null;
    }

}
