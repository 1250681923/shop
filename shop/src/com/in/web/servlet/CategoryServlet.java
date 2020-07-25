package com.in.web.servlet;

import com.in.service.CategoryService;
import com.in.service.impl.CategoryServiceImpl;
import com.in.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 前台分类模块
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 查询所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//0.Définir le code de réponse
			response.setContentType("text/html;charset=utf-8");
			
			//1.interroger toutes les catégories, renvoyer la valeur json string
			CategoryService cs = new CategoryServiceImpl();
			//Récupérer la liste de mysql
			String value = cs.findAll();
			
			//Obtenir la liste de redis
			//String value = cs.findAllFromRedis();
			//2.Réécriver la chaîne dans le navigateur
			response.getWriter().println(value);
		} catch (Exception e) {
		}
		return null;
	}

}
