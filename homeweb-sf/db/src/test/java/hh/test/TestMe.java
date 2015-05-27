package hh.test;

import static org.junit.Assert.*;

import hh.dao.PersonDao;
import hh.entity.Person;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
public class TestMe {

    @Autowired
    private PersonDao personDao;

    @Test
    public void testConnection() {
        personDao.findById(2);

    }

    @Test
    public void testGetByAddressID() {
        List<Person> p = personDao.getPersonByAddressId(10);

        assertEquals(2, p.size());
    }

    /*
     * Test CRUD
     */

    @Test
    public void testCreate() {
        //Person p = new Person("John", "", "Doe", "jdoe", "jdoe123", 1, 10);
        
        //personDao.persist(p);
        
        
    }
}
