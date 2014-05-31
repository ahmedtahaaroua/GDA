package com.wide.control;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.wide.dao.LignelivraisonmagasinFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Lignelivraisonmagasin;

@Named("lignelivraisonmagasinController")
@SessionScoped
public class LignelivraisonmagasinController implements Serializable {

    private LignelivraisonmagasinFacade ejbFacade;
    private List<Lignelivraisonmagasin> items = null;
    private Lignelivraisonmagasin selected;

    public LignelivraisonmagasinController() {
    }

    public Lignelivraisonmagasin getSelected() {
        return selected;
    }

    public void setSelected(Lignelivraisonmagasin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LignelivraisonmagasinFacade getFacade() {
        return ejbFacade;
    }

    public Lignelivraisonmagasin prepareCreate() {
        selected = new Lignelivraisonmagasin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LignelivraisonmagasinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LignelivraisonmagasinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LignelivraisonmagasinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Lignelivraisonmagasin> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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

    public Lignelivraisonmagasin getLignelivraisonmagasin(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Lignelivraisonmagasin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Lignelivraisonmagasin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Lignelivraisonmagasin.class)
    public static class LignelivraisonmagasinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LignelivraisonmagasinController controller = (LignelivraisonmagasinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lignelivraisonmagasinController");
            return controller.getLignelivraisonmagasin(getKey(value));
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
            if (object instanceof Lignelivraisonmagasin) {
                Lignelivraisonmagasin o = (Lignelivraisonmagasin) object;
                return getStringKey(o.getIdLigLivMag());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Lignelivraisonmagasin.class.getName()});
                return null;
            }
        }

    }

}
