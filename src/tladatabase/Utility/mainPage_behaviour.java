/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;

/**
 *
 * @author Elorm
 */
public class mainPage_behaviour {

    public static void buttons_behaviour_to_TabPane(TabPane tabpane, Button newStudent_Button, Button sms_Button, MenuItem newStud) {

        if (tabpane.getTabs().isEmpty()) {

            newStudent_Button.setDisable(true);
            sms_Button.setDisable(true);
            newStud.setDisable(true);
        } else {
            newStudent_Button.setDisable(false);
            sms_Button.setDisable(false);
            newStud.setDisable(false);
        }

    }

}
