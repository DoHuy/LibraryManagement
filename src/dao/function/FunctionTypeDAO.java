/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class FunctionTypeDAO {
    
    private final String GET_BY_ID = "select * from FunctionType where functionTypeId = ?";
    private final String GET_ALL_TYPE_NAME = "select functionTypeName from FunctionType";
    
    /**
    *Hàm này lấy tất cả các functionTypeName trong bảng FunctionType
    *@return String là chuỗi các functionTypeName cách nhau bởi dấu phẩy
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */  
    public String[] getAllFunctionTypeName() throws SQLException, ClassNotFoundException{
        String functionTypeNames = new String();
        Connection conn = DataAccessHelper.getConnection(); 
        PreparedStatement ps = conn.prepareStatement(GET_ALL_TYPE_NAME);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            functionTypeNames+= rs.getString("functionTypeName")+",";
        }
        functionTypeNames = functionTypeNames.substring(0, functionTypeNames.length()-1);
        conn.close();
        String result[] = functionTypeNames.split(",");
        return result;
    }
    
    /**
    *Hàm này tìm functionTypeName theo id
    *@param functionTypeId là id của 1 bản ghi trong bảng FunctionType 
    *@return String functionTypeName tên của loại chức năng
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */  
    public String getFunctionTypeName(int functionTypeId) throws SQLException, ClassNotFoundException{
        Connection conn = DataAccessHelper.getConnection(); 
        PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
        ps.setInt(1, functionTypeId);
        ResultSet rs = ps.executeQuery();
        if(rs!= null && rs.next()){
            return rs.getString("fucntionTypeName");
        }
        conn.close();
        return null;
    }
}
