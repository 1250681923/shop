package com.in.web.servlet;

import com.in.constant.Constant;
import com.in.domain.Cart;
import com.in.domain.CartItem;
import com.in.domain.Order;
import com.in.domain.OrderItem;
import com.in.domain.PageBean;
import com.in.domain.Product;
import com.in.domain.User;
import com.in.service.OrderService;
import com.in.service.ProductService;
import com.in.utils.BeanFactory;
import com.in.utils.UUIDUtils;
import com.in.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 订单模块
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 获取订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取oid
			String oid = request.getParameter("oid");
			
			//2.调用service 查询单个订单 
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(oid);
			
			//3.请求转发
			request.setAttribute("bean",order);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "La requête a échoué");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	
	
	/**
	 * 我的订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findMyOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pageNumber 设置pagesize 获取userid
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize=3;
			
			User user=(User)request.getSession().getAttribute("user");
			if(user == null){
				//未登录 提示
				request.setAttribute("msg", "S'il vous plait Connectez-vous d'abord!");
				return "/jsp/msg.jsp";
			}
			
			//2.调用service获取当前页所有数据  pagebean
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			PageBean<Order> bean = os.findMyOrdersByPage(pageNumber,pageSize,user.getUid());
			
			//3.将pagebean放入request域中,请求转发 order_list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Impossible d'obtenir mes commande");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_list.jsp";
	}
	/**
	 * 保存订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//-1.Obtenir user dans session()
			User user=(User) request.getSession().getAttribute("user");
			if(user == null){
				// faut login d'abord
				request.setAttribute("msg", "S'il vous plait Connectez-vous d'abord!");
				return "/jsp/msg.jsp";
			}
			
			//0.obtenir panier
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			
			//1.Encapsulation
			//1.1Creer un order 
			Order order = new Order();
			
			//1.2oid 
			order.setOid(UUIDUtils.getId());
			
			//1.3ordertime
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			order.setOrdertime(df.format(new Date()));			
			
			//1.4total 
			order.setTotal(cart.getTotal());
			
			//1.5state
			order.setState(Constant.ORDER_WEIFUKUAN);
			
			//1.6user
			order.setUser(user);
			
			//1.7 Parcourer la liste des articles d'achat
			for (CartItem	ci : cart.getCartItems()) {
				//a.Creer orderitem
				OrderItem oi = new OrderItem();
				
				//b.itemid uuid
				oi.setItemid(UUIDUtils.getId());
				
				//c.count
				oi.setCount(ci.getCount());
				
				//d.subtotal 
				oi.setSubtotal(ci.getSubtotal());
				
				//e.product
				oi.setProduct(ci.getProduct());
				
				//f.order 
				oi.setOrder(order);
				
				//1.7.2 Mettre orderitem dans order 
				order.getItems().add(oi);
			}
			
			
			//2.Finir l'operation
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			os.save(order);
			
			//3.Sauter a order_info.jsp
			request.setAttribute("bean", order);
		} catch (Exception e) {
		}
		return "/jsp/order_info.jsp";
	}
	/**
	 * 在线支付
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取收获信息 获取oid 获取银行
		
		//2.调用service获取订单 修改收获人信息  更新订单
		
		//3.拼接给第三方的url
		
		//4.重定向
		
		
		try {
			//接受参数
			String address=request.getParameter("address");
			String name=request.getParameter("name");
			String telephone=request.getParameter("telephone");
			String oid=request.getParameter("oid");
			
			
			//通过id获取order
			OrderService s=(OrderService) BeanFactory.getBean("OrderService");
			Order order = s.getById(oid);
			
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			order.setState(1);
			//更新order
			s.update(order);
			
			request.setAttribute("msg", "Paiement réussi");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Paiement échoué");
		
		}
		
		return "/jsp/msg.jsp";
	}
	

}
