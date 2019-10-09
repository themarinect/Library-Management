/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author Duc Do
 */
public class CallNumber {
    //String space = "Mot";
        //String space1 = "Bon";
        //String s1 = new String();
        //String s2 = new String();
DBCallISBN dbt = new DBCallISBN();
String cn;
public int spritCallNumber() throws SQLException, IOException, ClassNotFoundException
{
    String s = dbt.getCallNumber();
    String[] s1 = s.split("-");
    int c =Integer.parseInt(s1[2])+1;
    return c;
}


        //int index=0;
        //int length=space.length();
        //int length1 =space1.length();
        public String getCallNumber(String s1,String s2) throws SQLException, IOException, ClassNotFoundException
        {
            String s3 = new String();
            ArrayList ar = new ArrayList();
            ArrayList ar1 = new ArrayList();
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)==' ')
            {
            if(s1.charAt(i+1)!=' ')
                    //System.out.println(space.charAt(i+1));
                ar.add(s1.charAt(i+1));
                //index++;

            }
            //break;
        }

        for(int i=0;i<s2.length();i++){
            if(s2.charAt(i)==' ')
            {
            if(s2.charAt(i+1)!=' ')
                    //System.out.println(space.charAt(i+1));
                ar1.add(s2.charAt(i+1));
                //index++;

            }
            //break;
        }
        NumberFormat nf=NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(3);
        nf.setMaximumIntegerDigits(3);
//        for(int i=0;i<100;i++)
//        {
            String sb = nf.format(spritCallNumber());
//            System.out.println(sb);
//        }
        //for(int i=0;i<c.length;i++)
        if(s1.length()!=0 &s2.length()!=0)
        {
        if(ar.size()!=0 &ar1.size()!=0)
        //System.out.println(space.charAt(0));
        //s2.concat(s1);
        //if(ar.size()>1 & ar1.size()>1)
        s3=s1.charAt(0)+String.valueOf(ar.get(0))+"-"+s2.charAt(0)+String.valueOf(ar1.get(0))+"-"+sb;
        if(ar.size()==0 &ar1.size()!=0)
        s3=s1.charAt(0)+"-"+s2.charAt(0)+String.valueOf(ar1.get(0))+"-"+sb;
        if(ar.size()!=0 &ar1.size()==0)
            s3=s1.charAt(0)+String.valueOf(ar.get(0))+"-"+s2.charAt(0)+"-"+sb;
        if(ar.size()==0 &ar1.size()==0)
            s3=s1.charAt(0)+"-"+s2.charAt(0)+"-"+sb;
            //a.concat((String) ar.get(1));
        //a.concat("-");
        //a.concat((String) ar1.get(0));
        //a.concat((String) ar1.get(1));
        //System.out.println(s1);
        //System.out.println(s2);
        //s3=s1.concat(s2);
        //System.out.println(s3);
        return s3;
        }
        else
            return null;
        }


}
