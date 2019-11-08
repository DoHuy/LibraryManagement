/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import dao.entity.UserDAO;
import entity.User;
import java.sql.SQLException;

/**
 * Lớp điều khiển các chức năng liên quan đến người dùng
 * @author Raph
 */
public class UserController {

    private UserDAO userDAO = new UserDAO();

    private static UserController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private UserController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của UserController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của UserController
     */
    public static UserController getInstance() {
        if (me == null) {
            me = new UserController();
        }
        return me;
    }
    
     /**
     * Hàm thêm người dùng cho hệ thống
     * @param user
     * @return true nếu add thành công, ngược lại false
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    
    public boolean addUser(User user) throws SQLException, ClassNotFoundException{
        return userDAO.addUser(user);
    }

    /**
     * Hàm này để kiểm tra người dùng có thể đăng nhập vào hệ thống không
     * @param username là email người dùng
     * @param password là mật khẩu người dùng
     * @return true nếu tài khoản đúng, ngược lại false
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean checkLogin(String username,String password) throws SQLException, ClassNotFoundException{
       return userDAO.checkLogin(username, password);
    }
    
    /**
     * 
     * @param roleId
     * @param username
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateUserRole(int roleId, String username) throws ClassNotFoundException, SQLException{
        userDAO.updateUserRole(roleId, username);
    }
    
    /**
     * Hàm này dùng để lấy thông tin người dùng
     * @param username : username
     * @return User
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public User getUserInfor(String username) throws SQLException, ClassNotFoundException{
        return userDAO.getUserInfo(username);
    }
}
