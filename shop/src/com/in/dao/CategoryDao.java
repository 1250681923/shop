package com.in.dao;

import java.util.List;

import com.in.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void save(Category c) throws Exception;


}
