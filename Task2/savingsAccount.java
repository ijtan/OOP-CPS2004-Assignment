package Task2;

public class savingsAccount extends account{
    private double interestRate;
    public savingsAccount(String ownerID) {
        super(ownerID);
    }
    public double getInterestRate(){
        return interestRate;
    }
    
}
