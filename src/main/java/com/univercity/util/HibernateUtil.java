package com.univercity.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: KVB
 * Date: 15.12.13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            //creates the session factory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
