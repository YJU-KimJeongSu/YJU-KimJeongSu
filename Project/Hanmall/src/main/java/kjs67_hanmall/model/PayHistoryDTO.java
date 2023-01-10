package kjs67_hanmall.model;

public class PayHistoryDTO {
	private int user_num;
	private String user_name;
	private String user_phone;
	private String user_zip;
	private String user_addr;
	private String user_detail_addr;
	private String deliv_name;
	private String deliv_phone;
	private String deliv_zip;
	private String deliv_addr;
	private String deliv_detail_addr;
	private String order_id;
	private String pay_method;
	private int pay_amount;
	private int pay_success;
	private String pay_date;
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_zip() {
		return user_zip;
	}
	public void setUser_zip(String user_zip) {
		this.user_zip = user_zip;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getUser_detail_addr() {
		return user_detail_addr;
	}
	public void setUser_detail_addr(String user_detail_addr) {
		this.user_detail_addr = user_detail_addr;
	}
	public String getDeliv_name() {
		return deliv_name;
	}
	public void setDeliv_name(String deliv_name) {
		this.deliv_name = deliv_name;
	}
	public String getDeliv_phone() {
		return deliv_phone;
	}
	public void setDeliv_phone(String deliv_phone) {
		this.deliv_phone = deliv_phone;
	}
	public String getDeliv_zip() {
		return deliv_zip;
	}
	public void setDeliv_zip(String deliv_zip) {
		this.deliv_zip = deliv_zip;
	}
	public String getDeliv_addr() {
		return deliv_addr;
	}
	public void setDeliv_addr(String deliv_addr) {
		this.deliv_addr = deliv_addr;
	}
	public String getDeliv_detail_addr() {
		return deliv_detail_addr;
	}
	public void setDeliv_detail_addr(String deliv_detail_addr) {
		this.deliv_detail_addr = deliv_detail_addr;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public int getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(int pay_amount) {
		this.pay_amount = pay_amount;
	}
	public int getPay_success() {
		return pay_success;
	}
	public void setPay_success(int pay_success) {
		this.pay_success = pay_success;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	@Override
	public String toString() {
		return "PayHistoryDTO [user_num=" + user_num + ", user_name=" + user_name + ", user_phone=" + user_phone
				+ ", user_zip=" + user_zip + ", user_addr=" + user_addr + ", user_detail_addr=" + user_detail_addr
				+ ", deliv_name=" + deliv_name + ", deliv_phone=" + deliv_phone + ", deliv_zip=" + deliv_zip
				+ ", deliv_addr=" + deliv_addr + ", deliv_detail_addr=" + deliv_detail_addr + ", order_id=" + order_id
				+ ", pay_method=" + pay_method + ", pay_amount=" + pay_amount + ", pay_success=" + pay_success
				+ ", pay_date=" + pay_date + "]";
	}
	
}
