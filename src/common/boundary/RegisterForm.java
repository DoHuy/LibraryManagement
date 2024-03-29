/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.boundary;

import entity.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.controller.UserController;

/**
 * Lớp bao đăng ký
 *
 * @author Raph
 */
public class RegisterForm extends javax.swing.JDialog {

    /**
     * Creates new form RegisterForm
     */
    public RegisterForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Đăng ký");
        jLabelAlert.setVisible(false);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButtonFemale = new javax.swing.JRadioButton();
        jRadioButtonMale = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonSubmit = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jTextFieldUsername = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldContact = new javax.swing.JTextField();
        jPasswordFieldPW = new javax.swing.JPasswordField();
        jPasswordFieldConfirmPW = new javax.swing.JPasswordField();
        jLabelAlert = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        jLabel1.setText("Username");

        jLabel2.setText("Mật khẩu");

        jLabel3.setText("Xác nhận mật khẩu");

        jLabel4.setText("Tên");

        jLabel5.setText("Giới tính");

        buttonGroup1.add(jRadioButtonFemale);
        jRadioButtonFemale.setSelected(true);
        jRadioButtonFemale.setText("Nữ");

        buttonGroup1.add(jRadioButtonMale);
        jRadioButtonMale.setText("Nam");

        jLabel6.setText("Email");

        jLabel7.setText("Liên hệ");

        jButtonSubmit.setText("OK");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Hủy");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jTextFieldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldUsernameKeyTyped(evt);
            }
        });

        jTextFieldName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNameKeyTyped(evt);
            }
        });

        jTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEmailKeyTyped(evt);
            }
        });

        jTextFieldContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldContactKeyTyped(evt);
            }
        });

        jPasswordFieldPW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordFieldPWKeyTyped(evt);
            }
        });

        jPasswordFieldConfirmPW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordFieldConfirmPWKeyTyped(evt);
            }
        });

        jLabelAlert.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAlert.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlert)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonFemale)
                                .addGap(56, 56, 56)
                                .addComponent(jRadioButtonMale))
                            .addComponent(jTextFieldUsername)
                            .addComponent(jTextFieldName)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldContact)
                            .addComponent(jPasswordFieldPW)
                            .addComponent(jPasswordFieldConfirmPW, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordFieldPW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordFieldConfirmPW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jRadioButtonFemale)
                    .addComponent(jRadioButtonMale))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabelAlert)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonSubmit))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        try {
            addUser();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jTextFieldUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUsernameKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jTextFieldUsernameKeyTyped

    private void jPasswordFieldPWKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldPWKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jPasswordFieldPWKeyTyped

    private void jPasswordFieldConfirmPWKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldConfirmPWKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jPasswordFieldConfirmPWKeyTyped

    private void jTextFieldEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jTextFieldEmailKeyTyped

    private void jTextFieldNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNameKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jTextFieldNameKeyTyped

    private void jTextFieldContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContactKeyTyped
        // TODO add your handling code here:
        jLabelAlert.setVisible(false);
    }//GEN-LAST:event_jTextFieldContactKeyTyped

    /**
     * Hàm này thực hiện chức năng đăng ký khi ấn vào nút register
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean checkRegister() throws SQLException, ClassNotFoundException {
        String password = new String(jPasswordFieldPW.getPassword());
        String confirmPass = new String(jPasswordFieldConfirmPW.getPassword());
        String username = jTextFieldUsername.getText();
        String name = jTextFieldName.getText();

        if (username.isEmpty()) {
            jLabelAlert.setText("Username trống");
            jLabelAlert.setVisible(true);
            return false;
        } else if (password.isEmpty()) {
            jLabelAlert.setText("Mật khẩu trống");
            jLabelAlert.setVisible(true);
            return false;
        } else if (confirmPass.isEmpty()) {
            jLabelAlert.setText("Xác nhận mật khẩu trống");
            jLabelAlert.setVisible(true);
            return false;
        } else if (name.isEmpty()) {
            jLabelAlert.setText("Tên trống");
            jLabelAlert.setVisible(true);
            return false;
        } else if (!password.equals(confirmPass)) {
            jLabelAlert.setText("Mật khẩu không trùng khớp");
            jLabelAlert.setVisible(true);
            return false;
        } else {
            return true;
        }

    }

    private void addUser() throws SQLException, ClassNotFoundException {
        if (checkRegister()) {
            String password = new String(jPasswordFieldPW.getPassword());
            String username = jTextFieldUsername.getText().trim();
            String name = jTextFieldName.getText().trim();
            boolean isFemale = jRadioButtonFemale.isSelected();
            String email = jTextFieldEmail.getText().isEmpty() ? null : jTextFieldEmail.getText().trim();
            String contact = jTextFieldContact.getText().isEmpty() ? null : jTextFieldContact.getText().trim();
            if (!UserController.getInstance().addUser(new User(username, password, name, isFemale, email, contact))) {
                jLabelAlert.setText("Tài khoản tồn tại");
                jLabelAlert.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Đăng ký thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelAlert;
    private javax.swing.JPasswordField jPasswordFieldConfirmPW;
    private javax.swing.JPasswordField jPasswordFieldPW;
    private javax.swing.JRadioButton jRadioButtonFemale;
    private javax.swing.JRadioButton jRadioButtonMale;
    private javax.swing.JTextField jTextFieldContact;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}
