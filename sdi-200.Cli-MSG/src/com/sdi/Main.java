package com.sdi;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import com.sdi.client.Consumer;
import com.sdi.client.Producer;
import com.sdi.client.TripInfo;
import com.sdi.ui.Console;

public class Main {
    public static void main(String[] args) throws JMSException {
	new Main().run();
    }

    private String usuario;
    private TripInfo viaje;
    
    private Consumer consumer;
    private Producer producer;

    private void run() throws JMSException {
	initialize();
	getLoginUsuario();
	getMessages();
    }

    private void getMessages() throws JMSException {

	try {
	    while (true) {
		String msg = Console.readString("--> ");
		MapMessage mapmsg = createMessage(msg);
		producer.send(mapmsg);
	    }
	}
	
	catch (Exception e) {
	    System.out.println(e.getLocalizedMessage());
	}
	
	finally {
	    consumer.close();
	    producer.close();
	}
    }

    private void getLoginUsuario() {
	usuario = Console.readString("Nombre de usuario");
	usuario = Console.readString("Nombre de usuario");
    }

    private void initialize() throws JMSException {
	consumer = new Consumer(usuario);
	producer = new Producer();
    }

    private MapMessage createMessage(String msg) throws JMSException {

	MapMessage m = producer.createMapMessage();
	
	m.setString("id", usuario);
	m.setString("cuerpo", msg);
	
	return m;
    }
}