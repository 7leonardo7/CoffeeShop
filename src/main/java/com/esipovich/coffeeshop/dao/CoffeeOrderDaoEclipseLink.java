package com.esipovich.coffeeshop.dao;

import com.esipovich.coffeeshop.model.CoffeeOrder;
import com.esipovich.coffeeshop.util.DBUtil.EclipseLinkUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CoffeeOrderDaoEclipseLink implements CoffeeOrderDao{

    @Override
    public void save(CoffeeOrder coffeeOrder) {
        EntityManager entityManager = EclipseLinkUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(coffeeOrder);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(CoffeeOrder coffeeOrder) {
        EntityManager entityManager = EclipseLinkUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(coffeeOrder);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(CoffeeOrder coffeeOrder) {
        EntityManager entityManager = EclipseLinkUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        //In general when entity is deattached,
        // EntityManager's method merge takes entity as argument
        // and returns managed instance. Entity given as argument
        // does not transform to be attached
        entityManager.remove(entityManager.merge(coffeeOrder));
        entityManager.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CoffeeOrder> getAll() {
        EntityManager entityManager = EclipseLinkUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from CoffeeOrder e", CoffeeOrder.class);
        entityManager.getTransaction().commit();
        return query.getResultList();
    }
}
