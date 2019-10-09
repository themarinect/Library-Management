/*
 * ChangePassword.java
 *
 * Created on April 6, 2009, 9:34 PM
 */
package librarymanagementbook;

import java.util.Vector;
import javax.swing.JOptionPane;
import util.DBConnection;
import util.MD5;

/**
 *
 * @author  Nguyen Manh Truong
 */
public class ChangePassword extends javax.swing.JPanel {
    Home home;
    String username;
    DBConnection dc = new DBConnection();

    /** Creates new form ChangePassword */
    public ChangePassword(Home h) {
        home = h;
        initComponents();
        username = Home.UserLogon;
        txt_username.setText(username);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bt_cancel = new javax.swing.JButton();
        bt_Apply = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lb_username = new javax.swing.JLabel();
        lb_oldpass = new javax.swing.JLabel();
        lb_newpass = new javax.swing.JLabel();
        lb_renewpass = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        txt_oldPass = new javax.swing.JPasswordField();
        txt_newPass = new javax.swing.JPasswordField();
        txt_reNewPass = new javax.swing.JPasswordField();

        setLayout(new java.awt.GridBagLayout());

        bt_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_cancel.png"))); // NOI18N
        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        bt_Apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_ok.png"))); // NOI18N
        bt_Apply.setText("Apply");
        bt_Apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ApplyActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Login Information"));

        lb_username.setText("Username");

        lb_oldpass.setText("Old Password");

        lb_newpass.setText("New Password");

        lb_renewpass.setText("Re-Type New Password");

        txt_username.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_renewpass)
                    .addComponent(lb_newpass)
                    .addComponent(lb_oldpass)
                    .addComponent(lb_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_username)
                    .addComponent(txt_oldPass)
                    .addComponent(txt_newPass)
                    .addComponent(txt_reNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_username)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_oldpass)
                    .addComponent(txt_oldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_newpass)
                    .addComponent(txt_newPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_renewpass)
                    .addComponent(txt_reNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(132, 132, 132)
                            .addComponent(bt_Apply)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_cancel)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_Apply)
                        .addComponent(bt_cancel))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(jPanel2, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
    home.tbpanel.removeTabAt(home.tbpanel.indexOfTab("Change Password"));
}//GEN-LAST:event_bt_cancelActionPerformed

private void bt_ApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ApplyActionPerformed
    if (txt_oldPass.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "You have to type Old Passord!");
        txt_oldPass.requestFocus();
        txt_oldPass.selectAll();
        return;
    }
    if (txt_newPass.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "You have to type New Passord!");
        txt_newPass.requestFocus();
        txt_newPass.selectAll();
        return;
    }
    if (txt_reNewPass.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "You have to type Re-Passord!");
        txt_reNewPass.requestFocus();
        txt_reNewPass.selectAll();
        return;
    }
    
    String OldPass = MD5.getHashString(String.valueOf(txt_oldPass.getPassword()));
    String NewPass = MD5.getHashString(String.valueOf(txt_newPass.getPassword()));
    String reNewPass = MD5.getHashString(String.valueOf(txt_reNewPass.getPassword()));
    if(!NewPass.equals(reNewPass))
    {
        JOptionPane.showMessageDialog(null, "Re-New Passord not equals New Password!");
        txt_reNewPass.requestFocus();
        txt_reNewPass.selectAll();
        return;
    }
        String sqlCheckPass = "select Password from tblUsers where Username = '" + username + "'";
        dc.executequery(sqlCheckPass);
        String Password = ((Vector) dc.data.elementAt(0)).elementAt(0).toString();
        if (!Password.equals(OldPass)) {
            JOptionPane.showMessageDialog(null, "Old Password you enter not match!");
            txt_oldPass.requestFocus();
            txt_oldPass.selectAll();
            return;
            
        }
        String update = "update tblUsers set Password = '"+NewPass+"' where Username = '"+username+"'";
        dc.execute(update);
        JOptionPane.showMessageDialog(null, "Change password Succesfull!");
        
    
    home.tbpanel.removeTabAt(home.tbpanel.indexOfTab("Change Password"));
    
    

}//GEN-LAST:event_bt_ApplyActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Apply;
    private javax.swing.JButton bt_cancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb_newpass;
    private javax.swing.JLabel lb_oldpass;
    private javax.swing.JLabel lb_renewpass;
    private javax.swing.JLabel lb_username;
    private javax.swing.JPasswordField txt_newPass;
    private javax.swing.JPasswordField txt_oldPass;
    private javax.swing.JPasswordField txt_reNewPass;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}