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
public class RoleDAO {
    
    private static final String GET_BY_EMAIL = "select Role.* from TaiKhoan, UserRole, Role where TaiKhoan.username = UserRole.username and Role.roleId = UserRole.roleId and TaiKhoan.username = ?";
    private static final String GET_ALL = "select roleName from Role";
    
    /**
    *Hàm này lấy tất cả role name trong csdl
    *@return String là mảng roleName cách nhau bởi dấu phẩy
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */ 
    public String[] getAllRoleName() throws SQLException, ClassNotFoundException{
        String roleNames = new String();
        Connection conn = DataAccessHelper.getConnection(); 
        PreparedStatement ps = conn.prepareStatement(GET_ALL);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            roleNames+= rs.getString("roleName")+",";
        }
        roleNames = roleNames.substring(0, roleNames.length()-1);
        conn.close();
        String result[] = roleNames.split(",");      // có thể dùng StringTokenizer
        return result;
    }
    
    /**
    *Hàm này tìm ra các vai trò của người dùng theo email
    *@param email email của người cần tìm vài trò
    *@return  String là mảng roleName cách nhau bởi dấu phẩy
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */ 
    public String getRoleIdByUser(String email) throws SQLException, ClassNotFoundException{
        String roles = new String();
        Connection conn = DataAccessHelper.getConnection(); 
        PreparedStatement ps = conn.prepareStatement(GET_BY_EMAIL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            roles+= rs.getInt("roleId")+",";
        }
        roles = roles.substring(0, roles.length()-1);
        conn.close();
        return roles;
    }
    
}
