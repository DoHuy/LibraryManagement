/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import common.controller.BookController;
import common.controller.BookRelaController;
import entity.Author;
import entity.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class BookDAO {

    private final String ADD_NEW_BOOK = "insert into Sach(maSach,tieuDe,maTheLoai,maNhaPhatHanh,isbn) values (?,?,?,?,?)";
    private final String UPDATE = "update Sach set tieuDe=?,maTheLoai=?,maNhaPhatHanh=?,isbn=? where maSach = ?";
    private final String LOAD_BOOK = "select top 15 maSach,tieuDe,Sach.maTheLoai,Sach.maNhaPhatHanh,isbn from Sach,NhaPhatHanh,TheLoai where Sach.maNhaPhatHanh = NhaPhatHanh.maNhaPhatHanh and Sach.maTheLoai = TheLoai.maTheLoai order by tieuDe asc";

    private final String SEARCH_BOOK_BY_NAME = "select maSach,tieuDe,Sach.maTheLoai,Sach.maNhaPhatHanh,isbn from Sach,NhaPhatHanh,TheLoai where tieuDe like ? and Sach.maNhaPhatHanh = NhaPhatHanh.maNhaPhatHanh and Sach.maTheLoai = TheLoai.maTheLoai order by tieuDe asc";
    private final String SEARCH_BOOK_BY_CATEGORY = "select maSach,tieuDe,Sach.maTheLoai,Sach.maNhaPhatHanh,isbn from Sach,NhaPhatHanh,TheLoai where tenTheLoai like ? and Sach.maNhaPhatHanh = NhaPhatHanh.maNhaPhatHanh and Sach.maTheLoai = TheLoai.maTheLoai order by tenTheLoai asc";
    private final String SEARCH_BOOK_BY_AUTHOR = "select maSach,tieuDe,Sach.maTheLoai,Sach.maNhaPhatHanh,isbn from Sach,NhaPhatHanh,TheLoai where Sach.maSach in (select maSach from TacGia, TacGiaCoSach where TacGia.maTacGia = TacGiaCoSach.maTacGia and TacGia.ten like ?) and Sach.maNhaPhatHanh = NhaPhatHanh.maNhaPhatHanh and Sach.maTheLoai = TheLoai.maTheLoai order by tieuDe asc";
    private final String SEARCH_BOOK_BY_PUBLISHER = "select maSach,tieuDe,Sach.maTheLoai,Sach.maNhaPhatHanh,isbn from Sach,NhaPhatHanh,TheLoai where Sach.maNhaPhatHanh = NhaPhatHanh.maNhaPhatHanh and Sach.maTheLoai = TheLoai.maTheLoai and NhaPhatHanh.ten like ? order by ten asc";

    private final String SEARCH_BOOK_BY_ID = "select maSach,tieuDe,Sach.maTheLoai,Sach.maNhaPhatHanh,isbn from Sach,NhaPhatHanh,TheLoai where maSach like ? and Sach.maNhaPhatHanh = NhaPhatHanh.maNhaPhatHanh and Sach.maTheLoai = TheLoai.maTheLoai order by tieuDe asc";
    private final String CHECK_BOOK = "select tieuDe from Sach where tieuDe = ?";

    /**
     *
     * @param book
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addNewBook(Book book) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_NEW_BOOK);
        ps.setString(1, book.getBookId());
        ps.setString(2, book.getNameOfBook());
        ps.setInt(3, book.getCategoryID());
        ps.setInt(4, book.getPublisherID());
        ps.setString(5, book.getIsbn());
        ps.executeUpdate();
        conn.close();
    }

    /**
     *
     * @param nameValue
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public String checkBook(String nameValue) throws ClassNotFoundException, SQLException {
        String name = null;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(CHECK_BOOK);
        ps.setString(1, nameValue);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            name = rs.getString("tieuDe");
        }
        conn.close();
        return name;
    }

    /**
     * Hàm này thực hiện load tất cả các cuốn sách có trong csdl
     *
     * @return : mảng các cuốn sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Book> loadBook() throws ClassNotFoundException, SQLException {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(LOAD_BOOK);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            Book book = new Book();
            book.setBookId(rs.getString("maSach"));
            book.setNameOfBook(rs.getString("tieuDe"));
            book.setCategoryID(rs.getInt("maTheLoai"));
            book.setAuthor(genAuthor(book.getBookId()));
            book.setPublisherID(rs.getInt("maNhaPhatHanh"));
            book.setIsbn(rs.getString("isbn"));
            books.add(book);
        }

        conn.close();
        return books;
    }
    
    /**
     * 
     * @param book
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateBook(Book book) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE);
        ps.setString(1, book.getNameOfBook());
        ps.setInt(2, book.getCategoryID());
        ps.setInt(3, book.getPublisherID());
        ps.setString(4, book.getIsbn());
        ps.setString(5, book.getBookId());
        ps.executeUpdate();
        conn.close();
    }

    /**
     * Hàm này tìm kiếm các cuốn sách theo từng cách khác nhau: Tên sách, Mã
     * sách, Tên nhà phát hành, Tác giả
     *
     * @param key ở đây có thể là: Tên sách, Mã sách, Tên nhà phát hành, Tác giả
     * @param type là loại tìm kiếm.
     * @return mảng các sách thỏa mãn
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Book> findBook(String key, int type) throws SQLException, ClassNotFoundException {
        String query = null;
        switch (type) {
            case BookController.SEARCH_BY_BOOK_NAME: {
                query = SEARCH_BOOK_BY_NAME;
                break;
            }

            case BookController.SEARCH_BY_CATEGORY: {
                query = SEARCH_BOOK_BY_CATEGORY;
                break;
            }

            case BookController.SEARCH_BY_AUTHOR: {
                query = SEARCH_BOOK_BY_AUTHOR;
                break;
            }

            case BookController.SEARCH_BY_PUBLISHER: {
                query = SEARCH_BOOK_BY_PUBLISHER;
                break;
            }
        }

        ArrayList<Book> books = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, "%" + key + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            Book book = new Book();
            book.setBookId(rs.getString("maSach"));
            book.setNameOfBook(rs.getString("tieuDe"));
            book.setCategoryID(rs.getInt("maTheLoai"));
            book.setPublisherID(rs.getInt("maNhaPhatHanh"));
            book.setAuthor(genAuthor(book.getBookId()));
            book.setIsbn(rs.getString("isbn"));
            books.add(book);
        }

        conn.close();
        return books;
    }

    /**
     * Hàm này lấy thông tin của một cuốn sách
     *
     * @param bookId là mã cuốn sách
     * @return String là thông tin cuốn sách (đã phân cách)
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Book getBookInforById(String bookId) throws ClassNotFoundException, SQLException {
        Book book = new Book();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_BOOK_BY_ID);
        ps.setString(1, bookId);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            book.setBookId(bookId);
            book.setNameOfBook(rs.getString("tieuDe"));
            book.setCategoryID(rs.getInt("maTheLoai"));
            book.setPublisherID(rs.getInt("maNhaPhatHanh"));
            book.setAuthor(genAuthor(bookId));
            book.setIsbn(rs.getString("isbn"));
        }

        conn.close();
        return book;
    }

    /**
     * Hàm này chỉnh mảng tác giả thành một chuỗi các tác giả duy nhất
     *
     * @param listAuthor là mảng các tác giả
     * @return String một chuỗi tác giả
     */
    private String genAuthor(String bookId) {
        ArrayList<Author> listAuthor = new ArrayList<>();
        try {
            listAuthor = BookRelaController.getInstance().getListAuthor(bookId);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        String list = "";
        for (Author a : listAuthor) {
            list += a.getAuthor_name() + ", ";
        }

        return list.substring(0, list.length() - 2);
    }

}
