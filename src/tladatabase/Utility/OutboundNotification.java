/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

/**
 *
 * @author Elorm
 */
public class OutboundNotification implements IOutboundMessageNotification {

    @Override
    public void process(AGateway gateway, OutboundMessage msg) {
        System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
        System.out.println(msg);
    }

}
