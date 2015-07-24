package hh.test;

import static org.junit.Assert.*;
import hh.dao.PersonDao;
import hh.entity.Appointment;
import hh.entity.Person;
import hh.entity.Pet;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void testConnection() {
        personDao.findById(2);

    }

    @Ignore
    @Test
    public void testGetByAddressId() {
        List<Person> p = personDao.getPersonByAddressId(10);

        assertEquals(2, p.size());

    }

    @Ignore
    @Test
    public void testGetCount() {
        assertEquals(personDao.getCount(), new BigInteger("5"));
    }

    @Ignore
    @Test
    public void testCreate() {
        personDao.persist(p);

        assertEquals(personDao.getCount(), new BigInteger("6"));
    }

    @Ignore
    @Test
    public void testGetPhones() {
        Person pp = personDao.findById(5);

        assertEquals(2, pp.getPhones().size());
    }

    @Ignore
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

    @Ignore
    @Test
    public void testGetPersonByAddressId() {
        List<Person> persons = personDao.getPersonByAddressId(10);

        for (Person p : persons) {
            System.out.println("## " + p.getFirstName());

            List<Pet> pets = p.getPets();
            for (Pet pp : pets) {
                System.out.println("### " + pp.getId().getPetName());
            }
        }
    }
    
    /*
     * Note: findbyid is kinda messed up.  it won't get related entity like pet/appointment from person.
     */
    @Test
    public void testMe() {
        List<Person> persons = personDao.getByRangeWithAlivePet(0, 100);
        
        for (Person p : persons) {
            System.out.println("## " + p.getFirstName());

            List<Appointment> pets = p.getAppointments();
            for (Appointment pp : pets) {
                System.out.println("### " + pp.getId().getApptDt());
            }
        }
    }

    @After
    public void breakUp() {
        p = null;
    }
}
