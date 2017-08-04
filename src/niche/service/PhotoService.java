package niche.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import niche.bean.Photo;

public class PhotoService 
{
	
private static final String COL_VISIBLE = "visible";
private static final String COL_USERID = "user_userid";

/******************        PHOTOS TABLE METHODS        ******************/	
	
	public static List<Photo> getAllPhotos() 
	{
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos", Photo.class);
			photos = q.getResultList();
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}
	
	public static List <Photo> getPrivatePhotosOfUser(int userid) {
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos where " + COL_VISIBLE + " = '0' AND " +
														COL_USERID + " = " + userid, Photo.class);
			photos = q.getResultList();
			Collections.reverse(photos);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}
	
	public static List <Photo> getAllPublicPhotos() 
	{
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos where " + COL_VISIBLE + " = '1'", Photo.class);
			photos = q.getResultList();

			Collections.reverse(photos);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}

	public static List<Photo> getPhotosOfUser(int id) 
	{
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos where " + COL_USERID + " = " + id, Photo.class);
			photos = q.getResultList();

			Collections.reverse(photos);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}
	
	public static boolean addPhoto(Photo p) {
		boolean added = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
		
			em.merge(p);
			
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
	
	public static boolean updatePhoto(int id, Photo newinfo)
	{
		boolean updated = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Photo p = em.find(Photo.class, id);
			
			p.setTitle(newinfo.getTitle());
			p.setDescription(newinfo.getDescription());
			p.setPath(newinfo.getPath());
			p.setVisible(newinfo.isVisible());
			p.setUser(newinfo.getUser());
			
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
	
	public static boolean deletePhoto(int id)
	{
		boolean deleted = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Photo p = em.find(Photo.class, id);
			
			em.remove(p);
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
	
	public static Photo getPhoto(int id)
	{
		Photo p = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			p = em.find(Photo.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return p;
	}

	public static List <Photo> getPublicPhotosOfUser(int userid) {
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos where " + COL_VISIBLE + " = '1' AND " +
														COL_USERID + " = " + userid, Photo.class);
			photos = q.getResultList();
			Collections.reverse(photos);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}

}
