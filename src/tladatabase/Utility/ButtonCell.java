/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
/**
 *
 * @author Elorm
 */
public class ButtonCell extends TableCell<String, Boolean> {

    final Button cellButton = new Button();

    ButtonCell() {
        cellButton.setStyle("-fx-background-color:  linear-gradient(lightgray,  red);\n"
                + "-fx-background-radius: 5em; "
                + "-fx-min-width: 5px; "
                + "-fx-min-height: 13px; "
                + "-fx-max-width: 13px; "
                + "-fx-max-height: 13px;");

        cellButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                // do something when button clicked
                //...
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        }
    }
}
