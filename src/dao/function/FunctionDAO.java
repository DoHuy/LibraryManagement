/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.function;

import function.entity.Function;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class FunctionDAO {
    
    private final String GET_BY_TYPE = "select functionName,boundaryClass,functionTypeName from FunctionType,ChucNang where FunctionType.functionTypeId = Function.functionTypeId";
    private final String GET_BY_ROLES = "select functionName,boundaryClass,functionTypeName from FunctionType,ChucNang where FunctionType.functionTypeId = ChucNang.functionTypeId and ChucNang.functionId in (select functionId from RoleFunction where RoleFunction.roleId in ("; 
    public static final String GET_BY_ONE_ROLE = "select functionId,functionName from ChucNang where Function.functionId in (select functionId from RoleFunction where RoleFunction.roleId = ?)"; 
    public static final String GET_BY_DIFF_ONE_ROLE = "select functionId,functionName from ChucNang where Function.functionId not in (select functionId from RoleFunction where RoleFunction.roleId = ?)"; 
    private final String DELETE = "delete from ChucNang where functionName = ?";
    private final String ADD="INSERT INTO ChucNang (functionName,functionTypeId,boundaryClass) VALUES (?,?,?)";
    private final String GET_BY_FUNCTION_NAME = "select functionId,functionName,boundaryClass,functionTypeId from ChucNang where functionName = ?";
    private final String UPDATE = "update ChucNang set functionName = ?, functionTypeId=?, boundaryClass = ? where functionName=? ";
    
    /**
    * Hàm này để lấy ra list function theo role và query select của nó.
    * @param roleId là id của trường vai trò
    * @param query  là một câu truy vấn
    * @return ArrayList<Function> là list function trả về từ query
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */    
    public ArrayList<Function> getFunctionByRoleId(int roleId, String query) throws SQLException, ClassNotFoundException{
        ArrayList<Function> functions = new ArrayList<Function>();
        Connection conn = DataAccessHelper.getConnection(); 
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, roleId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){            
            Function function = new Function();
            function.setFunctionId(rs.getInt("functionId"));
            function.setFunctionName(rs.getString("functionName"));
            functions.add(function);
        }
        conn.close();
        return functions;
    }
    
    
    /**
    *Hàm này để lấy list chức năng theo list các tên vai trò (roles)
    *@param roles list các roleName 
    *@return Map<String,ArrayList<Function>> key là typFunctionName value là list function
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */
    public Map<String,ArrayList<Function>> getFunctionsWithTypeByRoles(String roles) throws SQLException, ClassNotFoundException{
        Map<String,ArrayList<Function>> typeFunctionMap = new  HashMap<>();
        Connection conn = DataAccessHelper.getConnection();
        String typeName = new String();
        String query = new String();
        query+= GET_BY_ROLES+roles+"))";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Function item = new Function();
            item.setFunctionName(rs.getString("functionName"));
            item.setBoundaryClass(rs.getString("boundaryClass"));
            typeName= rs.getString("functionTypeName");
            if(typeFunctionMap.containsKey(typeName)){
                typeFunctionMap.get(typeName).add(item);
            }else{    
                ArrayList<Function> functions = new ArrayList<>();
                functions.add(item);
                typeFunctionMap.put(typeName, functions);
            }
        }
        conn.close();
        return typeFunctionMap;
    }
    /**
    *Hàm này để lấy list chức năng theo id của FunctionType
    *@param functionTypeId id của FunctionType 
    *@return ArrayList là list các function
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */    
    public ArrayList<Function> getFunctionByTypeId(int functionTypeId) throws SQLException, ClassNotFoundException{
        ArrayList<Function> functions = new  ArrayList<>();
        String queryForType = " and function.functionTypeId = ? ";
        String query = GET_BY_TYPE;
        if(functionTypeId !=0)
            query+=queryForType;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        if(functionTypeId !=0)
            ps.setInt(1, functionTypeId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Function item = new Function();
            item.setFunctionName(rs.getString("functionName"));
            item.setBoundaryClass(rs.getString("boundaryClass"));
            item.setFunctionTypeName(rs.getString("functionTypeName"));
            functions.add(item);
        }            
        conn.close();
        return functions;
    }
    
    /**
    *Hàm này để tìm chức năng theo tên Function
    *@param functionName là tên function 
     * @return  
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */      
    public Function getFunctionByFunctionName(String functionName) throws SQLException, ClassNotFoundException{
        Function function = new Function();
        Connection conn = DataAccessHelper.getConnection();     
        PreparedStatement ps = conn.prepareStatement(GET_BY_FUNCTION_NAME);
        ps.setString(1, functionName);
        ResultSet rs = ps.executeQuery();
        if(rs!=null && rs.next()){
            function.setFunctionId(rs.getInt("functionId"));
            function.setBoundaryClass(rs.getString("boundaryClass"));
            function.setFunctionName(rs.getString("functionName"));
            function.setFunctionTypeId(rs.getInt("functionTypeId"));
        }
        conn.close();
        
        return function;
    }
}
