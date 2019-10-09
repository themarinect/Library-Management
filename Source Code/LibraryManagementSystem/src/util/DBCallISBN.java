/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import util.*;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Vector;
import java.sql.*;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duc Do
 */
public class DBCallISBN {
    Connection conn=null;
    CallableStatement cs = null;
    //PreparedStatement prst=null;
    ResultSet rs=null;
    String cn;
    DBConnection obj1 = new DBConnection();
    public String getCallNumber() throws SQLException,IOException, ClassNotFoundException
   
    {

        try {
            conn = obj1.connect();
            cs = conn.prepareCall("{call sp_getcallnumber(?)}");
            cs.registerOutParameter(1,Types.VARCHAR);
            cs.execute();
            cn =cs.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            cs.close();
            conn.close();
        }
        return cn ;
    }
     public String getCallNumberEdit(String h) throws SQLException,IOException, ClassNotFoundException
   
    {

        try {
            conn = obj1.connect();
            cs = conn.prepareCall("{call sp_getcallnumberedit(?,?)}");
            cs.setString(1,h);
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.execute();
            cn =cs.getString(2);

        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            cs.close();
            conn.close();
        }
        return cn ;
    }
//    public void addBook(String callnumber, String isbn,String title, int copies, String author)
//    {
//        try {
//            conn = MyConnection.getConnection();
//            cs = conn.prepareCall("{call sp_insertintoBook(?,?,?,?,?)}");
//            //cs.registerOutParameter(1,Types.VARCHAR);
//            cs.setString(1, callnumber.trim());
//            cs.setString(2, isbn.trim());
//            cs.setString(3, title.trim());
//            cs.setInt(4, copies);
//            cs.setString(5, author.trim());
//            //cs.setInt(6, remain);
//
//            cs.execute();
//
//            //cn =cs.getString(1);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally
//        {
//            try {
//                cs.close();
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(DBtools.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//
//    }
    public Vector getSubject()
    {
        ResultSet rs1=null;
        Vector v = null;

        try {
            v= new Vector();
            v.add("Please Choise Subject");
            conn = obj1.connect();
            cs = conn.prepareCall("{call sp_getSubject}");
            //cs.registerOutParameter(1,Types.VARCHAR);
//            cs.setString(1, callnumber);
//            cs.setString(2, isbn);
//            cs.setString(3, title);
//            cs.setInt(4, copies);
//            cs.setInt(5, author);
//            //cs.setInt(6, remain);
//
//            cs.execute();

            //cn =cs.getString(1);
            rs1 = cs.executeQuery();
            while(rs1.next())
            {
                v.add(rs1.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                cs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBCallISBN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return v;
    }
    
    public String getsubjectid(String subjectname)
    {
        //int sid = 0;
        NumberFormat nf=NumberFormat.getInstance();
        //nf.setParseIntegerOnly(false);
        nf.setMinimumIntegerDigits(3);
        nf.setMaximumIntegerDigits(3);
//        for(int i=0;i<100;i++)
//        {
            String sb = null;
        
        try {
            //v= new Vector();
            conn = obj1.connect();
            cs = conn.prepareCall("{call sp_subjectid(?,?)}");
            cs.registerOutParameter(2,Types.INTEGER);
            cs.setString(1, subjectname);
//            cs.setString(2, isbn);
//            cs.setString(3, title);
//            cs.setInt(4, copies);
//            cs.setInt(5, author);
//            //cs.setInt(6, remain);
//
            cs.execute();

            sb =nf.format(cs.getInt(2));
            //rs1 = cs.executeQuery();
//            while(rs1.next())
//            {
//                v.add(rs1.getString(1));
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                cs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBCallISBN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb;
    }
    public String getisbn(String subid)
    {
        String sb = null;
        String isbn = null;
        NumberFormat nf=NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(4);
        nf.setMaximumIntegerDigits(4);
        try {
            
            conn = obj1.connect();
            cs = conn.prepareCall("{call sp_getisbn(?,?)}");
            
            cs.setString(1,subid);
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.execute();

            isbn =cs.getString(2);

            if (isbn!=null)
            {
    String[] s1 = isbn.split("-");
    int c =Integer.parseInt(s1[1])+1;
        
        //System.out.println(nf.format(c)+"");
//        for(int i=0;i<100;i++)
//        {
            sb = subid+"-"+nf.format(c);
            }else{
            sb=subid+"-"+nf.format(1);
            }

            //System.out.println(subid);
            //System.out.println(isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                cs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBCallISBN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb ;
    }

    public String getisbnEdit(String subid,String BookI)
    {
        String sb = null;
        String isbn = null;
        NumberFormat nf=NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(4);
        nf.setMaximumIntegerDigits(4);
        try {
            
            conn = obj1.connect();
            cs = conn.prepareCall("{call sp_getisbnedit(?,?,?)}");
            
            cs.setString(1,subid);
            cs.setString(2,BookI);
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.execute();

            isbn =cs.getString(3);

            if (isbn!=null)
            {
    String[] s1 = isbn.split("-");
    int c =Integer.parseInt(s1[1]);
        
        //System.out.println(nf.format(c)+"");
//        for(int i=0;i<100;i++)
//        {
            sb = subid+"-"+nf.format(c);
            }else{
            sb=subid+"-"+nf.format(1);
            }

            //System.out.println(subid);
            //System.out.println(isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                cs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBCallISBN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb ;
    }
    public static void main(String[] args) {
        DBCallISBN db = new DBCallISBN();
        String isbn = db.getisbn("001");
        System.out.println(isbn);
    }
}
