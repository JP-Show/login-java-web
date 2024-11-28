package application.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import application.country.Country;
import application.web.exception.WebExceptions;

@FacesConverter("countryConverter")
public class CountryConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) throw new WebExceptions("Country is empty");		
		return new Country("value");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null || value == "") throw new WebExceptions("Country is null");
		Country country = new Country((String)value);
		return country.getName();
	}
}
