package hh.test;

import static org.junit.Assert.assertEquals;
import hh.dao.PersonDao;
import hh.entity.Person;

import java.math.BigInteger;
import java.util.List;

import org.junit.After;
import org.junit.Before;
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

    private Person p;

    @Before
    public void setUp() {
        p = new Person("John", "", "Doe", "jdoe", "jdoe123", 1, 10);
    }

    @Test
    public void testConnection() {
        personDao.findById(2);
    }

    @Test
    public void testGetByAddressId() {
        List<Person> p = personDao.getPersonByAddressId(10);

        assertEquals(2, p.size());

    }

    @Test
    public void testGetCount() {
        assertEquals(personDao.getCount(), new BigInteger("5"));
    }
    
    @Test
    public void testCreate() {
        personDao.persist(p);

        assertEquals(personDao.getCount(), new BigInteger("6"));
    }

    @After
    public void breakUp() {
        p = null;
    }
}
