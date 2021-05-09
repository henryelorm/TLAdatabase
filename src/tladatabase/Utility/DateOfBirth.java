/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.sql.Date;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Elorm
 */
public class DateOfBirth {

    public static Date date(String day, ComboBox monthBox, String year) {

        Date date;
        int i = 0;
        if (monthBox.getValue().equals("JANUARY")) {

            i = 0;
        } else if (monthBox.getValue().equals("FEBRUARY")) {
            i = 1;
        } else if (monthBox.getValue().equals("MARCH")) {
            i = 2;
        } else if (monthBox.getValue().equals("APRIL")) {
            i = 3;
        } else if (monthBox.getValue().equals("MAY")) {
            i = 4;
        } else if (monthBox.getValue().equals("JUNE")) {
            i = 5;
        } else if (monthBox.getValue().equals("JULY")) {
            i = 6;
        } else if (monthBox.getValue().equals("AUGUST")) {
            i = 7;
        } else if (monthBox.getValue().equals("SEPTEMBER")) {
            i = 8;
        } else if (monthBox.getValue().equals("OCTOBER")) {
            i = 9;
        } else if (monthBox.getValue().equals("NOVEMBER")) {
            i = 10;
        } else if (monthBox.getValue().equals("DECEMBER")) {
            i = 11;
        }

        return date = new Date((Integer.parseInt(year) - 1900), i, Integer.parseInt(day));

    }

    public static String getMonth(int month) {

        if (month == 0) {
            return "JANUARY";
        } else if (month == 1) {
            return "FEBRUARY";
        } else if (month == 2) {
            return "MARCH";
        } else if (month == 3) {
            return "APRIL";
        } else if (month == 4) {
            return "MAY";
        } else if (month == 5) {
            return "JUNE";
        } else if (month == 6) {
            return "JULY";
        } else if (month == 7) {
            return "AUGUST";
        } else if (month == 8) {
            return "SEPTEMBER";
        } else if (month == 9) {
            return "OCTOBER";
        } else if (month == 10) {
            return "NOVEMBER";
        } else if (month == 11) {
            return "DECEMBER";
        }
        return "";
    }

}
