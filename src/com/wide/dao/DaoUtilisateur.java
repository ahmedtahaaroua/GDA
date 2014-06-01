package com.wide.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.wide.model.Utilisateur;

public class DaoUtilisateur {
	private static final String JPA_UNIT_NAME = "tn.fitness_GDA_war_1.0-SNAPSHOTPU";
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory(
					JPA_UNIT_NAME).createEntityManager();
		}
		return entityManager;
	}
	 
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

		//méthode ajouter d'une entité à  la bd
		 public void ajouter(Utilisateur u)
		{
		 	EntityTransaction tx = entityManager.getTransaction();
		 	tx.begin();
		 	entityManager.persist(u);
		 	tx.commit();
		 	  
		}
		 //méthode Modifier d'une entité à  partir de la bd
		 public   void modifier(Utilisateur u)
			{
			 	EntityTransaction tx = entityManager.getTransaction();
			 	tx.begin();
			 	entityManager.merge(u);
			 	tx.commit();
			 	  
			}
		 
		 //méthode Supprimer d'une entité à  partir de la bd
		 public  void supprimer(Utilisateur u)
		{

			 
			EntityTransaction tx = entityManager.getTransaction();
		    tx.begin();
		    u=entityManager.merge(u); // important
		    entityManager.remove(u);
		    tx.commit();
		 	   
		}
		 //méthode Consulter d'une entité à  partir de la bd
		 public  Object Consulter(Utilisateur u,Object id)
		{
		 	return entityManager.find(u.getClass(), id);
			}
		 
		 //méthode pour lister tous les objetsà  partir de la bd
			public List<Utilisateur> selectAll() {
				List<Utilisateur> utils = getEntityManager().createQuery("select u from Utilisateur u").getResultList();
				return utils;
			}
			public Utilisateur verifier(Utilisateur ut)
			{
			String vlogin=ut.getLogin();
			String vpwd=ut.getMotPasse();
			Query query;
			query = getEntityManager().createQuery("select u from Utilisateur u where u.login=:login and u.mot_passe=:pwd");
			query.setParameter("login", vlogin);
			query.setParameter("pwd", vpwd);
			List<Utilisateur> utils=query.getResultList();
			return utils.get(0);
			}
		
			
}


