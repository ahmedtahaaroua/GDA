package com.wide.control;
import java.io.Serializable;
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

import com.wide.dao.FamilleFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Famille;

@Named("familleController")
@SessionScoped
public class FamilleController implements Serializable {

    private FamilleFacade ejbFacade;
    private List<Famille> items = null;
    private Famille selected;
    private DataModel familles;

    public FamilleController() {
    	ejbFacade=new FamilleFacade();
    
    		familles = new ListDataModel();
    		familles.setWrappedData(ejbFacade.findAll());
		
    	
    }

    public Famille getSelected() {
        return selected;
    }

    public void setSelected(Famille selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FamilleFacade getFacade() {
        return ejbFacade;
    }

    public Famille prepareCreate() {
        selected = new Famille();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FamilleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
       familles.setWrappedData(ejbFacade.findAll());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FamilleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FamilleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Famille> getItems() {
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

    public Famille getFamille(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Famille> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Famille> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Famille.class)
    public static class FamilleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamilleController controller = (FamilleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "familleController");
            return controller.getFamille(getKey(value));
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
            if (object instanceof Famille) {
                Famille o = (Famille) object;
                return getStringKey(o.getIdFamille());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Famille.class.getName()});
                return null;
            }
        }

    }

}
