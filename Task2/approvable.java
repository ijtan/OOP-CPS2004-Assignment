package Task2;

import java.io.Serializable;

// public abstract interface requestHandler {
//     public void approve(request r);

//     public void decline(request r);
// }

public abstract class approvable{
        // public static void approve(request r) throws Exception {
        //     throw new Exception("This should be overriden to handle object approval");
        // };
        // public static void decline(request r) throws Exception {
        //     throw new Exception("This should be overriden to handle object decline");
        // };
        public abstract void approve(request r);
        public abstract void decline(request r);
    
}
