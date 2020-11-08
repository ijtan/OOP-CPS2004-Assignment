package Task2;

public class request {
    account acc;
    card cr;


    user u;
    int type; //0 = close account 1 = open account 2 = new card 3 = close card
    
    public request(user requester) {
        this.u = requester;
    }
    
    public request(user requester, account acc, boolean toClose) {
        this.u = requester;
        this.acc = acc;
        if(toClose)
            type = 0;
        else
            type = 1;
    }
    
    public request(user requester, account account, card cr, boolean toClose) {
        this.u = requester;
        this.acc = account;
        this.cr = cr;
        if(toClose)
            type = 2;
        else
            type = 3;
    }

    public void approve(){
        if(type==0){
            u.accounts.remove(acc);
        }
        else if (type == 1) {
            u.accounts.add(acc);
        }
        else if (type == 2) {
            int accIndex = u.accounts.indexOf(acc);
            account newAcc = u.accounts.get(accIndex);
            newAcc.cards.add(cr);
            u.accounts.set(accIndex, newAcc);
        }
        else if (type == 3) {
            int accIndex = u.accounts.indexOf(acc);
            account newAcc = u.accounts.get(accIndex);
            newAcc.cards.remove(cr);
            u.accounts.set(accIndex, newAcc);
        }
        
    }

    public void deny() {

    }
}
