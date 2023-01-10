package kjs67_hanmall.model;

public class LoginDTO {
	private int user_num;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_mail;
	private String user_zip;
	private String user_addr;
	private String user_detail_addr;
	private String user_date;
	private int user_class;
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
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
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
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
	public String getUser_date() {
		return user_date;
	}
	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}
	public int getUser_class() {
		return user_class;
	}
	public void setUser_class(int user_class) {
		this.user_class = user_class;
	}
	@Override
	public String toString() {
		return "LoginDTO [user_num=" + user_num + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_name="
				+ user_name + ", user_phone=" + user_phone + ", user_mail=" + user_mail + ", user_zip=" + user_zip
				+ ", user_addr=" + user_addr + ", user_detail_addr=" + user_detail_addr + ", user_date=" + user_date
				+ ", user_class=" + user_class + "]";
	}
}
