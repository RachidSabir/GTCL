package com.myapp.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/Tableau_de_Bord.jsp")
public class Filter_jee extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;

	        String userName = null;
	        Cookie[] cookies = httpRequest.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals("email")) {
	                    userName = cookie.getValue();
	                    break;
	                }
	            }
	        }
	        if (userName == null) {
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Athentification.jsp");
	            return;
	        }
	        chain.doFilter(request, response);
	    }

}
