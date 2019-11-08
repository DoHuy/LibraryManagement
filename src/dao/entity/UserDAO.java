/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class UserDAO {

    private final static String ADD_USER = "insert into TaiKhoan (username,password,ten,gioiTinhNu,email,lienHe) values (?,?,?,?,?,?)";
    private final static String ADD_USER_ROLE = "insert into UserRole (username,roleId) values (?,?)";
    private final static String UPDATE_USER_ROLE = "update UserRole set roleId=? where username=?";
    private final static String GET_LOGIN = "select * from TaiKhoan where username=? and password=?";
    private final static String SEARCH_USER_BY_EMAIL = "select * from TaiKhoan where username=?";

    /**
     * Thêm mới người dùng
     * @param user
     * @return boolean cho thấy có sửa được trong cá sở dữ liệu không, nếu
     * không, do lỗi sql.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement(SEARCH_USER_BY_EMAIL);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            conn.close();
            return false;
        } else {
            ps = conn.prepareStatement(ADD_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setBoolean(4, user.isIsFemale());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getContact());
            ps.executeUpdate();
            ps = conn.prepareStatement(ADD_USER_ROLE);
            ps.setString(1, user.getUsername());
            ps.setInt(2, 4);
            ps.executeUpdate();
        }
        conn.close();
        return true;
    }

    /**
     * Hàm này kiểm tra xem username và password có khớp với csdl không
     *
     * @param username là email người dùng
     * @param password là mật khẩu người dùng
     * @return true hoặc false. Nếu true là đúng, false là sai mật khẩu
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        if (findByEmail(username)) {
            Connection conn = DataAccessHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_LOGIN);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                conn.close();
                return true; // đăng nhập thành công
            } else {
                conn.close();
                return false; // sai mật khẩu
            }
        }
        return false; // email không tồn tại
    }

    /**
     * Tìm người dùng theo username
     *
     * @param username là email người dùng
     * @return boolean nếu user tồn tại return true, ngược lại thì return false;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean findByEmail(String username) throws SQLException, ClassNotFoundException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_USER_BY_EMAIL);
        ps.setString(1, username);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            conn.close();
            return true;
        }
        conn.close();
        return false;

    }
    
    /**
     * 
     * @param roleId
     * @param username
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateUserRole(int roleId, String username) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_USER_ROLE);
        ps.setInt(1, roleId);
        ps.setString(2, username);
        ps.executeUpdate();
        conn.close();
    }

    /**
     * Hàm lấy thông tin người dùng
     *
     * @param username : username
     * @return String : chuỗi thông tin người dùng đã ngăn cách
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public User getUserInfo(String username) throws SQLException, ClassNotFoundException {
        User user = new User();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_USER_BY_EMAIL);
        ps.setString(1, username);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            if (rs.getString("ten") == null) {
                conn.close();
                return null;
            } else {
                user.setName(rs.getString("ten"));
                user.setIsFemale(rs.getBoolean("gioiTinhNu"));
                
                
                if (rs.getString("email") == null) {
                    user.setEmail("null");
                } else {
                    user.setEmail(rs.getString("email"));
                }

                if (rs.getString("lienHe") == null) {
                    user.setContact("null");
                } else {
                    user.setContact(rs.getString("lienHe"));
                }
            }

        }

        conn.close();

        return user;
    }
}
