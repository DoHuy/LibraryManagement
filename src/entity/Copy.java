/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 * Lớp xử lý bản sao
 * @author Raph
 */
public class Copy {
    
    public final static int AVAILABLE       = 1;        // Khả dụng nhé :v
    public final static int PENDING         = 2;         // Mới đăng ký, cần đến thư viện để mượn
    public final static int BORROWED        = 3;         // Đã mượn, không cho phép đăng ký mượn nữa
    public final static int LENT            = 4;         // Đây là các cuốn sách được mượn tại chỗ
    
    
    private String copyId;
    private int orderNumber;
    private int type;
    private int status;
    private String bookId;
    private double price;

    
    public Copy(){}

    public Copy(String copyId, int orderNumber, int type, int status, String bookId, double price) {
        this.copyId = copyId;
        this.orderNumber = orderNumber;
        this.type = type;
        this.status = status;
        this.bookId = bookId;
        this.price = price;
    }
    
    
    
    /**
     * Lấy giá trị bản sao từ bảng BanSao
     * @return 
     */
    public String getCopyId() {
        return copyId;
    }

    /**
     * Gán giá trị cho mã bản sao (6 ký tự, include số và chữ)
     * @param copyId : mã bản sao
     */
    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    /**
     * Hàm lấy số thứ tự bản sao trong bảng BanSao
     * @return int : số thứ tự
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Hàm gán số thứ tự bản sao
     * @param orderNumber : số thứ tự
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Hàm lấy giá trị loại bản sao từ bảng BanSao
     * @return int : loại bản sao
     */
    public int getType() {
        return type;
    }

    /**
     * Hàm gán giá trị loại bản sao
     * @param type : loại bản sao
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Hàm lấy trạng thái của bản sao từ bảng BanSao
     * @return int là trạng thái, 1. Available, 2. Pending, 3. Borrowed, 4. Lent
     */
    public int getStatus() {
        return status;
    }

    /**
     * Hàm gán giá trị cho trạng thái
     * @param status : trạng thái
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Hàm lấy giá trị mã sách tương ứng cho bản sao từ bảng BanSao
     * @return String : mã sách 
     */
    public String getBookId() {
        return bookId;
    }
    
    /**
     * Hàm gán giá trị cho mã sách (don't need)
     * @param bookId : mã sách
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * Hàm lấy thông tin giá bán sao từ bảng BanSao
     * @return double : giá
     */
    public double getPrice() {
        return price;
    }

    /**
     * Hàm gán giá trị bản sao
     * @param price : giá
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
