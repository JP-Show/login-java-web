package application.utils.web.exceptions;

public class WebExceptions extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	public WebExceptions (String msg) {
		super(msg);
	}
}
