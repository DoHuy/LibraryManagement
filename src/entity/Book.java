/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 * Lớp này làm mọi thứ liên quan đến sách
 * @author Raph
 */
public class Book {
    
    private String bookId;
    private String nameOfBook;
    private int category_id;
    private int publisher_id;
    private String author;
    private String isbn;

    public Book(){}

    public Book(String bookId, String nameOfBook, int category_id, int publisher_id, String isbn) {
        this.bookId = bookId;
        this.nameOfBook = nameOfBook;
        this.category_id = category_id;
        this.publisher_id = publisher_id;
        this.isbn = isbn;
    }
    
    
    
    /**
     * Lấy mã sách trong bảng Sach
     * @return String là mã sách (6 ký tự)
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Gán giá trị cho mã sách
     * @param bookId là mã sách với 6 ký tự (bắt buộc)
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * Lấy tên sách trong bảng Sach
     * @return String là tên sách
     */
    public String getNameOfBook() {
        return nameOfBook;
    }

    /**
     * Gán giá trị cho sách
     * @param nameOfBook là tên cuốn sách
     */
    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    /**
     * 
     * @return 
     */
    public int getCategoryID() {
        return category_id;
    }

    /**
     *
     * @param category_id
     */
    public void setCategoryID(int category_id) {
        this.category_id = category_id;
    }

    /**
     * Lấy nhà phát hành từ bảng NhaPhatHanh
     * @return String là tên nhà hành
     */
    public int getPublisherID() {
        return publisher_id;
    }

    /**
     * Gán tên nhà phát hành
     * @param publisher_id  là tên nhà phát hành
     */
    public void setPublisherID(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    

    /**
     * Lấy chỉ số ISBN từ bảng Sach
     * @return String là chỉ số ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Gán chỉ số ISBN
     * @param isbn là ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
}