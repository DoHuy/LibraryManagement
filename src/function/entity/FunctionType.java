/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.entity;


/**
 * Class này quản lý FunctionType trong cơ sở dữ liệu
 * @author Raph, dựa trên tài liệu của cô N.T.T.Trang
 */
public class FunctionType {
    
    private int functionTypeId;
    private String functionTypeName;
    
    /**
    * Hàm này để lấy ra functionTypeId trong bảng FunctionType
    * @return int là functionTypeId
    */ 
    public int getFunctionTypeId() {
        return functionTypeId;
    }
    
    /**
    * Hàm này để gán giá trị cho functionTypeid trong bảng FunctionType
    * @param functionTypeid là functionTypeid
    */ 
    public void setFunctionTypeId(int functionTypeid) {
        this.functionTypeId = functionTypeid;
    }
    
    /**
    * Hàm này để lấy ra functionTypeName trong bảng FunctionType
    * @return String là functionTypeName
    */ 
    public String getFunctionTypeName() {
        return functionTypeName;
    }
    
    /**
    * Hàm này để gán giá trị cho functionTypeName trong bảng FunctionType
    * @param functionTypeName là functionTypeName
    */ 
    public void setFunctionTypeName(String functionTypeName) {
        this.functionTypeName = functionTypeName;
    }
    
}
