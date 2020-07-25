package com.in.domain;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.in.utils.DataSourceUtils;

public class Product {
	/*
	 * `pid` varchar(32) NOT NULL,
		  `pname` varchar(50) DEFAULT NULL,
		  `market_price` double DEFAULT NULL,
		  
		  `shop_price` double DEFAULT NULL,
		  `pimage` varchar(200) DEFAULT NULL,
		  `pdate` date DEFAULT NULL,
		  
		  `is_hot` int(11) DEFAULT NULL,
		  `pdesc` varchar(255) DEFAULT NULL,
		  `pflag` int(11) DEFAULT NULL,
		  
		  `cid` varchar(32) DEFAULT NULL,
	 */
	private String pid;
	private String pname;
	private Double market_price;
	
	private Double shop_price;
	private String pimage;
	private String pdate;
	
	private Integer is_hot;  //是否热门  1:热门    0:不热门
	private String pdesc;
	private Integer pflag;	//是否下架    1:下架	0:未下架
	
	//在多的一方放入一个一的一方的对象 用来表示属于那个分类
	private Category category;

	private String cid;

	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

/*
	public String getCname(String cid) throws Exception{
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select cname from category where cid=?";
		String cname = null;
		try {
			cname = qr.query(sql, new ScalarHandler(), cid).toString();
		} catch (SQLException e) {
		}
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	*/
	
}