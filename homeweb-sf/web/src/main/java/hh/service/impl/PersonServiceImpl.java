package hh.service.impl;

import hh.dao.PersonDao;
import hh.entity.Person;
import hh.service.PersonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("personServiceImpl")
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;
    
    public List<Person> getAllPerson() {
        return personDao.findAll();
    }

}