package br.com.viniciuskegler.dao;


import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import br.com.viniciuskegler.model.Animal;
import br.com.viniciuskegler.model.Cachorro;
import br.com.viniciuskegler.model.Comportamento;
import br.com.viniciuskegler.model.Gato;


public class HibernateUtil {

    private static final SessionFactory sessionFactory; //Singleton

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Animal.class);
            cfg.addAnnotatedClass(Cachorro.class);
            cfg.addAnnotatedClass(Comportamento.class);
            cfg.addAnnotatedClass(Gato.class);
            cfg.configure("/META-INF/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().
                    applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session abrirConexao() {
        return sessionFactory.openSession();
    }
}
