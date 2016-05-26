package uo.sdi.client.messaging;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import alb.util.console.Console;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/MensajeriaCorreo") })
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

    private void process(Message message) throws JMSException {
	// Obtener los datos del mensaje

	MapMessage msg = (MapMessage) message;

	String usuario = msg.getString("from");
	String mensage = msg.getString("message");
	String fecha = dateToString(new Date(msg.getLong("date")),
		"dd/MM/yyyy HH:mm");

	// Preparar el mensaje para mostrarselo al usuario

	StringBuilder sb = new StringBuilder();

	sb.append(fecha).append(" --> ");
	sb.append(usuario).append(": ").append(mensage);

	// Imprimir el mensaje

	Console.println(sb.toString());
    }

    private String dateToString(Date fecha, String formato) {
	try {
	    String fechaConvertida = "";

	    SimpleDateFormat sdf = new SimpleDateFormat(formato);

	    fechaConvertida = sdf.format(fecha);
	    return fechaConvertida;
	}

	catch (Exception e) {
	    return "Fecha desconocida";
	}
    }
}