/*
 * ChangePersonalInformation.java
 *
 * Created on April 6, 2009, 9:35 PM
 */
package librarymanagementbook;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import util.DBConnection;

/**
 *
 * @author  Nguyen Manh Truong
 */
public class ChangePersonalInformation extends javax.swing.JPanel {
    Home home;
    static String username;
    DBConnection dc = new DBConnection();
    Connection con;
    Statement stmt;
    JDateChooser date;
    SimpleDateFormat dateFormat;

    /** Creates new form ChangePersonalInformation */
    /**
     * This constructor is used to initialize a new ChangePersonalInformation form object.
     * @param username - String Username
     */
    public ChangePersonalInformation(String username,Home h) {
        home = h;
        initComponents();
        con = dc.connect();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");



        ButtonGroup bg = new ButtonGroup();
        bg.add(rd_female);
        bg.add(rd_male);
        

        boolean gender;
        try {
            stmt = con.createStatement();
            Statement stmt2 = con.createStatement();

//            String sqlAdmin = "Select * from tblAdmin where UserName = '" + username + "'";
            String sqlUser = "select * from tblUsers where UserName = '" + username + "'";

            ResultSet rsUser = stmt.executeQuery(sqlUser);
//            ResultSet rsAdmin = stmt2.executeQuery(sqlAdmin);
//            if (Home.AdminLogin == true) {
//                while (rsAdmin.next()) {
//                    txt_fullname.setText(rsAdmin.getString("A_Name"));
//                    txt_email.setText(rsAdmin.getString("Email"));
//
//                }
//            }
//            if (Home.AdminLogin == false) {
                while (rsUser.next()) {
                    txt_fullname.setText(rsUser.getString("U_Name"));
                    txt_email.setText(rsUser.getString("Email"));
                    txt_phone.setText(rsUser.getString("Phone"));
                    //b.setText(rsUser.getString("BirthDate"));
                      
//                    
//                    date = new JDateChooser(rsUser.getDate("BirthDay"), "dd-MM-yyyy");
//                    pn_birthdate.add(date);
                }
//            }
            dc.disconnect(rsUser);
//            dc.disconnect(rsAdmin);
            dc.disconnect(stmt);
            dc.disconnect(stmt2);
            dc.disconnect(con);



        } catch (SQLException ex) {
            Logger.getLogger(ChangePersonalInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pn_change = new javax.swing.JPanel();
        bt_cancel = new javax.swing.JButton();
        bt_apply = new javax.swing.JButton();
        pn_user = new javax.swing.JPanel();
        lb_fullname = new javax.swing.JLabel();
        txt_fullname = new javax.swing.JTextField();
        lb_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lb_phone = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        lb_phone1 = new javax.swing.JLabel();
        rd_male = new javax.swing.JRadioButton();
        rd_female = new javax.swing.JRadioButton();

        setLayout(new java.awt.GridBagLayout());

        bt_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_cancel.png"))); // NOI18N
        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        bt_apply.setIcon(new javax.swing.ImageIcon("E:\\NetBeansProjects\\LibraryManagementSystem\\src\\images\\button_ok.png")); // NOI18N
        bt_apply.setText("Apply");
        bt_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_applyActionPerformed(evt);
            }
        });

        pn_user.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Infomation"));

        lb_fullname.setText("Full Name");

        lb_email.setText("Email");

        lb_phone.setText("Phone");

        txt_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phoneActionPerformed(evt);
            }
        });

        lb_phone1.setText("Gender");

        buttonGroup1.add(rd_male);
        rd_male.setSelected(true);
        rd_male.setText("Male");

        buttonGroup1.add(rd_female);
        rd_female.setText("Female");

        javax.swing.GroupLayout pn_userLayout = new javax.swing.GroupLayout(pn_user);
        pn_user.setLayout(pn_userLayout);
        pn_userLayout.setHorizontalGroup(
            pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_fullname)
                    .addComponent(lb_email)
                    .addComponent(lb_phone1)
                    .addComponent(lb_phone))
                .addGap(19, 19, 19)
                .addGroup(pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pn_userLayout.createSequentialGroup()
                        .addComponent(rd_male)
                        .addGap(18, 18, 18)
                        .addComponent(rd_female))
                    .addComponent(txt_fullname)
                    .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(txt_phone))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        pn_userLayout.setVerticalGroup(
            pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_userLayout.createSequentialGroup()
                .addGroup(pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_fullname)
                    .addComponent(txt_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_email)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_phone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_phone1)
                    .addComponent(rd_male)
                    .addComponent(rd_female))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_changeLayout = new javax.swing.GroupLayout(pn_change);
        pn_change.setLayout(pn_changeLayout);
        pn_changeLayout.setHorizontalGroup(
            pn_changeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_changeLayout.createSequentialGroup()
                .addGroup(pn_changeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_changeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pn_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_changeLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(bt_apply)
                        .addGap(44, 44, 44)
                        .addComponent(bt_cancel)))
                .addContainerGap())
        );
        pn_changeLayout.setVerticalGroup(
            pn_changeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_changeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(pn_changeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_apply)
                    .addComponent(bt_cancel))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        add(pn_change, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
    home.tbpanel.removeTabAt(home.tbpanel.indexOfTab("Change personal information"));
}//GEN-LAST:event_bt_cancelActionPerformed

private void bt_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_applyActionPerformed
// TODO add your handling code here:

    String FullName = txt_fullname.getText();
    String Email = txt_email.getText();

    String phone = txt_phone.getText();
    boolean gender = false;
    if (rd_male.isSelected()) {
        gender = true;
    }
    if (rd_female.isSelected()) {
        gender = false;
    }
//    if (Home.AdminLogin == true) {
//        String sqlAdmin = "UPDATE tblAdmin set A_Name = '" + FullName + "',Email = '" + Email + "' where userName = '" + Home.UserLogon + "'";
//        dc.execute(sqlAdmin);
//
//    }
//    if (Home.AdminLogin == false) {
        
        String sqlUser = "UPDATE tblUsers set U_Name = '" + FullName + "',Email = '" + Email + "',Phone = '" + phone + "',gender = '" + gender + "' where userName = '" + Home.UserLogon + "'";
        dc.execute(sqlUser);
//    }
    home.tbpanel.removeTabAt(home.tbpanel.indexOfTab("Change personal information"));
    JOptionPane.showMessageDialog(null, "Update Success!");
    
}//GEN-LAST:event_bt_applyActionPerformed

private void txt_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phoneActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txt_phoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_apply;
    private javax.swing.JButton bt_cancel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lb_email;
    private javax.swing.JLabel lb_fullname;
    private javax.swing.JLabel lb_phone;
    private javax.swing.JLabel lb_phone1;
    private javax.swing.JPanel pn_change;
    private javax.swing.JPanel pn_user;
    private javax.swing.JRadioButton rd_female;
    private javax.swing.JRadioButton rd_male;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fullname;
    private javax.swing.JTextField txt_phone;
    // End of variables declaration//GEN-END:variables
}
