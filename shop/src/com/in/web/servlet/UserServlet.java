package com.in.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.in.constant.Constant;
import com.in.dao.UserDao;
import com.in.dao.impl.UserDaoImpl;
import com.in.domain.User;
import com.in.service.UserService;
import com.in.service.impl.UserServiceImpl;
import com.in.utils.UUIDUtils;
import com.in.web.servlet.base.BaseServlet;



/**
 * 用户模块
 */

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 退出
	 */

	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath());
		return null;
	}
	
	
		
	
	
	/*
	 * 用户登录
	 */
	
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			//Obtenir les parametres
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//Login par service  valeur retrour：user
			UserService us = new UserServiceImpl();
			User user  = us.login(username,password);
			
			//Jugements 
			if(user == null) {
				//Nom d'utilisateur et mot de passe ne correspondent pas
				request.setAttribute("msg", "username ou password incorrect.");
				return "/jsp/login.jsp";
			}
			//Déterminer s'il faut activer
			if(Constant.USER_IS_ACTIVE != user.getState()) {
				//non activé
				request.setAttribute("msg", "Veuillez vous activer avant la connexion.");
				return "/jsp/msg.jsp";
			}
			//Captcha
			  String cap = request.getParameter("cap");
		      Integer res = (Integer)request.getSession().getAttribute("res");
	        if(Integer.parseInt(cap) == res) {
	            //  réussi
	        	request.getSession().removeAttribute("res");
	            //Enregistrer le statut de connexion
				request.getSession().setAttribute("user", user);
	 
	        } else {
	        	request.setAttribute("msg", "Captcha est incorrect.");
				return "/jsp/login.jsp";
	        }
			///////Save name
			//Déterminer s'il faut se souvenir du nom d'utilisateur
			if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
				Cookie c = new Cookie("saveName", URLEncoder.encode(username, "utf-8"));
				
				c.setMaxAge(Integer.MAX_VALUE);
				c.setPath(request.getContextPath()+"/");
				
				response.addCookie(c);
			}
			///////////
			//index.jsp
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
		
			e.printStackTrace();
			request.setAttribute("msg", "Échec de la connexion.");
			return "/jsp/msg.jsp";
		}
		
		return null;
	}
	/*
	 * 跳转到登陆界面
	 * 
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/jsp/login.jsp";
	}
	
	
	
	
	
	

	/*
	 * 用户激活
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.La paramètre code
			String code = request.getParameter("code");
			
			//2.Finir activer en utilisant service，valeur de retour：user
			UserService us = new UserServiceImpl();
			User user = us.active(code);
			
			//3.Jugement
			if(user == null) {
				//échoué
				request.setAttribute("msg", "L'activation a échoué, veuillez réactiver ou vous réinscrire~");
				return "/jsp/msg.jsp";
			}
			//reussi
			request.setAttribute("msg", "Félicitations, l'activation est réussie, vous pouvez vous connecter~");
		} catch (Exception e) {
			request.setAttribute("msg", "L'activation a échoué, veuillez réactiver ou vous réinscrire~");
			return "/jsp/msg.jsp";
		}
		return "/jsp/msg.jsp";
	}
	
	
	
	

	/*
	 * 用户注册
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try {
		//1. Encapsulation d'objet
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			//1.1 Encapsulation uid
			user.setUid(UUIDUtils.getId());
			
			//1.2 Encapsulation state
			user.setState(Constant.USER_IS_NOT_ACTIVE);
			
			//1.3 Encapsulation la code d'activiation
			user.setCode(UUIDUtils.getCode());
			
			//2. Utiliser service pour finir l'inscription
			UserService us = new UserServiceImpl();
			
			// pour evider le double saisir
			UserDao ud = new UserDaoImpl();
			User usere = ud.getByUsername(user.getUsername());
					
			if(usere == null) {	
			String cap = request.getParameter("cap");
		    Integer res = (Integer)request.getSession().getAttribute("res");
	        if(Integer.parseInt(cap) == res) {
	        	String conf = request.getParameter("conf");
	        	String passw =  request.getParameter("password");
	        	if(passw.equals(conf)) {
		        	us.regist(user);
		        	//3. Sauter et afficher les infors
					request.setAttribute("msg", "Felicitation, succès de l'inscription. Veuillez vous connecter votre mail pour terminer l'activation.");
	        	}else
	        	{
	        		request.setAttribute("msg1", "Veuillez conserver le même mot de passe.");
					return "/jsp/register.jsp";
	        	}	        	
	        } else {
	        	request.setAttribute("msg2", "Captcha est incorrect.");
				return "/jsp/register.jsp";
	        }
			
			}else {
				request.setAttribute("msg", "Vous vous avez deja inscrit, merci de l'activer ou bien re-inscrire.");
				return "/jsp/register.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			//sauter a msg.jsp
			request.setAttribute("msg", "L'inscription de l'utilisateur a échoué.");
			return "/jsp/msg.jsp";
		}
	  
		return "/jsp/msg.jsp";
	}
	
	/*
	 * 跳转到注册页面
	 */
	
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		return "/jsp/register.jsp";
			
		}
		
		
		
		
}

