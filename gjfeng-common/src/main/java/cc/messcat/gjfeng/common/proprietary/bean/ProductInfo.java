package cc.messcat.gjfeng.common.proprietary.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3541310485224688688L;

	private String goods_id;
	
	private String cat_id;
	
	private String goos_sn;
	
	private String goods_name;
	
	private String brand_id;
	
	private String store_count;
	
	private String weight;
	
	private BigDecimal market_price;
	
	private BigDecimal shop_price;
	
	private BigDecimal cost_price;
	
	private String keywords;
	
	private String goods_rematk;
	
	private String goods_content;
	
	private String is_real;
	
	private String is_on_sale;
	
	private String is_free_shipping;
	
	private List<SperGoodsPrice> spec_goods_price;
	
	private BigDecimal vip_price;
	
	private String goods_images;
	
	private String sort;
	
	private String profit;
	
	private String packing;
	

	private String is_score;
	
	private String exchange_integral;
	

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	public String getGoos_sn() {
		return goos_sn;
	}

	public void setGoos_sn(String goos_sn) {
		this.goos_sn = goos_sn;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getStore_count() {
		return store_count;
	}

	public void setStore_count(String store_count) {
		this.store_count = store_count;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public BigDecimal getMarket_price() {
		return market_price;
	}

	public void setMarket_price(BigDecimal market_price) {
		this.market_price = market_price;
	}

	public BigDecimal getShop_price() {
		return shop_price;
	}

	public void setShop_price(BigDecimal shop_price) {
		this.shop_price = shop_price;
	}

	public BigDecimal getCost_price() {
		return cost_price;
	}

	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getGoods_rematk() {
		return goods_rematk;
	}

	public void setGoods_rematk(String goods_rematk) {
		this.goods_rematk = goods_rematk;
	}

	public String getGoods_content() {
		return goods_content;
	}

	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}

	public String getIs_real() {
		return is_real;
	}

	public void setIs_real(String is_real) {
		this.is_real = is_real;
	}

	public String getIs_on_sale() {
		return is_on_sale;
	}

	public void setIs_on_sale(String is_on_sale) {
		this.is_on_sale = is_on_sale;
	}

	public String getIs_free_shipping() {
		return is_free_shipping;
	}

	public void setIs_free_shipping(String is_free_shipping) {
		this.is_free_shipping = is_free_shipping;
	}

	
	public BigDecimal getVip_price() {
		return vip_price;
	}

	public void setVip_price(BigDecimal vip_price) {
		this.vip_price = vip_price;
	}

	public String getIs_score() {
		return is_score;
	}

	public void setIs_score(String is_score) {
		this.is_score = is_score;
	}

	public String getExchange_integral() {
		return exchange_integral;
	}

	public void setExchange_integral(String exchange_integral) {
		this.exchange_integral = exchange_integral;
	}

	public String getGoods_images() {
		return goods_images;
	}

	public void setGoods_images(String goods_images) {
		this.goods_images = goods_images;
	}

	public List<SperGoodsPrice> getSpec_goods_price() {
		return spec_goods_price;
	}

	public void setSpec_goods_price(List<SperGoodsPrice> spec_goods_price) {
		this.spec_goods_price = spec_goods_price;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}
	
	
	
}
