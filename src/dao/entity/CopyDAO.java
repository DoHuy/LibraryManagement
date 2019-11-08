/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.Copy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class CopyDAO {
    
    private final String ADD_NEW_COPY = "insert into BanSao(maSoBanSao,soThuTu,loaiBanSao,trangThai,maSach,gia) values (?,?,?,?,?,?)";
    
    private final static String SEARCH_COPY_BY_ID = "select soThuTu,loaiBanSao,TrangThai,maSach,gia from BanSao where maSoBanSao=?";
    private final static String SEARCH_COPY_BY_BOOK_ID = "select maSoBanSao,soThuTu,loaiBanSao,trangThai,gia from BanSao where BanSao.maSach=?";
    private final static String UPDATE_COPY_STATUS = "update BanSao set trangThai=? where maSoBanSao=?";
    
    
    /**
     *
     * @param copy
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addNewCopy(Copy copy) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_NEW_COPY);
        ps.setString(1, copy.getCopyId());
        ps.setInt(2, copy.getOrderNumber());
        ps.setInt(3, copy.getType());
        ps.setInt(4, copy.getStatus());
        ps.setString(5, copy.getBookId());
        ps.setDouble(6, copy.getPrice());
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * Hàm tìm kiếm thông tin bản sao
     * @param maSoBanSao mã số bản sao
     * @return String là thông tin bản sao (đã phân cách)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Copy findCopyInforById(String maSoBanSao) throws ClassNotFoundException, SQLException{
        Copy copy = new Copy();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_COPY_BY_ID);
        ps.setString(1, maSoBanSao);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            copy.setOrderNumber(rs.getInt("soThuTu"));
            copy.setStatus(rs.getInt("trangThai"));
            copy.setType(rs.getInt("loaiBanSao"));
            copy.setBookId(rs.getString("maSach"));
            copy.setPrice(rs.getDouble("gia"));
        }
        
        conn.close();
        
        return copy;
    }
    
    /**
     * Hàm tìm kiếm các bản sao theo mã sách
     * @param maSach là mã sách
     * @return mảng các bản sao
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Copy> findCopyByBookId(String maSach) throws ClassNotFoundException, SQLException{
        ArrayList<Copy> copys = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_COPY_BY_BOOK_ID);
        ps.setString(1, maSach);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Copy copy = new Copy();
            copy.setCopyId(rs.getString("maSoBanSao"));
            copy.setOrderNumber(rs.getInt("soThuTu"));
            copy.setType(rs.getInt("loaiBanSao"));
            copy.setStatus(rs.getInt("trangThai"));
            copy.setPrice(rs.getDouble("gia"));
            copys.add(copy);
        }
        
        conn.close();
        
        return copys;
    }
    
    /**
     * Hàm cập nhật trạng thái cho bản sao mỗi khi: mượn, đăng ký, trả, mượn tại chỗ, vv
     * @param maSoBanSao mã số bản sao
     * @param trangThai trạng thái của cuốn sách: Available, Pending, Borrowed, Lent
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateCopyStatus(String maSoBanSao, int trangThai) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_COPY_STATUS);
        ps.setInt(1, trangThai);
        ps.setString(2, maSoBanSao);
        ps.executeUpdate();
        conn.close();
    }
}
