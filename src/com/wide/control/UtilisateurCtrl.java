package com.wide.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.wide.dao.DaoMagasin;
import com.wide.dao.DaoUtilisateur;
import com.wide.model.Magasin;
import com.wide.model.Utilisateur;

@ManagedBean
@SessionScoped
public class UtilisateurCtrl implements Serializable {
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur = new Utilisateur();
	Utilisateur editUtilisateur;
	private List<SelectItem> UtilisateurItems;
	private DaoUtilisateur DaoU;
	private DataModel utilisateurs;
	private List<Magasin> magasins;

	public UtilisateurCtrl() {
		DaoU = new DaoUtilisateur();
		if (utilisateurs == null) {
			utilisateurs = new ListDataModel();
			utilisateurs.setWrappedData(DaoU.selectAll());

		}

	}

	public String creer() {
		utilisateur = new Utilisateur();
		return "add";
	}

	public String ajouter() {
		DaoU.ajouter(utilisateur);
		utilisateurs.setWrappedData(DaoU.selectAll());
		FacesMessage msg = new FacesMessage("Ajout  effectué avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "list";
	}

	public String modifier() {
		DaoU.modifier(editUtilisateur);
		utilisateurs.setWrappedData(DaoU.selectAll());
		FacesMessage msg = new FacesMessage(
				"Modification  effectué avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "list";
	}

	public String supprimer() {
		Utilisateur u = (Utilisateur) utilisateurs.getRowData();
		DaoU.supprimer(u);
		utilisateurs.setWrappedData(DaoU.selectAll());
		FacesMessage msg = new FacesMessage("suppression  effectué avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "list";
	}

	public String remplir() {
		editUtilisateur = (Utilisateur) utilisateurs.getRowData();
		return "edit";
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getEditUtilisateur() {
		return editUtilisateur;
	}

	public void setEditUtilisateur(Utilisateur editUtilisateur) {
		this.editUtilisateur = editUtilisateur;
	}

	public List<SelectItem> getUtilisateurItems() {
		return UtilisateurItems;
	}

	public void setUtilisateurItems(List<SelectItem> utilisateurItems) {
		UtilisateurItems = utilisateurItems;
	}

	public DaoUtilisateur getDaoU() {
		return DaoU;
	}

	public void setDaoU(DaoUtilisateur daoU) {
		DaoU = daoU;
	}

	public DataModel getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(DataModel utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public List<Utilisateur> getutils() {
		return DaoU.selectAll();
	}

	public List<Magasin> getMagasins() {
		DaoMagasin daom = new DaoMagasin();
		magasins = daom.selectAll();
		return magasins;
	}

	public void setMagasins(List<Magasin> magasins) {
		this.magasins = magasins;
	}

	/*
	 * public String checkValidUser() { Utilisateur u=
	 * DaoU.verifier(utilisateur); if(!u.equals(null)) { if
	 * (u.getRole().getId_role()==1) {logedIn=true; return "templateadmin"; } if
	 * (u.getRole().getId_role()==2) {logedIn=true; setVisibleMag(true);
	 * setVisibleResponable(true); return "template"; } if
	 * (u.getRole().getId_role()==3) {logedIn=true; setVisibleMag(true);
	 * setVisibleResponable(false); return "template"; } } FacesMessage msg =
	 * new FacesMessage("Incorrect Login Or Password");
	 * FacesContext.getCurrentInstance().addMessage(null, msg); return "login";
	 * 
	 * 
	 * }
	 */

}
