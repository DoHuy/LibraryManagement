/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrower.boundary;

import common.boundary.MainForm;
import common.controller.BookController;
import common.controller.BookRelaController;
import common.controller.CardController;
import common.controller.TaskController;
import entity.Book;
import entity.BorrowedInfo;
import entity.Card;
import entity.Copy;
import entity.RegistrationInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Lớp bao cho danh sách mượn, đăng ký
 *
 * @author Raph
 */
public class ListOfBorrowedCopyForm extends javax.swing.JPanel {

    private int cardId;

    /**
     * Creates new form ListOfBorrowedCopyForm
     */
    public ListOfBorrowedCopyForm() {
        initComponents();
        jLabelAlert.setVisible(false);
        loading();
    }

    /**
     * Loading table
     */
    private void loading() {
        String username = MainForm.getUsername();
        try {
            cardId = CardController.getInstance().findCardId(username);

            if (cardId == -1) { // nó nghĩa là không thẻ mượn khả dụng
                // do nothing
            } else {
                int cardStatus = CardController.getInstance().getCardStatus(cardId);
                if (cardStatus == Card.EXPIRED) {
                    jLabelAlert.setText("Thẻ hết hạn");
                    jLabelAlert.setVisible(true);
                } else if (cardStatus == Card.DEACTIVATE) {
                    jLabelAlert.setText("Thẻ bị hủy khả dụng");
                    jLabelAlert.setVisible(true);
                }

                configBorrowedTable(cardId);
                configRegistrationTable(cardId);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListOfBorrowedCopyForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hàm này đổ dữ liệu vào bảng mượn
     */
    private void configBorrowedTable(int cardId) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowedInfo> listBorrowedInfo = new ArrayList<>();
        Vector<Object> row;

        listBorrowedInfo = TaskController.getInstance().findBorrowedInfoOverCardId(cardId);
        if (!listBorrowedInfo.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (BorrowedInfo b : listBorrowedInfo) {
                row = new Vector<>();
                int registId = b.getRegistrationInfoId();
                Date borrowedDate = b.getBorrowedDate();    // gotcha
                Date repaymentDate = b.getRepaymentDate();  // gotcha

                RegistrationInfo registInfo = TaskController.getInstance().getRegisterInfor(registId);
                String copyId = registInfo.getCopyId();          // gotcha

                Copy copy = BookController.getInstance().searchCopyInforById(copyId);
                int orderNumber = copy.getOrderNumber();
                String copyStatus = translateCopyStatus(copy.getStatus());
                String bookId = copy.getBookId();
                double price = copy.getPrice();

                Book book = BookController.getInstance().getBookInfor(bookId);
                String bookName = book.getNameOfBook();
                int categoryID = book.getCategoryID();
                String category = BookRelaController.getInstance().getCategoryByID(categoryID);
                int publisherID = book.getPublisherID();
                String publisher = BookRelaController.getInstance().getPublisherByID(publisherID);
                String author = book.getAuthor();

                row.add(bookId);
                row.add(copyId);
                row.add(orderNumber);
                row.add(bookName);
                row.add(category);
                row.add(author);
                row.add(publisher);
                row.add(copyStatus);
                row.add(borrowedDate);
                row.add(repaymentDate);
                row.add(price);

                model.addRow(row);

            }
        }
    }

    /**
     * Hàm này đổ dữ liệu vào bảng đăng ký
     */
    private void configRegistrationTable(int cardId) throws ClassNotFoundException, SQLException {

        listRegistId.clear();
        map.clear();

        ArrayList<RegistrationInfo> listRegistration = new ArrayList<>();
        Vector<Object> row;
        listRegistration = TaskController.getInstance().findRegistrationInfoOverCardId(cardId);

        if (!listRegistration.isEmpty()) {

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            for (RegistrationInfo r : listRegistration) {
                int registId = r.getRegisterId();
                String copyId = r.getCopyId();
                Date registerDate = r.getRegisterDate();
                
                // khi người mượn vào xem danh sách mượn, hệ thống sẽ kiểm tra danh sách đăng ký, nếu quá hạn đến mượn 2 ngày -> del thông tin mượn và update trạng thái sách
                if (isExpiredCopy(registerDate)) {
                    TaskController.getInstance().deleteRegistrationInfor(registId);
                    BookController.getInstance().updateCopyStatus(copyId, Copy.AVAILABLE);
                    continue;
                }

                row = new Vector<>();
                listRegistId.add(registId);
                map.put(registId, copyId);

               Copy copy = BookController.getInstance().searchCopyInforById(copyId);
                int orderNumber = copy.getOrderNumber();
                String copyStatus = translateCopyStatus(copy.getStatus());
                String bookId = copy.getBookId();
                double price = copy.getPrice();

                Book book = BookController.getInstance().getBookInfor(bookId);
                String bookName = book.getNameOfBook();
                int categoryID = book.getCategoryID();
                String category = BookRelaController.getInstance().getCategoryByID(categoryID);
                int publisherID = book.getPublisherID();
                String publisher = BookRelaController.getInstance().getPublisherByID(publisherID);
                String author = book.getAuthor();

                row.add(bookId);
                row.add(copyId);
                row.add(orderNumber);
                row.add(bookName);
                row.add(category);
                row.add(author);
                row.add(publisher);
                row.add(copyStatus);
                row.add(registerDate);
                row.add(price);
                row.add(false);

                model.addRow(row);
            }
        }
    }

    /**
     * Hàm chuyển đổi trạng thái (trong csdl là kiểu int) sang kiểu chuỗi
     *
     * @param status : trạng thái
     * @return String : chuỗi
     */
    private String translateCopyStatus(int status) {
        String s = null;
        switch (status) {
            case Copy.AVAILABLE:
                s = "Available";
                break;
            case Copy.PENDING:
                s = "Pending";
                break;
            case Copy.BORROWED:
                s = "Borrowed";
                break;
            case Copy.LENT:
                s = "Lent";
                break;
        }

        return s;
    }

    /**
     * Hàm kiểm tra bản copy đã quá hạn mượn hay chưa
     *
     * @param dateValue : ngày đăng ký
     * @return true nếu quá hạn (2 ngày), ngược lại trả về false (0, 1, 2 ngày
     * :v)
     */
    private boolean isExpiredCopy(Date dateValue) {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        long milis = System.currentTimeMillis();
        Date currentDate = new Date(milis);

        cal1.setTime(dateValue);
        cal2.setTime(currentDate);

        return daysBetween(cal1.getTime(), cal2.getTime()) > 2;
    }

    /**
     * Hàm tính toán số ngày giữa 2 thời điểm
     *
     * @param d1 : thời điểm 1
     * @param d2 : thời điểm 2
     * @return
     */
    private int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCacelRegister = new javax.swing.JButton();
        jLabelAlert = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Mã số bản sao", "STT", "Tiêu đề", "Thể loại", "Tác giả", "NPH", "Trạng thái", "TG mượn", "Hạn trả", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã số sách", "Mã số bản sao", "STT", "Tiêu đề", "Thể loại", "Tác giả", "NPH", "Trạng thái", "Thời gian ĐK", "Giá", "Hủy đăng ký"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setText("Danh sách mượn");

        jLabel2.setText("Danh sách đăng ký");

        btnCacelRegister.setText("Hủy đăng ký");
        btnCacelRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCacelRegisterActionPerformed(evt);
            }
        });

        jLabelAlert.setForeground(new java.awt.Color(255, 0, 51));
        jLabelAlert.setText("jLabel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 396, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCacelRegister)
                                .addGap(84, 84, 84))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(403, 403, 403))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(400, 400, 400)
                                .addComponent(jLabel1)
                                .addGap(0, 400, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelAlert)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabelAlert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCacelRegister)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCacelRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCacelRegisterActionPerformed
        // TODO add your handling code here:
        delelte();
    }//GEN-LAST:event_btnCacelRegisterActionPerformed

    private void delelte() {
        listRowToDel.clear();
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            Boolean checkedBox = Boolean.valueOf(jTable2.getValueAt(i, 10).toString());

            if (checkedBox) {
                listRowToDel.add(i);
            }
        }

        if (listRowToDel.size() > 0) {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy đăng ký " + listRowToDel.size() + " cuốn sách", "Cảnh báo", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

                listRowToDel.stream().forEach((i) -> {
                    try {
                        TaskController.getInstance().deleteRegistrationInfor(listRegistId.get(i));
                        BookController.getInstance().updateCopyStatus(map.get(listRegistId.get(i)), Copy.AVAILABLE);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ListOfBorrowedCopyForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                model.setRowCount(0);
                try {
                    configRegistrationTable(cardId);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ListOfBorrowedCopyForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không sách nào được chọn", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }

    }

    private final HashMap<Integer, String> map = new HashMap<>();
    private final ArrayList<Integer> listRegistId = new ArrayList<>();
    private final ArrayList<Integer> listRowToDel = new ArrayList<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCacelRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAlert;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
