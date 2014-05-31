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
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findByRefProd", query = "SELECT p FROM Produit p WHERE p.refProd = :refProd"),
    @NamedQuery(name = "Produit.findByNomProduit", query = "SELECT p FROM Produit p WHERE p.nomProduit = :nomProduit"),
    @NamedQuery(name = "Produit.findByQuantite", query = "SELECT p FROM Produit p WHERE p.quantite = :quantite"),
    @NamedQuery(name = "Produit.findByPrixAchat", query = "SELECT p FROM Produit p WHERE p.prixAchat = :prixAchat"),
    @NamedQuery(name = "Produit.findByQuantiteMin", query = "SELECT p FROM Produit p WHERE p.quantiteMin = :quantiteMin"),
    @NamedQuery(name = "Produit.findByQuantiteMax", query = "SELECT p FROM Produit p WHERE p.quantiteMax = :quantiteMax")})
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "refProd")
    private Integer refProd;
    @Size(max = 254)
    @Column(name = "nom_produit")
    private String nomProduit;
    @Column(name = "quantite")
    private Integer quantite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_achat")
    private Float prixAchat;
    @Column(name = "quantite_min")
    private Integer quantiteMin;
    @Column(name = "quantite_max")
    private Integer quantiteMax;
    @JoinColumn(name = "id_famille", referencedColumnName = "id_famille")
    @ManyToOne(optional = false)
    private Famille idFamille;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refProd")
    private Collection<LigneTransfert> ligneTransfertCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refProd")
    private Collection<LigneCommande> ligneCommandeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refProd")
    private Collection<Ligneapprovisionnement> ligneapprovisionnementCollection;
    @OneToMany(mappedBy = "refProd")
    private Collection<Magasinproduit> magasinproduitCollection;

    public Produit() {
    }

    public Produit(Integer refProd) {
        this.refProd = refProd;
    }

    public Integer getRefProd() {
        return refProd;
    }

    public void setRefProd(Integer refProd) {
        this.refProd = refProd;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Integer getQuantiteMin() {
        return quantiteMin;
    }

    public void setQuantiteMin(Integer quantiteMin) {
        this.quantiteMin = quantiteMin;
    }

    public Integer getQuantiteMax() {
        return quantiteMax;
    }

    public void setQuantiteMax(Integer quantiteMax) {
        this.quantiteMax = quantiteMax;
    }

    public Famille getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Famille idFamille) {
        this.idFamille = idFamille;
    }

    @XmlTransient
    public Collection<LigneTransfert> getLigneTransfertCollection() {
        return ligneTransfertCollection;
    }

    public void setLigneTransfertCollection(Collection<LigneTransfert> ligneTransfertCollection) {
        this.ligneTransfertCollection = ligneTransfertCollection;
    }

    @XmlTransient
    public Collection<LigneCommande> getLigneCommandeCollection() {
        return ligneCommandeCollection;
    }

    public void setLigneCommandeCollection(Collection<LigneCommande> ligneCommandeCollection) {
        this.ligneCommandeCollection = ligneCommandeCollection;
    }

    @XmlTransient
    public Collection<Ligneapprovisionnement> getLigneapprovisionnementCollection() {
        return ligneapprovisionnementCollection;
    }

    public void setLigneapprovisionnementCollection(Collection<Ligneapprovisionnement> ligneapprovisionnementCollection) {
        this.ligneapprovisionnementCollection = ligneapprovisionnementCollection;
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
        hash += (refProd != null ? refProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.refProd == null && other.refProd != null) || (this.refProd != null && !this.refProd.equals(other.refProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Produit[ refProd=" + refProd + " ]";
    }
    
}
