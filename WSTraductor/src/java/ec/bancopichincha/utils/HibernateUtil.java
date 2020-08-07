/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.bancopichincha.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *  Establece la conección a la base de datos .
 * @author roni9
 */
public class HibernateUtil {

    private static final SessionFactory SESION_MODULO;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SESION_MODULO = configuration.buildSessionFactory(service);
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSesionModulo() {
        return SESION_MODULO;
    }
}
