/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import dao.entity.BorrowedInfoDAO;
import dao.entity.RegistrationInfoDAO;
import dao.entity.ReturnInfoDAO;
import entity.BorrowedInfo;
import entity.RegistrationInfo;
import entity.ReturnInfo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Lớp điểu khiển các chức năng liên quan đến nghiệp vụ: thông tin đăng ký, thông tin mượn, thông tin trả
 * @author Raph
 */
public class TaskController {

    private RegistrationInfoDAO reg_iDAO = new RegistrationInfoDAO();
    private BorrowedInfoDAO bi_DAO = new BorrowedInfoDAO();
    private ReturnInfoDAO ret_iDAO = new ReturnInfoDAO();
    
    private static TaskController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private TaskController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của TaskController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của TaskController
     */
    public static TaskController getInstance() {
        if (me == null) {
            me = new TaskController();
        }
        return me;
    }
    
    /**
     * Hàm trả về một mảng các thông tin đăng ký thông qua mã thẻ
     * @param cardId : mã thẻ
     * @return mảng các thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<RegistrationInfo> findRegistrationInfoOverCardId(int cardId) throws ClassNotFoundException, SQLException {
        return reg_iDAO.findRegisterOverCardId(cardId);
    }
    
    /**
     * Hàm này trả về một mảng thông tin mượn thông qua mã thẻ
     * @param cardId : mã thẻ
     * @return mảng các thông tin mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<BorrowedInfo> findBorrowedInfoOverCardId(int cardId) throws ClassNotFoundException, SQLException{
        return bi_DAO.findBorrowedInfoOverCardId(cardId);
    }

    /**
     * Hàm thực hiện việc thêm một thông tin đăng ký vào CSDL
     * @param cardId : mã thẻ
     * @param copyId : mã bản sao
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addRegistrationInfor(int cardId, String copyId) throws ClassNotFoundException, SQLException {
        reg_iDAO.addInfor(cardId, copyId);
    }
    
    /**
     * Hàm thực hiện việc thêm một thông tin mượn vào CSDL
     * @param registerId : mã thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addBorrowedInfor(int registerId) throws ClassNotFoundException, SQLException{
        bi_DAO.addBorrowedInfo(registerId);
    }
    
    /**
     * Hàm thực hiện việc thêm thông tin trả vào CSDL
     * @param info
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addReturnInfor(ReturnInfo info) throws ClassNotFoundException, SQLException{
        ret_iDAO.addReturnInfo(info);
    }
    
    /**
     * Hàm này thực hiện việc xóa thông tin đăng ký (khi nào? Khi quá hạn mượn, khi trả)
     * @param registerId : mã thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void deleteRegistrationInfor(int registerId) throws ClassNotFoundException, SQLException{
        reg_iDAO.deleteBorrowedInfor(registerId);
    }
    
    /**
     * Hàm này thực hiện thao tác xóa thông tin mượn (khi nào? Khi trả)
     * @param borrowedInfoId : mã thông tin mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void deleteBorrowedInfor(int borrowedInfoId) throws ClassNotFoundException, SQLException{
        bi_DAO.delBorrowedInfo(borrowedInfoId);
    }
    
    /**
     * Hàm này trả về thông tin đăng ký (dạng chuỗi) thông qua mã thông tin đăng ký
     * @param registId : mã thông tin đăng ký
     * @return String : chuỗi thông tin đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public RegistrationInfo getRegisterInfor(int registId) throws ClassNotFoundException, SQLException{
        return reg_iDAO.getRegisterInfor(registId);
    }
    
    /**
     * Hàm này trả về thông tin mượn (dạng chuỗi) thông qua mã thông tin mượn
     * @param borrowedId : mã thông tin mượn
     * @return String : chuỗi thông tin mượn (đã phân cách)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public BorrowedInfo getBorrowedInfor(int borrowedId) throws ClassNotFoundException, SQLException{
        return bi_DAO.getBorrowedInfo(borrowedId);
    }
}
