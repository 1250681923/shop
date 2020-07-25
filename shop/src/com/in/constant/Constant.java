package com.in.constant;

public interface Constant {
  /*
   * Activiation
   */
  int USER_IS_NOT_ACTIVE = 0;    
  int USER_IS_ACTIVE = 1;
  
  /*
   * Mémoriser le nom du compte
   */
  String SAVE_NAME = "ok";
  /**
	 *La clé de la liste de catagory stockée dans redis
	 */
	String STORE_CATEGORY_LIST="STORE_CATEGORY_LIST";
	
	/**
	 * adresse du serveur de redis
	 */
	String REDIS_HOST = "192.168.17.136";
	
	/**
	 * Numéro de port du serveur de redis
	 */
	int  REDIS_PORT =  6379;
	/**
	 * Les produits plus vendus
	 */
	int PRODUCT_IS_HOT = 1;
	
	/**
	 * Statut de produit
	 */
	int PRODUCT_IS_UP = 0;
	int PRODUCT_IS_DOWN = 1;
	/**
	 * Statut de Order (non payé)
	 * 
	 */
	int ORDER_WEIFUKUAN=0;
	
	/**
	 * Payé
	 */
	int ORDER_YIFUKUAN=1;
	
	/**
	 *  Expédié
	 */
	int ORDER_YIFAHUO=2;
	
	/**
	 *  Fini
	 */
	int ORDER_YIWANCHENG=3;
}
