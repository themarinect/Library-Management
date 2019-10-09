/*
 * Search.java
 *
 * Created on January 13, 2009, 7:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package util;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Taurus
 */
public class Search {

    /** Creates a new instance of Search */
    //DefaultTableModel model;
    String procName;
    String sqlQuery;
    DBConnection dc;
    /**
     * 
     */
    public Search() 
    {
        dc = new DBConnection();
    }

    /**This method use to get result
     *
     * @param str
     * @return Vector data result
     */
    public Vector getResult(String tblName, String str) {
        try {
            int id = Integer.parseInt(str);
            procName = tblName + "_SearchByID";
            sqlQuery = getSQLString(id);
        } catch (NumberFormatException e) {
            procName = tblName + "_SearchByName";
            sqlQuery = getSQLString(str);
        }
        dc.executequery(sqlQuery);
        return dc.data;
        
    }
    /**This method use to get SQL Query
     * @param str
     * @return String Query
     */
    public String getSQLString(String str) {
        String sql = "Exec " + procName + " '" + str + "'";
        return sql;
    }

    /**
     *
     * @param id
     * @return SQL String
     */
   // Exec + tenbang+_SearchByName+tentukhoa
    public String getSQLString(int id) {
        String sql = "Exec " + procName + " " + id;
        return sql;
    }
    /**This method use to get SQL Query
     * 
     * @param tblName - String name of table in database
     * @param str - key search by ID or by Name
     * @return String Query
     */
    public String getSQLQuery(String tblName,String str) {
        try {
            int id = Integer.parseInt(str);
            procName = tblName + "_SearchByID";
            sqlQuery = getSQLString(id);
        } catch (NumberFormatException e) {
            procName = tblName + "_SearchByName";
            sqlQuery = getSQLString(str);
        }
        return sqlQuery;
    }
    
     public String getSQLQuery1(String tblName,String str) {
        try {
            int id = Integer.parseInt(str);
            procName = tblName  ;
            sqlQuery = getSQLString(id);
        } catch (NumberFormatException e) {
            procName = tblName ;
            sqlQuery = getSQLString(str);
        }
        return sqlQuery;
    }
}
