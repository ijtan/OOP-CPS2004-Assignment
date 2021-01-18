package Bank;

import java.io.Serializable;

public abstract class person implements Serializable{
    String name, surname, id;
    person(String name, String surname, String id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }
}
