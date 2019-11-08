/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class PublisherDAO {

    private final static String ADD_NEW_PUBLISHER = "insert into NhaPhatHanh(ten) values (?)";
    private final static String GET_PUBLISHER_BY_ID = "select ten from NhaPhatHanh where maNhaPhatHanh = ?";
    private final static String CHECK_PUBLISHER = "select ten from NhaPhatHanh where ten = ?";
    private final static String GET_ID_BY_NAME = "select maNhaPhatHanh from NhaPhatHanh where ten = ?";
    
     /**
     * 
     * @param name
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNew(String name) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_NEW_PUBLISHER);
        ps.setString(1, name);
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * 
     * @param categoryId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String getPublisherNameOverId(int categoryId) throws ClassNotFoundException, SQLException{
        String name = null;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_PUBLISHER_BY_ID);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            name = rs.getString("ten");
        }
        conn.close();
        return name;
    } 
    
    
    /**
     * 
     * @param nameValue
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    public String checkPublisher(String nameValue) throws ClassNotFoundException, SQLException{
        String name = null;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(CHECK_PUBLISHER);
        ps.setString(1, nameValue);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            name = rs.getString("ten");
        }
        
        conn.close();
        return name;
    }

    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getPublisherIdByName(String name) throws ClassNotFoundException, SQLException{
        int id = -1;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_ID_BY_NAME);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            id = rs.getInt("maNhaPhatHanh");
        }
        
        conn.close();
        return id;
    }
}
