package com.in.service.impl;

import java.util.List;

import com.in.constant.Constant;
import com.in.dao.CategoryDao;
import com.in.dao.impl.CategoryDaoImpl;
import com.in.domain.Category;
import com.in.service.CategoryService;
import com.in.utils.BeanFactory;
import com.in.utils.JedisUtils;
import com.in.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {


	@Override
	/**
	 * Afficher toutes les catégories en arrière-plan
	 */
	public List<Category> findList() throws Exception {
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		return cd.findAll();
	}
	@Override
	/**
	 * Rechercher toutes les catégories
	 */
	public String findAll() throws Exception {
		/*//1.调用dao 查询所有分类
		CategoryDao cd = new CategoryDaoImpl();
		List<Category> list = cd.findAll();*/
		
		List<Category> list=findList();
		
		//2.Convertir la liste en chaîne json
		if(list!=null && list.size()>0){
			return JsonUtil.list2json(list);
		}
		return null;
	}


	@Override
	/**
	 * Recherchez toutes les catégories de Redis
	 */
	public String findAllFromRedis() throws Exception {		
		Jedis j =null;
		String value=null;
		try {
			//1.Obtenez les informations de catagory de redis
			try {
				//1.1 Connexion
				j = JedisUtils.getJedis();
				
				//1.2 Obtenir les donnees
				value = j.get(Constant.STORE_CATEGORY_LIST);
				
				//1.3 Si non vide, renvoyer les données
				if(value!=null){
					System.out.println("Il y a des données dans le cache");
					return value;
				}
			} catch (Exception e) {
			}			
			//2 S'il y n'a pas de donnees dans Redis, on prend les donnees dans Mysql et le met dans Redis
			value = findAll();
								
			//3.Mettre les values dans Redis
			try {
				j.set(Constant.STORE_CATEGORY_LIST, value);
				System.out.println("Les données ont été placées dans le cache");
			} catch (Exception e) {
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			//Fermer Jedis
			JedisUtils.closeJedis(j);
		}		
		return value;
	}

	@Override
	/**
	 * 添加分类
	 */
	public void save(Category c) throws Exception {
		//1.调用dao 完成添加
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		cd.save(c);
	
		//2.更新redis
//		Jedis j = null;
//		try {
//			j=JedisUtils.getJedis();
//			//清除redis中数据
//			j.del(Constant.STORE_CATEGORY_LIST);
//		} finally {
//			JedisUtils.closeJedis(j);
//		}
	}

	
}
