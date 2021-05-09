/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import tladatabase.Entity.Student;

/**
 *
 * @author Elorm
 */
public class Tables {

    public static TableColumn<Student, String> lastName(TableColumn<Student, String> lname) {

        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lname;
    }

    public static TableColumn<Student, String> firstName(TableColumn<Student, String> fname) {

        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return fname;
    }

    public static TableColumn<Student, String> otherName(TableColumn<Student, String> oname) {

        oname.setCellValueFactory(new PropertyValueFactory<>("otherName"));
        return oname;
    }

    public static TableColumn<Student, String> phoneNo(TableColumn<Student, String> phoneNo) {

        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        return phoneNo;
    }

    /* public static TableColumn feesIden(TableColumn feeIden) {

     feeIden.setCellFactory(
     new Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>>() {

     @Override
     public TableCell<String, Boolean> call(TableColumn<String, Boolean> p) {
     return new ButtonCell();
     }

     });

     return feeIden;
     }
     */
    public static TableColumn numberColumn(TableColumn number) {

        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, Student>, ObservableValue<Student>>() {
            @Override
            public ObservableValue<Student> call(TableColumn.CellDataFeatures<Student, Student> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        number.setCellFactory(new Callback<TableColumn<Student, Student>, TableCell<Student, Student>>() {
            @Override
            public TableCell<Student, Student> call(TableColumn<Student, Student> param) {
                return new TableCell<Student, Student>() {
                    @Override
                    protected void updateItem(Student item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        return number;
    }

}
