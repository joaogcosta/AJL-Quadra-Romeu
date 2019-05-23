package util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LHRIES
 */
public class JPAUtil {
    private static final String NOMEPU= "appAsphaltCrowsPubPU";
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory(NOMEPU);
    
    public static EntityManager createManager(){
        return factory.createEntityManager();
    }
}
