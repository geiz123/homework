package hh.controller;

import hh.entity.Person;
import hh.service.PersonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller ("personController")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    public List<Person> getEveryone() {
        return personService.getAllPerson();
    }

}
