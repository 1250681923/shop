package com.in.service;

import java.util.List;

import com.in.domain.PageBean;
import com.in.domain.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception;
	
	List<Product> findAll() throws Exception;

	void save(Product p)throws Exception;

	void update(Product p)throws Exception;

}
