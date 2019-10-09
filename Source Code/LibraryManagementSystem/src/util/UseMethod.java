/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import librarymanagementbook.BorrowDetails;

/**
 *
 * @author HA
 */
public class UseMethod {
    
     static DBConnection dc = new DBConnection();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static Date date1,  date2;

    /**This method is use to get Product ID
     * @param P_Name - The Product name
     * @return an integer is Product ID
     */
    public static int getB_ID(String B_Name) {
        int ID = 0;
        String sql = "Select bookID from Book where CallNumber = '" + B_Name + "'";
        dc.executequery(sql);

        ID = Integer.parseInt(((Vector) dc.data.elementAt(0)).elementAt(0).toString());
        return ID;
    }

    /**This method is return Customer ID
     * @param C_Name - The Customer name
     * @return an integer is Customer ID
     */
    public static int GetE_ID(String E_Name) {
        int E_ID = 0;
        String sql = "select EmployeeID from Employee where e_Name = '" + E_Name + "'";
        dc.executequery(sql);
        E_ID = Integer.parseInt(((Vector) dc.data.elementAt(0)).elementAt(0).toString());
        return E_ID;
    }

    /**This method use to get Product Name
     * 
     * @param P_ID Product ID
     * @return Product Name
     */
    public static Vector getB_Name(int B_ID) {
        Vector BVector = new Vector();
        String sql = "Select * from Book where bookID = " + B_ID;
        dc.executequery(sql);
       // BVector = ((Vector) dc.data.elementAt(0)).elementAt(0).toString();
        BVector = (Vector) dc.data.elementAt(0);
        return BVector;

    }

    /**This method is use to get Supplier ID
     * @param S_Name - The Supplier name
     * @return an integer is Supplier ID
     */
    public static int getS_ID(String S_Name) {
        int ID = 0;
        String sql = "Select S_ID from tblSupplier where S_Name = '" + S_Name + "'";
        dc.executequery(sql);
        ID = Integer.parseInt(((Vector) dc.data.elementAt(0)).elementAt(0).toString());
        return ID;
    }

    /**This method is use to get Supplier Name
     * @param S_ID - The Supplier ID
     * @return a String is Supplier Name
     */
    public static String getS_Name(int S_ID) {
        String S_Name = "";
        String sql = "Select S_Name from tblSupplier where S_ID = '" + S_ID + "'";
        System.out.println(sql);
        dc.executequery(sql);
        S_Name = ((Vector) dc.data.elementAt(0)).elementAt(0).toString();
        return S_Name;
    }

    /**This method is use to get Supplier Name
     * @param P_ID - The Supplier ID
     * @return a String is Supplier Name
     */
    public static String getS_NameP_ID(int P_ID) {
        String S_Name = "";
        String sql = "select S_Name from tblSupplier s join tblProducts p on s.S_ID = p.S_ID where P_ID = " + P_ID;
        dc.executequery(sql);
        S_Name = ((Vector) dc.data.elementAt(0)).elementAt(0).toString();
        return S_Name;
    }

    /**This method is use to get Supplier Type ID
     * @param ST_Name - The Supplier Type name
     * @return an integer is Supplier Type ID
     */
    public static int getST_ID(String ST_Name) {
        int ST_ID = 0;
        String sql = "select ST_ID from tblSupplierType where ST_Name = '" + ST_Name + "'";
        dc.executequery(sql);
        ST_ID = Integer.parseInt((((Vector) (dc.data.elementAt(0))).elementAt(0)).toString());
        return ST_ID;
    }

    /**This method use to get Supplier Type Name
     * 
     * @param ST_ID - Supplier Type ID
     * @return String Supplier Type Name
     */
    public static String getST_Name(int ST_ID) {
        String ST_Name = null;
        String sql = "Select ST_Name from tblSupplierType where ST_ID = " + ST_ID;
        dc.executequery(sql);

        ST_Name = ((Vector) dc.data.elementAt(0)).elementAt(0).toString();
        return ST_Name;
    }

    /**This method use to get dicount of Customer
     * 
     * @param C_ID - Int Customer ID
     * @return Discount of customer
     */
    public static int getDiscount(int C_ID) {
        int discout = 0;
        String sql = "Select Discount from tblCustomers where C_ID = " + C_ID;
        dc.executequery(sql);

        discout = Integer.parseInt(((Vector) dc.data.elementAt(0)).elementAt(0).toString());
        return discout;
    }

    /**This method return the quantity avalainle in stock
     * 
     * @param P_ID - Product ID
     * @return Integer - Quantity avalaible in stock
     */
    public static int getQuantityInStock(int P_ID) {
        int quantity = 0;
        String sql = "select Quantity from tblProducts where P_ID = " + P_ID;
        dc.executequery(sql);
        quantity = Integer.parseInt(((Vector) dc.data.elementAt(0)).elementAt(0).toString());
        return quantity;
    }

    /**This method will check the quanity in stock
     * @param quantity - The quanitity customer want to buy
     * @param P_ID - The Product ID
     * @return boolean values if true the quantity in stock avalaible for bill,if false the quanity in stock not avalaible for this bill
     */
    public static boolean checkQuantity(int quantity, int P_ID) {
        int quantityAvalaible = getQuantityInStock(P_ID);
        if (quantity <= quantityAvalaible) {
            return true;
        } else {
            return false;
        }

    }

    /**This method will check two date
     * @param dateOne the first date
     * @param dateTwo the second date
     * @return a boolean if value of boolean = true: the first date before second date
     */
    public static boolean checkDate(String dateOne, String dateTwo) {
        try {
            date1 = dateFormat.parse(dateOne);
            date2 = dateFormat.parse(dateTwo);
            if (date2.before(date1)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(BorrowDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**This method use to check valid for a email address
     * 
     * @param email - String email want to valid
     * @return boolean - if True : The email is valid,if False the email is invalid
     */
    public static boolean checkEmail(String email) {

        Pattern p = Pattern.compile("[_a-zA-Z]*[_0-9]*@[a-zA-Z]*.[a-zA-Z]*");

        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound == true) {
            System.out.println("Valid Email ID");
            return true;
        } else {
            System.out.println("InValid Email ID");
            return false;
        }

    }
     


}
