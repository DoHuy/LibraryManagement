/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.controller;

import dao.function.*;
import function.entity.Function;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Lớp điều khiển các chức năng
 * @author Raph, dựa trên tài liệu của cô N.T.T.Trang
 */
public class FunctionController {

    private FunctionDAO functionDAO = new FunctionDAO();
    private RoleDAO roleDAO = new RoleDAO();
    
    private static FunctionController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private FunctionController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của FunctionController
     * nếu đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của FunctionController
     */
    public static FunctionController getInstance() {
        if (me == null) {
            me = new FunctionController();
        }
        return me;
    }

    /**
     * Hàm này để lấy ra list function theo roleId và query
     *
     * @param roleId là id của vai trò
     * @param query là câu query select
     * @return ArrayList<Function> là list các function
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Function> getFunctionsByRoleId(int roleId, String query) throws ClassNotFoundException, SQLException {
        return new FunctionDAO().getFunctionByRoleId(roleId, query);
    }


    /**
     * lấy Map function theo email
     *
     * @param email, newPassword email
     * @return Map<String,ArrayList<Function>> key là typeFunctionName, value là
     * list function
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public Map<String, ArrayList<Function>> getFunctionsWithType(String email) throws SQLException, ClassNotFoundException {
        String roles = roleDAO.getRoleIdByUser(email);
        return functionDAO.getFunctionsWithTypeByRoles(roles);
    }

    /**
     * Hàm này để lấy list function theo id của FunctionType
     *
     * @param functionType là id của functionTypeId trong bảng function
     * @return ArrayList<Function> là list function
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Function> getFunctions(int functionType) throws SQLException, ClassNotFoundException {
        ArrayList<Function> functions = functionDAO.getFunctionByTypeId(functionType);
        return functions;
    }

}
