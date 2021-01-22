package Bank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class request implements Serializable {
    private String requesterID;// holds the id of the user which requested
    approverInterface approver;//holds an instance of an approverInterface
    //thanks to this, the request does not know / does not need to know what the action will be once approve
    //we can just always to request.approve() regardless of the actionable

    public request(String requesterID, approverInterface appInt) {
        this.requesterID = requesterID; 
        this.approver = appInt; 
    }

    public void approve(){
        approver.approve(this); //all we need to do to approve
    }

    public String getRequester() {
        return requesterID;
    }
}
