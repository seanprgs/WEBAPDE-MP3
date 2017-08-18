package niche.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import niche.bean.Photo;
import niche.bean.User;

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
	
	public static List<Photo> getNumberOfPhotos(int photoCounter, int size) 
	{
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos", Photo.class);
			List<Photo> result = q.getResultList();
			
			//reverse the query to have the latest entry in the first index of the list
			Collections.reverse(result);
			
			//get only N(size) queries
			if(size > result.size() - photoCounter)//check if number of entries is less than size (capacity)
				photos = result.subList(photoCounter, result.size()); //sublist(from, to) from = inclusive, to = exclusive
			else
				photos = result.subList(photoCounter, photoCounter + size);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}

	public static List<Photo> getNumberOfPhotosPublic(int photoCounter, int size) 
	{
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos where " + COL_VISIBLE + " = '1'", Photo.class);
			List<Photo> result = q.getResultList();
			
			//reverse the query to have the latest entry in the first index of the list
			Collections.reverse(result);
			
			//get only N(size) queries
			if(size > result.size() - photoCounter)//check if number of entries is less than size (capacity)
				photos = result.subList(photoCounter, result.size()); //sublist(from, to) from = inclusive, to = exclusive
			else
				photos = result.subList(photoCounter, photoCounter + size);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}
	
	public static List <Photo> getPrivatePhotosOfUser(int userid, int photoCounter, int size) {
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			//need to get private photos uploaded by user AND private photos shared to user
			TypedQuery<Photo> q = em.createQuery("FROM photos where " + COL_VISIBLE + " = '0'", Photo.class);
			List<Photo> queryResult = q.getResultList();
			
			Photo temp = null;
			
			for(int i = 0; i < queryResult.size(); i++)
			{
				temp = queryResult.get(i);
				
				//check if photo shared to user
				for(User u : temp.getHasAccess())
				{
					if(u.getUserid() == userid)
						photos.add(temp);
				}
				
				//check if photo uploaded by user as private
				if(temp.getUser().getUserid() == userid)
					photos.add(temp);
			}
			
			Collections.reverse(photos);
			
			//get only N(size) queries
			if(size > photos.size() - photoCounter)//check if number of entries is less than size (capacity)
				photos = photos.subList(photoCounter, photos.size()); //sublist(from, to) from = inclusive, to = exclusive
			else
				photos = photos.subList(photoCounter, photoCounter + size);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return photos;
	}
	
	public static List <Photo> getPhotosSeenByUser (int id, int photoCounter, int size) {
		List<Photo> photos = new ArrayList<Photo>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Photo> q = em.createQuery("FROM photos", Photo.class);
			
			photos = q.getResultList();
			
			for(int i = 0; i < photos.size(); i++)
			{
				Iterator <User> access = photos.get(i).getHasAccess().iterator();
				boolean retain = false;
				
				while(access.hasNext()) {
					User acc = access.next();
					if(id == acc.getUserid()) {
						retain = true;
					}
					
					if(retain)
						break;
				}
				
				if (id == photos.get(i).getUser().getUserid()) {
					retain = true;
				}
				
				if(!retain) {
					photos.remove(i);
					i--;
				}
			}

			Collections.reverse(photos);			
			
			//get only N(size) queries
			if(size > photos.size() - photoCounter)//check if number of entries is less than size (capacity)
				photos = photos.subList(photoCounter, photos.size()); //sublist(from, to) from = inclusive, to = exclusive
			else
				photos = photos.subList(photoCounter, photoCounter + size);
			
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
			p.setTags(newinfo.getTags());
			
			em.merge(p);
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

	public static boolean sharePhoto(int id, User giveAccess)
	{
		boolean shared = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			
			Photo p = null;
			p = em.find(Photo.class, id);
			
			Set<User> hasAccess = p.getHasAccess();
			
			if(!hasAccess.contains(giveAccess));
				hasAccess.add(giveAccess);
				
			p.setHasAccess(hasAccess);
			
			em.merge(p);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return shared;
	}
}
