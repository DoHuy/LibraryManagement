/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarian.boundary;

import common.controller.CardController;
import entity.Card;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raph
 */
public class CardManagementForm extends javax.swing.JPanel {

    /**
     * Creates new form CardManagementForm
     */
    public CardManagementForm() {
        initComponents();
        loadTable();
    }

    private void loadTable() {
        try {
            ArrayList<Card> list = CardController.getInstance().getListCard();
            Vector<Object> row;
            if (!list.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                for (Card c : list) {
                    row = new Vector<>();
                    row.add(c.getCardId());
                    row.add(c.getStatus());
                    row.add(c.getExpiredDate());
                    row.add(c.getCode());
                    if (c.getBorrowerId() == 0) {
                        row.add("null");
                    } else {
                        row.add(c.getBorrowerId());
                    }

                    model.addRow(row);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CardManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonDeactivate = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã số thẻ", "Trạng thái", "Ngày hết hạn", "Mã kích hoạt", "Mã người mượn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Danh sách thẻ");

        jLabel2.setFont(new java.awt.Font("UTM Copperplate", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý thẻ");

        jButton1.setText("Tạo mới 1 thẻ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonDeactivate.setText("Hủy kích hoạt 1 thẻ");
        jButtonDeactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeactivateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jLabel2)
                        .addGap(0, 362, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonDeactivate)
                        .addGap(139, 139, 139)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonDeactivate))
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            CardController.getInstance().addNewCard();
            JOptionPane.showMessageDialog(this, "Tạo mới 1 thẻ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CardManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDeactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeactivateActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Không thẻ mượn được chọn", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int cur_status = (int) model.getValueAt(row, 1);
            if (cur_status != 2) {
                int cardId = (int) model.getValueAt(row, 0);
                try {
                    CardController.getInstance().updateCardStatus(2, cardId);
                    model.setValueAt(2, row, 1);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(CardManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thẻ đã hủy khả dụng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButtonDeactivateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDeactivate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
