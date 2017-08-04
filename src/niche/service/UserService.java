package niche.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import niche.bean.User;

public class UserService 
{
	
/******************        USERS TABLE METHODS        ******************/	
	
	public static final String TABLE = "user";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	public static final String COL_ID = "userid";
	public static final String COL_DESC = "description";
	
	
	public static List<User> getAllUsers() 
	{
		List<User> users = new ArrayList<User>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<User> q = em.createQuery("FROM users", User.class);
			users = q.getResultList();
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return users;
	}
	
	public static boolean addUser(User u) 
	{
		boolean added = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(u);
			
			trans.commit();
			added = true;
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return added;
	}

	public static boolean updateUser(int id, User newinfo)
	{
		boolean updated = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			User u = em.find(User.class, id);
			
			u.setUsername(newinfo.getUsername());
			u.setPassword(newinfo.getPassword());
			u.setDescription(newinfo.getDescription());
			
			trans.commit();
			updated = true;
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return updated;
	}
	
	public static boolean deleteUser(int id)
	{
		boolean deleted = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			User u = em.find(User.class, id);
			
			em.remove(u);
			trans.commit();
			deleted = true;
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return deleted;
	}
	
	public static User getUser(int id)
	{
		User u = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			u = em.find(User.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return u;
	}
	

	public static User findUser(String username, String password) {
		User user = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM users where " + COL_USERNAME + " = :username AND " + COL_PASSWORD + " = :password";
			TypedQuery<User> q = em.createQuery(query, User.class);
			q.setParameter("username", username);
			q.setParameter("password", password);
			List <User> users = q.getResultList();
			
			if(users != null && users.size() != 0) {
				user = users.get(0);
			}
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}	
		
		return user;
	}
	
	public static void main (String[] args) {
		
		List<User> users = getAllUsers();
		for(User u: users)
		{
			System.out.println(u.toString());
		}
	}
}
