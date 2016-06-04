package uo.sdi.business.integration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import uo.sdi.client.util.Jndi;

public class Producer {
    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String CORREO_TOPIC = "jms/topic/MensajeriaCorreo";

    private static final String USER = "sdi";
    private static final String PASSWORD = "password";

    private Connection con;
    private Session session;

    private MessageProducer sender;

    public Producer() throws JMSException {
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

	con = factory.createConnection(USER, PASSWORD);
	session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

	// ===========================
	// Crear productor de mensajes
	// ===========================

	Destination topic = Jndi.getDestination(CORREO_TOPIC);
	sender = session.createProducer(topic);

	// =======
	// Iniciar
	// =======

	con.start();
    }

    public MapMessage createMapMessage() throws JMSException {
	return session.createMapMessage();
    }
    
    public void send(MapMessage mapMSG) throws JMSException {
	sender.send(mapMSG);
    }

    public void close() throws JMSException {
	con.close();
    }
}