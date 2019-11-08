/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import dao.entity.CardDAO;
import entity.Card;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Lớp điều khiển các chức năng liên quan đến thẻ
 * @author Raph
 */
public class CardController {
    
    private CardDAO c = new CardDAO();
    
    private static CardController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    private CardController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của CardController nếu
     * đối tượng đó null
     *
     * @return đối tượng dùng chung duy nhất của CardController
     */
    public static CardController getInstance() {
        if (me == null) {
            me = new CardController();
        }
        return me;
    }
    
    /**
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Card> getListCard() throws ClassNotFoundException, SQLException{
        return c.getAllCard();
    }
    
    /**
     * 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void addNewCard() throws ClassNotFoundException, SQLException{
        c.addNewCard();
    }
    
    /**
     * Hàm này kiểm tra mã thẻ có tồn tại trong hệ thống hay không?
     * @param cardId : mã thẻ
     * @return true nếu tồn tại, ngược lại trả về false
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkCard(int cardId) throws ClassNotFoundException, SQLException{
        return c.checkCardId(cardId);
    }
    
    /**
     * Hàm lấy trạng thái của thẻ
     * @param cardId : mã thẻ
     * @return int: trạng thái của thẻ
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getCardStatus(int cardId) throws ClassNotFoundException, SQLException{
        return c.getCardStatus(cardId);
    }
    
    /**
     * Hàm lấy mã người mượn thông qua mã kích hoạt
     * @param code : mã kích hoạt
     * @return int: mã người mượn, trong trường hợp, không tồn tại mã kích hoạt, trả về -1 (not exist code); thẻ chưa kích hoạt, trả về -2 (card free)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getBorrowerIdByCode(String code) throws ClassNotFoundException, SQLException{
        return c.getBorrowerIdOverCode(code);
    }
    
    /**
     * 
     * @param code
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getCardIdByCode(String code) throws ClassNotFoundException, SQLException{
        return c.getCardIdOverCode(code);
    }
    
    /**
     * 
     * @param borrowerId
     * @param cardId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateBorrowerIdInCard(int borrowerId, int cardId) throws ClassNotFoundException, SQLException{
        c.updateBorrowerIdInCard(borrowerId, cardId);
    }
    
    /**
     * Hàm trả về mã người mượn thông qua mã thẻ
     * @param cardId : mã thẻ
     * @return int: Nếu không tồn tại (sẽ là thẻ tự do - CARD_FREE), ngược lại sẽ trả về mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getBorrowerIdById(int cardId) throws ClassNotFoundException, SQLException{
        return c.getBorrowerIDOverCardId(cardId);
    }
    
    /**
     * Hàm này thực hiện cập nhật trạng thái cho thẻ (Khi thủ thư deactivate 1 thẻ, thẻ hết hạn khi check)
     * @param status : trạng thái
     * @param cardId : mã thẻ
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void updateCardStatus(int status, int cardId) throws ClassNotFoundException, SQLException{
        c.updateCardStatus(status, cardId);
    }
    
    /**
     * Hàm này dùng để tìm kiếm mã thẻ trong csdl
     * @param username là email người dùng
     * @return mã thẻ
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int findCardId(String username) throws ClassNotFoundException, SQLException{
        return c.getCardId(username);
    }
}
