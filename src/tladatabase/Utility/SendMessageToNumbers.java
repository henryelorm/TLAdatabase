/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.TimeoutException;
import tladatabase.Controllers.OptionsController;

/**
 *
 * @author Elorm
 */
public class SendMessageToNumbers {

    public void SendMessageToNumbers(String number, String message) {

        try {
            //"0249002546"
            OutboundMessage msg = new OutboundMessage(number, message);
            org.smslib.Service.getInstance().sendMessage(msg);
        } catch (TimeoutException ex) {
            BugLog.Error_alert("Message", null, null);
        } catch (GatewayException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
