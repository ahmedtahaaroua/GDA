/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wide.model;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed Taha
 */
@Entity
@Table(name = "livraisonmagasin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livraisonmagasin.findAll", query = "SELECT l FROM Livraisonmagasin l"),
    @NamedQuery(name = "Livraisonmagasin.findByIdLivraisonMagasin", query = "SELECT l FROM Livraisonmagasin l WHERE l.idLivraisonMagasin = :idLivraisonMagasin"),
    @NamedQuery(name = "Livraisonmagasin.findByDateLivraison", query = "SELECT l FROM Livraisonmagasin l WHERE l.dateLivraison = :dateLivraison")})
public class Livraisonmagasin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLivraisonMagasin")
    private Integer idLivraisonMagasin;
    @Column(name = "dateLivraison")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLivraison;
    @OneToMany(mappedBy = "idLivraisonMagasin")
    private Collection<Lignelivraisonmagasin> lignelivraisonmagasinCollection;
    @JoinColumn(name = "id_magasin", referencedColumnName = "id_magasin")
    @ManyToOne
    private Magasin idMagasin;

    public Livraisonmagasin() {
    }

    public Livraisonmagasin(Integer idLivraisonMagasin) {
        this.idLivraisonMagasin = idLivraisonMagasin;
    }

    public Integer getIdLivraisonMagasin() {
        return idLivraisonMagasin;
    }

    public void setIdLivraisonMagasin(Integer idLivraisonMagasin) {
        this.idLivraisonMagasin = idLivraisonMagasin;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @XmlTransient
    public Collection<Lignelivraisonmagasin> getLignelivraisonmagasinCollection() {
        return lignelivraisonmagasinCollection;
    }

    public void setLignelivraisonmagasinCollection(Collection<Lignelivraisonmagasin> lignelivraisonmagasinCollection) {
        this.lignelivraisonmagasinCollection = lignelivraisonmagasinCollection;
    }

    public Magasin getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Magasin idMagasin) {
        this.idMagasin = idMagasin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivraisonMagasin != null ? idLivraisonMagasin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraisonmagasin)) {
            return false;
        }
        Livraisonmagasin other = (Livraisonmagasin) object;
        if ((this.idLivraisonMagasin == null && other.idLivraisonMagasin != null) || (this.idLivraisonMagasin != null && !this.idLivraisonMagasin.equals(other.idLivraisonMagasin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Livraisonmagasin[ idLivraisonMagasin=" + idLivraisonMagasin + " ]";
    }
    
}
