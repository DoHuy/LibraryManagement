/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import dao.entity.BorrowerDAO;
import entity.Borrower;
import java.sql.SQLException;

/**
 * Lớp điểu khiển các vấn đề liên quan đến người mượn
 * @author Raph
 */
public class BorrowerController {

    private BorrowerDAO b = new BorrowerDAO();

    private static BorrowerController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private BorrowerController() {
    }
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của BorrowerController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của BorrowerController
     */
    public static BorrowerController getInstance() {
        if (me == null) {
            me = new BorrowerController();
        }
        return me;
    }
    
    /**
     * 
     * @param username
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewBorrower(String username) throws ClassNotFoundException, SQLException{
        b.addNewBorrower(username);
    }
    
    /**
     * Hàm này dùng để kiểm tra username có tồn tại trong bảng Người Mượn hay không?
     * @param username : username
     * @return true nếu tồn tại, ngược lại trả về false
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkBorrower(String username) throws ClassNotFoundException, SQLException{
        return b.checkBorrower(username);
    }
    
    
    /**
     * Hàm này giúp lấy thông tin người mượn
     * @param username : username
     * @return borrower
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Borrower getBorrowerInfor(String username) throws ClassNotFoundException, SQLException{
        return b.getBorrowerInfor(username);
    }
    
    /**
     * Hàm này giúp lấy thông tin người mượn
     * @param borrwerId là mã người mượn
     * @return borrower
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Borrower getBorrowerInfor(int borrwerId) throws ClassNotFoundException, SQLException{
        return b.getBorrowerInfor(borrwerId);
    }
}
