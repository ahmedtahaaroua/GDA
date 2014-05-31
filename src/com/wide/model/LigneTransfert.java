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
@Table(name = "ligne_transfert")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneTransfert.findAll", query = "SELECT l FROM LigneTransfert l"),
    @NamedQuery(name = "LigneTransfert.findByIdLigneTransfert", query = "SELECT l FROM LigneTransfert l WHERE l.idLigneTransfert = :idLigneTransfert"),
    @NamedQuery(name = "LigneTransfert.findByQteTran", query = "SELECT l FROM LigneTransfert l WHERE l.qteTran = :qteTran")})
public class LigneTransfert implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ligne_transfert")
    private Integer idLigneTransfert;
    @Column(name = "QteTran")
    private Integer qteTran;
    @JoinColumn(name = "id_transfert", referencedColumnName = "id_transfert")
    @ManyToOne(optional = false)
    private Transfert idTransfert;
    @JoinColumn(name = "refProd", referencedColumnName = "refProd")
    @ManyToOne(optional = false)
    private Produit refProd;

    public LigneTransfert() {
    }

    public LigneTransfert(Integer idLigneTransfert) {
        this.idLigneTransfert = idLigneTransfert;
    }

    public Integer getIdLigneTransfert() {
        return idLigneTransfert;
    }

    public void setIdLigneTransfert(Integer idLigneTransfert) {
        this.idLigneTransfert = idLigneTransfert;
    }

    public Integer getQteTran() {
        return qteTran;
    }

    public void setQteTran(Integer qteTran) {
        this.qteTran = qteTran;
    }

    public Transfert getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(Transfert idTransfert) {
        this.idTransfert = idTransfert;
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
        hash += (idLigneTransfert != null ? idLigneTransfert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneTransfert)) {
            return false;
        }
        LigneTransfert other = (LigneTransfert) object;
        if ((this.idLigneTransfert == null && other.idLigneTransfert != null) || (this.idLigneTransfert != null && !this.idLigneTransfert.equals(other.idLigneTransfert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.LigneTransfert[ idLigneTransfert=" + idLigneTransfert + " ]";
    }
    
}
