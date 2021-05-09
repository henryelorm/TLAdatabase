/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.event.EventHandler;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import tladatabase.Entity.Class;
import tladatabase.Entity.Fees_Class;
import tladatabase.EntityManager.Fees_ClassManager;
import tladatabase.Entity.Fees_Class;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class FeesTable {

    public static TableColumn<FeesFill, String> classes(TableColumn<FeesFill, String> classes) {

        classes.setCellValueFactory(new PropertyValueFactory<>("className"));
        return classes;
    }

    public static TableColumn fees(EntityManager em, TableColumn<FeesFill, String> fees, TableView<FeesFill> feesTable) throws Exception {

      //  GetClassIDFromName gcif = new GetClassIDFromName();
        fees.setCellValueFactory(new PropertyValueFactory<>("fees"));
        fees.setCellFactory(TextFieldTableCell.<FeesFill>forTableColumn());
        fees.setOnEditCommit((CellEditEvent<FeesFill, String> t) -> {

            if (EditCellInputCheck.setInput(t.getNewValue()) == true) {
              //  System.out.println(" " + t.getNewValue());
                SaveInput(em, feesTable, Double.parseDouble(t.getNewValue()));

            } else {

                BugLog.Error_alert("Wrong Input", null, MainPage.stage);

            }
        });
        return fees;
    }

    private static void SaveInput(EntityManager em, TableView<FeesFill> feesTable, double fees) {

        // System.err.println(feesTable.getSelectionModel().getSelectedItem().getId());
        Fees_ClassManager fcm = new Fees_ClassManager();
        List<Fees_Class> ls = fcm.findAllFeesClass(em);
        if (!ls.isEmpty()) {

            for (int i = 0; i < ls.size(); i++) {

                if (ls.get(i).getId().equals(feesTable.getSelectionModel().getSelectedItem().getClassId())) {

                    Fees_Class fc = em.find(Fees_Class.class, ls.get(i).getId());
                    em.getTransaction().begin();
                    fc.setClassFees(fees);
                    em.getTransaction().commit();
                    break;
                } else if ((i + 1) == ls.size()) {
                    em.getTransaction().begin();
                    Fees_Class fc = new Fees_Class(feesTable.getSelectionModel().getSelectedItem().getClassId(), fees);
                    em.persist(fc);
                    em.getTransaction().commit();
                }
            }

        } else {
            em.getTransaction().begin();
            Fees_Class fc = new Fees_Class(feesTable.getSelectionModel().getSelectedItem().getClassId(), fees);
            em.persist(fc);
            em.getTransaction().commit();
        }

    }

}
