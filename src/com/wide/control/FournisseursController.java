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
import javax.inject.Named;

import com.wide.dao.FournisseursFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Fournisseurs;

@Named("fournisseursController")
@SessionScoped
public class FournisseursController implements Serializable {

    private FournisseursFacade ejbFacade;
    private List<Fournisseurs> items= new ArrayList<Fournisseurs>() ;
    private Fournisseurs selected;

    public FournisseursController() {
    }

    public Fournisseurs getSelected() {
        return selected;
    }

    public void setSelected(Fournisseurs selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FournisseursFacade getFacade() {
        return ejbFacade;
    }

    public Fournisseurs prepareCreate() {
        selected = new Fournisseurs();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FournisseursCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FournisseursUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FournisseursDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Fournisseurs> getItems() {
      
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

    public Fournisseurs getFournisseurs(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Fournisseurs> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Fournisseurs> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Fournisseurs.class)
    public static class FournisseursControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FournisseursController controller = (FournisseursController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fournisseursController");
            return controller.getFournisseurs(getKey(value));
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
            if (object instanceof Fournisseurs) {
                Fournisseurs o = (Fournisseurs) object;
                return getStringKey(o.getIdFournisseur());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Fournisseurs.class.getName()});
                return null;
            }
        }

    }

}
