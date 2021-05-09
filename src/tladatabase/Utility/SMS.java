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
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.OutboundWapSIMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;
import org.smslib.helper.CommPortIdentifier;

public class SMS {

    public static String modemFound = "";
   public  static  String connection = "";
     
    public static void smsProtocol_Send(SerialModemGateway gateway) {

        try {
            OutboundNotification outboundnotification = new OutboundNotification();
            gateway.setInbound(true);
            gateway.setOutbound(true);
            gateway.setSimPin("0000");
            Service.getInstance().setOutboundMessageNotification(outboundnotification);
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();
            modemFound = gateway.getManufacturer();
           // connection = gateway.get

        } catch (GatewayException ex) {
            //   Logger.getLogger(SMS.class.getName()).log(Level.SEVERE, null, ex);
            // System.err.println(ex.getCause());

        } catch (SMSLibException ex) {
            System.out.println("error 1");
        } catch (IOException ex) {
            System.out.println("error 2");
        } catch (InterruptedException ex) {
            // Logger.getLogger(SMS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error 3");

        } catch (IllegalStateException err) {
            System.err.println("error 4");
        } catch (Exception ee) {
            System.out.println("err");
        }

    }

}
