/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import entity.Card;
import static entity.Card.ACTIVE;
import static entity.Card.CARD_FREE;
import static entity.Card.DEACTIVATE;
import static entity.Card.EXPIRED;
import static entity.Card.NOT_EXIST_CODE;
import static entity.Card.USED_CARD;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import utils.DataAccessHelper;

/**
 *
 * @author Raph
 */
public class CardDAO {

    private static final char[] CHARSET_az_09 = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    private final static String GET_ALL_CARD = "select * from The";
    private final static String ADD_NEW_CARD = "insert into The(ngayHetHan,trangThai,maKichHoat) values (?,?,?)";
    private final static String GET_CARD_STATUS_BY_ID = "select ngayHetHan,trangThai from The where maSoThe=?";  // need to.. owh,!!
    private final static String GET_BORROWER_BY_CODE = "select maNguoiMuon from The where maKichHoat=?";  // if rs is null ~ wrong code, if maNguoiMuon = null, you can take it...
    private final static String GET_CARD_ID_BY_CODE = "select maSoThe,maNguoiMuon from The where maKichHoat=? and trangThai = 1";
    private final static String GET_BORROWER_ID_BY_ID = "select maNguoiMuon from The where maSoThe=?";    // after find cardid, we can find borrowerId
    private final static String UPDATE_CARD_STATUS = "update The set trangThai=? where maSoThe=?";
    private final static String UPDATE_BORROWERID_IN_CARD = "update The set maNguoiMuon = ? where maSoThe = ?";
    private final static String GET_CARD_ID = "select maSoThe from The where maNguoiMuon in (select maNguoiMuon from NguoiMuon where username=?)";    // thẻ ở trạng thái active

    /**
     *
     * @return @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Card> getAllCard() throws ClassNotFoundException, SQLException {
        ArrayList<Card> listCard = new ArrayList<>();
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_ALL_CARD);
        ResultSet rs = ps.executeQuery();
        while (rs != null && rs.next()) {
            Card card = new Card();
            card.setCardId(rs.getInt("maSoThe"));
            card.setStatus(rs.getInt("trangThai"));
            card.setExpiredDate(rs.getDate("ngayHetHan"));
            card.setCode(rs.getString("maKichHoat"));
            card.setBorrowerId(rs.getInt("maNguoiMuon"));

            listCard.add(card);
        }

        conn.close();

        return listCard;
    }
    
    /**
     * 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewCard() throws ClassNotFoundException, SQLException{
        Date date = new Date(System.currentTimeMillis());
        Date repayDate = calRepaymentDate(date);
        
        java.sql.Date sqlRepayDate = new java.sql.Date(repayDate.getTime());
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(ADD_NEW_CARD);
        ps.setDate(1, sqlRepayDate);
        ps.setInt(2, ACTIVE);
        ps.setString(3, randomString());
        ps.executeUpdate();
        conn.close();
    }
    

    /**
     * Hàm kiểm tra thẻ có tồn tại trong hệ thống hay không
     *
     * @param cardId mã thẻ
     * @return true nếu thẻ tồn tại, ngược lại trả về false
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean checkCardId(int cardId) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_CARD_STATUS_BY_ID);
        ps.setInt(1, cardId);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            conn.close();
            return true;
        }

        conn.close();
        return false;
    }

    /**
     * Hàm lấy trạng thái của thẻ
     *
     * @param cardId mã thẻ
     * @return int là trạng thái: 1. Active, 2. Deactivate, 3. Expired
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getCardStatus(int cardId) throws ClassNotFoundException, SQLException {
        int stt = ACTIVE;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_CARD_STATUS_BY_ID);
        ps.setInt(1, cardId);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            if (rs.getInt("trangThai") == 2) {
                stt = DEACTIVATE;
            } else if (rs.getInt("trangThai") == 1 && isExpiredCard(rs.getDate("ngayHetHan"))) {  //
                updateCardStatus(EXPIRED, cardId);          // cập nhật lại
                stt = EXPIRED;
            } else if (rs.getInt("trangThai") == 3) {
                stt = EXPIRED;  // nếu đã là expired
            }
        }

        conn.close();
        return stt;
    }

    /**
     * Hàm này thực hiện cập nhật trạng thái cho thẻ (TH sử dụng: thủ thư
     * deactivate thẻ, thẻ hết hạn khi check)
     *
     * @param status : trạng thái mới
     * @param cardId : mã thẻ
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateCardStatus(int status, int cardId) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_CARD_STATUS);
        ps.setInt(1, status);
        ps.setInt(2, cardId);
        ps.executeUpdate();
        conn.close();
    }

    /**
     * Hàm lấy mã người mượn thông qua mã kích hoạt
     *
     * @param code : mã kích hoạt
     * @return int là mã người mượn, trong trường hợp, không tồn tại mã kích
     * hoạt, trả về -1 (not exist code); thẻ chưa kích hoạt, trả về -2 (card
     * free)
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getBorrowerIdOverCode(String code) throws ClassNotFoundException, SQLException {
        int id = 0;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BORROWER_BY_CODE);
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        if (rs == null) {
            id = NOT_EXIST_CODE;
        } else if (rs.next()) {
            if (rs.getObject("maNguoiMuon") == null) {
                id = CARD_FREE;
            } else {
                id = rs.getInt("maNguoiMuon");
            }
        }

        conn.close();

        return id;
    }

    /**
     *
     * @param code
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getCardIdOverCode(String code) throws ClassNotFoundException, SQLException {
        int id = NOT_EXIST_CODE;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_CARD_ID_BY_CODE);
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();

        if (rs == null) {
            id = NOT_EXIST_CODE;
        } else if (rs.next()) {
            if (rs.getObject("maNguoiMuon") == null) {
                id = rs.getInt("maSoThe");
            } else {
                id = USED_CARD;
            }
        }

        conn.close();
        return id;
    }

    /**
     *
     * @param borrowerId
     * @param cardId
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateBorrowerIdInCard(int borrowerId, int cardId) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_BORROWERID_IN_CARD);
        ps.setInt(1, borrowerId);
        ps.setInt(2, cardId);
        ps.executeUpdate();
        conn.close();
    }

    /**
     * Hàm lấy mã người mượn thông qua mã thẻ
     *
     * @param cardId mã thẻ
     * @return int: Nếu không tồn tại (sẽ là thẻ tự do), ngược lại sẽ trả về mã
     * người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getBorrowerIDOverCardId(int cardId) throws ClassNotFoundException, SQLException {
        int id = 0;
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BORROWER_ID_BY_ID);
        ps.setInt(1, cardId);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            if (rs.getObject("maNguoiMuon") == null) {
                id = CARD_FREE;
            } else {
                id = rs.getInt("maNguoiMuon");
            }
        }

        conn.close();

        return id;
    }

    /**
     * Hàm lấy mã thẻ của người mượn
     *
     * @param username là email người mượn
     * @return mã thẻ nêu tồn tại, ngược lại trả về -1 (không tồn tại)
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getCardId(String username) throws ClassNotFoundException, SQLException {
        Connection conn = DataAccessHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_CARD_ID);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {

            int id = rs.getInt("maSoThe");
            conn.close();
            return id;
        }

        conn.close();

        return -1; // không thẻ
    }

    // need to check smt :v
    /**
     * Kiểm tra xem thẻ có hết hạn
     *
     * @param dateValue ngày hết hạn trên thẻ
     * @return true nếu hết hạn, ngược lại trả về false
     */
    private boolean isExpiredCard(Date dateValue) {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        long milis = System.currentTimeMillis();
        Date currentDate = new Date(milis);

        cal1.setTime(dateValue);
        cal2.setTime(currentDate);

        return daysBetween(cal1.getTime(), cal2.getTime()) >= 0;
    }

    /**
     * Hàm tính toán số ngày giữa 2 thời điểm
     *
     * @param d1 thời điểm 1
     * @param d2 thời điểm 2
     * @return số ngày (âm hoặc không âm)
     */
    private int daysBetween(Date d1, Date d2) {
        System.out.println((int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)));
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    
    // need to check smt :v
    /**
     * Tính toán ngày hết hạn (sẽ cộng lên 180 ngày)
     * @param date ngày tạo
     * @return 
     */
    private Date calRepaymentDate(Date date) {
        Calendar cal1 = new GregorianCalendar();

        cal1.setTime(date);
        cal1.add(Calendar.DAY_OF_MONTH, 180);        // cộng lên 180 ngày

        return cal1.getTime();
    }

    /**
     * Tạo ngẫu nhiên 1 mã code
     * @return 
     */
    public String randomString() {
        Random random = new SecureRandom();
        char[] result = new char[6];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(CHARSET_az_09.length);
            result[i] = CHARSET_az_09[randomCharIndex];
        }
        return new String(result);
    }

}
