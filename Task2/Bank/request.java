package Bank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class request implements Serializable {
    private String requesterID;
    private Map<String, Object> params = new HashMap<String, Object>();
    approverInterface approver;

    public request(String requesterID, Map<String, Object> params) {
        this.requesterID = requesterID;
        this.params = params;
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

    public String getRequester() {
        return requesterID;
    }

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
