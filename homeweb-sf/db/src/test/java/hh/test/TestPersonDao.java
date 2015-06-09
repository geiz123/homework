package hh.test;

import static org.junit.Assert.*;
import hh.dao.PersonDao;
import hh.entity.Person;
import hh.entity.Pet;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
public class TestPersonDao extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject
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

    @Test
    public void testGetPhones() {
        Person pp = personDao.findById(5);

        assertEquals(2, pp.getPhones().size());
    }

    @Test
    public void testGetPet() {
        Person pp = personDao.findById(1);

        // List<PetCollection> pets = pp.getPets();
        //
        // PetCollection pet1 = pets.get(0);
        // PetCollection pet2 = pets.get(1);
        //
        // for (PetCollection p : pets) {
        // System.out.println("### " + p.getDateOfBirth());
        // }
        
        List<Pet> pets = pp.getPets();
        Pet pet1 = pets.get(0);
        Pet pet2 = pets.get(1);
        
        for (Pet p : pets) {
            System.out.println("### " + p.getId().getDateOfBirth());
        }
        
        assertTrue(!pet1.equals(pet2));
    }

    @After
    public void breakUp() {
        p = null;
    }
}
