package services;

import POJO.Person;
import Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public class PersonService {

    private HashMap<String, Person> personHashMap = new HashMap<>();

    @Autowired
    private PersonRepo personRepo;

    PersonService() {

        try {
            Person p1 = new Person();
            p1.setId("1");
            p1.setFirstName("Arish");
            p1.setLastName("Ballana");
            p1.setAge(24);
            persistPerson(p1);

            Person p2 = new Person();
            p1.setId("2");
            p1.setFirstName("Kavish");
            p1.setLastName("Kansal");
            p1.setAge(25);
            persistPerson(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validatePerson(Person person) throws Exception {
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            throw new Exception("Invalid Name");
        }

        if (person.getLastName() == null || person.getLastName().isEmpty()) {
            throw new Exception("Invalid Name");
        }

        if (person.getAge() <= 0 || person.getAge() >= 150) {
            throw new Exception("Invalid Age");
        }
    }

    public void persistPerson(Person person) throws Exception {
        validatePerson(person);
        personRepo.save(person);
    }

    public Person getPerson(String id) throws Exception {
        Optional<Person> first = personRepo.findAll().stream().filter(x -> x.getId().equalsIgnoreCase(id)).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            throw new Exception("No such employee found");
        }
    }

    public Person getPerson(Long uuid) throws Exception {
        Optional<Person> byId = personRepo.findById(uuid);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new Exception("No Such employee found");
        }
    }

    public ArrayList<Person> getAll() {
        return new ArrayList<Person>(personRepo.findAll());
    }

}
