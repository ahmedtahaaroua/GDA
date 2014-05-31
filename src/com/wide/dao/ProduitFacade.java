/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wide.model.Produit;

/**
 *
 * @author Ahmed Taha
 */
public class ProduitFacade extends AbstractFacade<Produit> {
    @PersistenceContext(unitName = "tn.fitness_GDA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
    
}
