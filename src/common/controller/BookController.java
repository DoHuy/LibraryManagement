/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import dao.entity.BookDAO;
import dao.entity.CopyDAO;
import entity.Book;
import entity.Copy;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Lớp điều khiển các chức năng, nghiệp vụ liên quan đến Sách, Bản sao
 * @author Raph
 */
public class BookController {

    // Các kiểu tìm kiếm khả dụng
    public final static int SEARCH_BY_BOOK_NAME       = 1;
    public final static int SEARCH_BY_CATEGORY        = 2;
    public final static int SEARCH_BY_AUTHOR          = 3;
    public final static int SEARCH_BY_PUBLISHER       = 4;
    
    private BookDAO bookDAO = new BookDAO();
    private CopyDAO copyDAO = new CopyDAO();

    private static BookController me;
    
    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private BookController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của BookController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của BookController
     */
    public static BookController getInstance() {
        if (me == null) {
            me = new BookController();
        }
        return me;
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String checkBook(String name) throws ClassNotFoundException, SQLException{
        return bookDAO.checkBook(name);
    }
    
    /**
     * 
     * @param book
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewBook(Book book) throws ClassNotFoundException, SQLException{
        bookDAO.addNewBook(book);
    }
    
    /**
     * 
     * @param book
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateBook(Book book) throws ClassNotFoundException, SQLException{
        bookDAO.updateBook(book);
    }
    
    /**
     * 
     * @param copy
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewCopy(Copy copy) throws ClassNotFoundException, SQLException{
        copyDAO.addNewCopy(copy);
    }
    
    /**
     * Hàm này thực hiện việc load tất cả các cuốn sách có trong csdl
     * @return mảng các cuốn sách
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Book> loadBook() throws ClassNotFoundException, SQLException{
        return bookDAO.loadBook();
    }
    
    /**
     * Hàm tìm kiếm một mảng các sách theo từng tiêu chí: theo tên sách, mã sách, tên tác giả, tên nhà phát hành
     * @param key : ở đây có thể là: Tên sách, Mã sách, Tên nhà phát hành, Tác giả
     * @param type : loại tìm kiếm
     * @return mảng các sách thỏa mãn
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Book> searchBook(String key, int type) throws SQLException, ClassNotFoundException{
        return bookDAO.findBook(key, type);
    }
    
    /**
     * Hàm lấy thông tin liên quan đến sách
     * @param bookId mã sách
     * @return chuỗi chứa thông tin sách khi tìm theo mã sách (đã phân cách)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Book getBookInfor(String bookId) throws ClassNotFoundException, SQLException{
        return bookDAO.getBookInforById(bookId);
    }
    
    /**
     * Hàm lấy thông tin bản sao
     * @param copyId mã bản sao
     * @return thông tin bản sao khi tìm với mã bản sao (đã phân cách)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Copy searchCopyInforById(String copyId) throws ClassNotFoundException, SQLException{
        return copyDAO.findCopyInforById(copyId);
    }
    
    /**
     * Hàm tìm kiếm bản sao theo mã sách
     * @param bookId mã Sách
     * @return mảng các bản sao tìm kiếm được theo mã sách
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Copy> searchCopyByBookId(String bookId) throws ClassNotFoundException, SQLException{
        return copyDAO.findCopyByBookId(bookId);
    }
    
    /**
     * Hàm cập nhật trạng thái cho bản sao mỗi khi đăng ký mượn, mượn, trả, mượn tại chỗ
     * @param keyId mã bản sao
     * @param type loại trạng thái (available, pending, borrowed, lent)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateCopyStatus(String keyId, int type) throws ClassNotFoundException, SQLException{
        copyDAO.updateCopyStatus(keyId, type);
    }
}
