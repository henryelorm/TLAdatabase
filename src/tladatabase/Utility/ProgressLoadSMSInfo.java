/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import org.controlsfx.dialog.Dialogs;

import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.modem.SerialModemGateway;
import tladatabase.Controllers.OptionsController;
import tladatabase.Entity.ProgramRecords;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class ProgressLoadSMSInfo {
    
    OptionStage ops = new OptionStage();

    public void progress(TextField modemManufac, ComboBox availableports, ProgramRecords prg, EntityManager em, Button sendButton) {

        Service<Void> serviceProg = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {

                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {

                        updateMessage("Searching for modem");
                        updateProgress(0, 10);

                        try {

                            if (portsAvailable(availableports) == true) {

                                SerialModemGateway gateway = new SerialModemGateway("modem.com", "COM3", 115200, "", "");

                                Thread.sleep(400);
                                updateProgress(5, 10);
                                if (gateway.getStatus().toString() != "STARTED") {
                                    SMS.smsProtocol_Send(gateway);
                                }
                                System.err.println("hss  "+availableports.getValue().toString());

                            }else{
                                SerialModemGateway gateway = new SerialModemGateway("modem.com", availableports.getValue().toString(), 115200, "", "");

                                Thread.sleep(400);
                                updateProgress(5, 10);
                                if (gateway.getStatus().toString() != "STARTED") {
                                    SMS.smsProtocol_Send(gateway);
                                }
                                System.err.println(availableports.getValue().toString());
                            }

                            modemManufac.setText(SMS.modemFound);

                            if (modemManufac.getText().isEmpty()) {
                                modemManufac.setText("No modem Found");
                                sendButton.setDisable(true);
                                //BugLog.Error_alert("Please make sure your modem is connected OR \nis being used by another Software/User", null, ops.primaryStage);
                               BugLog.Error_static("Please make sure your modem is connected OR \nis not being used by another Software/User, \n then restart program", null);
                            }
                            updateProgress(10, 10);

                            if (prg.getComports() != null) {
                                //availableports.getItems().clear();
                                // availableports.setValue("fffs");
                                

                            } else {
                                if (!PortsListing.portList().isEmpty()) {
                                    availableports.setItems(PortsListing.portList());

                                } else {
                                    
                                    //  System.out.println("modem not inserted into the correct port \nChange the USB Port and restart software to detect modem");
                                    BugLog.Error_static("modem not inserted into the correct port \nChange the USB Port and restart software to detect modem", "Port Unavailable");

                                }

                            }

                            // org.smslib.Service.getInstance().stopService();
                        } catch (InterruptedException ex) {
                            // Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
                            System.err.println("error 5");
                        } catch (Exception ex) {
                            System.out.println("fasx");
                        } catch (Error e) {
                            System.out.println("ccc");
                        }

                        updateMessage("Complete");
                        return null;
                    }

                };
            }
        };

        OptionStage os = new OptionStage();
        Dialogs.create()
                .owner(os.primaryStage)
                .title("GSM Search")
                .showWorkerProgress(serviceProg);

        serviceProg.start();

    }

    private boolean portsAvailable(ComboBox ports) {
        return ports.getValue().equals(null) || ports.getValue().equals("");
    }

}
