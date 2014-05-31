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
@Table(name = "lignelivraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lignelivraison.findAll", query = "SELECT l FROM Lignelivraison l"),
    @NamedQuery(name = "Lignelivraison.findByIdLigneLivraison", query = "SELECT l FROM Lignelivraison l WHERE l.idLigneLivraison = :idLigneLivraison"),
    @NamedQuery(name = "Lignelivraison.findByRefProduit", query = "SELECT l FROM Lignelivraison l WHERE l.refProduit = :refProduit"),
    @NamedQuery(name = "Lignelivraison.findByQteLiv", query = "SELECT l FROM Lignelivraison l WHERE l.qteLiv = :qteLiv"),
    @NamedQuery(name = "Lignelivraison.findByIdLivraison", query = "SELECT l FROM Lignelivraison l WHERE l.idLivraison = :idLivraison")})
public class Lignelivraison implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLigneLivraison")
    private Integer idLigneLivraison;
    @Column(name = "refProduit")
    private Integer refProduit;
    @Column(name = "qteLiv")
    private Integer qteLiv;
    @Column(name = "idLivraison")
    private Integer idLivraison;

    public Lignelivraison() {
    }

    public Lignelivraison(Integer idLigneLivraison) {
        this.idLigneLivraison = idLigneLivraison;
    }

    public Integer getIdLigneLivraison() {
        return idLigneLivraison;
    }

    public void setIdLigneLivraison(Integer idLigneLivraison) {
        this.idLigneLivraison = idLigneLivraison;
    }

    public Integer getRefProduit() {
        return refProduit;
    }

    public void setRefProduit(Integer refProduit) {
        this.refProduit = refProduit;
    }

    public Integer getQteLiv() {
        return qteLiv;
    }

    public void setQteLiv(Integer qteLiv) {
        this.qteLiv = qteLiv;
    }

    public Integer getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Integer idLivraison) {
        this.idLivraison = idLivraison;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLigneLivraison != null ? idLigneLivraison.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lignelivraison)) {
            return false;
        }
        Lignelivraison other = (Lignelivraison) object;
        if ((this.idLigneLivraison == null && other.idLigneLivraison != null) || (this.idLigneLivraison != null && !this.idLigneLivraison.equals(other.idLigneLivraison))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Lignelivraison[ idLigneLivraison=" + idLigneLivraison + " ]";
    }
    
}
