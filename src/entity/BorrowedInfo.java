/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Lớp thông tin mượn
 * @author Raph
 */
public class BorrowedInfo {


    private int borrowedInfoId;
    private int registrationInfoId;
    private Date borrowedDate;      // ngày đến mượn :v
    private Date repaymentDate;     // hạn trả :v

    /**
     * Lấy mã thông tin mượn từ bảng ThongTinMuon
     * @return int là mã thông tin mượn
     */
    public int getBorrowedInfoId() {
        return borrowedInfoId;
    }

    /**
     * Gán mã thông tin mượn (Có vẻ như không cần thiết khi nó đã tự tăng)
     * @param borrowedInfoId là mã thông tin mượn
     */
    public void setBorrowedInfoId(int borrowedInfoId) {
        this.borrowedInfoId = borrowedInfoId;
    }

    /**
     * Lấy thông tin đăng ký từ bảng ThongTinMuon
     * @return int là mã thông tin đăng ký
     */
    public int getRegistrationInfoId() {
        return registrationInfoId;
    }

    /**
     * Don't need this function
     * @param registrationInfoId 
     */
    public void setRegistrationInfoId(int registrationInfoId) {
        this.registrationInfoId = registrationInfoId;
    }

    /**
     * Lấy thông tin về ngày mượn từ bảng ThongTinMuon. Chỉ là ngày tháng năm mượn (Không phải hms)
     * @return date là ngày mượn
     */
    public Date getBorrowedDate() {
        return borrowedDate;
    }

    /**
     * Gán ngày mượn
     * @param borrowedDate là ngày mượn
     */
    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    /**
     * Lấy thông tin hạn trả từ bảng ThongTinMuon
     * @return date là hạn trả
     */
    public Date getRepaymentDate() {
        return repaymentDate;
    }

    /**
     * Gán giá trị cho hạn trả
     * @param repaymentDate hạn trả
     */
    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

}
