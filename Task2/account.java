package Task2;

import java.util.ArrayList;

public class account extends approvable {

    private int accountNo;
    private double balance;
    private char currency;
    private ArrayList<card> cards = new ArrayList<card>();
    // private ArrayList<String> transactions = new ArrayList<String>();
    private String ownerID;




    public account(String ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public void approve(request r) {//TODO
        // if(r.toClose)
        //     accountMediator.approveAccountDeletion(r, this);
        // else
        //     accountMediator.approveNewAccount(r, this);
    }

    @Override
    public void deny(request r) {
    //     accountMediator.deny(r);
    }

    public String getOwnerID(){
        return ownerID;
    }
    
    public char getCurrency() {
        return currency;
    }

    public void subtract(double value) throws Exception {
        if (balance < value){
            throw new Exception("Not enough money in sender account");
        }
        balance -= value;
        // transactions.add("Subtracted " + currency + value);
    }

    public void add(double value) {
        balance += value;
        // transactions.add("Added " + currency + value);
    }

    public ArrayList<card> getAllCards() {
        return cards;
    }

    public void addCard(card c) {
        this.cards.add(c);
    }

    public void removeCard(card c) throws Exception {
        if (this.cards.contains(c))
            cards.remove(c);
        else
            throw new Exception("Card not found!");
    }

    public int getAccountNumber() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }
    

    
}
