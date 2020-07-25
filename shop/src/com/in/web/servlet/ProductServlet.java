package com.in.web.servlet;

import com.in.domain.PageBean;
import com.in.domain.Product;
import com.in.service.ProductService;
import com.in.service.impl.ProductServiceImpl;
import com.in.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台商品模块
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类商品分页展示
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.Obtenir les paramètres    Définir la taille de la page
			/*String parameter = request.getParameter("pageNumber");*/
			int pageNumber = 1;
			
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException e) {
			}
			
			int pageSize = 12;
			String cid = request.getParameter("cid");
			
			//2. Trouver les produits par page   valeur retour:pagebean
			ProductService ps = new ProductServiceImpl();
			PageBean<Product> bean=ps.findByPage(pageNumber,pageSize,cid);
			
			//3.le transfert de la demande  product_list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			request.setAttribute("msg", "Délai d'expiration du service, veuillez mettre à jour");
			return "/jsp/msg.jsp";
		}
		return "/jsp/product_list.jsp";
	}
	
	/**
	 * Info de produit
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.Obtenir pid
			String pid = request.getParameter("pid");
			
			//2.Paramètre :pid     valeur retrour:product
			ProductService ps =new ProductServiceImpl();
			Product pro=ps.getById(pid);
			
			//3.Aller a la page  /jsp/product_info.jsp
			request.setAttribute("bean", pro);
		} catch (Exception e) {
			request.setAttribute("msg", "Délai d'expiration du service, veuillez mettre à jour");
			return "/jsp/msg.jsp";
		}
		
		return "/jsp/product_info.jsp";
	}
}