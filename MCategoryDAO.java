package com.internousdev.leo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.leo.dto.MCategoryDTO;
import com.internousdev.leo.util.DBConnector;

public class MCategoryDAO {

	public List<MCategoryDTO> getMCategoryList(){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
		String sql = "select * from m_category";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(resultSet.getInt("id"));
				mCategoryDTO.setCategoryId(resultSet.getInt("category_id"));
				mCategoryDTO.setCategoryName(resultSet.getString("category_name"));
				mCategoryDTO.setCategoryDescription(resultSet.getString("category_description"));
				mCategoryDTOList.add(mCategoryDTO);
			}
		}
		// 商品検索にエラーが発生したとき
		catch (SQLException e){
			e.printStackTrace();
		}
		//  finally(最後に必ず実行する処理)をしてから try-catch(例外が発生する処理)を行う
		finally{
			try{
				connection.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
		return mCategoryDTOList;
	}
}