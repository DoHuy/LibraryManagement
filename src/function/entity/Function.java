/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.entity;


/**
 * Class này quản lý Function trong cơ sở dữ liệu
 * @author Raph, dựa trên tài liệu của cô N.T.T.Trang
 */
public class Function {
    
    private int functionId;
    private String functionName;
    private String functionTypeName;
    private int functionTypeId;
    private String boundaryClass;
    private int roleId;
    
    /**
    * Hàm này để lấy ra roleId 
    * roleId này thực chất không phải của Function 
    * mà chỉ lưu vào với mục đích sử dụng khác
    * @return int roleId
    */    
    public int getRoleId() {
        return roleId;
    }
    
    /**
    * Hàm này để gán giá trị cho roleId 
    * roleId này thực chất không phải của Function 
    * mà chỉ lưu vào với mục đích sử dụng khác
    * @param roleId là roleId
    */  
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    /**
    * Hàm này để lấy ra functionTypeName
    * functionTypeName này thực chất không phải của Function 
    * mà chỉ lưu vào với mục đích sử dụng khác
    * @return String functionTypeName
    */  
    public String getFunctionTypeName() {
        return functionTypeName;
    }
    
    /**
    * Hàm này để gán giá trị cho functionTypeName
    * functionTypeName này thực chất không phải của Function 
    * mà chỉ lưu vào với mục đích sử dụng khác
    * @param functionTypeName là functionTypeName
    */ 
    public void setFunctionTypeName(String functionTypeName) {
        this.functionTypeName = functionTypeName;
    }
    
    /**
    * Hàm này để lấy ra functionId trong bảng Funtion
    * @return int functionId
    */ 
    public int getFunctionId() {
        return functionId;
    }
    
    /**
    * Hàm này để gán giá trị cho functionId trong bảng Function
    * @param functionId là functionId
    */ 
    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }
    
    /**
    * Hàm này để lấy ra getFunctionName trong bảng Function
    * @return String là getFunctionName 
    */ 
    public String getFunctionName() {
        return functionName;
    }
    
    /**
    * Hàm này để gán giá trị cho functionName trong bảng Function
    * @param functionName là functionName
    */ 
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    
    /**
    * Hàm này để lấy ra functionTypeId trong bảng Function
    * @return int là functionTypeId
    */ 
    public int getFunctionTypeId() {
        return functionTypeId;
    }
    
    /**
    * Hàm này để gán giá trị cho functionTypeId trong bảng Function
    * @param functionTypeId là functionTypeId
    */ 
    public void setFunctionTypeId(int functionTypeId) {
        this.functionTypeId = functionTypeId;
    }

    /**
    * Hàm này để lấy ra boundaryClass trong bảng Function
    * @return String là boundaryClass
    */ 
    public String getBoundaryClass() {
        return boundaryClass;
    }
    
    /**
    * Hàm này để gán giá trị cho boundaryClass trong bảng Function
    * @param boundaryClass là boundaryClass
    */ 
    public void setBoundaryClass(String boundaryClass) {
        this.boundaryClass = boundaryClass;
    }
    
}
