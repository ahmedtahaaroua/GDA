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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "magasin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Magasin.findAll", query = "SELECT m FROM Magasin m"),
    @NamedQuery(name = "Magasin.findByIdMagasin", query = "SELECT m FROM Magasin m WHERE m.idMagasin = :idMagasin"),
    @NamedQuery(name = "Magasin.findByNomMagasin", query = "SELECT m FROM Magasin m WHERE m.nomMagasin = :nomMagasin"),
    @NamedQuery(name = "Magasin.findByAdresse", query = "SELECT m FROM Magasin m WHERE m.adresse = :adresse"),
    @NamedQuery(name = "Magasin.findByTel", query = "SELECT m FROM Magasin m WHERE m.tel = :tel")})
public class Magasin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_magasin")
    private Integer idMagasin;
    @Size(max = 254)
    @Column(name = "nom_magasin")
    private String nomMagasin;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "tel")
    private Integer tel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMagasin")
    private Collection<Transfert> transfertCollection;
    @OneToMany(mappedBy = "idMagasin")
    private Collection<Livraisonmagasin> livraisonmagasinCollection;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Utilisateur idUser;
    @OneToMany(mappedBy = "idMagasin")
    private Collection<Approvisionemment> approvisionemmentCollection;
    @OneToMany(mappedBy = "idMagasin")
    private Collection<Magasinproduit> magasinproduitCollection;

    public Magasin() {
    }

    public Magasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
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
    public Collection<Transfert> getTransfertCollection() {
        return transfertCollection;
    }

    public void setTransfertCollection(Collection<Transfert> transfertCollection) {
        this.transfertCollection = transfertCollection;
    }

    @XmlTransient
    public Collection<Livraisonmagasin> getLivraisonmagasinCollection() {
        return livraisonmagasinCollection;
    }

    public void setLivraisonmagasinCollection(Collection<Livraisonmagasin> livraisonmagasinCollection) {
        this.livraisonmagasinCollection = livraisonmagasinCollection;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<Approvisionemment> getApprovisionemmentCollection() {
        return approvisionemmentCollection;
    }

    public void setApprovisionemmentCollection(Collection<Approvisionemment> approvisionemmentCollection) {
        this.approvisionemmentCollection = approvisionemmentCollection;
    }

    @XmlTransient
    public Collection<Magasinproduit> getMagasinproduitCollection() {
        return magasinproduitCollection;
    }

    public void setMagasinproduitCollection(Collection<Magasinproduit> magasinproduitCollection) {
        this.magasinproduitCollection = magasinproduitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMagasin != null ? idMagasin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Magasin)) {
            return false;
        }
        Magasin other = (Magasin) object;
        if ((this.idMagasin == null && other.idMagasin != null) || (this.idMagasin != null && !this.idMagasin.equals(other.idMagasin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomMagasin;
    }
    
}
