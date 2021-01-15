package Task2;

public class card {
    private String parentAccNo;

    public card(String CardNo, String parentAccountNumber) {
        this.parentAccNo = parentAccountNumber;
    }

    public void setParentAccount(String parAccNo) {
        this.parentAccNo = parAccNo;
    }

    public String getParentAccount() {
        return parentAccNo;
    }

    public void transact(String destinationAccount, double amount) {
        requestorInterface.transact(parentAccNo, destinationAccount, amount);
    }

}
