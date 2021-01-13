package Task2;

import java.util.ArrayList;

public class account extends approvable {

    private String accountNo;
    private double balance;
    private char currency;
    private ArrayList<card> cards = new ArrayList<card>();
    // private ArrayList<String> transactions = new ArrayList<String>();
    private String ownerID;


    public account(String ownerID) {
        this.ownerID = ownerID;
    }

    public account(String ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public void approve(request r) {
        if(r.getParam(0).equals("delete"));
        accountMediator.approveAccountDeletion(r, r.getParam(1));
    }    

    @Override
    public void deny(request r) {
        accountMediator.deny(r);
    }

    public void setAccountNumber(String acNo) {
        this.accountNo = acNo;
    }

    public String getOwnerID(){
        return ownerID;
    }
    
    public char getCurrency() {
        return currency;
    }

    public void subtract(double value) throws Exception {
        if (balance < value){
            throw new Exception("Insufficient balance in account");
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

    public String getAccountNumber() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }
    

    
}
