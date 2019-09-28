package Controller;

import POJO.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import Exceptions.UnAuthorizedException;
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
        try{
        		return personService.getAll();
        }catch(UnAuthorizedException e){
        	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
        	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        	//e.printStackTrace();
        	//return null;
        }
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
