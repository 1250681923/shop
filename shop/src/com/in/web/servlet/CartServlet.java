package com.in.web.servlet;

import com.in.domain.Cart;
import com.in.domain.CartItem;
import com.in.domain.Product;
import com.in.service.ProductService;
import com.in.utils.BeanFactory;
import com.in.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车模块
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.Vider panier
		getCart(request).clearCart();
		
		//2.retour
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
	
	/**
	 * 从购物车移除商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.Obtenir pid
		String pid = request.getParameter("pid");
		
		//2.Supprimer
		getCart(request).removeFromCart(pid);
		
		//3.Retour
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add2cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//1.Parametre pid count
			String pid = request.getParameter("pid");
			int count = Integer.parseInt(request.getParameter("count"));
			
			
			// Obtenir product
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			Product product = ps.getById(pid);
			
			//Creer cartitem
			CartItem cartItem = new CartItem(product, count);
			
			//3 Mettre cartitem dans cart
			Cart cart=getCart(request);
			
			cart.add2cart(cartItem);
			
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "ajouter au panier est échec");
			return "/jsp/msg.jsp";
		}
		
		return null;
	}

	/**
	 * 获取购物车
	 * @param request
	 * @return
	 */
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			
			//mettre cart dans session
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}

