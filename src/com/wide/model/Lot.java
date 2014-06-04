/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.model;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed Taha
 */
@Entity
@Table(name = "lot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lot.findAll", query = "SELECT l FROM Lot l"),
    @NamedQuery(name = "Lot.findByRefProduit", query = "SELECT l FROM Lot l WHERE l.refProduit = :refProduit"),
    @NamedQuery(name = "Lot.findByQte", query = "SELECT l FROM Lot l WHERE l.qte = :qte")})
public class Lot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "refProduit")
    private Integer refProduit;
    @Column(name = "qte")
    private Integer qte;
    
    private Collection<Lignelivraisonmagasin> lignelivraisonmagasinCollection;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "id_fournisseur")
    @ManyToOne
    private Fournisseurs idFournisseur;

    public Lot() {
    }

    public Lot(Integer refProduit) {
        this.refProduit = refProduit;
    }

    public Integer getRefProduit() {
        return refProduit;
    }

    public void setRefProduit(Integer refProduit) {
        this.refProduit = refProduit;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    @XmlTransient
    public Collection<Lignelivraisonmagasin> getLignelivraisonmagasinCollection() {
        return lignelivraisonmagasinCollection;
    }

    public void setLignelivraisonmagasinCollection(Collection<Lignelivraisonmagasin> lignelivraisonmagasinCollection) {
        this.lignelivraisonmagasinCollection = lignelivraisonmagasinCollection;
    }

    public Fournisseurs getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseurs idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refProduit != null ? refProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lot)) {
            return false;
        }
        Lot other = (Lot) object;
        if ((this.refProduit == null && other.refProduit != null) || (this.refProduit != null && !this.refProduit.equals(other.refProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Lot[ refProduit=" + refProduit + " ]";
    }
    
}
