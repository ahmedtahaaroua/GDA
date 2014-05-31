package com.wide.dao;



import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.wide.jpaUtil.JPAutil;


//classe contenant les m�thodes g�n�riques sauver,  supprimer, consulter par cl� primaire (Id)
public  class Dao {
private EntityManager entityManager=JPAutil.getEntityManager("GDA");
 
 
//m�thode ajouter d'une entit� �  la bd
	 public   void ajouter(Object o)
	{
	 	EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.persist(o);
	 	tx.commit();
	 	  
	}
	 //m�thode Modifier d'une entit� �  partir de la bd
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
	//m�thode Supprimer d'une entit� �  partir de la bd
	 public  void supprimer(Object o)
	{

		 
		EntityTransaction tx = entityManager.getTransaction();
	    tx.begin();
	    o=entityManager.merge(o); // important
	    entityManager.remove(o);
	    tx.commit();
	 	   
	}
	 //m�thode Consulter d'une entit� �  partir de la bd
	 public  Object Consulter(Object o,Object id)
	{
	 	return entityManager.find(o.getClass(), id);
		}
	 
	 //m�thode pour lister tous les objets�  partir de la bd
	 public List<Object> Lister(String entityName) {
		 List<Object> objets =entityManager.createQuery("select o from "+entityName+" o").getResultList();
		 return objets;
		 }
	 
}
