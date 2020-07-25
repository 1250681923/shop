package com.in.web.servlet;

import com.in.domain.Product;
import com.in.service.ProductService;
import com.in.service.impl.ProductServiceImpl;
import com.in.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
	首页模块
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.Chercher les nouveaux produits et produits les plus vendus
			ProductService ps = new ProductServiceImpl();
			List<Product> hotList=ps.findHot();
			List<Product> newList=ps.findNew();
			
			//2.Mettre les 2 listes
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
		}
		
		return "/jsp/index.jsp";
	}
}
