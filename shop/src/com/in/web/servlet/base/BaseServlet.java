package com.in.web.servlet.base;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ͨ�õ�servlet
 */

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. Obtenir le nom de la methode
			String mName = request.getParameter("method");
					
			//1.1 Determiner si les parametres de methode sont transmis 
			if(mName == null || mName.trim().length()==0) {
				mName = "index";
			}
			
			//2. Obtenir un objet de methode
			Method method = this.getClass().getMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
						
			//3. Laisser la methode s'executer et recever la valeur de retour.
			String path = (String) method.invoke(this, request, response);
				
			//4. Determiner si la valeur de retour est vide, sinon transfert de demande
			if(path != null) {
			    request.getRequestDispatcher(path).forward(request, response);
			}
	
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
		public String index(HttpServletRequest request, HttpServletResponse response)throws  ServletException,IOException{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("~~Sans blague svp~~~");
			return null;
		}
}
