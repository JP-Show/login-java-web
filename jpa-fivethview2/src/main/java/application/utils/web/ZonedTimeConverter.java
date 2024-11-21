package application.utils.web;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("zonedTimeConverter")
public class ZonedTimeConverter implements Converter {
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println(value);
		if(value == null || value == "") return null;
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(value);
		return zonedDateTime;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) return null;
		ZonedDateTime zonedDateTime = (ZonedDateTime) value;
		return zonedDateTime.toString();
	}

}
