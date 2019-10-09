/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import librarymanagementbook.*;

/**
 *
 * @author HA
 */
public class DBConnection {
    Login login;
    Home home;
    private static String ODBC,username,  password;
    private static final String ConfigPath = System.getProperty("user.dir") + File.separatorChar + "ConnectionConfig.properties";
    private static PropertyResourceBundle prb;
    private   Connection con;
    private static ResultSet rs = null;
    private static ResultSetMetaData rsmd = null;
    private static Statement stmt = null;
    public static Vector header;
    public static Vector data;
    
     public static boolean loadFileConfig() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(ConfigPath);
            prb = new PropertyResourceBundle(fis);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public DBConnection(Login l)
    {
        this.login = l;
    }
       public DBConnection()
    {

    }
    public DBConnection(Home h)
    {
        this.home = h;
    }
    
    public DBConnection(Login l, Home h) {
        this.home = h;
        this.login = l;
    }
    
     public   Connection connect() {
        con = null;
        loadFileConfig();
        ODBC = prb.getString("odbc");
        username = prb.getString("username");
        password = prb.getString("password");

        try {

           // Class.forName(driver);
//            String strConnect = url + server + ":" + port + ";database = " + database;
//            con = DriverManager.getConnection(strConnect, username, password);
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  	    con = DriverManager.getConnection(ODBC,username,password);
            System.out.println("Successful Connection");
        } catch (SQLException ex) {
            if(login != null)
            {
               login.setVisible(false); 
            }
            JOptionPane.showMessageDialog(null, "Can\'t not connect to server!");
            if(home == null)
            {
                home = new Home();
            }
            
            home.setVisible(true);
            int tabCount = home.tbpanel.getTabCount();

            for (int i = 0; i < tabCount; i++) {
                if (home.tbpanel.getTitleAt(i).equals("Connection Config")) {
                    home.tbpanel.setSelectedIndex(home.tbpanel.indexOfTab("Connection Config"));
                    return con;
                }
            }

            ConnectionConfig cc = new ConnectionConfig(home);
            home.tbpanel.add("Connection Config", cc);
            home.tbpanel.setTabComponentAt(tabCount, new CloseableTabComponent(home.tbpanel, "Connection Config"));
            home.tbpanel.setSelectedIndex(home.tbpanel.indexOfTab("Connection Config"));
            ex.printStackTrace();
            return con;


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "There was an SQL error!");
            // System.exit(0);
            return con;
        }
        return con;
    }
     
     public void disconnect(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnect");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "There was an SQL error!");
                //  System.exit(0);
                return;
            }
        }
    }
     
      public void disconnect(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                System.out.println("Statement Closed!");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "There was an SQL error!");
                // System.exit(0);
                return;
            }
        }
    }
      
       public void disconnect(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("ResultSet Closed!");
            } catch (SQLException ex) {

                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "There was an SQL error!");
                // System.exit(0);
                return;
            }
        }
    }
       
        public void execute(String sql) {
        try {
            con = connect();
            stmt = con.createStatement();
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was an SQL error!");
            //System.exit(0);
            return;

        } finally {
            disconnect(stmt);
            disconnect(con);
        }

        

    }
        
       public  void executequery(String sql) {
        try {
            con = connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();
            header = new Vector();
            for (int i = 1; i <= col; i++) {
                header.add(rsmd.getColumnName(i));
            }
            data = new Vector();
            while (rs.next()) {
                Vector temp = new Vector();

                for (int i = 1; i <= col; i++) {
                    temp.add(rs.getString(i));
                }
                data.add(temp);
            }


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was an SQL error!");
            //System.exit(0);
            return;

        } finally {
            disconnect(rs);
            disconnect(stmt);
            disconnect(con);
        }

    }



}
