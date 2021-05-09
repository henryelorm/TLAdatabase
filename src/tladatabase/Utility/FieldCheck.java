/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class FieldCheck {

    public static boolean nameCheck(TextField lastName, TextField firstName, TextField otherName, Label nm) {

        boolean namech = (lastName.getText().isEmpty() || firstName.getText().isEmpty()
                || otherName.getText().isEmpty());

        return namech;

    }

    public static boolean citizenCheck(TextField citi, Label c) {

        return citi.getText().isEmpty();
    }

    public static boolean homeTown(TextField homeTown, Label ht) {

        return homeTown.getText().isEmpty();
    }

    public static boolean dobCheck(ComboBox dayBox, ComboBox monthBox, ComboBox yearBox, Label dob) {

        return dayBox.getValue() == null || monthBox.getValue() == null || yearBox.getValue() == null;
    }

    public static boolean studentsClass(ComboBox studentsBox) {
        return studentsBox.getValue() == null;
    }

    public static int yearBoxCheck(ComboBox yr, Label dob) {

        RestrictTextField rtfYear = new RestrictTextField();
        rtfYear.setRestrict("[0-9]");
        rtfYear.setMaxLength(10);

        rtfYear.setText(yr.getValue().toString());

        if (rtfYear.getText().equals("")) {

            dob.setOpacity(1.0);
            return 0;

        } else {
            return 1;
        }

    }

    public static int fatherNumber(TextField field, Label fl, Label al) {

        RestrictTextField rtfNumber = new RestrictTextField();
        rtfNumber.setRestrict("[0-9]");
        rtfNumber.setMaxLength(10);
        rtfNumber.setText(field.getText());
        if (field.getText().equals("")) {
            return 1;
        } else {
            if (rtfNumber.getText().equals("") || rtfNumber.getText().length() != 10) {
                fl.setOpacity(1);
                al.setOpacity(1);
                BugLog.phoneNumberCorrect("Please input correct phone number: eg. 024******* \n with 10 numbers for each field ",
                        "Incorrect Phone Number Format", null);

                return 0;
            } else {

                return 1;
            }

        }

    }

    public static int motherNumber(TextField field, Label fl, Label al) {

        RestrictTextField rtfNumber = new RestrictTextField();
        rtfNumber.setRestrict("[0-9]");
        rtfNumber.setMaxLength(10);
        rtfNumber.setText(field.getText());
        if (field.getText().equals("")) {
            return 1;
        } else {
            if (rtfNumber.getText().equals("") || rtfNumber.getText().length() != 10) {
                fl.setOpacity(1);
                al.setOpacity(1);
                BugLog.phoneNumberCorrect("Please input correct phone number: eg. 024******* \n with 10 numbers for each field",
                        "Incorrect Number Format", null);

                return 0;
            } else {

                return 1;
            }

        }

    }

    public static int sexCheckBox(CheckBox male, CheckBox female, Label sexCheck) {

        if (male.isSelected() == true || female.isSelected() == true) {

            return 1;
        } else {
            sexCheck.setOpacity(1.0);
            return 0;
        }

    }

    public static double feesField(TextField field, Label text) {
        RestrictTextField rtfees = new RestrictTextField();
        rtfees.setRestrict("[0.00-9.00]");
        rtfees.setMaxLength(10);
        rtfees.setText(field.getText());
        if (!rtfees.getText().equals("")) {
            return Double.valueOf(rtfees.getText());
        } else {

           text.setOpacity(1.0);
            return 0;
        }

    }
    /*
     public static int simNumber(String field) {

     RestrictTextField rtfNumber = new RestrictTextField();
     rtfNumber.setRestrict("[0-9]");
     rtfNumber.setMaxLength(10);
     rtfNumber.setText(field);
     if (field.equals("")) {
     return 1;
     } else {
     if (rtfNumber.getText().equals("") || rtfNumber.getText().length() != 10) {

     BugLog.phoneNumberCorrect("Please input correct phone number: eg. 024******* \n with 10 numbers for each field",
     "Incorrect Number Format", null);

     return 0;
     } else {

     return 1;
     }

     }

     }
     */
}
