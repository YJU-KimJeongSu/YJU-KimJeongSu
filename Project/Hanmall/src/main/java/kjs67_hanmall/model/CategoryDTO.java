package kjs67_hanmall.model;

import java.util.ArrayList;

public class CategoryDTO {
	String category;
	ArrayList<String> detailCategory;
	int nowDCategory;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ArrayList<String> getDetailCategory() {
		return detailCategory;
	}
	public void setDetailCategory(ArrayList<String> detailCategory) {
		this.detailCategory = detailCategory;
	}
	public int getNowDCategory() {
		return nowDCategory;
	}
	public void setNowDCategory(int nowDCategory) {
		this.nowDCategory = nowDCategory;
	}
	@Override
	public String toString() {
		return "CategoryDTO [category=" + category + ", detailCategory=" + detailCategory + ", nowDCategory="
				+ nowDCategory + "]";
	}
}
