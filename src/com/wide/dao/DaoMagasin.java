package com.wide.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.wide.model.Magasin;

public class DaoMagasin {
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

	// méthode ajouter d'une entité à la bd
	public void ajouter(Magasin m) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(m);
		tx.commit();

	}

	// méthode Modifier d'une entité à partir de la bd
	public void modifier(Magasin m) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(m);
		tx.commit();

	}

	// méthode Supprimer d'une entité à partir de la bd
	public void supprimer(Magasin m) {

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		m = entityManager.merge(m); // important
		entityManager.remove(m);
		tx.commit();

	}

	// méthode Consulter d'une entité à partir de la bd
	public Object Consulter(Magasin m, Object id) {
		return entityManager.find(m.getClass(), id);
	}

	// méthode pour lister tous les objetsà partir de la bd
	public List<Magasin> selectAll() {
		List<Magasin> utils = getEntityManager().createQuery(
				"select m from Magasin m").getResultList();
		return utils;
	}

}
