package Task2;

import java.util.ArrayList;

public class account  {

    private String accountNo;
    private double balance;
    private char currency;
    private ArrayList<card> cards = new ArrayList<card>();
    // private ArrayList<String> transactions = new ArrayList<String>();
    private String ownerID;

    // public interface requestHandler{

    // }

    public account(String AccountNumber, String ownerID,char currency) {
        this.ownerID = ownerID;
        this.accountNo = AccountNumber;
        this.currency = currency;
    }

    public account(String AccountNumber, String ownerID) {
        this.ownerID = ownerID;
        this.accountNo = AccountNumber;
    }

    public account(String ownerID) {
        this.ownerID = ownerID;
    }

    // @Override
    // public void approve(request r) {
    //     if(r.getParam("type")!= null && r.getParam("type").equals("delete"))
    //         accountMediator.approveAccountDeletion(r, r.getParam("accountNumber"));
    //     else{
    //         accountMediator.approveNewAccount(r, r.getParam("accountNumber"));
    //     }
    // }

    // @Override
    // public void decline(request r) {
    //     accountMediator.deny(r);
    // }

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
