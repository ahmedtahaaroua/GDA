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

import com.wide.dao.MagasinFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Magasin;

@Named("magasinController")
@SessionScoped
public class MagasinController implements Serializable {

    private MagasinFacade ejbFacade;
    private List<Magasin> items = null;

    private Magasin selected;
    private DataModel magasins;
    
    public MagasinController() {
    	ejbFacade=new MagasinFacade();
			magasins = new ListDataModel();
			magasins.setWrappedData(ejbFacade.findAll());


    }

    public void setItems(List<Magasin> items) {
		this.items = items;
	}

	public Magasin getSelected() {
        return selected;
    }

    public void setSelected(Magasin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MagasinFacade getFacade() {
        return ejbFacade;
    }

    public Magasin prepareCreate() {
        selected = new Magasin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MagasinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        magasins.setWrappedData(ejbFacade.findAll());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MagasinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MagasinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Magasin> getItems() {

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

    public Magasin getMagasin(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Magasin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Magasin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Magasin.class)
    public static class MagasinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MagasinController controller = (MagasinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "magasinController");
            return controller.getMagasin(getKey(value));
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
            if (object instanceof Magasin) {
                Magasin o = (Magasin) object;
                return getStringKey(o.getIdMagasin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Magasin.class.getName()});
                return null;
            }
        }

    }

}
