 package Task2;

import java.io.Serializable;

public class request implements Serializable{
    private String requesterID;
    //int type; //0 = close account 1 = open account 2 = new card 3 = close card
    approvable action;
    String[] params;
    

    public request(String requester, approvable a) {
        this.requesterID = requester;
        this.action = a;
    }

    public request(String requester, approvable a, String params[]) {
        this.requesterID = requester;
        this.action = a;
        this.params = params;
    }

    
    public String getRequester(){
        return requesterID;
    }

    public String[] getParams() {
        return params;
    }

    public String getParam(int x) {
        return params[x];
    }

    public void approve(){
        action.approve(this);
    }
    public void deny() {
        action.deny(this);
    }
}


// public <T> requestRemove (user requester, account account, card cr, boolean
// toClose, T t, void method) {
// this.u = requester;
// T approvable;
// if(toClose)
// type = 2;
// else
// type = 3;
// }

// public void approve(){
// if(type==0){
// u.accounts.remove(acc);
// }
// else if (type == 1) {
// u.accounts.add(acc);
// }
// else if (type == 2) {
// int accIndex = u.accounts.indexOf(acc);
// account newAcc = u.accounts.get(accIndex);
// newAcc.addCard(cr);
// u.accounts.set(accIndex, newAcc);
// }
// else if (type == 3) {
// int accIndex = u.accounts.indexOf(acc);
// account newAcc = u.accounts.get(accIndex);
// try{newAcc.removeCard(cr);}catch(Exception e){System.err.println("Unable to
// remove card: "+e.getMessage());}

// u.accounts.set(accIndex, newAcc);
// }

// }

