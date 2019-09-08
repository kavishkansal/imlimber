package POJO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Person {

    private long id;
    private String firstName;
    private String lastName;
    private int age;

}
