/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.dao;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.wide.model.Lignelivraisonmagasin;

/**
 *
 * @author Ahmed Taha
 */
public class LignelivraisonmagasinFacade extends AbstractFacade<Lignelivraisonmagasin> {
	private static final String JPA_UNIT_NAME = "GDA";
	private EntityManager em;
    @Override
    protected EntityManager getEntityManager() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory(
					JPA_UNIT_NAME).createEntityManager();
		}
		return em;
	}

    public LignelivraisonmagasinFacade() {
        super(Lignelivraisonmagasin.class);
    }
    
}
