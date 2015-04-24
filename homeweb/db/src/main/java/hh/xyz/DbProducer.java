/**
 * Needed so we can use @Inject for EntityManagers.
 * w/o it, CDI will complain.
 * @ http://stackoverflow.com/questions/15413227/jsf-deploy-exception-unsatisfied-dependencies-for-type-entitymanager
 * 
 * **Might not need this anymore not that DAO works after we started using stateless with EJB
 */
package hh.xyz;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DbProducer {
    @Produces
    @PersistenceContext
    private EntityManager em;
}
