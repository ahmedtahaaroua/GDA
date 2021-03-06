package com.wide.control;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import com.wide.dao.LignelivraisonmagasinFacade;
import com.wide.dao.LivraisonmagasinFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Lignelivraisonmagasin;
import com.wide.model.Livraisonmagasin;
import com.wide.model.Produit;

@Named("livraisonmagasinController")
@SessionScoped
public class LivraisonmagasinController implements Serializable {

    private LivraisonmagasinFacade ejbFacade;
    private List<Livraisonmagasin> items = null;
    private Livraisonmagasin selected;
    private DataModel livraisons;
    Produit produit;
    List<Produit> produits;
    
    public List<Produit> getProduits() {
    	produits=new ArrayList<Produit>();
    	LignelivraisonmagasinFacade lignelivraisonFacade=new LignelivraisonmagasinFacade();
    	List<Lignelivraisonmagasin> lignelivraisons=lignelivraisonFacade.findAll();
    	if(!lignelivraisons.isEmpty()){
    	for(Lignelivraisonmagasin lignelivraison:lignelivraisons){
    		if(selected.getIdLivraisonMagasin()== lignelivraison.getIdLivraisonMagasin().getIdLivraisonMagasin()){
    			produits.add(lignelivraison.getRefProduit());
    		}
    	}
    	}
    	
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public LivraisonmagasinController() {
    	ejbFacade=new LivraisonmagasinFacade();
    	
    		livraisons = new ListDataModel();
    		livraisons.setWrappedData(ejbFacade.findAll());


		
    }

    public Livraisonmagasin getSelected() {
        return selected;
    }

    public void setSelected(Livraisonmagasin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LivraisonmagasinFacade getFacade() {
        return ejbFacade;
    }

    public Livraisonmagasin prepareCreate() {
        selected = new Livraisonmagasin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LivraisonmagasinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LivraisonmagasinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LivraisonmagasinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Livraisonmagasin> getItems() {
       
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Livraisonmagasin getLivraisonmagasin(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Livraisonmagasin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Livraisonmagasin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Livraisonmagasin.class)
    public static class LivraisonmagasinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LivraisonmagasinController controller = (LivraisonmagasinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "livraisonmagasinController");
            return controller.getLivraisonmagasin(getKey(value));
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
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Livraisonmagasin) {
                Livraisonmagasin o = (Livraisonmagasin) object;
                return getStringKey(o.getIdLivraisonMagasin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Livraisonmagasin.class.getName()});
                return null;
            }
        }

    }

}
