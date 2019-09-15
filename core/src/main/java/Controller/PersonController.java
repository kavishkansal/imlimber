package Controller;

import POJO.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.PersonService;

import java.util.ArrayList;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persist")
    public void persist(@RequestBody Person person) {
        try {
            System.out.println(person.toString());
            personService.persistPerson(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/all")
    public ArrayList<Person> getAll() {
        return personService.getAll();
    }

    @RequestMapping("{id}")
    public Person getPerson(@PathVariable("id") String id) {
        try {
            return personService.getPerson(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
