package com.internousdev.leo.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.leo.dao.ProductInfoDAO;
import com.internousdev.leo.dto.ProductInfoDTO;
import com.internousdev.leo.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware{

	// jspのファイルから受け取る値(フィールド変数)を定義する。 --※ jspと同じ名前にすること！--
	private String categoryId;
	private String keywords;
	private List<String> keywordsErrorMessageList;
	private List<ProductInfoDTO> productInfoDTOList;
	private Map<String, Object> session;

	public String execute() {
		// カテゴリーの選択肢を選択していない(存在していない)ときは、全てのカテゴリーを選択する
		if (categoryId == null){
			categoryId = "1";
		}

		InputChecker inputChecker = new InputChecker();
		String tempKeywords = null;
		// 処理するための変数に値を代入する
		if (StringUtils.isBlank(keywords)){
			tempKeywords = "";
		}
		else {
			tempKeywords = keywords.replaceAll("　", " ").replaceAll("\\s{2,}", " ");
		}
		// 添付キーワードがDTOから持ってきた値と各々一致しているか確認する
		if(!(tempKeywords.equals(""))){
			keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords,0,50, true, true, true, true, false, true, true);
			if (keywordsErrorMessageList.size() > 0){
				return SUCCESS;
			}
		}
		// 商品一覧画面に移動するために
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		switch (categoryId) {
		case "1":
			productInfoDTOList = productInfoDAO.getProductInfoListByKeyword(tempKeywords.split(" "));
			break;
		default:
			productInfoDTOList = productInfoDAO.getProductInfoListByCategoryIdAndKeyword(tempKeywords.split(" "), categoryId);
			break;
		}
		return SUCCESS;
	}
	// カテゴリーIDを定義して、jspファイルでユーザーさんが入力したカテゴリーIDをフィールド変数に収納する。
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	// キーワードを定義して、jspファイルでユーザーさんが入力したキーワードをフィールド変数に収納する。
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public List<String> getKeywordsErrorMessageList() {
		return keywordsErrorMessageList;
	}
	public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList) {
		this.keywordsErrorMessageList = keywordsErrorMessageList;
	}
	public List<ProductInfoDTO> getProductInfoDTOList() {
		return productInfoDTOList;
	}
	public void setProductInfoList(List<ProductInfoDTO> productInfoDTOList) {
		this.productInfoDTOList = productInfoDTOList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}