/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lớp xử lý thẻ
 * @author Raph
 */
public class Card {
    
    public final static Pattern INVALID_CARD_ID = Pattern.compile(".*\\D.*");
    
    public final static int ACTIVE = 1;        // có thể hoạt động
    public final static int DEACTIVATE = 2;      // bị hủy khả dụng
    public final static int EXPIRED = 3;        // hết hạn

    public final static int NOT_EXIST_CODE = -1;    // không tồn tại mã code tương ứng với thẻ
    public final static int CARD_FREE = -2;         // thẻ có thể dùng để cấp cho người mượn/ người dùng mới
    public final static int USED_CARD = -3;

    private int cardId;
    private Date expiredDate;
    private int status;
    private String code;
    private int borrowerId;
    
    
    /**
     * Lấy thông tin mã thẻ (có thể bị null, khi mã thẻ bị hủy khả dụng hoặc hết hạn) từ bảng The
     * @return : mã thẻ
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * Gán Giá trị mã thẻ
     * @param cardId : mã thẻ
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    /**
     * Lấy thông tin ngày hết hạn của thẻ từ bảng The
     * @return date: ngày hết hạn
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * Gán giá trị cho ngày hết hạn
     * @param expiredDate : ngày hết hạn
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * Lấy thông tin trạng thái của thẻ từ bảng The
     * @return int: 1. Active, 2. Deactive, 3. Expired
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gán trạng thái cho thẻ
     * @param status : trạng thái thẻ
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Lấy mã kích hoạt của thẻ từ bảng The
     * @return String là mã kích hoạt (6 ký tự)
     */
    public String getCode() {
        return code;
    }

    /**
     * Gán giá trị cho mã kích hoạt của thẻ (6 ký tự)
     * @param code là mã kích hoạt
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return 
     */
    public int getBorrowerId() {
        return borrowerId;
    }

    /**
     * 
     * @param borrowerId 
     */
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }
      
    
    /**
     * Hàm kiểm tra xem mã thẻ có chính xác hay không (kiểu số)
     *
     * @param cardIdValue mã thẻ nhận vào
     * @return true nếu không chính xác, ngược lại false
     */
    public static boolean validateCardId(String cardIdValue) {
        Matcher matcher = INVALID_CARD_ID.matcher(cardIdValue);
        return matcher.find();
    }

    
}
