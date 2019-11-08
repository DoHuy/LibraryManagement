/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 * Lớp xử lý thông tin trả
 * @author Raph
 */
public class ReturnInfo {
    
    private int returnInfoId;
    private int borrowerId;
    private String copyId;
    private String description;
    private double compensationPrice;  // giá đền bù
    private Date returnDate;
    
    public ReturnInfo(){}

    public ReturnInfo(int borrowerId, String copyId, String description, double compensationPrice) {
        this.borrowerId = borrowerId;
        this.copyId = copyId;
        this.description = description;
        this.compensationPrice = compensationPrice;
    }
    

    
    
    /**
     * Hàm lấy mã thông tin trả từ bảng ThongTinTra
     * @return int : mã thông tin trả
     */
    public int getReturnInfoId() {
        return returnInfoId;
    }

    /**
     * Hàm gán mã thông tin trả (don't need)
     * @param returnInfoId : mã thông tin trả
     */
    public void setReturnInfoId(int returnInfoId) {
        this.returnInfoId = returnInfoId;
    }

    /**
     * Hàm lấy thông tin mã người mượn
     * @return int : mã người mượn
     */
    public int getBorrowerId() {
        return borrowerId;
    }

    /**
     * Hàm gán giá trị mã người mượn (don't need)
     * @param borrowerId : mã người mượn
     */
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    /**
     * Hàm lấy thông tin mã bản sao
     * @return String : mã bản sao
     */
    public String getCopyId() {
        return copyId;
    }

    /**
     * Hàm gán giá trị mã bản sao
     * @param copyId : mã bản sao
     */
    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }
    
    /**
     * Hàm lấy thông tin mô tả tử bảng ThongTinTra
     * @return String : mô tả
     */
    public String getDescription() {
        return description;
    }

    /**
     * Hàm gán mô tả, ở đây là mô tả cho các thứ liên quan đến chất lượng sách trả, vv
     * @param description : mô tả
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Hàm lấy giá trị giá đền bù từ bảng ThongTinTra
     * @return double : tiền đền bù
     */
    public double getCompensationPrice() {
        return compensationPrice;
    }

    /**
     * Hàm gán giá trị cho giá đền bù
     * @param compensationPrice : giá đền bù
     */
    public void setCompensationPrice(double compensationPrice) {
        this.compensationPrice = compensationPrice;
    }

    /**
     * Hàm lấy thông tin ngày trả sách từ bảng ThongTinTra
     * @return date : ngày trả
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Hàm gán giá trị cho ngày trả
     * @param returnDate : ngày trả
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    
}
