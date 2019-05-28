package com.internousdev.leo.dto;

public class MCategoryDTO {
	// 決めたテーブルから、変数を決める
	private int id;
	private int categoryId;
	private String categoryName;
	private String categoryDescription;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	// Actionからの依頼を完成してActionに送信
	public String getCategoryName() {
		return categoryName;
	}
	// カテゴリーDAOからの物を受け取ってDTOのカテゴリー名に収納！
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
}