/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.entity;

/**
 * Class này quản lý các thông tin liên quan đến RoleFunction trong cơ sở dữ liệu
 * @author Raph, dựa trên tài liệu của cô N.T.T.Trang
 */
public class RoleFunction {
    
    private int functionId,roleId;
    
    /**
    * Hàm này để lấy ra functionId trong bảng RoleFunction
    * @return int là functionId
    */ 
    public int getFunctionId() {
        return functionId;
    }
    /**
    * Hàm này để gán giá trị cho functionId trong bảng RoleFunction
    * @param functionId là functionId
    */ 
    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }
    /**
    * Hàm này để lấy ra roleId trong bảng RoleFunction
    * @return int là roleId
    */ 
    public int getRoleId() {
        return roleId;
    }
    /**
    * Hàm này để gán giá trị cho roleId trong bảng RoleFunction
    * @param roleId là roleId
    */ 
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
}
