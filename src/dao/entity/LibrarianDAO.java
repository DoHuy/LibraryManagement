/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.Librarian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class LibrarianDAO {
    
    private final static String GET_LIBRARIAN_INFOR = "select maSoThuThu,ten,gioiTinhNu,email,lienHe,birth from TaiKhoan, ThuThu where TaiKhoan.username=? and TaiKhoan.username = ThuThu.username";
    
    /**
     * Hàm kiểm tra username có trong bang ThuThu hay không
     * @param username : username
     * @return true nếu tồn tại, ngược lại trả về false
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkLibrarian(String username) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_LIBRARIAN_INFOR);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            conn.close();
            return true;
        }
        
        conn.close();
        return false;
    }
    
    /**
     * Hàm lấy thông tin liên quan đến thủ thư
     * @param username là mã đăng nhập
     * @return String là chuỗi thông tin đã phân cách
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Librarian getLibrarianInfor(String username) throws ClassNotFoundException, SQLException{
        Librarian librarian = new Librarian();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_LIBRARIAN_INFOR);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            librarian.setLibrarianId(rs.getInt("maSoThuThu"));
            librarian.setName(rs.getString("ten"));
            librarian.setIsFemale(rs.getBoolean("gioiTinhNu"));
            librarian.setEmail(rs.getString("email"));
            librarian.setEmail(rs.getString("lienHe"));
            librarian.setBirthDay(rs.getDate("birth"));
        }
        
        conn.close();
        return librarian;
    } 
}
