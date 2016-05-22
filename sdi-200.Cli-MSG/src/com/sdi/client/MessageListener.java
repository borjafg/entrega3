package com.sdi.client;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

@MessageDriven(activationConfig =
{
    @ActivationConfigProperty(
	propertyName = "destination",
	propertyValue = "topic/MensajeriaCorreo")
})
public class MessageListener implements javax.jms.MessageListener {

    @Override
    public void onMessage(Message msg) {
	try {
	    process(msg);
	}
	
	catch (JMSException jex) {
	    jex.printStackTrace();
	}
    }

    private void process(Message msg) throws JMSException {
	MapMessage m = (MapMessage) msg;
	
	String usuario = m.getString("from");
	String cuerpo = m.getString("message");
	
	System.out.println(usuario + ": " + cuerpo);
    }
}