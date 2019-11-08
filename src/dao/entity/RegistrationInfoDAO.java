/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.RegistrationInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class RegistrationInfoDAO {
         
    private final String GET_REGISTER_INFOR_BY_CARD_ID = "select maThongTinDangKy,maBanSao,thoiGianDangKy from ThongTinDangKy,BanSao where maTheMuon=? and BanSao.maSoBanSao = ThongTinDangKy.maBanSao and trangThai = 2";
    private final String GET_REGISTER_INFOR = "select maThongTinDangKy,maBanSao,thoiGianDangKy from ThongTinDangKy where maThongTinDangKy=?";
    private final String ADD_REGISTER_INFOR = "insert into ThongTinDangKy (maTheMuon,maBanSao,thoiGianDangKy) values (?,?,?)";
    private final String DEL_REGISTER_INFOR = "delete from ThongTinDangKy where maThongTinDangKy=?";
    
    /**
     * Lấy danh sách các thông tin đăng ký từ mã thẻ
     * @param cardId : mã thẻ
     * @return mảng các thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<RegistrationInfo> findRegisterOverCardId(int cardId) throws ClassNotFoundException, SQLException{
        ArrayList<RegistrationInfo> listRegister = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_REGISTER_INFOR_BY_CARD_ID);
        ps.setInt(1, cardId);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            RegistrationInfo ri = new RegistrationInfo();
            ri.setRegisterId(rs.getInt("maThongTinDangKy"));
            ri.setCopyId(rs.getString("maBanSao"));
            ri.setRegisterDate(rs.getDate("thoiGianDangKy"));
            
            listRegister.add(ri);
        }
        
        conn.close();
        return listRegister;
    }
    
    /**
     * Hàm lấy thông tin đăng ký
     * @param registId : mã thông tin đăng ký
     * @return String : chuỗi thông tin đăng ký (đã phân cách)
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public RegistrationInfo getRegisterInfor(int registId) throws ClassNotFoundException, SQLException{
        RegistrationInfo infor = new RegistrationInfo();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_REGISTER_INFOR);
        ps.setInt(1, registId);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            infor.setCopyId(rs.getString("maBanSao"));
            infor.setRegisterDate(rs.getDate("thoiGianDangKy"));
        }
        
        conn.close();
        return infor;
    }
    
    // get maSoBanSao, thoiGianDangKy
    
    /**
     * Hàm thêm thông tin đăng ký vào bảng ThongTinDangKy
     * @param cardId : mã thẻ
     * @param copyId : mã bản sao
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addInfor (int cardId, String copyId) throws ClassNotFoundException, SQLException{
        long minis = System.currentTimeMillis();
        Date date = new Date(minis);

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_REGISTER_INFOR);
        ps.setInt(1, cardId);
        ps.setString(2, copyId);
        ps.setDate(3, sqlDate);
        
        ps.executeUpdate();
        conn.close();
    }
    
    
    /**
     * Hàm xóa thông tin đăng ký (cần khi hủy 1 thông tin đăng ký mượn)
     * @param infoId : mã thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void deleteBorrowedInfor(int infoId) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(DEL_REGISTER_INFOR);
        ps.setInt(1, infoId);

        ps.executeUpdate();
        conn.close();
    }
}
