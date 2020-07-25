package com.in.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 购物车
 * @author Administrator
 *
 */
public class Cart {
	private Map<String, CartItem> itemMap=new HashMap<String, CartItem>();
	private Double total=0.0;
	
	/**
	 * Obtenir tous les produits d'achat
	 * @return
	 */
	public  Collection<CartItem> getCartItems(){
		return itemMap.values();
	}
	public Map<String, CartItem> getItemMap() {
		return itemMap;
	}
	public void setItemMap(Map<String, CartItem> itemMap) {
		this.itemMap = itemMap;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * Ajouter au panier
	 * @param item
	 */
	public void add2cart(CartItem item){
		//Obtenir id
		String pid = item.getProduct().getPid();
		
		//Déterminer s'il y en a dans le panier
		if(itemMap.containsKey(pid)){
			//oui, on change la quantité
			CartItem oItem = itemMap.get(pid);
			
			oItem.setCount(oItem.getCount()+item.getCount());
		}else{
			//non
			itemMap.put(pid, item);
		}
		
		//Modifier le montant total
		total += item.getSubtotal();
	}
	
	/**
	 * Supprimer un produit du panier
	 * @param pid
	 */
	public void removeFromCart(String pid){
		//1.Supprimer le produit du panier 
		CartItem item = itemMap.remove(pid);
		
		//2.Modifier le montant total
		total -= item.getSubtotal();
	}
	
	/**
	 * Vider panier
	 */
	public void clearCart(){
		//1.Vider map
		itemMap.clear();
		
		//2.Modifier le montant total = 0
		total=0.0;
	}
}
