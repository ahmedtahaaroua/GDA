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
@Table(name = "ligneapprovisionnement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ligneapprovisionnement.findAll", query = "SELECT l FROM Ligneapprovisionnement l"),
    @NamedQuery(name = "Ligneapprovisionnement.findByIdApprMagasin", query = "SELECT l FROM Ligneapprovisionnement l WHERE l.idApprMagasin = :idApprMagasin"),
    @NamedQuery(name = "Ligneapprovisionnement.findByQteApp", query = "SELECT l FROM Ligneapprovisionnement l WHERE l.qteApp = :qteApp")})
public class Ligneapprovisionnement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_appr_magasin")
    private Integer idApprMagasin;
    @Column(name = "qteApp")
    private Integer qteApp;
    @JoinColumn(name = "id_approvisionnement", referencedColumnName = "id_approvisionnement")
    @ManyToOne
    private Approvisionemment idApprovisionnement;
    @JoinColumn(name = "refProd", referencedColumnName = "refProd")
    @ManyToOne(optional = false)
    private Produit refProd;

    public Ligneapprovisionnement() {
    }

    public Ligneapprovisionnement(Integer idApprMagasin) {
        this.idApprMagasin = idApprMagasin;
    }

    public Integer getIdApprMagasin() {
        return idApprMagasin;
    }

    public void setIdApprMagasin(Integer idApprMagasin) {
        this.idApprMagasin = idApprMagasin;
    }

    public Integer getQteApp() {
        return qteApp;
    }

    public void setQteApp(Integer qteApp) {
        this.qteApp = qteApp;
    }

    public Approvisionemment getIdApprovisionnement() {
        return idApprovisionnement;
    }

    public void setIdApprovisionnement(Approvisionemment idApprovisionnement) {
        this.idApprovisionnement = idApprovisionnement;
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
        hash += (idApprMagasin != null ? idApprMagasin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ligneapprovisionnement)) {
            return false;
        }
        Ligneapprovisionnement other = (Ligneapprovisionnement) object;
        if ((this.idApprMagasin == null && other.idApprMagasin != null) || (this.idApprMagasin != null && !this.idApprMagasin.equals(other.idApprMagasin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Ligneapprovisionnement[ idApprMagasin=" + idApprMagasin + " ]";
    }
    
}
