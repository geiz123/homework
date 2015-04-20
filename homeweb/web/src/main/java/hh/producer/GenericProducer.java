/**
 * Needed so we can use @Inject for EntityManagers.
 * w/o it, CDI will complain.
 * @ http://stackoverflow.com/questions/15413227/jsf-deploy-exception-unsatisfied-dependencies-for-type-entitymanager
 */
package hh.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericProducer {
    @Produces
    @PersistenceContext
    private EntityManager em;

}