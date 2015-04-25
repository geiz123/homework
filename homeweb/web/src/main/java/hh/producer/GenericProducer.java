/**
 * Needed so we can use @Inject for EntityManagers.
 * w/o it, CDI will complain.
 * @ http://stackoverflow.com/questions/15413227/jsf-deploy-exception-unsatisfied-dependencies-for-type-entitymanager
 * 
 * **Might not need this anymore not that DAO works after we started using stateless with EJB
 */
package hh.producer;

public class GenericProducer {
//    @Produces
//    @PersistenceContext
//    private EntityManager em;

}