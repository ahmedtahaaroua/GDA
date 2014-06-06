package com.wide.control;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.wide.dao.ApprovisionemmentFacade;
import com.wide.dao.LigneapprovisionnementFacade;
import com.wide.dao.MagasinproduitFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Approvisionemment;
import com.wide.model.Ligneapprovisionnement;
import com.wide.model.Magasinproduit;

@Named("magasinproduitController")
@SessionScoped
public class MagasinproduitController implements Serializable {

	private MagasinproduitFacade ejbFacade;
	private ApprovisionemmentFacade approvisionemmentFacade;
	private LigneapprovisionnementFacade ligneapprovisionnementFacade;
	private List<Magasinproduit> items = null;
	private Magasinproduit selected;
	private DataModel magasinProduit;
	private int qte=0;
	
	
	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public MagasinproduitController() {
		ejbFacade = new MagasinproduitFacade();
			magasinProduit = new ListDataModel();
			magasinProduit.setWrappedData(ejbFacade.findAll());

	}

	public Magasinproduit getSelected() {
		
		
		return selected;
	}

	public void setSelected(Magasinproduit selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private MagasinproduitFacade getFacade() {
		return ejbFacade;
	}

	public Magasinproduit prepareCreate() {
		selected = new Magasinproduit();
		initializeEmbeddableKey();
		qte=selected.getQte();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("MagasinproduitCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("MagasinproduitUpdated"));
	}

	public void createApprovisionnement() {
        RequestContext context = RequestContext.getCurrentInstance();
		 FacesMessage msg = null;
		approvisionemmentFacade = new ApprovisionemmentFacade();
		ligneapprovisionnementFacade = new LigneapprovisionnementFacade();
		Approvisionemment approvisionemment = new Approvisionemment();
		approvisionemment.setIdMagasin(this.selected.getIdMagasin());
		approvisionemment.setValidee(false);
		approvisionemmentFacade.create(approvisionemment);
		Ligneapprovisionnement ligneapprovisionnement = new Ligneapprovisionnement();
		ligneapprovisionnement.setRefProd(this.selected.getRefProd());
		ligneapprovisionnement.setIdApprovisionnement(approvisionemment);
		ligneapprovisionnement.setQteApp(qte);
		ligneapprovisionnementFacade.create(ligneapprovisionnement);
		qte=0;
		msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "Demande Envoyé", "Votre demande d'approvisionnement est envoyé avec succes au chef depot");
		 FacesContext.getCurrentInstance().addMessage(null, msg);
         context.addCallbackParam("loggedIn", false);

	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("MagasinproduitDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public List<Magasinproduit> getItems() {
			items = getFacade().findAll();
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					getFacade().edit(selected);
				} else {
					getFacade().remove(selected);
				}
				JsfUtil.addSuccessMessage(successMessage);
			} catch (EJBException ex) {
				String msg = "";
				Throwable cause = ex.getCause();
				if (cause != null) {
					msg = cause.getLocalizedMessage();
				}
				if (msg.length() > 0) {
					JsfUtil.addErrorMessage(msg);
				} else {
					JsfUtil.addErrorMessage(
							ex,
							ResourceBundle.getBundle("/Bundle").getString(
									"PersistenceErrorOccured"));
				}
			} catch (Exception ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
						.getString("PersistenceErrorOccured"));
			}
		}
	}

	public Magasinproduit getMagasinproduit(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<Magasinproduit> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<Magasinproduit> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}

	@FacesConverter(forClass = Magasinproduit.class)
	public static class MagasinproduitControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			MagasinproduitController controller = (MagasinproduitController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"magasinproduitController");
			return controller.getMagasinproduit(getKey(value));
		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Magasinproduit) {
				Magasinproduit o = (Magasinproduit) object;
				return getStringKey(o.getIdMagasinProd());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								Magasinproduit.class.getName() });
				return null;
			}
		}

	}

}
