/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.model;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed Taha
 */
@Entity
@Table(name = "ligne_commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneCommande.findAll", query = "SELECT l FROM LigneCommande l"),
    @NamedQuery(name = "LigneCommande.findByIdligneCmd", query = "SELECT l FROM LigneCommande l WHERE l.idligneCmd = :idligneCmd"),
    @NamedQuery(name = "LigneCommande.findByQteCom", query = "SELECT l FROM LigneCommande l WHERE l.qteCom = :qteCom")})
public class LigneCommande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ligne_Cmd")
    private Integer idligneCmd;
    @Column(name = "QteCom")
    private Integer qteCom;
    @JoinColumn(name = "refProd", referencedColumnName = "refProd")
    @ManyToOne(optional = false)
    private Produit refProd;
    @JoinColumn(name = "id_Commande", referencedColumnName = "id_Commande")
    @ManyToOne(optional = false)
    private Commande idCommande;

    public LigneCommande() {
    }

    public LigneCommande(Integer idligneCmd) {
        this.idligneCmd = idligneCmd;
    }

    public Integer getIdligneCmd() {
        return idligneCmd;
    }

    public void setIdligneCmd(Integer idligneCmd) {
        this.idligneCmd = idligneCmd;
    }

    public Integer getQteCom() {
        return qteCom;
    }

    public void setQteCom(Integer qteCom) {
        this.qteCom = qteCom;
    }

    public Produit getRefProd() {
        return refProd;
    }

    public void setRefProd(Produit refProd) {
        this.refProd = refProd;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idligneCmd != null ? idligneCmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommande)) {
            return false;
        }
        LigneCommande other = (LigneCommande) object;
        if ((this.idligneCmd == null && other.idligneCmd != null) || (this.idligneCmd != null && !this.idligneCmd.equals(other.idligneCmd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.LigneCommande[ idligneCmd=" + idligneCmd + " ]";
    }
    
}
