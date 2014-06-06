package com.wide.control;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.wide.dao.ApprovisionemmentFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Approvisionemment;

@Named("approvisionemmentController")
@SessionScoped
public class ApprovisionemmentController implements Serializable {

    private ApprovisionemmentFacade ejbFacade;
    private List<Approvisionemment> items = null;
    private Approvisionemment selected;
    private DataModel approv;
    private int n;
	private JasperPrint jasperPrint;
    
    public int getN() {
    	
    	if (items == null)
    		return 0;
    	else 
    	{
    		return 	items.size();
    		
    	}
	}

	public void setN(int n) {
		this.n = n;
	}

	public ApprovisionemmentController() {
    	
    	ejbFacade=new ApprovisionemmentFacade();
    	if (items == null) {
    		approv = new ListDataModel();
    		approv.setWrappedData(ejbFacade.findAll());

		}
    	
    }

    public Approvisionemment getSelected() {
        return selected;
    }

    public void setSelected(Approvisionemment selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ApprovisionemmentFacade getFacade() {
        return ejbFacade;
    }

    public Approvisionemment prepareCreate() {
        selected = new Approvisionemment();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ApprovisionemmentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        approv.setWrappedData(ejbFacade.findAll());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ApprovisionemmentUpdated"));
        
        
        
    }
    public void GenererPDF() {
		
			
			
			   init();  
		       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
		      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");  
		       ServletOutputStream servletOutputStream = null;
			try {
				servletOutputStream = httpServletResponse.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		       try {
				JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		       FacesContext.getCurrentInstance().responseComplete();  	
		
    }
    
    private void init() {
    	Map parameters = new HashMap();

		parameters.put("id", selected.getIdApprovisionnement());
		parameters.put("nomR", "nom resp");
		parameters.put("prenomR", "prenpdsdsd");
		parameters.put("telR", "212121");
		parameters.put("nomM", "sdsd");
		parameters.put("prenomM", "sdsd");
		parameters.put("telM", "555");
		parameters.put("magasin", selected.getIdMagasin().getNomMagasin());
    	 JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(items);  
    	 String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/bonLivraison.jasper");       
    	 try {
			jasperPrint=JasperFillManager.fillReport(reportPath, parameters,beanCollectionDataSource);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

	public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ApprovisionemmentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Approvisionemment> getItems() {
       items=new ArrayList<Approvisionemment>();
        	List<Approvisionemment> approvisionemments=getFacade().findAll();
        			
         
            for (Approvisionemment i : approvisionemments) {
            	if (! i.getValidee())
            		items.add(i);
            	
            		
				
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

    public Approvisionemment getApprovisionemment(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Approvisionemment> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Approvisionemment> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Approvisionemment.class)
    public static class ApprovisionemmentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApprovisionemmentController controller = (ApprovisionemmentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "approvisionemmentController");
            return controller.getApprovisionemment(getKey(value));
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
            if (object instanceof Approvisionemment) {
                Approvisionemment o = (Approvisionemment) object;
                return getStringKey(o.getIdApprovisionnement());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Approvisionemment.class.getName()});
                return null;
            }
        }

    }

}
