package domain.Repositorio;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import java.util.function.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class AdapterJPA {

    /*
    private static EntityManagerFactory emf;

    private static ThreadLocal<EntityManager> threadLocal;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("db");
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private static EntityManager entityManager = null;

    public static EntityManager entityManager() {
        return getEntityManager();
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = PerThreadEntityManagers.getEntityManager();
        }
        return entityManager;
    }
/*
    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        threadLocal.set(null);
        em.close();
    }*/

    public static void beginTransaction() {
        EntityManager em = AdapterJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if (!tx.isActive()) {
            tx.begin();
        }
    }

    public static void commit() {
        EntityManager em = AdapterJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.commit();
        }

    }

    public static void rollback() {
        EntityManager em = AdapterJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.rollback();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object o) {
        entityManager().persist(o);
    }

    public static void withTransaction(Runnable action) {
        withTransaction(() -> {
            action.run();
            return null;
        });
    }

    public static <A> A withTransaction(Supplier<A> action) {
        beginTransaction();
        try {
            A result = action.get();
            commit();
            return result;
        } catch (Throwable e) {
            rollback();
            throw e;
        }
    }

    public static void cleanCache() {
        if (entityManager() != null) {
            entityManager().clear();
            entityManager().getEntityManagerFactory().getCache().evictAll();
        }
    }
}