package com.wide.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

	// m�thode ajouter d'une entit� � la bd
	public void ajouter(Magasin m) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(m);
		tx.commit();

	}

	// m�thode Modifier d'une entit� � partir de la bd
	public void modifier(Magasin m) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(m);
		tx.commit();

	}

	// m�thode Supprimer d'une entit� � partir de la bd
	public void supprimer(Magasin m) {

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		m = entityManager.merge(m); // important
		entityManager.remove(m);
		tx.commit();

	}

	// m�thode Consulter d'une entit� � partir de la bd
	public Object Consulter(Magasin m, Object id) {
		return entityManager.find(m.getClass(), id);
	}

	// m�thode pour lister tous les objets� partir de la bd
	public List<Magasin> selectAll() {
		List<Magasin> utils = getEntityManager().createQuery(
				"select m from Magasin m").getResultList();
		return utils;
	}

}
