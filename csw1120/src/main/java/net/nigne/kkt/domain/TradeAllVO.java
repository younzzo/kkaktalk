package net.nigne.kkt.domain;

import java.util.List;

public class TradeAllVO {
	List <ProductImageVO> product_image;
	List <TradeVO> trade;
	
	public List<ProductImageVO> getProduct_image() {
		return product_image;
	}
	public void setProduct_image(List<ProductImageVO> product_image) {
		this.product_image = product_image;
	}
	public List<TradeVO> getTrade() {
		return trade;
	}
	public void setTrade(List<TradeVO> trade) {
		this.trade = trade;
	}

}
