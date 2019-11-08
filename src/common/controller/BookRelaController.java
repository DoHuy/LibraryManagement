/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import dao.entity.AuthorDAO;
import dao.entity.CategoryDAO;
import dao.entity.PublisherDAO;
import entity.Author;
import entity.Category;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Raph
 *
*/
public class BookRelaController {
    
    private AuthorDAO authorDAO = new AuthorDAO();
    private PublisherDAO publisherDAO = new PublisherDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    
    private static BookRelaController me;
    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private BookRelaController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của BookRelaController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của BookRelaController
     */
    public static BookRelaController getInstance() {
        if (me == null) {
            me = new BookRelaController();
        }
        return me;
    }
    
    /**
     * Hàm load tất cả các thể loại của sách
     * @return mảng các thể loại sách
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Category> loadCategory() throws ClassNotFoundException, SQLException{
        return categoryDAO.loadCategory();
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String getCategoryByID(int id) throws ClassNotFoundException, SQLException{
        return categoryDAO.getCategoryNameOverId(id);
    }
    
    /**
     * 
     * @param num
     * @param name
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateNum(int num, String name) throws ClassNotFoundException, SQLException{
        categoryDAO.updateNum(num, name);
    }
    
    /**
     * 
     * @param name
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewPublisher(String name) throws ClassNotFoundException, SQLException{
        publisherDAO.addNew(name);
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String getPublisherByID(int id) throws ClassNotFoundException, SQLException{
        return publisherDAO.getPublisherNameOverId(id);
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getPubliserId(String name) throws ClassNotFoundException, SQLException{
        return publisherDAO.getPublisherIdByName(name);
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String checkPublisher(String name) throws ClassNotFoundException, SQLException{
        return publisherDAO.checkPublisher(name);
    }
    
    /**
     * 
     * @param name
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewAuthor(String name) throws ClassNotFoundException, SQLException{
        authorDAO.addNew(name);
    }
    
    /**
     * 
     * @param id
     * @param bookId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewAuthorHasBook(int id, String bookId) throws ClassNotFoundException, SQLException{
        authorDAO.addNewAuthorHasBook(id, bookId);
    }
    
    /**
     * 
     * @param newId
     * @param previousId
     * @param bookId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateNewAuthorHasBook(int newId, int previousId, String bookId) throws ClassNotFoundException, SQLException{
        authorDAO.updateAuthorHasBook(newId, previousId, bookId);
    }
    
    /**
     * 
     * @param authorId
     * @param bookId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void delAuthorHasBook(int authorId, String bookId) throws ClassNotFoundException, SQLException{
        authorDAO.delAuthorHasBook(authorId, bookId);
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getAuthorId(String name) throws ClassNotFoundException, SQLException{
        return authorDAO.getAuthorIdByName(name);
    }
    
    /**
     * 
     * @param bookId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Author> getListAuthor(String bookId) throws SQLException, ClassNotFoundException{
        return authorDAO.findAuthor(bookId);
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String checkAuthor(String name) throws ClassNotFoundException, SQLException{
        return authorDAO.checkAuthor(name);
    }
}
