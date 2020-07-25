package com.in.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in.constant.Constant;
import com.in.domain.Order;
import com.in.domain.Product;
import com.in.service.CategoryService;
import com.in.service.OrderService;
import com.in.service.ProductService;
import com.in.utils.BeanFactory;
import com.in.web.servlet.base.BaseServlet;

/**
 * 后台商品模块
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到添加的页面上
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//调用categoryservice 查询所有分类
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			
			request.setAttribute("list", cs.findList());
		} catch (Exception e) {
		}
		return "/admin/product/add.jsp";
	}
	
	/**
	 * 展示已上架商品列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用service 查询以上架商品
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			List<Product> list = ps.findAll();
			
			//2.将返回值放入request中,请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/product/list.jsp";
	}

	/**
	 * 删除某个商品
	 * 
	 */
	
	public String deleteProduit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取oid
			String pid = request.getParameter("pid");
			
			//2.调用service 获取订单
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			Product p = ps.getById(pid);
			
			//3.设置订单的状态,更新
			p.setPflag(1);
			ps.update(p);
			
			//4.重定向
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
		} catch (Exception e) {
		}
		return null;
	}
}
