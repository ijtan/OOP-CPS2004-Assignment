package Task2;

import java.util.ArrayList;

public class currentAccount extends account{
    private double interest = 0;
    private ArrayList<String> chequeBooks= new ArrayList<String>();
    public currentAccount(String ownerID) {
        super(ownerID);
    }
    public void createChequeBook(String id){
        //implement approval process here but for now just add
        chequeBooks.add(id);

    }

    public String getChequeBook(String id) throws Exception{
        // implement approval process here but for now just add
        for (String cb : chequeBooks) 
            if(cb.equals(id))
                return cb;
        
        throw new Exception("Cheque book not found!");

    }

    public double getInterestRate(){
        return interest;
    }
    
}
