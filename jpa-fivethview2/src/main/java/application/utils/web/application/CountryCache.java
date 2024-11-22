package application.utils.web.application;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import application.country.Country;
import application.country.CountryServices;
import application.utils.JPAUtils;

@ManagedBean
@ViewScoped
public class CountryCache {
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
