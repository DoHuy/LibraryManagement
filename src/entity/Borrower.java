/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 * Lớp xử lý người mượn
 * @author Raph
 */
public class Borrower extends User {
    
    private int borrowerId;
    private String studentId;   // mssv
    private double deposit;     // số tiền cọc
    private String period;      // giai đoạn học

    /**
     * Lấy mã người mượn trong bảng NguoiMuon
     * @return int là mã người mượn
     */
    public int getBorrowerId() {
        return borrowerId;
    }

    /**
     * Gán giá trị mã người mượn (don't need)
     * @param borrowerId : mã người mượn
     */
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    /**
     * Lấy giá trị mã số sinh viên từ bảng NguoiMuon
     * @return String : mssv
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gán giá trị mssv
     * @param studentId : mssv 
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Lấy thông tin số tiền cọc từ bảng NguoiMuon
     * @return double : số tiền cọc
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * Gán giá trị số tiền cọc
     * @param deposit : số tiền cọc
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    /**
     * Lấy thông tin về giai đoạn học của người mượn trong trường hợp đó là sinh viên trong bảng NguoiMuon
     * @return String giai đoạn học (nên token ra để dễ dàng trong việc xử lý)
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Gán giai đoạn học
     * @param period là giai đoạn học
     */
    public void setPeriod(String period) {
        this.period = period;
    }
    
    
    
}
