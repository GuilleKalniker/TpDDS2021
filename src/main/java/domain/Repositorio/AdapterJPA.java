package domain.Repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AdapterJPA {
    public static void persistir(Object o) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(o);

        transaction.commit();
    }
}
