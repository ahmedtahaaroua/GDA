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
@Table(name = "magasinproduit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Magasinproduit.findAll", query = "SELECT m FROM Magasinproduit m"),
    @NamedQuery(name = "Magasinproduit.findByIdMagasinProd", query = "SELECT m FROM Magasinproduit m WHERE m.idMagasinProd = :idMagasinProd"),
    @NamedQuery(name = "Magasinproduit.findByQte", query = "SELECT m FROM Magasinproduit m WHERE m.qte = :qte")})
public class Magasinproduit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_magasin_prod")
    private Integer idMagasinProd;
    @Column(name = "qte")
    private Integer qte;
    @JoinColumn(name = "id_magasin", referencedColumnName = "id_magasin")
    @ManyToOne
    private Magasin idMagasin;
    @JoinColumn(name = "refProd", referencedColumnName = "refProd")
    @ManyToOne
    private Produit refProd;

    public Magasinproduit() {
    }

    public Magasinproduit(Integer idMagasinProd) {
        this.idMagasinProd = idMagasinProd;
    }

    public Integer getIdMagasinProd() {
        return idMagasinProd;
    }

    public void setIdMagasinProd(Integer idMagasinProd) {
        this.idMagasinProd = idMagasinProd;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Magasin getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Magasin idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Produit getRefProd() {
        return refProd;
    }

    public void setRefProd(Produit refProd) {
        this.refProd = refProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMagasinProd != null ? idMagasinProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Magasinproduit)) {
            return false;
        }
        Magasinproduit other = (Magasinproduit) object;
        if ((this.idMagasinProd == null && other.idMagasinProd != null) || (this.idMagasinProd != null && !this.idMagasinProd.equals(other.idMagasinProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Magasinproduit[ idMagasinProd=" + idMagasinProd + " ]";
    }
    
}
