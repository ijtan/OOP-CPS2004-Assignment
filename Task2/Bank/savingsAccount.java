package Bank;

public class savingsAccount extends account {
    private double interestRate;

    public savingsAccount(String ownerID, String accountNumber) {
        super(ownerID, accountNumber);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double ir) {
        interestRate = ir;
    }

}
