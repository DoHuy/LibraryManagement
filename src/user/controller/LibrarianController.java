/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import dao.entity.LibrarianDAO;
import entity.Librarian;
import java.sql.SQLException;

/**
 * Hàm điều khiển các vấn đề liên quan đến thủ thư
 * @author Raph
 */
public class LibrarianController {
    
    private LibrarianDAO l = new LibrarianDAO();

    private static LibrarianController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private LibrarianController() {
    }
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của LibrarianController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của LibrarianController
     */
    public static LibrarianController getInstance() {
        if (me == null) {
            me = new LibrarianController();
        }
        return me;
    }
    
    /**
     * Hàm này dùng để kiểm tra xem username có tồn tại trong bảng ThuTHu hay không
     * @param username : username
     * @return true nếu tồn tại, false ngược lại
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkLibrarian(String username) throws ClassNotFoundException, SQLException{
        return l.checkLibrarian(username);
    }
    
    /**
     * Hàm này dùng để lấy thông tin của thủ thư
     * @param username : username
     * @return String : chuỗi thông tin của thủ thư đã phân cách
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Librarian getLibrarianInfor(String username) throws ClassNotFoundException, SQLException{
        return l.getLibrarianInfor(username);
    }
}
