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
@Table(name = "approvisionemment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Approvisionemment.findAll", query = "SELECT a FROM Approvisionemment a"),
    @NamedQuery(name = "Approvisionemment.findByIdApprovisionnement", query = "SELECT a FROM Approvisionemment a WHERE a.idApprovisionnement = :idApprovisionnement"),
    @NamedQuery(name = "Approvisionemment.findByDateCreation", query = "SELECT a FROM Approvisionemment a WHERE a.dateCreation = :dateCreation"),
    @NamedQuery(name = "Approvisionemment.findByValidee", query = "SELECT a FROM Approvisionemment a WHERE a.validee = :validee")})
public class Approvisionemment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_approvisionnement")
    private Integer idApprovisionnement;
    @Column(name = "Date_Creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "validee")
    private Boolean validee;
    @JoinColumn(name = "id_magasin", referencedColumnName = "id_magasin")
    @ManyToOne
    private Magasin idMagasin;
    @OneToMany(mappedBy = "idApprovisionnement")
    private Collection<Ligneapprovisionnement> ligneapprovisionnementCollection;

    public Approvisionemment() {
    }

    public Approvisionemment(Integer idApprovisionnement) {
        this.idApprovisionnement = idApprovisionnement;
    }

    public Integer getIdApprovisionnement() {
        return idApprovisionnement;
    }

    public void setIdApprovisionnement(Integer idApprovisionnement) {
        this.idApprovisionnement = idApprovisionnement;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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
    public Collection<Ligneapprovisionnement> getLigneapprovisionnementCollection() {
        return ligneapprovisionnementCollection;
    }

    public void setLigneapprovisionnementCollection(Collection<Ligneapprovisionnement> ligneapprovisionnementCollection) {
        this.ligneapprovisionnementCollection = ligneapprovisionnementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApprovisionnement != null ? idApprovisionnement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Approvisionemment)) {
            return false;
        }
        Approvisionemment other = (Approvisionemment) object;
        if ((this.idApprovisionnement == null && other.idApprovisionnement != null) || (this.idApprovisionnement != null && !this.idApprovisionnement.equals(other.idApprovisionnement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.fitness.gda.Approvisionemment[ idApprovisionnement=" + idApprovisionnement + " ]";
    }
    
}
