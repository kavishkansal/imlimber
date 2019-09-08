package Controller;

import POJO.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PersonService;

import java.util.ArrayList;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;


    @RequestMapping("/all")
    public ArrayList<Person> getAll(){
        return personService.getAll();
    }

    @RequestMapping("{id}")
    public  Person getPerson(@PathVariable("id") String id){
        return personService.getPerson(id);
    }
}
