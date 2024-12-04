package com.webmovie.daoimples;

import com.webmovie.daos.LogDAO;
import com.webmovie.entities.Log;
import com.webmovie.utils.XJpa;
import jakarta.persistence.EntityManager;

public class LogDAOImple implements LogDAO {
    EntityManager em = XJpa.getEntityManager();

    @Override
    public void create(Log item) {
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
