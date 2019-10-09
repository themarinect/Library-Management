package util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PTBCN
 */
public class ValidateInput {
//    public static boolean validateCallNumber (String callnumber)
//    {
//        return callnumber.matches("[A-Z]{2}-[A-Z]{2}-[0-9]{3}");
//    }
//    public static boolean validateISBN(String isbn)
//    {
//        return isbn.matches("[0-9]{3}-[0-9]{3}");
//    }
    public static boolean validateAuthor(String author)
    {
        return author.matches("([a-zA-Z]+([ ][a-zA-Z]*)*){1,29}");
    }
    public static boolean validateTitle(String title)
    {
        return title.matches("([a-zA-Z0-9]+([ ][a-zA-Z0-9]*)*){1,100}");
    }
    public static boolean validateCopies(String copies)
    {
        return copies.matches("\\d+");
    }
    public static boolean validateCopiesRemain(String remain)
    {
        return remain.matches("\\d+");
    }
}
