package Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class account {

    private String accountNo;
    private double balance;
    private char currency;
    private HashMap<String, card> cards = new HashMap<String, card>();
    private String ownerID;
    private int cardCounter;

    public account(String AccountNumber, String ownerID, char currency) {
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

    public void setAccountNumber(String acNo) {
        this.accountNo = acNo;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public char getCurrency() {
        return currency;
    }

    public void subtract(double value) throws Exception {
        if (balance < value) {
            throw new Exception("Insufficient balance in account");
        }
        balance -= value;
        // transactions.add("Subtracted " + currency + value);
    }

    public void add(double value) {
        balance += value;
    }

    public Set<String> getCardNos() {
        return cards.keySet();
    }

    public String addCard() {
        cardCounter++;
        String cardNo = accountNo+"CARD"+cardCounter;
        card newCard = new card(cardNo, accountNo);
        this.cards.put(cardNo,newCard);
        return cardNo;
    }

    public void removeCard(String cardNo) throws Exception {
        if (this.cards.containsKey(cardNo))
            cards.remove(cardNo);
        else
            throw new Exception("Card not found!");
    }

    public String listCards() {
        String ret = "";
        for (Map.Entry<String, card> set : cards.entrySet())
            ret+=set.getKey() + "\t|\t" + set.getValue().getParentAccount()+'\n';
        
        return ret;
    }

    public String getAccountNumber() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }
    

    
}
