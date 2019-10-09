/*
 * ProductManagement.java
 *
 * Created on Ngày 07 tháng 4 năm 2009, 09:04
 */
package librarymanagementbook;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DBConnection;

/**
 *
 * @author  t0709k_tmtrung
 */
public class BorrowManager extends javax.swing.JPanel {

    DBConnection dc;
    Connection con;
    Statement stmt;
    JDateChooser StartDate, EndDate;
    SimpleDateFormat formatDate, formatInsertDate;
    Vector header = new Vector();
    Vector data = new Vector();
    DefaultTableModel d_Borrow = new DefaultTableModel(data, header);

    /** Creates new form ProductManagement */
    public BorrowManager() {
        initComponents();
        formatDate = new SimpleDateFormat("dd-MM-yyyy");
        formatInsertDate = new SimpleDateFormat("MM-dd-yyyy");
        StartDate = new JDateChooser(null, "dd-MM-yyyy");
        EndDate = new JDateChooser(null, "dd-MM-yyyy");
        pn_StartDate.add(StartDate);
        pn_EndDate.add(EndDate);
        dc = new DBConnection();
        SetModel();

    }

    /**This method use to set model for table
     * 
     */
    public void SetModel() {
        header = new Vector();
        data = new Vector();

        header.add("ID");
        header.add("Date Start");
        header.add("Date Due");
        header.add("Real Date");
        header.add("Money Punish");
        header.add("Status");
        header.add("Employee Name");

        String sql = "select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow";
        con = dc.connect();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector temp = new Vector();
                temp.add(rs.getString(1));
                temp.add(formatDate.format(rs.getDate(2)));
                temp.add(formatDate.format(rs.getDate(3)));
                String h = rs.getString(4);
                // temp.add(formatDate.format(rs.getDate(4)));
                if (h == null) {
                    temp.add("Borrowing");

                } else if (h != null) {
                    temp.add(h);
                }
                String j = rs.getString(5);
                if (j != null) {
                    temp.add(j);
                } else if (j == null) {
                    temp.add("Borrowing");
                }
                boolean status = rs.getBoolean(6);
                if (status == true) {
                    temp.add("Delivered");
                }
                if (status == false) {
                    temp.add("Undelivered");
                }
                String sqlCust = "select E_Name from Employee where EmployeeID = " + rs.getInt(7);
                dc.executequery(sqlCust);
                temp.add(((Vector) dc.data.elementAt(0)).elementAt(0));
                data.add(temp);
            }
            dc.disconnect(rs);
            dc.disconnect(stmt);
            dc.disconnect(con);
            d_Borrow = new DefaultTableModel(data, header) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tb_Book.setModel(d_Borrow);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**This method use to search Order
     * 
     */
    public void Search() {
        if (txt_SearchKey.getText().equals("")) {
            if (StartDate.getDate() == null) {
                String sql = "select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow";
                ReturnSalesSearch(sql);
                return;
            } else if (EndDate.getDate() == null) {
                String sql = "exec tblBorrow_SearchByStartDate '" + formatInsertDate.format(StartDate.getDate()) + "'";
                ReturnSalesSearch(sql);
            } else {
                String sql = "exec tblBorow_SearchByDate '" + formatInsertDate.format(StartDate.getDate()) + "','" + formatInsertDate.format(EndDate.getDate()) + "'";
                ReturnSalesSearch(sql);
            }

        } else {
            if (StartDate.getDate() == null && EndDate.getDate() == null) {
                String searchKey = txt_SearchKey.getText();
                String sql = "exec tblBorrow_SearchByCustomerName '" + searchKey + "'";
                ReturnSalesSearch(sql);
                return;
            }else
            if (EndDate.getDate() == null) {
                String searchKey = txt_SearchKey.getText();
                String sql = "exec tblBorrow_SearchByCustomerNameAndStartDate '" + searchKey + "','" + formatInsertDate.format(StartDate.getDate()) + "'";
                ReturnSalesSearch(sql);
            }else
             if (StartDate.getDate() == null) {
                String searchKey = txt_SearchKey.getText();
                String sql = "exec tblBorrow_SearchByCustomerNameAndEndDate '" + searchKey + "','" + formatInsertDate.format(EndDate.getDate()) + "'";
                ReturnSalesSearch(sql);
            }else

            if ((!txt_SearchKey.getText().equals("")) && (EndDate.getDate() != null)&&(StartDate.getDate() != null)) {
                String searchKey = txt_SearchKey.getText();
                String sql = "exec tblBorrow_SearchByEmployeeNameAndDate '" + searchKey + "','" + formatInsertDate.format(StartDate.getDate()) + "','" + formatInsertDate.format(EndDate.getDate()) + "'";
                ReturnSalesSearch(sql);
            }

        }
    }

    /**This method will return value of search
     * 
     * @param sql String SQL Command
     */
    public void ReturnSalesSearch(String sql) {
        data = new Vector();
        con = dc.connect();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector temp = new Vector();
                temp.add(rs.getString(1));
                temp.add(formatDate.format(rs.getDate(2)));
                temp.add(formatDate.format(rs.getDate(3)));
                String h = rs.getString(4);
                // temp.add(formatDate.format(rs.getDate(4)));
                if (h == null) {
                    temp.add("Borrowing");

                } else if (h != null) {
                    temp.add(h);
                }
                String j = rs.getString(5);
                if (j != null) {
                    temp.add(j);
                } else if (j == null) {
                    temp.add("Borrowing");
                }
                boolean status = rs.getBoolean(6);
                if (status == true) {
                    temp.add("Delivered");
                }
                if (status == false) {
                    temp.add("Undelivered");
                }
                String sqlCust = "select E_Name from Employee where EmployeeID = " + rs.getString(7);
                dc.executequery(sqlCust);
                temp.add(((Vector) dc.data.elementAt(0)).elementAt(0));
                data.add(temp);
               
            }
            d_Borrow = new DefaultTableModel(data, header) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tb_Book.setModel(d_Borrow);
            if(data.size() == 0){
            
                JOptionPane.showMessageDialog(this, "Not Search Borrow!");
            }
            dc.disconnect(rs);
            dc.disconnect(stmt);
            dc.disconnect(con);


        } catch (SQLException ex) {
            Logger.getLogger(BorrowManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**This method use to view details of order
     * 
     */
    public void Details() {
        int Row[] = tb_Book.getSelectedRows();
        if (Row.length > 1) {
            JOptionPane.showMessageDialog(null, "You can choose only one Borrow at same time!");
            return;
        }
        if (tb_Book.getSelectedRow() >= 0) {

            BorrowDetails od = new BorrowDetails(this);
            od.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please choose an borrow!");
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

        jPanel2 = new javax.swing.JPanel();
        bt_add = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Book = new javax.swing.JTable();
        lb_typeBill1 = new javax.swing.JLabel();
        txt_SearchKey = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pn_StartDate = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pn_EndDate = new javax.swing.JPanel();
        bt_Search = new javax.swing.JButton();
        bt_detail = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        bt_add.setIcon(new javax.swing.ImageIcon("E:\\NetBeansProjects\\LibraryManagementSystem\\src\\images\\edit_add.png")); // NOI18N
        bt_add.setText("CheckOut");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Borrow Management"));

        tb_Book.setModel(d_Borrow);
        tb_Book.getTableHeader().setReorderingAllowed(false);
        tb_Book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_BookMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Book);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );

        lb_typeBill1.setText("Employee Name");

        txt_SearchKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_SearchKeyKeyTyped(evt);
            }
        });

        jLabel1.setText("Date borrow");

        pn_StartDate.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setText("Date due");

        pn_EndDate.setLayout(new java.awt.GridLayout(1, 0));

        bt_Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_48.png"))); // NOI18N
        bt_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SearchActionPerformed(evt);
            }
        });

        bt_detail.setIcon(new javax.swing.ImageIcon("E:\\NetBeansProjects\\LibraryManagementSystem\\src\\images\\list_Borrow.png")); // NOI18N
        bt_detail.setText("CheckIn");
        bt_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_detailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_typeBill1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addGap(28, 28, 28)
                .addComponent(bt_detail)
                .addGap(50, 50, 50))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_detail)
                        .addComponent(bt_add))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_SearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_typeBill1)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bt_Search, 0, 0, Short.MAX_VALUE))
                                .addComponent(pn_EndDate, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(pn_StartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(451, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void bt_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SearchActionPerformed
    Search();
}//GEN-LAST:event_bt_SearchActionPerformed

private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
    AddBorrow ao = new AddBorrow(this);
    ao.setVisible(true);
}//GEN-LAST:event_bt_addActionPerformed

private void bt_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_detailActionPerformed
    Details();
}//GEN-LAST:event_bt_detailActionPerformed

private void txt_SearchKeyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SearchKeyKeyTyped
    if (evt.getKeyChar() == evt.VK_ENTER) {
        Search();
    }

}//GEN-LAST:event_txt_SearchKeyKeyTyped

private void tb_BookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_BookMouseClicked
    if (evt.getClickCount() == 2) {
        Details();
    }
}//GEN-LAST:event_tb_BookMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Search;
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_detail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_typeBill1;
    private javax.swing.JPanel pn_EndDate;
    private javax.swing.JPanel pn_StartDate;
    public javax.swing.JTable tb_Book;
    private javax.swing.JTextField txt_SearchKey;
    // End of variables declaration//GEN-END:variables
}
