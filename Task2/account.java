package Task2;

import java.util.ArrayList;
public class account {
    int accountNo;
    int balance;
    char currency;
    ArrayList<card> cards = new ArrayList<card>();
    ArrayList<String> transactions = new ArrayList<String>();

    public boolean subtract(int value){
        if(balance<value)
            return false;
        balance-=value;
        transactions.add("Subtracted " + currency + value);

        return true;
    } 
    
    public void add(int value){ 
        balance += value;
        transactions.add("Added "+currency+value);
    }
    
}
