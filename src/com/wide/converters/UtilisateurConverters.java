package com.wide.converters;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.wide.model.Utilisateur;


import com.wide.dao.Dao;


@FacesConverter(forClass= Utilisateur.class)
public class UtilisateurConverters implements Converter{
	
	Dao uDao=new Dao();
	Utilisateur util =new Utilisateur();
		@Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if(value== null )
	        {
	            return null;
	        }

	        int id_user = Integer.parseInt(value);
	        return uDao.Consulter(util, id_user);
	    }

	    @Override    
	    public String getAsString(FacesContext context, UIComponent component, Object value) {        

	        if (value == null || value.equals("")) 
	        {
	            return "";            
	        }
	        else
	        {
	            Integer id_user = ((Utilisateur)value).getIdUser();
	            return String.valueOf(id_user);
	        }

	    }

}
