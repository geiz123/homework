package hh.xyz;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DbProducer {
    @Produces
    @PersistenceContext
    private EntityManager em;
}
