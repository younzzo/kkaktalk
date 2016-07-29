package net.nigne.kkt.domain;

import java.util.List;

import net.nigne.kkt.domain.ProductImageVO;

public class EscrowVO {
	
	String orderNo;
	int amount;
	String productDesc;
	String address;
	int postcode;
	String date;
	int e_account_num;
	boolean buyer_received_check;
	boolean seller_received_check;
	boolean deposit_check;
	int trade_no;
	String apiKey;
	

	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getE_account_num() {
		return e_account_num;
	}
	public void setE_account_num(int e_account_num) {
		this.e_account_num = e_account_num;
	}
	public boolean isBuyer_received_check() {
		return buyer_received_check;
	}
	public void setBuyer_received_check(boolean buyer_received_check) {
		this.buyer_received_check = buyer_received_check;
	}
	public boolean isSeller_received_check() {
		return seller_received_check;
	}
	public void setSeller_received_check(boolean seller_received_check) {
		this.seller_received_check = seller_received_check;
	}
	public boolean isDeposit_check() {
		return deposit_check;
	}
	public void setDeposit_check(boolean deposit_check) {
		this.deposit_check = deposit_check;
	}
	public int getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(int trade_no) {
		this.trade_no = trade_no;
	}

}
