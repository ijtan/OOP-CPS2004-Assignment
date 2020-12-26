package Task2;

import java.io.Serializable;

public class person implements Serializable{
    protected String name, surname, id;
    person(String name, String surname, String id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }
}
