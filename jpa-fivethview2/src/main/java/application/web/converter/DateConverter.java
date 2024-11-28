package application.web.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import application.web.exception.WebExceptions;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) {
			throw new WebExceptions("Date is null or empty: " + value);
		}
		return LocalDate.parse(value, DTF);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			throw new WebExceptions("Date is null");
		}
		LocalDate date = (LocalDate) value;
		return DTF.format(date);
		
	}

}
