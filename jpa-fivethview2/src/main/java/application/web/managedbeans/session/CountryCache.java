package application.web.managedbeans.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import application.country.Country;
import application.country.CountryServices;

@ManagedBean
//eu prefiri usar o seassionscoped porque não quero que isso seja chamado toda vez que a página recarregar
//só apenas quando carrega pela primeira vez
@SessionScoped
public class CountryCache implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SelectItem> selectItems;
	
	@PostConstruct
	public void init() {
		System.out.println("Iniciou");
		selectItems = new ArrayList<>();
		List<Country> countries = CountryServices.findAll();
		for (Country country : countries) {
			selectItems.add(new SelectItem(country.getName()));
		}
	}
	public List<SelectItem> getSelectItems() {
		return selectItems;
	}
	
}
