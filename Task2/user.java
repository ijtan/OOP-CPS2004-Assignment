package Task2;

import java.util.ArrayList;

public class user {
    ArrayList<account> accounts = new ArrayList<account>();
    ArrayList<String> transactions = new ArrayList<String>();
    String name, surname, id;

    public boolean moveMoney(int value, account myAccount, account recipient, boolean bankHoliday) {
        int myAccIndex;

        if(bankHoliday){
            return false;
        }
        if ((myAccIndex = accounts.indexOf(myAccount)) == -1) {
            return false;
        }
        myAccount = accounts.get(myAccIndex);

        if (myAccount.balance < value)
            return false;
        if (recipient.currency != myAccount.currency)
            return false;

        if(!myAccount.subtract(value))
            return false;
        recipient.add(value);

        transactions.add("Sent money from account ["+myAccount.accountNo+"] to account [" + recipient.accountNo+"] a value of" + recipient.currency + value);
        return true;

    }


    public request requestNewAccount(){
        request r = new request(this);
        return r;
    }

    

    public int getBalance(account a){
        if(accounts.contains(a))
            return a.balance;
        return -1;
    }
    public String listAccounts(){
        String accList = "";
        for (account acc : accounts) 
            accList+=acc.accountNo+" | "+acc.currency+acc.balance+'\n';
        return accList;
    }
}
