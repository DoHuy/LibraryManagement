/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.entity;

/**
 * Class này quản lý Role trong cơ sở dữ liệu
 * @author Raph, dựa trên tài liệu của cô N.T.T.Trang
 */
public class Role {
    private int roleId;
    private String roleName;
    
    /**
    * Hàm này để lấy ra roleId trong bảng Role
    * @return int là roleId
    */ 
    public int getRoleId() {
        return roleId;
    }
    
    /**
    * Hàm này để gán giá trị cho roleId trong bảng Role
    * @param roleId là roleId
    */ 
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    /**
    * Hàm này để lấy ra roleName trong bảng Role
    * @return String là roleName
    */ 
    public String getRoleName() {
        return roleName;
    }
    
    /**
    * Hàm này để gán giá trị cho roleName trong bảng Role
    * @param roleName là roleName
    */ 
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
