/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.boundary;

import common.boundary.MainForm;
import common.controller.CardController;
import entity.Card;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.controller.BorrowerController;
import user.controller.UserController;

/**
 *
 * @author Raph
 */
public class ActivateAccountForm extends javax.swing.JPanel {

    /**
     * Creates new form ActivateAccountForm
     */
    public ActivateAccountForm() {
        initComponents();
        jLabelAlert.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabelAlert = new javax.swing.JLabel();
        jButtonSubmit = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("UTM Copperplate", 1, 24)); // NOI18N
        jLabel1.setText("Kích hoạt tài khoản");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Nhập mã kích hoạt thẻ:");

        jTextFieldCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodeKeyTyped(evt);
            }
        });

        jLabelAlert.setForeground(new java.awt.Color(255, 0, 51));
        jLabelAlert.setText("alert");

        jButtonSubmit.setText("Kích hoạt");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSubmit)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlert)
                            .addComponent(jTextFieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabelAlert)
                .addGap(36, 36, 36)
                .addComponent(jButtonSubmit)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(63, 63, 63)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
        String code = jTextFieldCode.getText();
        if (code.isEmpty()) {
            jLabelAlert.setText("Ô nhập trống");
            jLabelAlert.setVisible(true);
        } else {
            try {
                int cardId = CardController.getInstance().getCardIdByCode(code);
                System.out.println(cardId);
                switch (cardId) {
                    case Card.NOT_EXIST_CODE:
                        jLabelAlert.setText("Không tồn tại mã code hoặc đã bị hủy khả dụng");
                        jLabelAlert.setVisible(true);
                        break;
                    case Card.USED_CARD:
                        jLabelAlert.setText("Code đã được sử dụng");
                        jLabelAlert.setVisible(true);
                        break;
                    default:
                        String username = MainForm.getUsername();
                        BorrowerController.getInstance().addNewBorrower(username);
                        int borrowerId = BorrowerController.getInstance().getBorrowerInfor(username).getBorrowerId();
                        CardController.getInstance().updateBorrowerIdInCard(borrowerId, cardId);
                        UserController.getInstance().updateUserRole(3, username);
                        JOptionPane.showMessageDialog(this, "Kích hoạt thành công, đăng nhập lại để hoàn tất", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ActivateAccountForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jTextFieldCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodeKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jTextFieldCodeKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAlert;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldCode;
    // End of variables declaration//GEN-END:variables
}
