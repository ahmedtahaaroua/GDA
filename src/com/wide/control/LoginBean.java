package com.wide.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.wide.model.Utilisateur;


@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

   
    private Utilisateur selectedClient =null;
  
    UtilisateurCtrl utilCtrl = new UtilisateurCtrl();
    private String username;
    private String passwd;
    
    public LoginBean() {
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
	
    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
    	List<Utilisateur> utilisateurs= utilCtrl.getutils();
    	ExternalContext context1 = FacesContext.getCurrentInstance()
				.getExternalContext();
    	for (Utilisateur c :utilisateurs ) {
        if(username != null && username.equals(c.getLogin()) && passwd != null && passwd.equals(c.getMotPasse())) {
                loggedIn = true;
                
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
               if ( c.getType().equals("magasigner"))
               {
            	   System.out.println("magasigner");
            	   try {
				context1.redirect("templateMagasigner/indexMagasigner.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Logger.getLogger(LoginBean.class.getName())
					.info("erreuuur");
				}
               }
               else 
            	   if (c.getType().equals("admin"))
            	   {
            		   System.out.println("admin");
            		   try {
           				context1.redirect("template/indexAdmin.xhtml");
           				} catch (IOException e) {
           					// TODO Auto-generated catch block
           					Logger.getLogger(LoginBean.class.getName())
           					.info("erreuuur");
           				}
            		   
            		   
            	   }
            	   else 
                	   if (c.getType().equals("responsable depot"))
                	   {
                		   System.out.println("responsable depot");
                		   try {
                  				context1.redirect("templateDepot/indexDepot.xhtml");
                  				} catch (IOException e) {
                  					// TODO Auto-generated catch block
                  					Logger.getLogger(LoginBean.class.getName())
                  					.info("erreuuur");
                  				}
                	   }
                
                
        } 
        
      
    	}
    	if ( loggedIn == false)
    	{
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid","Login / mot de passe incorrect");
	
    	}
    	
    	  FacesContext.getCurrentInstance().addMessage(null, msg);
          context.addCallbackParam("loggedIn", loggedIn);
}
	 
}
