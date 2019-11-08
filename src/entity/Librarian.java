/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 * Lớp xử lý liên quan đến thủ thư
 * @author Raph
 */
public class Librarian extends User {
    
    private int librarianId;
    private Date birthDay;

    /**
     * Hàm lấy mã thủ thư từ bảng ThuThu
     * @return int : mã thủ thư
     */
    public int getLibrarianId() {
        return librarianId;
    }

    /**
     * Hàm gán giá trị cho mã thủ thư
     * @param librarianId : mã thủ thư
     */
    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    /**
     * Hàm lấy giá trị ngày sinh của thủ thư từ bảng ThuThu
     * @return date : ngày sinh
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * Hàm gán giá trị ngày sinh cho thủ thư
     * @param birthDay : ngày sinh
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    
    
}
