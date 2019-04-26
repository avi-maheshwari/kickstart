package com.avi.DemoHibCache;

import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.type.Type;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//to store the data in database we use save...
    	        
        Configuration cf = new Configuration().configure().addAnnotatedClass(Alien.class);
        //buildsessionfactory is depricated
        //so we wud use servicefactorybuilder
        ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cf.getProperties()).buildServiceRegistry();
        SessionFactory sf = cf.buildSessionFactory(sr); //now its working with object of service registry
        Session session = sf.openSession();
        
    	//in order to get the details from the database
    	
        Alien student = null;//can be null also as object is return by get command //new Alien();
        
        Transaction tx = session.beginTransaction();
        
        student = (Alien) session.get(Alien.class, 103); //class, primary key
        
        System.out.println(student);

        //since you r firing the same query again (it is by default in first level cache
        student = (Alien) session.get(Alien.class, 103); //class, primary key
        
        System.out.println(student);

        tx.commit();
        session.close();
        
        Session session1 = sf.openSession();
        Transaction tx1 = session1.beginTransaction();
        
        //now the query would be called again as the prev session is closed and in the new session the data is not in first level cache
        //for second level cache we need to have third party support --> ehcache (second level cache)
        student = (Alien) session1.get(Alien.class, 103);
        
        System.out.println(student);
        
        //after setting cacheable, ehcache, cache in the all the settings finally second level cache is achieved
        tx1.commit();
        session1.close();

        Session session2 = sf.openSession();
        Query q = session2.createQuery("from Alien where aid=102"); 
        q.setCacheable(true);
        student = null;
        tx = session2.beginTransaction();
        student = (Alien) q.uniqueResult();        
        System.out.println(student);
        tx.commit();
        session2.close();
        
        //cache only enabled for get call so cache not being used in case of query call
        //a different property setting is req to enable the caching on query
        //first entry in hibernate cnf file and also query cacheable
        Session session3 = sf.openSession();
        Query q1 = session3.createQuery("from Alien where aid=102");    	
        q1.setCacheable(true);
        tx = session3.beginTransaction();
        student = (Alien) q1.uniqueResult();
        System.out.println(student);
        tx.commit();
        session3.close();
    }
}
