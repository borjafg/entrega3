package uo.sdi.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import alb.util.log.Log;
import alb.util.log.LogLevel;

/**
 * Ajusta el nivel de log para mostrar unicamente los mensajes con el nivel de
 * LOG adecuado (esto esta establecido en un parametro de inicializacion de la
 * aplicacion).
 * 
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class BeanLog {
    @PostConstruct
    public void inicializar() {
	String level = ((ServletContext) FacesContext.getCurrentInstance()
		.getExternalContext().getContext())
		.getInitParameter("logLevel");

	switch (level) {

	case "OFF":
	    Log.setLogLevel(LogLevel.OFF);
	    break;

	/*
	 * Ha ocurrido algo grave que impide la normal ejecución del programa.
	 * Seguramente el programa tenga que terminar o abortar esa
	 * transacción/petición
	 */
	case "ERROR":
	    Log.setLogLevel(LogLevel.ERROR);
	    break;

	/*
	 * Se ha producido algún problema no crítico para el funcionamiento del
	 * programa. El programa puede continuar pero algo raro ha ocurrido.
	 */
	case "WARN":
	    Log.setLogLevel(LogLevel.WARN);
	    break;

	/*
	 * Se ha alcanzado algún hito importante en la ejecución del programa.
	 */
	case "INFO":
	    Log.setLogLevel(LogLevel.INFO);
	    break;

	/*
	 * El programador necesita ver en detalle información sobre el
	 * comportamiento de la aplicación. Este nivel ya no debería estar
	 * activado cuando el programa está en producción.
	 */
	case "DEBUG":
	    Log.setLogLevel(LogLevel.DEBUG);
	    break;

	/*
	 * Nivel máximo de detalle de lo que hace la aplicación.
	 */
	case "TRACE":
	    Log.setLogLevel(LogLevel.TRACE);
	    break;

	case "ALL":
	    Log.setLogLevel(LogLevel.ALL);
	    break;
	}
    }
}