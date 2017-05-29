package com.esipovich.coffeeshop.util.DBUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//попробовать конфигурацию без класса
public class EclipseLinkUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("orders-pu");
    }


    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
