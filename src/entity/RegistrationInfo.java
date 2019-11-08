/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 * Lớp xử lý thông tin đăng ký
 * @author Raph
 */
public class RegistrationInfo {
    
    private int registerId;
    private String copyId;
    private Date registerDate;

    /**
     * Hàm lấy giá trị mã thông tin đăng ký từ bảng ThongTinDangKy
     * @return : int mã thông tin đăng ký
     */
    public int getRegisterId() {
        return registerId;
    }

    /**
     * Hàm gán giá trị cho mã thông tin đăng ký (don't need)
     * @param registerId : mã thông tin đăng ký
     */
    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    /**
     * Hàm lấy mã bản sao từ bảng ThongTinMuon
     * @return String mã bản sao (6 ký tự)
     */
    public String getCopyId() {
        return copyId;
    }

    /**
     * Hàm gán mã bản sao
     * @param copyId : mã bản sao
     */
    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    /**
     * Hàm lấy giá trị ngày đăng ký từ bảng ThongTinDangKy
     * @return date: ngày đăng ký
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * Hàm gán giá trị ngày đăng ký
     * @param registerDate : ngày đăng ký
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    
}
