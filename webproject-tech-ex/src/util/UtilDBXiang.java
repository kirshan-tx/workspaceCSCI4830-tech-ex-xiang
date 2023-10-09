/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.MyTableXiangTechEx;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBXiang {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<MyTableXiangTechEx> listExpenses() {
      List<MyTableXiangTechEx> resultList = new ArrayList<MyTableXiangTechEx>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> expenses = session.createQuery("FROM MyTableXiangTechEx").list();
         for (Iterator<?> iterator = expenses.iterator(); iterator.hasNext();) {
            MyTableXiangTechEx expense = (MyTableXiangTechEx) iterator.next();
            resultList.add(expense);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<MyTableXiangTechEx> listExpenses(String keyword) {
      List<MyTableXiangTechEx> resultList = new ArrayList<MyTableXiangTechEx>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((MyTableXiangTechEx)session.get(MyTableXiangTechEx.class, 1)); // use "get" to fetch data
         List<?> expenses = session.createQuery("FROM MyTableXiangTechEx").list();
         for (Iterator<?> iterator = expenses.iterator(); iterator.hasNext();) {
            MyTableXiangTechEx expense = (MyTableXiangTechEx) iterator.next();
            if (expense.getExpense().startsWith(keyword)) {
               resultList.add(expense);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static List<MyTableXiangTechEx> listExpensesByCategory(String category) {
	      List<MyTableXiangTechEx> resultList = new ArrayList<MyTableXiangTechEx>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         System.out.println((MyTableXiangTechEx)session.get(MyTableXiangTechEx.class, 1)); // use "get" to fetch data
	         List<?> expenses = session.createQuery("FROM MyTableXiangTechEx").list();
	         for (Iterator<?> iterator = expenses.iterator(); iterator.hasNext();) {
	            MyTableXiangTechEx expense = (MyTableXiangTechEx) iterator.next();
	            if (expense.getCategory().startsWith(category)) {
	               resultList.add(expense);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }


   public static void createExpense(String expense, int amount, String date, String category) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new MyTableXiangTechEx(expense, Integer.valueOf(amount), date, category));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
