/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.ReturnInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class ReturnInfoDAO {
    // query is here
    private final String ADD_RETURN_INFOR = "insert into ThongTinTra (maNguoiMuon,maBanSao,moTa,giaDenBu,thoiDiemTra) values (?,?,?,?,?)";
    
    /**
     * Hàm thêm một thông tin trả vào bảng ThongTinTra
     * @param infor
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addReturnInfo(ReturnInfo infor) throws ClassNotFoundException, SQLException {
        long minis = System.currentTimeMillis();
        Date date = new Date(minis);

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_RETURN_INFOR);
        ps.setInt(1, infor.getBorrowerId());
        ps.setString(2, infor.getCopyId());
        ps.setString(3, infor.getDescription());
        ps.setDouble(4, infor.getCompensationPrice());
        ps.setDate(5, sqlDate);

        ps.executeUpdate();
        conn.close();

    }
}
