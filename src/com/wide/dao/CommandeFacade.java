/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wide.model.Commande;

/**
 *
 * @author Ahmed Taha
 */
public class CommandeFacade extends AbstractFacade<Commande> {
    @PersistenceContext(unitName = "tn.fitness_GDA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }
    
}
