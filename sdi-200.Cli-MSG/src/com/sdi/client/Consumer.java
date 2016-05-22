package com.sdi.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.sdi.client.util.Jndi;

public class Consumer {
    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String CORREO_TOPIC = "jms/topic/MensajeriaCorreo";
    private Connection con;

    public Consumer() throws JMSException {
	run();
    }

    private void run() throws JMSException {
	initialize();
    }

    private void initialize() throws JMSException {
	// ===================================
	// Crear conexion e iniciar una sesion
	// ===================================

	ConnectionFactory factory = Jndi
		.getConnectionFactory(JMS_CONNECTION_FACTORY);

	con = factory.createConnection("sdi", "password");
	Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

	// ============================
	// Crear consumidor de mensajes
	// ============================

	Destination topic = Jndi.getDestination(CORREO_TOPIC);
	MessageConsumer consumer = session.createConsumer(topic, "idViaje = ''");
	
	consumer.setMessageListener(new MessageListener());

	con.start();
    }

    public void close() throws JMSException {
	con.close();
    }
}