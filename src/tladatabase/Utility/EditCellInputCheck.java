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
class EditCellInputCheck {

    public static boolean setInput(String value) {

        boolean isANumber = false;

        RestrictTextField rtValue = new RestrictTextField();
        rtValue.setRestrict("[0.00-9.00]");
        rtValue.setMaxLength(10);
        rtValue.setText(value);

        if (!rtValue.getText().equals("")) {

            isANumber = true;
            return isANumber;
        }
        return isANumber;
    }
}
