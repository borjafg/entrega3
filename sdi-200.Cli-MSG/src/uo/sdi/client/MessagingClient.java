package uo.sdi.client;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import uo.sdi.client.messaging.Consumer;
import uo.sdi.client.messaging.Producer;

public class MessagingClient {
    private Consumer consumer;
    private Producer producer;
    
    private String login;
    private Long idViaje;

    public void setIdViaje(Long idViaje) {
	this.idViaje = idViaje;
    }

    public void setLogin(String login) {
	this.login = login;
    }
    
    /**
     * Inicializa un consumidor y un productor de mensajes para que el usuario
     * pueda enviar y recibir mensajes sobre un viaje.
     * 
     * @param idViaje
     *            Identificador del viaje del que se quieren gestionar los
     *            mensajes (recibir y enviar)
     * 
     * @throws JMSException
     *             Ha ocurrido un error al iniciar el consumidor y el productor
     */
    public void inicializar_Productor_Consumidor(Long idViaje)
	    throws JMSException {

	// Sólo hace falta cerrar el consumidor, porque es el tiene que filtrar
	// los mensajes de la cola para recibir solo los mensajes del viaje.
	//
	// El productor solo necesita saber el id del viaje en el momento de
	// enviar un mensaje (esa informacion se adjunta al mensaje al crearlo)
	//
	closeConsumer();

	consumer = new Consumer(idViaje.toString());
	
	if(producer == null) {
	    producer = new Producer();
	}
	
	this.idViaje = idViaje;
    }

    /**
     * Crea un mensaje sobre un viaje y lo envia a todos los participantes de
     * ese viaje.
     * 
     * @param msg
     *            mensaje que se pretende enviar
     * 
     * @throws JMSException
     *             Ha habido un error al crear o enviar el mensaje
     * 
     */
    public void enviarMensaje(String msg) throws JMSException {
	MapMessage message = producer.createMapMessage();

	message.setString("from", login);
	message.setString("message", msg);
	message.setLong("date", new Date().getTime());
	message.setLong("idViaje", idViaje);

	producer.send(message);
    }

    /**
     * Se encarga de dejar al consumidor y al productor de mensajes en un estado
     * apropiado antesde cerrar la aplicacion.
     * 
     * @throws JMSException
     *             Ha ocurrido un error al cerrar el sistema de mensajeria
     * 
     */
    public void close() throws JMSException {
	closeConsumer();
	closeProducer();
    }

    private void closeProducer() throws JMSException {
	if (producer != null) {
	    producer.close();
	}
    }

    private void closeConsumer() throws JMSException {
	if (consumer != null) {
	    consumer.close();
	}
    }
}