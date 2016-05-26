package uo.sdi.client.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import uo.sdi.client.util.Jndi;

public class Consumer {
    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String CORREO_TOPIC = "jms/topic/MensajeriaCorreo";
    private Connection con;

    private static final String USER = "sdi";
    private static final String PASSWORD = "password";

    public Consumer(String idViaje) throws JMSException {
	run(idViaje);
    }

    private void run(String idViaje) throws JMSException {
	initialize(idViaje);
    }

    private void initialize(String idViaje) throws JMSException {
	// ===================================
	// Crear conexion e iniciar una sesion
	// ===================================

	ConnectionFactory factory = Jndi
		.getConnectionFactory(JMS_CONNECTION_FACTORY);

	con = factory.createConnection(USER, PASSWORD);
	Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

	// ============================
	// Crear consumidor de mensajes
	// ============================

	Destination topic = Jndi.getDestination(CORREO_TOPIC);

	MessageConsumer consumer = session.createConsumer(topic, "idViaje = '"
		+ idViaje + "'"); // Filtrar por viaje

	consumer.setMessageListener(new MessageListener());

	// ==================
	// Empezar a consumir
	// ==================

	con.start();
    }

    public void close() throws JMSException {
	con.close();
    }
}