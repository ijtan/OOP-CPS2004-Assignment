package Task2;

import java.io.Serializable;

public abstract class approvable implements Serializable{
    public abstract void approve(request r);    
    public abstract void deny(request r);
}
