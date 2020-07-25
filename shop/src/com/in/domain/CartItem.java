package com.in.domain;
/**
 * 购物项
 * @author Administrator
 *
 */
public class CartItem {
	//Produit
	private Product product;
	
	//Subtotal
	private Double subtotal;
	
	//quantite
	private Integer count;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getSubtotal() {
		return product.getShop_price()*count;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public CartItem(Product product, Integer count) {
		super();
		this.product = product;
		this.count = count;
	}
	
	
}
