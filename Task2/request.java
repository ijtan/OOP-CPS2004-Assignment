 package Task2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class request implements Serializable{
    private String requesterID;
    //int type; //0 = close account 1 = open account 2 = new card 3 = close card
    private approvable action;
    
    private Map<String,String> params = new HashMap<String, String>();

    
    

    public request(String requester, approvable a) {
        this.requesterID = requester;
        this.action = a;
    }

    public request(String requester, approvable a, Map<String,String> params) {
        this.requesterID = requester;
        this.action = a;
        this.params = params;
    }

    public approvable getAction() {
        return action;
    }

    public String getRequester(){
        return requesterID;
    }

    public Map<String,String> getParams() {
        return params;
    }

    public String getParam(String key) {
        return params.get(key);
    }

    public void approve(){
        action.approve(this);
    }
    public void deny() {
        action.decline(this);
    }
}

