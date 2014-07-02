package com.wide.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.wide.jpaUtil.JPAutil;


//classe contenant les méthodes génériques sauver,  supprimer, consulter par clé primaire (Id)
public  class Dao {
private EntityManager entityManager=JPAutil.getEntityManager("GDA");
 
 
//méthode ajouter d'une entité à  la bd
	 public   void ajouter(Object o)
	{
	 	EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.persist(o);
	 	tx.commit();
	 	  
	}
	 //méthode Modifier d'une entité à  partir de la bd
	 public   void modifier(Object o)
		{
		 	EntityTransaction tx = entityManager.getTransaction();
		 	tx.begin();
		 	entityManager.merge(o);
		 	tx.commit();
		 	  
		}
	 
	 public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	//méthode Supprimer d'une entité à  partir de la bd
	 public  void supprimer(Object o)
	{

		 
		EntityTransaction tx = entityManager.getTransaction();
	    tx.begin();
	    o=entityManager.merge(o); // important
	    entityManager.remove(o);
	    tx.commit();
	 	   
	}
	 //méthode Consulter d'une entité à  partir de la bd
	 public  Object Consulter(Object o,Object id)
	{
	 	return entityManager.find(o.getClass(), id);
		}
	 
	 //méthode pour lister tous les objetsà  partir de la bd
	 public List<Object> Lister(String entityName) {
		 List<Object> objets =entityManager.createQuery("select o from "+entityName+" o").getResultList();
		 return objets;
		 }
	 
}
