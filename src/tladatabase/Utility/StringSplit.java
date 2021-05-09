/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

/**
 *
 * @author Elorm
 */
public class StringSplit {

    public static String[] splitSpaceString(String string) {

        String parts[] = string.split(" ");

        return parts;
    }

    public static String[] splitDashString(String string) {

        String parts[] = string.split("/");

        return parts;
    }

    public static String[] splitUnderScoreString(String string) {
        
        String parts[] = string.split("_");

        return parts;
    }

}
