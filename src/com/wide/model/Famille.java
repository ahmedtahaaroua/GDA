/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.model;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed Taha
 */
@Entity
@Table(name = "famille")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Famille.findAll", query = "SELECT f FROM Famille f"),
    @NamedQuery(name = "Famille.findByIdFamille", query = "SELECT f FROM Famille f WHERE f.idFamille = :idFamille"),
    @NamedQuery(name = "Famille.findByNomFamille", query = "SELECT f FROM Famille f WHERE f.nomFamille = :nomFamille")})
public class Famille implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_famille")
    private Integer idFamille;
    @Size(max = 254)
    @Column(name = "nom_famille")
    private String nomFamille;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamille")
    private Collection<Produit> produitCollection;

    public Famille() {
    }

    public Famille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public Integer getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    @XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamille != null ? idFamille.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Famille)) {
            return false;
        }
        Famille other = (Famille) object;
        if ((this.idFamille == null && other.idFamille != null) || (this.idFamille != null && !this.idFamille.equals(other.idFamille))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomFamille;
    }
    
}
