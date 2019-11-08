/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class CategoryDAO {
    
    private final static String LOAD_CATEGORY_INFO = "select * from TheLoai";
    private final static String GET_CATEGORY_BY_ID = "select tenTheLoai from TheLoai where maTheLoai = ?";
    private final static String UPDATE_NUM = "update TheLoai set soLuongSach = ? where tenTheLoai = ?";
    
    
    /**
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Category> loadCategory() throws ClassNotFoundException, SQLException{
        ArrayList<Category> list = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(LOAD_CATEGORY_INFO);
        ResultSet rs = ps.executeQuery();
        while(rs != null && rs.next()){
            Category c = new Category();
            c.setCategory_id(rs.getInt("maTheLoai"));
            c.setCategory_name(rs.getString("tenTheLoai"));
            c.setCategory_code(rs.getString("ma"));
            c.setNumberOfBook(rs.getInt("soLuongSach"));
            
            list.add(c);
        }
        
        conn.close();
        return list;
    }
    
    /**
     * 
     * @param categoryId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String getCategoryNameOverId(int categoryId) throws ClassNotFoundException, SQLException{
        String name = null;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_CATEGORY_BY_ID);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            name = rs.getString("tenTheLoai");
        }
        conn.close();
        return name;
    } 
    
    /**
     * 
     * @param num
     * @param name
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateNum(int num, String name) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_NUM);
        ps.setInt(1, num);
        ps.setString(2, name);
        ps.executeUpdate();
        conn.close();
    }
}
