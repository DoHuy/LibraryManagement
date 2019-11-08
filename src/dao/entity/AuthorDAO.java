/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class AuthorDAO {
    
    
    private final String ADD_NEW_AUTHOR = "insert into TacGia(ten) values (?)";
    private final String ADD_AUTHOR_HAS_BOOK = "insert into TacGiaCoSach(maTacGia,maSach) values (?,?)";
    private final String DEL_AUTHOR_HAS_BOOK = "delete from TacGiaCoSach where maTacGia = ? and maSach = ?";
    private final String UPDATE_AUTHOR_HAS_BOOK = "update TacGiaCoSach set maTacGia = ? where maTacGia = ? and maSach = ?";
    private final String SEARCH_AUTHOR = "select TacGia.maTacGia,ten,moTa from TacGia,TacGiaCoSach where maSach=? and TacGia.maTacGia = TacGiaCoSach.maTacGia";
    private final String CHECK_AUTHOR = "select ten from TacGia where ten = ?";
    private final String GET_ID_BY_NAME = "select maTacGia from TacGia where ten = ?";
    
    /**
     * 
     * @param name
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNew(String name) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_NEW_AUTHOR);
        ps.setString(1, name);
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * 
     * @param authorId
     * @param bookId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewAuthorHasBook(int authorId, String bookId) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_AUTHOR_HAS_BOOK);
        ps.setInt(1, authorId);
        ps.setString(2, bookId);
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * 
     * @param newAuthorId
     * @param previousAuthorId
     * @param bookId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateAuthorHasBook(int newAuthorId, int previousAuthorId, String bookId) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_AUTHOR_HAS_BOOK);
        ps.setInt(1, newAuthorId);
        ps.setInt(2, previousAuthorId);
        ps.setString(3, bookId);
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * 
     * @param authorId
     * @param bookId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void delAuthorHasBook(int authorId, String bookId) throws ClassNotFoundException, SQLException{
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(DEL_AUTHOR_HAS_BOOK);
        ps.setInt(1, authorId);
        ps.setString(2, bookId);
        ps.executeUpdate();
        conn.close();
    }
    
    /**
     * Hàm tìm kiếm tác giả
     * @param maSach: mã cuốn sách
     * @return Mảng các tác giả (1 cuốn sách có thể có nhiều tác giả)
     * @throws SQLException 
     */
    public ArrayList<Author> findAuthor(String maSach) throws SQLException, ClassNotFoundException{
        ArrayList<Author> listAuthor = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_AUTHOR);
        ps.setString(1, maSach);
        ResultSet rs = ps.executeQuery();
        while(rs != null && rs.next()){
            Author author = new Author();
            author.setAuthor_id(rs.getInt("maTacGia"));
            author.setAuthor_name(rs.getString("ten"));
            author.setDescribe(rs.getString("moTa"));
            
            listAuthor.add(author);
        }
        
        conn.close();
        return listAuthor;
    }
    
    /**
     * 
     * @param nameValue
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    public String checkAuthor(String nameValue) throws ClassNotFoundException, SQLException{
        String name = null;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(CHECK_AUTHOR);
        ps.setString(1, nameValue);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            name = rs.getString("ten");
        }
        
        conn.close();
        return name;
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getAuthorIdByName(String name) throws ClassNotFoundException, SQLException{
        int id = -1;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_ID_BY_NAME);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            id = rs.getInt("maTacGia");
        }
        
        conn.close();
        return id;
    }
}
