package com.wide.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.wide.model.Utilisateur;

public class DaoUtilisateur {
	private static final String JPA_UNIT_NAME = "GDA";
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

		//m�thode ajouter d'une entit� �  la bd
		 public void ajouter(Utilisateur u)
		{
		 	EntityTransaction tx = entityManager.getTransaction();
		 	tx.begin();
		 	entityManager.persist(u);
		 	tx.commit();
		 	  
		}
		 //m�thode Modifier d'une entit� �  partir de la bd
		 public   void modifier(Utilisateur u)
			{
			 	EntityTransaction tx = entityManager.getTransaction();
			 	tx.begin();
			 	entityManager.merge(u);
			 	tx.commit();
			 	  
			}
		 
		 //m�thode Supprimer d'une entit� �  partir de la bd
		 public  void supprimer(Utilisateur u)
		{

			 
			EntityTransaction tx = entityManager.getTransaction();
		    tx.begin();
		    u=entityManager.merge(u); // important
		    entityManager.remove(u);
		    tx.commit();
		 	   
		}
		 //m�thode Consulter d'une entit� �  partir de la bd
		 public  Object Consulter(Utilisateur u,Object id)
		{
		 	return entityManager.find(u.getClass(), id);
			}
		 
		 //m�thode pour lister tous les objets�  partir de la bd
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


