package com.in.service.impl;

import java.util.List;

import com.in.dao.OrderDao;
import com.in.domain.Order;
import com.in.domain.OrderItem;
import com.in.domain.PageBean;
import com.in.service.OrderService;
import com.in.utils.BeanFactory;
import com.in.utils.DataSourceUtils;
public class OrderServiceImpl implements OrderService {

	@Override
	/**
	 * 保存订单
	 */
	public void save(Order order) throws Exception{
		try {
			//dao
			OrderDao od  = (OrderDao) BeanFactory.getBean("OrderDao");
			//1.Lancer la transaction
			DataSourceUtils.startTransaction();
			
			//2.Enregistrer
			od.save(order);
			
			//3.Enregistrer plusieurs items
			for (OrderItem oi : order.getItems()) {
				od.saveItem(oi);
			}
			
			//4.Fermer
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	@Override
	/**
	 * 我的订单
	 */
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		OrderDao od  = (OrderDao) BeanFactory.getBean("OrderDao");
		
		//1.创建pagebean
		PageBean<Order> pb = new PageBean<>(pageNumber, pageSize);
		
		//2.查询总条数  设置总条数 
		int totalRecord = od.getTotalRecord(uid);
		pb.setTotalRecord(totalRecord);
		
		//3.查询当前页数据 设置当前页数据
		List<Order> data = od.findMyOrdersByPage(pb,uid);
		pb.setData(data);
		return pb;
	}

	@Override
	/**
	 * 订单详情
	 */
	public Order getById(String oid) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		return od.getById(oid);
	}

	@Override
	/**
	 * 修改订单
	 */
	public void update(Order order) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		od.update(order);
	}


	@Override
	/**
	 * 后台查询订单列表
	 */
	public List<Order> findAllByState(String state) throws Exception {

		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		return od.findAllByState(state);
	}

}
