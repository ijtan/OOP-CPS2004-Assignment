package Bank;

public class admin extends person{

    public admin(String name, String surname, String id){
        super(name, surname,id);
    }

    public void approveRequest(request r){
        r.approve(); //calls approve method of request
    }
    
    public void denyRequest(request r) {
        
    }
}
