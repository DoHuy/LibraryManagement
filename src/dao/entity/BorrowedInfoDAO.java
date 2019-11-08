/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.BorrowedInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class BorrowedInfoDAO {
    
    private final String GET_BORROWED_INFOR_BY_ID = "select maThongTinDangKy,thoiDiemMuon,hanTra from ThongTinMuon where maThongTinMuon = ?";
    private final String GET_BORROWED_INFOR_BY_CARD_ID = "select maThongTinMuon,ThongTinMuon.maThongTinDangKy,thoiDiemMuon,hanTra from ThongTinMuon,ThongTinDangKy where maTheMuon=? and ThongTinMuon.maThongTinDangKy = ThongTinDangKy.maThongTinDangKy";
    private final String ADD_BORROWED_INFOR = "insert into ThongTinMuon (maThongTinDangKy,thoiDiemMuon,hanTra) values (?,?,?)";
    private final String DEL_BORROWED_INFOR = "delete from ThongTinMuon where maThongTinMuon=?";
    
    /**
     * Hàm lấy thông tin mượn thông qua mã thông tin mượn
     * @param borrowedInforId : mã thông tin mượn
     * @return String : chuỗi chứa thông tin mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public BorrowedInfo getBorrowedInfo(int borrowedInforId) throws ClassNotFoundException, SQLException{
        BorrowedInfo borrowedInfo = new BorrowedInfo();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BORROWED_INFOR_BY_ID);
        ps.setInt(1, borrowedInforId);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            borrowedInfo.setBorrowedInfoId(borrowedInforId);
            borrowedInfo.setRegistrationInfoId(rs.getInt("maThongTinDangKy"));
            borrowedInfo.setBorrowedDate(rs.getDate("thoiDiemMuon"));
            borrowedInfo.setRepaymentDate(rs.getDate("hanTra"));
        }
        
        conn.close();
        return borrowedInfo;
    }
    
    /**
     * Lấy danh sách các thông tin mượn từ mã thẻ
     * @param cardId : mã thẻ
     * @return mảng các thông tin mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<BorrowedInfo> findBorrowedInfoOverCardId(int cardId) throws ClassNotFoundException, SQLException{
        ArrayList<BorrowedInfo> listBorrowed = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BORROWED_INFOR_BY_CARD_ID);
        ps.setInt(1, cardId);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            BorrowedInfo bi = new BorrowedInfo();
            bi.setBorrowedInfoId(rs.getInt("maThongTinMuon"));
            bi.setRegistrationInfoId(rs.getInt("maThongTinDangKy"));
            bi.setBorrowedDate(rs.getDate("thoiDiemMuon"));
            bi.setRepaymentDate(rs.getDate("hanTra"));
            listBorrowed.add(bi);
        }
        
        conn.close();
        return listBorrowed;
    }
    

    /**
     * Hàm thêm một thông tin mượn vào bảng ThongTinMuon
     * @param registrationId mã thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addBorrowedInfo(int registrationId) throws ClassNotFoundException, SQLException {
        long minis = System.currentTimeMillis();
        Date date = new Date(minis);
        java.sql.Date sqlBorrowedDate = new java.sql.Date(date.getTime());        
        
        Date repayDate = calRepaymentData(date);
        
        java.sql.Date sqlRepayDate = new java.sql.Date(repayDate.getTime());
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_BORROWED_INFOR);
        ps.setInt(1, registrationId);
        ps.setDate(2, sqlBorrowedDate);
        ps.setDate(3, sqlRepayDate);

        ps.executeUpdate();
        conn.close();

    }
    
    /**
     * Hàm này thực hiện xóa một thông tin mượn thông qua mã thông tin mượn
     * @param infoId : mã thông tin mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void delBorrowedInfo(int infoId) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(DEL_BORROWED_INFOR);
        ps.setInt(1, infoId);
        ps.executeUpdate();
        
        conn.close();
    }

    // need to check smt :v
    /**
     * Tính toán hạn trả (sẽ cộng lên 14 ngày)
     * @param date ngày mượn
     * @return 
     */
    private Date calRepaymentData(Date date) {
        Calendar cal1 = new GregorianCalendar();

        cal1.setTime(date);
        cal1.add(Calendar.DAY_OF_MONTH, 14);        // cộng lên 14 ngày

        return cal1.getTime();
    }
    
    
}
