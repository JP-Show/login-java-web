package application.web.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import application.web.exception.WebExceptions;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws WebExceptions {
		LocalDate ld = null;
		try {
			if(value == null || value.isEmpty()) {
				throw new WebExceptions("Date is null or empty: " + value);
			}
			ld = LocalDate.parse(value, DTF);
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no registro: " + e.getMessage(), null));
		        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		        return null; // Mantém o usuário na mesma página
			
		}
		
		return ld;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws WebExceptions {
		String dateString = "";
		try {
			if(value == null) {
				throw new WebExceptions("Date is null");
			}
			LocalDate date = (LocalDate) value;
			dateString = DTF.format(date);
		} catch (Exception e) {
			throw new WebExceptions("Error for convert Data to text: " + e);
		}

		return dateString;
		
	}

}
