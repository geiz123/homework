package hh.test;

import hh.dao.PersonDao;
import hh.entity.Person;

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
    public void testJob() {
        Person p = personDao.findById(2);
        
        System.out.println(p.getAddress().getId());
    }
}
