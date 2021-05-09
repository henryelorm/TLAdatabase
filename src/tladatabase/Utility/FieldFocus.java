/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Elorm
 */
public class FieldFocus {

    public static void searchFieldFocusInstane(TextField textField, ListView<String> listView) {

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (textField.getText().isEmpty()) {
                    if (!listView.getItems().isEmpty() && listView.isFocused()) {

                    } else {
                        SearchEngine.getasString.setAll("");
                        listView.setItems(SearchEngine.getasString);
                    }

                }
            }
        });

    }

}
