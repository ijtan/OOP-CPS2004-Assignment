package Task2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// import Task2.accountMediator.approverInterface;

public class request implements Serializable {
    private String requesterID;
    // int type; //0 = close account 1 = open account 2 = new card 3 = close card
    private Map<String, Object> params = new HashMap<String, Object>();
    // T referral;
    approverInterface approver;

    public request(String requesterID, Map<String, Object> params) {
        this.requesterID = requesterID;
        this.params = params;
        // approverInterface approverI;
        // this.appr = ap;
    }

    public request(String requesterID, approverInterface appInt) {
        this.requesterID = requesterID;
        this.approver = appInt;
    }

    public request(String requesterID, approverInterface appInt, Map<String, Object> params) {
        this.requesterID = requesterID;
        this.approver = appInt;
        this.params = params;
    }

    public void approve(){
        approver.approve(this);
    }

    // public <T> request(String requesterID, Map<String, Object> params,approver
    // ap) {
    // this.requesterID = requesterID;
    // this.params = params;
    // this.appr = ap;
    // }

    // public request(String requester, approvable a, Map<String,String> params) {
    // this.requesterID = requester;
    // this.action = a;
    // this.params = params;
    // }

    public String getRequester() {
        return requesterID;
    }

    // public Map<String,Object> getParams() {
    // return params;
    // }

    public Object getParam(String key) throws Exception {
        Object obj = params.get(key);
        if (obj == null)
            throw new Exception("Key not associated with any value!");
        return obj;
    }

    public Object getParamNoExcept(String key) {
        Object obj = params.get(key);
        return obj;
    }
}
