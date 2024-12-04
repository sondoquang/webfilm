package com.webmovie.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XJpa {

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("DataTMovies1");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }


    public static <T> List<T> getResultList(Class<T> beanClass, String jpql,Map<String,Integer> map,Object... values) {
        try {
            TypedQuery<T> query = XJpa.getEntityManager().createQuery(jpql, beanClass);
            if (values.length > 0) {
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i + 1, values[i]);
                }
            }
            if(!map.isEmpty()){
                query.setFirstResult(map.get("firstResult"));
                query.setMaxResults(map.get("maxResults"));
            }
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getSingleResult(Class<T> beanClass, String jpql, Object... values) {
        List<T> lists = getResultList(beanClass, jpql,new HashMap<>(), values);
        if (!lists.isEmpty()) {
            return lists.get(0);
        }
        return null;
    }

    public static <T> T excuteUpdate(T entity, int type) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            switch (type) {
                case 1:
                    em.persist(entity);
                    break;
                case 0:
                    em.merge(entity);
                    break;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
//            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        }
        return entity;
    }

    public static <T> T excuteDUpdate(Integer id, Class<T> beanClass) {
        EntityManager em = XJpa.getEntityManager();
        T entity = null;
        try {
            em.getTransaction().begin();
            entity = (T) em.find(beanClass, id);
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        }
        return entity ;
    }
}
