package niche.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import niche.bean.PhotoTag;

public class TagsService 
{
	
/******************        PHOTO TAGS TABLE METHODS        ******************/	
	
	public static List<PhotoTag> getAllPhotoTags() {
		List<PhotoTag> phototags = new ArrayList<PhotoTag>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<PhotoTag> q = em.createQuery("FROM tags", PhotoTag.class);
			phototags = q.getResultList();
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return phototags;
	}
	
	public static boolean addPhotoTag(PhotoTag pt) {
		boolean added = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(pt);
			
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
	
	public static boolean deleteTag(int id)
	{
		boolean deleted = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			PhotoTag p = em.find(PhotoTag.class, id);
			
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
}
