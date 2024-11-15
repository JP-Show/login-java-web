package application.country.exceptions;

public class CountryExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CountryExceptions (String msg) {
		super(msg);
	}
}
