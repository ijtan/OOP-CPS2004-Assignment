package Task2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class account {

    private String accountNo;
    private double balance;
    private char currency;
    private HashMap<String, card> cards = new HashMap<String, card>();
    private ArrayList<String> ownerIDs;
    private ArrayList<HashMap<String,String>> trasnactions;
    private int cardCounter;

    public account(String AccountNumber, ArrayList<String> ownerIDs, char currency) {
        this.ownerIDs = ownerIDs;
        this.accountNo = AccountNumber;
        this.currency = currency;
    }

    public account(String AccountNumber, ArrayList<String> ownerIDs) {
        this.ownerIDs = ownerIDs;
        this.accountNo = AccountNumber;
    }

    public account(String AccountNumber, String ownerID, char currency) {
        this.ownerIDs.add(ownerID);
        this.accountNo = AccountNumber;
        this.currency = currency;
    }

    public account(String AccountNumber, String ownerID) {
        this.ownerIDs.add(ownerID);
        this.accountNo = AccountNumber;
    }




    public void setAccountNumber(String acNo) {
        this.accountNo = acNo;
    }

    public ArrayList<String> getOwnerID() {
        return ownerIDs;
    }

    public char getCurrency() {
        return currency;
    }

    public void subtract(double value) throws Exception {
        if (balance < value) {
            throw new Exception("Insufficient balance in account");
        }
        
        balance -= value;

        HashMap<String, String> log = new HashMap<>();
        log.put("type", "debit");
        log.put("amount", String.valueOf(value));
        log.put("date", getCurrentDate());
        trasnactions.add(log);
    }

    private String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currDateTime = LocalDateTime.now();
        return formatter.format(currDateTime);
    }

    public void add(double value) {
        balance += value;

        HashMap<String, String> log = new HashMap<>();
        log.put("type", "credit");
        log.put("amount", String.valueOf(value));
        log.put("date", getCurrentDate());
        trasnactions.add(log);
    }

    public Set<String> getCardNos() {
        return cards.keySet();
    }

    public String addCard() {
        cardCounter++;
        String cardNo = accountNo + "CARD" + cardCounter;
        card newCard = new card(cardNo, accountNo);
        this.cards.put(cardNo, newCard);
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
            ret += set.getKey() + "\t|\t" + set.getValue().getParentAccount() + '\n';

        return ret;
    }

    public String getAccountNumber() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void addOwner(String ownerID) {
        this.ownerIDs.add(ownerID);
    }

}
