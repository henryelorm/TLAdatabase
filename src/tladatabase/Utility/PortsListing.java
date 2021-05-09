/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.Enumeration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.comm.CommPortIdentifier;

/**
 *
 * @author Elorm
 */
public class PortsListing {

    public static ObservableList<String> portList() {

        ObservableList<String> li = FXCollections.observableArrayList();
        li.clear();
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        // System.out.println(portList.hasMoreElements());
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() != CommPortIdentifier.PORT_PARALLEL) {
                System.out.println(portId.getName());

                li.add(portId.getName());
            }

        }
        return li;
    }
}
