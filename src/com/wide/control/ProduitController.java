package com.wide.control;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.wide.dao.ApprovisionemmentFacade;
import com.wide.dao.LigneapprovisionnementFacade;
import com.wide.dao.MagasinFacade;
import com.wide.dao.MagasinproduitFacade;
import com.wide.dao.ProduitFacade;
import com.wide.jpaUtil.JsfUtil;
import com.wide.jpaUtil.JsfUtil.PersistAction;
import com.wide.model.Approvisionemment;
import com.wide.model.Ligneapprovisionnement;
import com.wide.model.Magasin;
import com.wide.model.Magasinproduit;
import com.wide.model.Produit;

@Named("produitController")
@SessionScoped
public class ProduitController implements Serializable {

    private ProduitFacade ejbFacade;
    private List<Produit> items = null;
    private Produit selected;
    private DataModel produits;
    private MagasinproduitFacade magasinproduitFacade;
    List<Produit> produitsStock=null;
   
	private ApprovisionemmentFacade approvisionemmentFacade;
	private LigneapprovisionnementFacade ligneapprovisionnementFacade;
    private List<Magasin> magasins=new ArrayList<Magasin>();
    private MagasinFacade magasinFacade;
    private int qte=0;
    private Magasin magasin;
    
    public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public List<Magasin> getMagasins() 
    
    {
    	magasins=new ArrayList<Magasin>();
    	magasinFacade=new MagasinFacade();
    	List<Magasin> list=new ArrayList<Magasin>();
    	list=magasinFacade.findAll();
    	boolean ok=true;
    	for(Magasin magasin:list){
    		ok=true;
    		for(Magasinproduit magasinproduit:magasin.getMagasinproduitCollection()){
    			if(magasinproduit.getRefProd().getRefProd()==selected.getRefProd()){
    				ok=false;
    				break;
    			}
    		}
    		if(ok){
    			magasins.add(magasin);
    		}
    	}
    	
    	return magasins;
	}

	public void setMagasins(List<Magasin> magasins) {
		this.magasins = magasins;
	}

	

	public ProduitController() {
    	ejbFacade=new ProduitFacade();
    	magasinproduitFacade=new MagasinproduitFacade();
    	List<Produit> produits1=new ArrayList<Produit>();
    	List<Magasinproduit> magasinproduits=new ArrayList<Magasinproduit>();
    	 produitsStock=new ArrayList<Produit>();
    		produits = new ListDataModel();
    		produits1=ejbFacade.findAll();
    		magasinproduits=magasinproduitFacade.findAll();
    		boolean ok =true;
    		for(Produit produit : produits1){
    			for (Magasinproduit magasinproduit:magasinproduits){
    				
    				if(produit.getRefProd()== magasinproduit.getRefProd().getRefProd()){
    					ok=false;
    					break;
    				}
    			
    			}
    			if(ok){
    				
    				produitsStock.add(produit);
    			}else{
    				ok=true;
    			}
    		}
    		
    		produits.setWrappedData(produitsStock);

		
    }
	public void createApprovisionnement() {
		System.out.println("jhjhjh");
        RequestContext context = RequestContext.getCurrentInstance();
		 FacesMessage msg = null;
		approvisionemmentFacade = new ApprovisionemmentFacade();
		ligneapprovisionnementFacade = new LigneapprovisionnementFacade();
		Approvisionemment approvisionemment = new Approvisionemment();
//		approvisionemment.setIdMagasin(this.selected.getIdMagasin());
		approvisionemment.setValidee(false);
		approvisionemmentFacade.create(approvisionemment);
		Ligneapprovisionnement ligneapprovisionnement = new Ligneapprovisionnement();
		ligneapprovisionnement.setRefProd(this.selected);
		ligneapprovisionnement.setIdApprovisionnement(approvisionemment);
		ligneapprovisionnement.setQteApp(qte);
		ligneapprovisionnementFacade.create(ligneapprovisionnement);
		qte=0;
		msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "Demande Envoyé", "Votre demande d'approvisionnement est envoyé avec succes au chef depot");
		 FacesContext.getCurrentInstance().addMessage(null, msg);
         context.addCallbackParam("loggedIn", false);

	}
    public Produit getSelected() {
        return selected;
    }

    public void setSelected(Produit selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProduitFacade getFacade() {
        return ejbFacade;
    }

    public Produit prepareCreate() {
        selected = new Produit();
        
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProduitCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
       produits.setWrappedData(ejbFacade.findAll());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProduitUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProduitDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Produit> getItems() {
        if (items == null) {
            items = ejbFacade.findAll();
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

    public Produit getProduit(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Produit> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Produit> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Produit.class)
    public static class ProduitControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProduitController controller = (ProduitController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "produitController");
            return controller.getProduit(getKey(value));
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
            if (object instanceof Produit) {
                Produit o = (Produit) object;
                return getStringKey(o.getRefProd());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Produit.class.getName()});
                return null;
            }
        }

    }

}
