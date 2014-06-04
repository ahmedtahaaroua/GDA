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
@Table(name = "fournisseurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fournisseurs.findAll", query = "SELECT f FROM Fournisseurs f"),
    @NamedQuery(name = "Fournisseurs.findByIdFournisseur", query = "SELECT f FROM Fournisseurs f WHERE f.idFournisseur = :idFournisseur"),
    @NamedQuery(name = "Fournisseurs.findByNomFournisseur", query = "SELECT f FROM Fournisseurs f WHERE f.nomFournisseur = :nomFournisseur"),
    @NamedQuery(name = "Fournisseurs.findByAdresse", query = "SELECT f FROM Fournisseurs f WHERE f.adresse = :adresse"),
    @NamedQuery(name = "Fournisseurs.findByTel", query = "SELECT f FROM Fournisseurs f WHERE f.tel = :tel")})
public class Fournisseurs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fournisseur")
    private Integer idFournisseur;
    @Size(max = 254)
    @Column(name = "nom_fournisseur")
    private String nomFournisseur;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "tel")
    private Integer tel;
    
    @OneToMany(mappedBy = "idFournisseur")
    private Collection<Lot> lotCollection;
    
    @OneToMany(mappedBy = "idFournisseur")
    private Collection<Commande> commandeCollection;
    
    @OneToMany(mappedBy = "idFournisseur")
    private Collection<Produit> produitsCollection;
    
    public Fournisseurs() {
    }

    public Fournisseurs(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Integer getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Collection<Produit> getProduitsCollection() {
		return produitsCollection;
	}

	public void setProduitsCollection(Collection<Produit> produitsCollection) {
		this.produitsCollection = produitsCollection;
	}

	public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @XmlTransient
    public Collection<Lot> getLotCollection() {
        return lotCollection;
    }

    public void setLotCollection(Collection<Lot> lotCollection) {
        this.lotCollection = lotCollection;
    }

    @XmlTransient
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFournisseur != null ? idFournisseur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fournisseurs)) {
            return false;
        }
        Fournisseurs other = (Fournisseurs) object;
        if ((this.idFournisseur == null && other.idFournisseur != null) || (this.idFournisseur != null && !this.idFournisseur.equals(other.idFournisseur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomFournisseur;
    }
    
}
