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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed Taha
 */
@Entity
@Table(name = "lignelivraisonmagasin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lignelivraisonmagasin.findAll", query = "SELECT l FROM Lignelivraisonmagasin l"),
    @NamedQuery(name = "Lignelivraisonmagasin.findByIdLigLivMag", query = "SELECT l FROM Lignelivraisonmagasin l WHERE l.idLigLivMag = :idLigLivMag"),
    @NamedQuery(name = "Lignelivraisonmagasin.findByQteLiv", query = "SELECT l FROM Lignelivraisonmagasin l WHERE l.qteLiv = :qteLiv")})
public class Lignelivraisonmagasin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLigLivMag")
    private Integer idLigLivMag;
    @Column(name = "qteLiv")
    private Integer qteLiv;
    @JoinColumn(name = "refProduit", referencedColumnName = "refProd")
    @ManyToOne
    private Produit refProduit;
    @JoinColumn(name = "idLivraisonMagasin", referencedColumnName = "idLivraisonMagasin")
    @ManyToOne
    private Livraisonmagasin idLivraisonMagasin;

    public Lignelivraisonmagasin() {
    }

    public Lignelivraisonmagasin(Integer idLigLivMag) {
        this.idLigLivMag = idLigLivMag;
    }

    public Integer getIdLigLivMag() {
        return idLigLivMag;
    }

    public void setIdLigLivMag(Integer idLigLivMag) {
        this.idLigLivMag = idLigLivMag;
    }

    public Integer getQteLiv() {
        return qteLiv;
    }

    public void setQteLiv(Integer qteLiv) {
        this.qteLiv = qteLiv;
    }

   

    public Produit getRefProduit() {
		return refProduit;
	}

	public void setRefProduit(Produit refProduit) {
		this.refProduit = refProduit;
	}

	public Livraisonmagasin getIdLivraisonMagasin() {
        return idLivraisonMagasin;
    }

    public void setIdLivraisonMagasin(Livraisonmagasin idLivraisonMagasin) {
        this.idLivraisonMagasin = idLivraisonMagasin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLigLivMag != null ? idLigLivMag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lignelivraisonmagasin)) {
            return false;
        }
        Lignelivraisonmagasin other = (Lignelivraisonmagasin) object;
        if ((this.idLigLivMag == null && other.idLigLivMag != null) || (this.idLigLivMag != null && !this.idLigLivMag.equals(other.idLigLivMag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Lignelivraisonmagasin[ idLigLivMag=" + idLigLivMag + " ]";
    }
    
}
