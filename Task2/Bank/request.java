package Bank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class request implements Serializable {
    private String requesterID;
    approverInterface approver;

    public request(String requesterID, approverInterface appInt) {
        this.requesterID = requesterID;
        this.approver = appInt;
    }

    public void approve(){
        approver.approve(this);
    }

    public String getRequester() {
        return requesterID;
    }
}
