package com.wide.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.wide.dao.Dao;
import com.wide.model.Magasin;

@FacesConverter(forClass = Magasin.class)
public class MagasinConverters implements Converter {

	Dao uDao = new Dao();
	Magasin util = new Magasin();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		int id_magasin = Integer.parseInt(value);
		return uDao.Consulter(util, id_magasin);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value == null || value.equals("")) {
			return "";
		} else {
			Integer id_magasin = ((Magasin) value).getIdMagasin();
			return String.valueOf(id_magasin);
		}

	}

}
