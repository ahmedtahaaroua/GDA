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
@Table(name = "transfert")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfert.findAll", query = "SELECT t FROM Transfert t"),
    @NamedQuery(name = "Transfert.findByIdTransfert", query = "SELECT t FROM Transfert t WHERE t.idTransfert = :idTransfert"),
    @NamedQuery(name = "Transfert.findByDateTransfert", query = "SELECT t FROM Transfert t WHERE t.dateTransfert = :dateTransfert"),
    @NamedQuery(name = "Transfert.findByValidee", query = "SELECT t FROM Transfert t WHERE t.validee = :validee")})
public class Transfert implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_transfert")
    private Integer idTransfert;
    @Column(name = "Date_Transfert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransfert;
    @Column(name = "validee")
    private Boolean validee;
    @JoinColumn(name = "id_magasin", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private Magasin idMagasin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransfert")
    private Collection<LigneTransfert> ligneTransfertCollection;

    public Transfert() {
    }

    public Transfert(Integer idTransfert) {
        this.idTransfert = idTransfert;
    }

    public Integer getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(Integer idTransfert) {
        this.idTransfert = idTransfert;
    }

    public Date getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(Date dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    public Boolean getValidee() {
        return validee;
    }

    public void setValidee(Boolean validee) {
        this.validee = validee;
    }

    public Magasin getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Magasin idMagasin) {
        this.idMagasin = idMagasin;
    }

    @XmlTransient
    public Collection<LigneTransfert> getLigneTransfertCollection() {
        return ligneTransfertCollection;
    }

    public void setLigneTransfertCollection(Collection<LigneTransfert> ligneTransfertCollection) {
        this.ligneTransfertCollection = ligneTransfertCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransfert != null ? idTransfert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfert)) {
            return false;
        }
        Transfert other = (Transfert) object;
        if ((this.idTransfert == null && other.idTransfert != null) || (this.idTransfert != null && !this.idTransfert.equals(other.idTransfert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Transfert[ idTransfert=" + idTransfert + " ]";
    }
    
}
