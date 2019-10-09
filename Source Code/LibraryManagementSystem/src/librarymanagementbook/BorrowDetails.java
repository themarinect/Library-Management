/*
 * NewPurchaseBill.java
 *
 * Created on April 9, 2009, 6:21 PM
 */
package librarymanagementbook;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DBConnection;
import util.UseMethod;

/**
 *
 * @author  Nguyen Manh Truong
 */
public class BorrowDetails extends javax.swing.JFrame {

    static BorrowManager om;
    static int O_ID;
    static Vector data;
    static Vector header;
    static DefaultTableModel d = new DefaultTableModel(data, header);
    DBConnection dc;
    Connection con;
    Statement stmt;
    JDateChooser StartDate, EndDate;
    SimpleDateFormat formatDate, formatInsertDate;

    /** Creates new form NewPurchaseBill */
    /**
     * This constructor is used to initialize a new OrderDetails form object.
     * @param o - The OrderManager object
     */
    public BorrowDetails(){}
    public BorrowDetails(BorrowManager o) {
        this.om = o;
        initComponents();
        formatDate = new SimpleDateFormat("dd-MM-yyyy");
        formatInsertDate = new SimpleDateFormat("MM-dd-yyyy");
        StartDate = new JDateChooser(null, "dd-MM-yyyy");
        pn_date.add(StartDate);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((ds.width - this.getWidth()) / 2, (ds.height - this.getHeight()) / 2);
        dc = new DBConnection();
        Vector v = new Vector();
        v = (Vector) om.d_Borrow.getDataVector().elementAt(om.tb_Book.getSelectedRow());
        O_ID = Integer.valueOf(v.elementAt(0).toString());
       //txt_punish.setText(0 + "");
        if (v.elementAt(5).toString().equals("Delivered")) {

            bt_add.setVisible(false);
            bt_remove.setVisible(false);
            bt_save.setVisible(false);
            pn_date.setVisible(false);
            jButton1.setVisible(false);
            jLabel8.setText(v.elementAt(3).toString());
            txt_punish.setText(v.elementAt(4).toString());
            jLabel3.setVisible(false);
            

        } else {

            bt_add.setVisible(false);
            bt_remove.setVisible(false);
            bt_save.setVisible(true);
        // pn_date.setVisible(false);

        }
        SetModel();
        jLabel7.setText(O_ID+"");
       
        lb_name.setText((v.elementAt(6).toString()));
        txt_date.setText(v.elementAt(1).toString());
        txt_date1.setText(v.elementAt(2).toString());
      //  txt_TotalBook.setText(String.valueOf(getTotal()));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_BooktList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_TotalBook = new javax.swing.JTextField();
        txt_date = new javax.swing.JTextField();
        lb_total = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lb_EmployeeName = new javax.swing.JLabel();
        lb_name = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();
        bt_remove = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bt_save = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_date1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pn_date = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txt_punish = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Borrow Details");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Book List"));

        tb_BooktList.setModel(d);
        tb_BooktList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_BooktList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Date Start:");

        txt_TotalBook.setEditable(false);
        txt_TotalBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TotalBookActionPerformed(evt);
            }
        });

        txt_date.setEditable(false);

        lb_total.setText("Total BookBorrow");

        jPanel2.setLayout(new java.awt.GridBagLayout());

        lb_EmployeeName.setFont(new java.awt.Font("Times New Roman", 1, 14));
        lb_EmployeeName.setText("Employee Name :");

        lb_name.setFont(new java.awt.Font("Times New Roman", 1, 14));

        bt_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_add.png"))); // NOI18N
        bt_add.setText("Add Book To List");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        bt_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editdelete.png"))); // NOI18N
        bt_remove.setText("Remove");
        bt_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_removeActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        bt_save.setIcon(new javax.swing.ImageIcon("E:\\NetBeansProjects\\LibraryManagementSystem\\src\\images\\save.png")); // NOI18N
        bt_save.setText("Save");
        bt_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_saveActionPerformed(evt);
            }
        });

        bt_cancel.setIcon(new javax.swing.ImageIcon("E:\\NetBeansProjects\\LibraryManagementSystem\\src\\images\\redo.png")); // NOI18N
        bt_cancel.setText("Back");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        jLabel2.setText("Date Due:");

        txt_date1.setEditable(false);

        jLabel3.setText("Date Real:");

        pn_date.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Punish"));

        txt_punish.setEditable(false);

        jLabel4.setText("MoneyPunish");

        jButton1.setText("Fees");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_punish, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_punish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel6.setText("Code Borow:");

        jLabel9.setText("DateReal:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(bt_save)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_total))
                        .addGap(10, 10, 10)
                        .addComponent(txt_TotalBook, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cancel)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lb_EmployeeName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_name)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(465, 465, 465)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_date, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_date1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                    .addComponent(pn_date, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addComponent(bt_add)
                                .addGap(48, 48, 48)
                                .addComponent(bt_remove)
                                .addGap(160, 160, 160))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_name)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(lb_EmployeeName))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_add)
                            .addComponent(bt_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pn_date, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_total)
                                .addComponent(txt_TotalBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_save)
                            .addComponent(bt_cancel))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**This method use to set model for table
     * 
     */
    public void SetModel() {
        con = dc.connect();
        header = new Vector();
        data = new Vector();
        header.add("ID");
        header.add("Call Number");
        header.add("ISBN");
        header.add("Title");
        header.add("Author");
        header.add("Quantity");
        header.add("BookContinue");
        header.add("BookCopy");

        String sql = "Select borrowID,bookid from BorrowDetail where borrowID = " + O_ID;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector temp = new Vector();
                int a = rs.getInt(2);
                temp.add(UseMethod.getB_Name(a).elementAt(0));
                temp.add(UseMethod.getB_Name(a).elementAt(1));
                temp.add(UseMethod.getB_Name(a).elementAt(2));
                temp.add(UseMethod.getB_Name(a).elementAt(3));
                temp.add(UseMethod.getB_Name(a).elementAt(6));
                temp.add(1);
                temp.add(UseMethod.getB_Name(a).elementAt(7));
                temp.add(UseMethod.getB_Name(a).elementAt(4));
                
                //temp.add(UseMethod.getB_Name(O_ID).elementAt(0));
//                int P_ID = rs.getInt("P_ID");
//                temp.add(UseMethod.getP_Name(P_ID));
//                temp.add(rs.getInt("Quantity"));
//                temp.add(UseMethod.getS_NameP_ID(P_ID));
//                temp.add(rs.getInt("Price"));
                data.add(temp);
            }
            dc.disconnect(rs);
            dc.disconnect(stmt);
            dc.disconnect(con);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        d = new DefaultTableModel(data, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }
        };

        tb_BooktList.setModel(d);
    }
private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
    data = new Vector();
    d = new DefaultTableModel(data, header);
    tb_BooktList.setModel(d);
    this.dispose();
}//GEN-LAST:event_bt_cancelActionPerformed
    /**This method use to add a new row to table
     * 
     * @param v - Vector contain data row will add to table
     */
    public  void AddToTable(Vector v) {
        Vector temp2 = new Vector();
        if (d.getRowCount() >= 0) {
            for (int i = 0; i < d.getRowCount(); i++) {
                Vector temp = (Vector) d.getDataVector().elementAt(i);
                String CallNumber = temp.elementAt(1).toString();
                if (CallNumber.equals(v.elementAt(1).toString())) {
                    JOptionPane.showMessageDialog(this, "This Book exists ! ");
                    return;
////                    int oldQty = Integer.parseInt(temp.elementAt(2).toString());
////                    int newQty = Integer.parseInt(v.elementAt(1).toString());
//
//                    int ID = Integer.parseInt(temp.elementAt(0).toString());
//                    //String P_Name = (String) v.elementAt(0);
//                    String isbn = v.elementAt(2).toString();
//                    String title = v.elementAt(3).toString();
//                    String author = v.elementAt(4).toString();
//                    String quanitty = temp.elementAt(5).toString();
//                    //int Price = Integer.parseInt(v.elementAt(3).toString());
//
//                    temp2.add(ID);
//                    temp2.add(CallNumber);
////                    temp2.add(oldQty + newQty);
//                    temp2.add(isbn);
//                    temp2.add(title);
//                    temp2.add(author);
//                    temp2.add(quanitty);
//                    d.removeRow(i);


                }

            }

        }
        int ID = Integer.parseInt(v.elementAt(0).toString());
        
        String isbn = v.elementAt(2).toString();
        String title = v.elementAt(3).toString();
        String author = v.elementAt(5).toString();
        //String quanitty = v.elementAt(4).toString();
        String CallNumber = v.elementAt(1).toString();
        String NumOfCon = v.elementAt(6).toString();
        String NumOfCopy = v.elementAt(4).toString();

        temp2.add(ID);
        temp2.add(CallNumber);
        temp2.add(isbn);
        temp2.add(title);
        temp2.add(author);
        temp2.add(1);
        temp2.add(NumOfCon);
        temp2.add(NumOfCopy);
        
        d.addRow(temp2);
        d.fireTableDataChanged();
    }

    /**This method use to get Total price of order
     * 
     * @return Total price of order
     */
    public static int getTotal() {
        int total = 0;
        for (int i = 0; i < tb_BooktList.getRowCount(); i++) {
            Vector temp = (Vector) d.getDataVector().elementAt(i);
            int to = Integer.valueOf(temp.elementAt(5).toString());
            total = total + to;
        }

        return total;
    }

private void txt_TotalBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TotalBookActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txt_TotalBookActionPerformed

private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed

    
//    if(txt_punish.getText().equals("")){
//    
//        JOptionPane.showMessageDialog(this, "Ban phai tinh so tien phat");
//        return;
//    }
    
    String k;
    try {
        k = formatDate.format(StartDate.getDate());
        if (UseMethod.checkDate(txt_date1.getText(), k) == false) {
            JOptionPane.showMessageDialog(null, "Real Date is erlier than Date Start");
            return;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "You have not select RealDate!");
        return;
    }
    
     
    if(txt_punish.getText().equals("")){
    
        JOptionPane.showMessageDialog(this, "Ban phai tinh so tien phat");
        return;
    }
    String [] a = new String[100];
    if (d.getRowCount() >= 0) {
            for (int i = 0; i < d.getRowCount(); i++) {
                Vector temp = (Vector) d.getDataVector().elementAt(i);
                a[i]=temp.elementAt(0).toString();
                String ga = temp.elementAt(3).toString();
                int c = Integer.parseInt(a[i]);
                System.out.println(a[i]);
                int h = Integer.parseInt(temp.elementAt(6).toString());
                int h1 = Integer.parseInt(temp.elementAt(7).toString());
                if(h>=h1){
                    JOptionPane.showMessageDialog(this, ga+"So luong sach con lai trong kho khong duoc lon hon hoac bang so copy");
                    continue;
                }               
//                if(h <= 0){
//                    JOptionPane.showMessageDialog(this, "Book In Store Not Continue! ");
//                    return;
//                }
                int b = h+1;
                String sql1 = "update book set BookContinue = "+b+"where bookID = "+c;
                dc.execute(sql1);
               //JOptionPane.showMessageDialog(this, "Ban da update NumOfContinue successful!");
                
            }
    }
    
    String delOldData = "delete from BorrowDetail where borrowid = " + O_ID;
    dc.execute(delOldData);
    if (d.getRowCount() > 0) {
        for (int i = 0; i < d.getRowCount(); i++) {
            Vector temp = (Vector) d.getDataVector().elementAt(i);
           // int book_ID = UseMethod.getP_ID(temp.elementAt(1).toString());
            int book_ID = Integer.parseInt(temp.elementAt(0).toString());
            String sql = "insert into BorrowDetail(borrowid,bookID) values(" + O_ID + "," + book_ID + ")";
            dc.execute(sql);
        }
        int z=  Integer.parseInt(txt_punish.getText());
        String g = formatInsertDate.format(StartDate.getDate());
        System.out.println(g);
        String updatefine = "update Borrow set fine = " + z + " where borrowID = " + O_ID;
        String updateRealdate = "update Borrow set realDate = '" + g + "',borrowStatus = 1 where borrowID = " + O_ID;
        dc.execute(updatefine);
        dc.execute(updateRealdate);
        JOptionPane.showMessageDialog(null, "Update Success!");
        om.SetModel();
        this.dispose();
    }else{
    
        JOptionPane.showMessageDialog(null, "You cant update empty order!");
        return;
    }
    //    for (int i = 0; i < d.getRowCount(); i++) {
//        Vector temp = (Vector) d.getDataVector().elementAt(i);
//        int P_ID = UseMethod.getP_ID(temp.elementAt(1).toString());
//        int Qty = Integer.parseInt(temp.elementAt(2).toString());
//        if (UseMethod.checkQuantity(Qty, P_ID) == false) {
//            JOptionPane.showMessageDialog(null, "Quantity of product : " + UseMethod.getP_Name(P_ID) + " in stock not enough for this order!");
//            return;
//
//        }
//    }
//
//    String delOldData = "delete from tblOrderDetails where O_ID = " + O_ID;
//    dc.execute(delOldData);
//    if (d.getRowCount() > 0) {
//        for (int i = 0; i < d.getRowCount(); i++) {
//            Vector temp = (Vector) d.getDataVector().elementAt(i);
//            int P_ID = UseMethod.getP_ID(temp.elementAt(1).toString());
//            int Qty = Integer.parseInt(temp.elementAt(2).toString());
//            int Price = Integer.parseInt(temp.elementAt(4).toString());
//            String sql = "insert into tblOrderDetails(O_ID,P_ID,Quantity,Price) values(" + O_ID + "," + P_ID + "," + Qty + "," + Price + ")";
//            dc.execute(sql);
//        }
//        String C_Name = lb_name.getText();
//        int C_ID = UseMethod.GetC_ID(C_Name);
//        String updateTotal = "update tblOrders set TotalPrice = " + getTotal() + " where O_ID = " + O_ID;
//        String updateC_Name = "update tblOrders set C_ID = " + C_ID + " where O_ID = " + O_ID;
//        dc.execute(updateTotal);
//        dc.execute(updateC_Name);
//        JOptionPane.showMessageDialog(null, "Update Success!");
//        om.SetModel();
//        this.dispose();
//    } else {
//        JOptionPane.showMessageDialog(null, "You cant update empty order!");
//        return;
//    }
}//GEN-LAST:event_bt_saveActionPerformed

private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
    ListAllBooks a = new ListAllBooks("Borrow Detail");
    a.setVisible(true);
}//GEN-LAST:event_bt_addActionPerformed

private void bt_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_removeActionPerformed
   if(tb_BooktList.getSelectedRow() >= 0){
    d.removeRow(tb_BooktList.getSelectedRow());
   // txt_TotalBook.setText(String.valueOf(getTotal()));
   }else{//GEN-LAST:event_bt_removeActionPerformed
   
       JOptionPane.showMessageDialog(this, "You choose book delete");
   }
}                                         

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    String k;
    try {
        k = formatDate.format(StartDate.getDate());
        if (UseMethod.checkDate(txt_date1.getText(), k) == false) {
            JOptionPane.showMessageDialog(null, "Real Date is erlier than Date Start");
            return;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "You have not select RealDate!");
        return;
    }
    String u = txt_date1.getText();
    String[] a = new String[3];
    String[] b = new String[3];
    a = k.split("-");
    b = u.split("-");

//    if(Integer.parseInt(a[0])-Integer.parseInt(b[0]) == 0){
//    
//        JOptionPane.showMessageDialog(this,"Ban khong bi phat");
//        return;
//    }
    int c = Integer.parseInt(b[0]);//ngay hen tra

    int p = Integer.parseInt(b[1]);//thang

    int e = Integer.parseInt(b[2]);//nam

    int f = Integer.parseInt(a[0]);//ngay thuc te tra

    int g = Integer.parseInt(a[1]);//thang

    int h = Integer.parseInt(a[2]);//nam

    if (f - c <= 5 && p == g && e == h) {

        JOptionPane.showMessageDialog(this, "Ban khong bi phat");
        txt_punish.setText(0 + "");
        return;
    }
    if (f - c > 5 && p == g && e == h) {

        int d1 = (f - c - 5) * 5;
        txt_punish.setText(d1 + "");
        JOptionPane.showMessageDialog(this, "Ban  bi phat");
        return;
    }
    if (f >= c && g > p && e == h) {

        int d2 = ((g - p) * 30 + (f - c) - 5) * 5;
        txt_punish.setText(d2 + "");
        JOptionPane.showMessageDialog(this, "Ban bi phat");
        return;
    }
    if (f <= c && g > p && e == h) {

        int d3 = ((g - p) * 30 - (c - f) - 5) * 5;
        txt_punish.setText(d3 + "");
        JOptionPane.showMessageDialog(this, "Ban bi phat");
        return;
    }

    if (f >= c && g > p && e > h) {
        int d4 = ((f - c) + (g - p) * 30 + (e - h) * 360 - 5) * 5;
        txt_punish.setText(d4 + "");
        JOptionPane.showMessageDialog(this, "Ban bi phat");
        return;
    }
    if (f <= c && g > p && e > h) {
        int d4 = ((g - p) * 30 + (e - h) * 360 - (c - f) - 5) * 5;
        txt_punish.setText(d4 + "");
        JOptionPane.showMessageDialog(this, "Ban bi phat");
        return;
    }
    if (f >= c && g < p && e > h) {
        // int d4 = ((f-c)-(p-g)*30+(e-h)*360)*5;
        int d4 = ((e - h) * 360 - (f - c) - (p - g) * 30 - 5) * 5;
        txt_punish.setText(d4 + "");
        JOptionPane.showMessageDialog(this, "Ban bi phat");
        return;
    }
    if (f <= c && g < p && e > h) {
        // int d4 = ((f-c)-(p-g)*30+(e-h)*360)*5;
        int d5 = ((e - h) * 360 + (c - f) - (p - g) * 30 - 5) * 5;
        txt_punish.setText(d5 + "");
        JOptionPane.showMessageDialog(this, "Ban bi phat");
        return;
    }



    System.out.println(k);
    System.out.println(u);






}//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new BorrowDetails(om).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_remove;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_EmployeeName;
    public javax.swing.JLabel lb_name;
    private javax.swing.JLabel lb_total;
    private javax.swing.JPanel pn_date;
    public static javax.swing.JTable tb_BooktList;
    public static javax.swing.JTextField txt_TotalBook;
    private javax.swing.JTextField txt_date;
    private javax.swing.JTextField txt_date1;
    private javax.swing.JTextField txt_punish;
    // End of variables declaration//GEN-END:variables
}
