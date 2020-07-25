package com.in.service.impl;

import java.util.List;

import com.in.dao.OrderDao;
import com.in.dao.ProductDao;
import com.in.dao.impl.ProductDaoImpl;
import com.in.domain.Order;
import com.in.domain.PageBean;
import com.in.domain.Product;
import com.in.service.ProductService;
import com.in.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {

	@Override
	/**
	 * Trouver les produits les plus vendus
	 */
	public List<Product> findHot() throws Exception {
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findHot();
	}

	@Override
	/**
	 * Trouver les nouveaux produits 
	 */
	public List<Product> findNew() throws Exception {
		//ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findNew();
	}


	@Override
	/**
	 * 单个商品详情
	 */
	public Product getById(String pid) throws Exception {
		ProductDao pd=(ProductDao) BeanFactory.getBean("ProductDao");
		
		return pd.getById(pid);
	}

	@Override
	/**
	 * 分页展示分类商品
	 */
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		//1.Creer pagebean
		PageBean<Product> pb = new PageBean<>(pageNumber, pageSize);
		
		//2.Définir les données de la page actuelle
		List<Product> data = pDao.findByPage(pb,cid);
		pb.setData(data);
		
		//3.Définir le nombre total d'enregistrements
		int totalRecord = pDao.getTotalRecord(cid);
		pb.setTotalRecord(totalRecord);
		
		return pb;
	}
	@Override
	/**
	 * 后台展示已上架商品
	 */
	public List<Product> findAll() throws Exception {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		return pDao.findAll();
	}

	@Override
	/**
	 * 保存商品
	 */
	public void save(Product p) throws Exception {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		pDao.save(p);
		
	}

	public void update(Product product) throws Exception {
		ProductDao pDao = (ProductDao) BeanFactory.getBean("ProductDao");
		pDao.update(product);
	}


}
