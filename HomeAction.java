package com.internousdev.leo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.leo.dao.MCategoryDAO;
import com.internousdev.leo.dto.MCategoryDTO;
import com.internousdev.leo.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

    //  SessionAwareを使って機能を拡張させる　
public class HomeAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;

	//  ここで ID・ログイン・カテゴリ(選択肢)の確認をする
	public String execute(){
		if (!(session.containsKey("tempUserId"))) {
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}
		if(!session.containsKey("logined")) {
			session.put("logined", 0);
		}
		if(!session.containsKey("mCategoryDTOList")){
			// 　< >内はListに収納するための型を決める。
			List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			try {
				mCategoryDTOList = mCategoryDAO.getMCategoryList();
			}
			catch (NullPointerException e) {
				mCategoryDTOList = null;
			}
			session.put("mCategoryDTOList", mCategoryDTOList);
		}
		return SUCCESS;  // ログイン(している)状態なのでSUCCESS と表記する
	}
	public Map<String, Object> getSession(){
		return session;
	}
	//  Overrideする
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
// コメントは随時追加していきます。