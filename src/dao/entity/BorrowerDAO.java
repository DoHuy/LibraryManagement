/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.Borrower;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class BorrowerDAO {
    
    private final static String ADD_NEW_BORROWER_BY_USERNAME = "insert into NguoiMuon(username,soTienCoc) values (?,?)";
    private final static String GET_INFOR_BY_ID = "select ten,gioiTinhNu,email,lienHe,mssv,soTienCoc,giaiDoanHoc from TaiKhoan, NguoiMuon where maNguoiMuon=? and TaiKhoan.username = NguoiMuon.username";
    private final static String GET_INFOR_BY_USERNAME = "select maNguoiMuon,ten,gioiTinhNu,email,lienHe,mssv,soTienCoc,giaiDoanHoc from TaiKhoan, NguoiMuon where TaiKhoan.username=? and TaiKhoan.username = NguoiMuon.username";
    
    /**
     * 
     * @param username
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewBorrower(String username) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_NEW_BORROWER_BY_USERNAME);
        ps.setString(1, username);
        ps.setDouble(2, 0);
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * Hàm kiểm tra username có trong bảng NguoiMuon hay không
     * @param username là email người mượn
     * @return true nếu tồn tại, ngược lại trả về false
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkBorrower(String username) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_INFOR_BY_USERNAME);
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
     * Hàm lấy thông tin người mượn
     * @param username username
     * @return String chứa thông tin người mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Borrower getBorrowerInfor(String username) throws ClassNotFoundException, SQLException{
        Borrower borrower = new Borrower();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_INFOR_BY_USERNAME);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            borrower.setBorrowerId(rs.getInt("maNguoiMuon"));
            borrower.setName(rs.getString("ten"));
            borrower.setIsFemale(rs.getBoolean("gioiTinhNu"));
            borrower.setEmail(rs.getString("email"));
            borrower.setContact(rs.getString("lienHe"));
            if(rs.getObject("mssv") == null){
                borrower.setStudentId("Not found");
                borrower.setPeriod("Not found");
            } else {
                borrower.setStudentId(rs.getString("mssv"));
                borrower.setStudentId(rs.getString("giaiDoanHoc"));
            }
            
            borrower.setDeposit(rs.getDouble("soTienCoc"));
        }
        
        conn.close();
        return borrower;
    }
    
    
    /**
     * Hàm lấy thông tin người mượn
     * @param borrowerId mã người mượn
     * @return String chứa thông tin người mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Borrower getBorrowerInfor(int borrowerId) throws ClassNotFoundException, SQLException{
        Borrower borrower = new Borrower();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_INFOR_BY_ID);
        ps.setInt(1, borrowerId);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            borrower.setName(rs.getString("ten"));
            borrower.setIsFemale(rs.getBoolean("gioiTinhNu"));
            borrower.setEmail(rs.getString("email"));
            borrower.setContact(rs.getString("lienHe"));
            if(rs.getObject("mssv") == null){
                borrower.setStudentId("Not found");
                borrower.setPeriod("Not found");
            } else {
                borrower.setStudentId(rs.getString("mssv"));
                borrower.setStudentId(rs.getString("giaiDoanHoc"));
            }
            
            borrower.setDeposit(rs.getDouble("soTienCoc"));
        }
        
        conn.close();
        return borrower;
    }
    
}
