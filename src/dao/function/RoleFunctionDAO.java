/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.function;

import function.entity.RoleFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class RoleFunctionDAO {
    
    private final String ADD = "insert into RoleFunction (functionId, roleId) values(?,?)";
    private final String DELETE = "delete from RoleFunction where functionId=? and roleId = ?";
    private final String DELETE_BY_FUNCTION_ID ="delete from RoleFunction where functionId=?";
    public static final String GET_BY_FUNCTION_ID_NOT_IN_ROLE  ="select roleId from RoleFunction where functionId=? and roleId <> ?";
    public static final String GET_BY_FUNCTION_ID_IN_ROLE="select roleId from RoleFunction where functionId=? and roleId = ?";
    
    
    
    
    /**
     * Hàm này để lấy roleId theo functionId
     * @param functionId là id của function
     * @param roleId
     * @param query
     * @return int là roleId
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public int getRoleIdByFunctionId(int functionId,int roleId,String query) throws SQLException, ClassNotFoundException{
        Connection conn = DataAccessHelper.getConnection(); 
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, functionId);
        ps.setInt(2,roleId);
        ResultSet rs = ps.executeQuery();
        RoleFunction roleFunction = new RoleFunction();
        while(rs.next()){
            roleFunction.setRoleId(rs.getInt("roleId"));
            
        }
        
        
        conn.close();
        return roleFunction.getRoleId();
    }
    
}
