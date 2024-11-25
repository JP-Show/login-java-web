package application.web.managedbeans.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.web.managedbeans.login.LoginBean;

public class LoginFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest servletReq, ServletResponse servletRes, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("entrou no filtro");
		HttpServletRequest req = (HttpServletRequest) servletReq;
		HttpServletResponse res = (HttpServletResponse) servletRes;
		String url = req.getRequestURL().toString();
		
		//Estamos utilizando esse método de pegar o LoginBean, porque nesta versão não temos o @Inject
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");
		
		if(url.contains("/logged") &&  loginBean != null && loginBean.getUserLogged() == null) 
			redirect(req, res);
		else filterChain.doFilter(req, res);	
	}

	private void redirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
	}
	
}
