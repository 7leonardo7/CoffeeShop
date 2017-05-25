package com.esipovich.coffeeshop.dao;

import com.esipovich.coffeeshop.model.CoffeeOrder;
import com.esipovich.coffeeshop.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class CoffeeOrderDaoHibernate implements CoffeeOrderDao{

    @Override
    public void save(CoffeeOrder coffeeOrder) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(coffeeOrder);
        session.getTransaction().commit();
    }

    @Override
    public void update(CoffeeOrder coffeeOrder) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(coffeeOrder);
        session.getTransaction().commit();
    }

    @Override
    public void delete(CoffeeOrder coffeeOrder) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(coffeeOrder);
        session.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CoffeeOrder> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<CoffeeOrder> coffeeOrders = session.createQuery("from CoffeeOrder").list();
        session.getTransaction().commit();
        return coffeeOrders;
    }
}
