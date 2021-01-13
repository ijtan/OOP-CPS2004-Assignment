package Task2;

public class card extends approvable{
    String parentAccNo;


    @Override
    public void approve(request r){
       accountMediator.addCardToAccount(parentAccNo, this);
    }

    @Override
    public void decline(request r){
        accountMediator.removeCardFromAccount(parentAccNo, this);
    }
}
