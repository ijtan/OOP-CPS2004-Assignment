package Task2;

import java.io.Serializable;

public abstract class approvable implements Serializable{

    // private static final long serialVersionUID = -8490485024370728923L;

    public abstract void approve(request r);
    public abstract void decline(request r);
    
}
