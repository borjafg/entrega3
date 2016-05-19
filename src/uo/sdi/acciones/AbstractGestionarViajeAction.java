package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.TypeManager;
import uo.sdi.acciones.util.ValidadorParametros;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;


/**
 * Comprueba que todos los parámetros de un viaje sean correctos y deja que
 * las subclases decidan cual es la lógica de negocio que hay que ejecutar.
 *
 */
public abstract class AbstractGestionarViajeAction implements Accion
{
	/**
	 * Ejecuta la lógica de nogocio asociada a la creación o modificación de
	 * un viaje.
	 * 
	 * @param request   petición
	 * @param response  respuesta
	 * @param viaje   viaje que contiene la información que se quiere procesar
	 * 
	 * @return EXITO o FRACASO
	 * 
	 */
	protected abstract String logicaNegocio(HttpServletRequest request,
			HttpServletResponse response, Trip datosViaje);
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	{
		// =========================================
		//		Recuperación de parámetros
		// =========================================
		
		String calleSalida = request.getParameter("calleSalida");
		String ciudadSalida = request.getParameter("ciudadSalida");
		String provinciaSalida = request.getParameter("provinciaSalida");
		String paisSalida = request.getParameter("paisSalida");
		String codigoPostalSalida = request.getParameter("codigoPostalSalida");
		
		String latitudSalida = request.getParameter("latitudSalida");
		String longitudSalida = request.getParameter("longitudSalida");
		
		
		String calleLlegada = request.getParameter("calleLlegada");
		String ciudadLlegada = request.getParameter("ciudadLlegada");
		String provinciaLlegada = request.getParameter("provinciaLlegada");
		String paisLlegada = request.getParameter("paisLlegada");
		String codigoPostalLlegada = request.getParameter("codigoPostalLlegada");
		
		String latitudLlegada = request.getParameter("latitudLlegada");
		String longitudLlegada = request.getParameter("longitudLlegada");
		
		
		String horaSalida = request.getParameter("horaSalida");
		String fechaSalida = request.getParameter("fechaSalida");
		
		String fechaLlegada = request.getParameter("fechaLlegada");
		String horaLlegada = request.getParameter("horaLlegada");
		
		
		String fechaLimiteApuntarse = request.getParameter("fechaLimite");
		String costeEstimadoTotal = request.getParameter("costeTotal");
		
		String descripcion = request.getParameter("descripcion");
		
		String numeroPlazasLibres = request.getParameter("plazasLibres");
		String maxPlazas = request.getParameter("maximoPlazas");
		
		
		// =========================================
		//				validaciones
		// =========================================
		
		
		String resultLugarSalidaValido = parametrosLugarValidos(calleSalida,
				ciudadSalida, provinciaSalida, paisSalida, codigoPostalSalida);
		
		if (resultLugarSalidaValido != null)
		{
			request.setAttribute("causaError", resultLugarSalidaValido);
			
			return "FRACASO";
		}
		
		
		String resultLugarLlegadaValido = parametrosLugarValidos(calleLlegada,
				ciudadLlegada, provinciaLlegada, paisLlegada, codigoPostalLlegada); 
		
		if (resultLugarLlegadaValido != null)
		{
			request.setAttribute("causaError", resultLugarLlegadaValido);
			
			return "FRACASO";
		}
		
		
		String coordenadasSalidaValidas = parametrosCoordenadasValidos(latitudSalida, longitudSalida);
		
		if (coordenadasSalidaValidas != null && coordenadasSalidaValidas.equals(""))
		{
			request.setAttribute("causaError", coordenadasSalidaValidas);
			
			return "FRACASO";
		}
		
		boolean coordenadasIndicadas = true;
		
		if (coordenadasSalidaValidas.equals("")) { coordenadasIndicadas = false; }
		
		
		if (coordenadasIndicadas)
		{
			String coordenadasLlegadaValidas = parametrosCoordenadasValidos(latitudLlegada, longitudLlegada);
			
			if (coordenadasLlegadaValidas != null && !coordenadasLlegadaValidas.equals(""))
			{
				request.setAttribute("causaError", coordenadasSalidaValidas);
				
				return "FRACASO";
			}
			
			else if (coordenadasLlegadaValidas != null && coordenadasLlegadaValidas.equals(""))
			{
				request.setAttribute("causaError", "Si indica las coordenadas de salida, tiene que " +
						"indicar las coordenas de salida.");
				
				return "FRACASO";
			}
		}
		
		
		String resultFechasValidas = parametrosFechaValidos(horaSalida,
				fechaSalida, horaLlegada, fechaLlegada, fechaLimiteApuntarse);
		
		if(resultFechasValidas != null)
		{
			request.setAttribute("causaError", resultFechasValidas);
			
			return "FRACASO";
		}
		
		
		if (ValidadorParametros.isParamsStringValid(descripcion))
		{
			request.setAttribute("causaError", "Tiene que describir el viaje");
			
			return "FRACASO";
		}
		
		
		String resultCosteTotalValido = parametroCosteTotalValido(costeEstimadoTotal);
		
		if (resultCosteTotalValido != null)
		{
			request.setAttribute("causaError", resultCosteTotalValido);
			
			return "FRACASO";
		}
		
		
		String resultPlazasViaje = parametrosPlazasViajeValidos(numeroPlazasLibres, maxPlazas);
		
		if (resultPlazasViaje != null)
		{
			request.setAttribute("causaError", resultPlazasViaje);
			
			return "FRACASO";
		}
		
		
		// =========================================
		//			Cambios de formato
		// =========================================
		
		
		Waypoint coordenadasSalida = null;
		Waypoint coordenadasLlegada = null;
		
		if(coordenadasIndicadas)
		{
			coordenadasSalida = new Waypoint(
					TypeManager.stringToDouble(latitudSalida),
					TypeManager.stringToDouble(longitudSalida));
			
			coordenadasLlegada = new Waypoint(
					TypeManager.stringToDouble(latitudLlegada),
					TypeManager.stringToDouble(longitudLlegada));
		}
		
		
		AddressPoint lugarSalida = new AddressPoint(calleSalida, ciudadSalida,
			provinciaSalida, paisSalida, codigoPostalSalida, coordenadasSalida);
		
		AddressPoint lugarLlegada = new AddressPoint(calleLlegada, ciudadLlegada,
			provinciaLlegada, paisLlegada, codigoPostalLlegada, coordenadasLlegada);
		
		
		Date fechaSalida_Date = TypeManager.stringToDate(fechaSalida, horaSalida);
		Date fechaLlegada_Date = TypeManager.stringToDate(fechaLlegada, horaLlegada);
		Date fechaLimite_Date = TypeManager.stringToDate(fechaLimiteApuntarse);
		
		
		Double costeEstimadoTotal_Double = TypeManager.stringToDouble(costeEstimadoTotal);
		
		
		Integer numeroPlazasLibres_Integer = TypeManager.stringToInteger(numeroPlazasLibres);
		Integer maxPlazas_Integer = TypeManager.stringToInteger(maxPlazas);
		
		
		
		// =========================================
		//			Lógica de negocio
		// =========================================
		
		Trip viaje = new Trip();
		
		viaje.setDeparture(lugarSalida);
		viaje.setDestination(lugarLlegada);
		
		viaje.setDepartureDate(fechaSalida_Date);
		viaje.setArrivalDate(fechaLlegada_Date);
		viaje.setClosingDate(fechaLimite_Date);
		
		viaje.setEstimatedCost(costeEstimadoTotal_Double);
		viaje.setComments(descripcion);
		
		viaje.setAvailablePax(numeroPlazasLibres_Integer);
		viaje.setMaxPax(maxPlazas_Integer);
		
		Long idPromotor = ((User) request.getSession().getAttribute("user")).getId();
		viaje.setPromoterId(idPromotor);
		
		
		/* 
		 * Se le pasa los datos sobre el viaje para que las subclases
		 * se encarguen de actualizar un viaje existente o de crearlo,
		 * según cual sea su implementación.
		 *  
		 */
		return logicaNegocio(request, response, viaje);
	}
	
	
	
	/* ===================================================
	 * 
	 * Separada parte de la validacion de parametros para
	 * que el código de la lógica quede más legible
	 * 
	 * ===================================================
	 */
	
	
	/* 
	 * Si los parámetros son correctos devuelve null. En caso contrario,
	 * devuelve un mensaje de error con formato String
	 * 
	 */
	
	
	
	private String parametrosLugarValidos(String calle, String ciudad,
		String provincia, String pais, String codigoPostal)
	{
		boolean lugarSalidaValido = ValidadorParametros.isParamsStringValid
			(calle, ciudad, provincia, pais, codigoPostal);
		
		
		if(!lugarSalidaValido)
		{
			return "Falta indicar algún dato sobre el lugar de salida";
		}
		
		
		return null;
	}
	
	
	/**
	 * Evalua si unas coordenadas que se le pasan como parámetro son correctas.
	 * 
	 * @param latitud   latitud a validar
	 * @param longitud   longitud a validar
	 * 
	 * @return mensaje de error sin las coordenadas no son válidas. "" si no se
	 * indican las coordenadas. Null si las coordenadas indicadas son correctas
	 * 
	 */
	private String parametrosCoordenadasValidos(String latitud, String longitud)
	{
		if(!ValidadorParametros.isParamsStringValid(latitud, longitud))
		{
			return ""; // No se indican las coordenadas
		}
		
		
		if(!ValidadorParametros.canBeParsedToDouble(latitud, longitud))
		{
			return "La latitud o la longitud especificada no son validas.";
		}
		
		
		Double latitud_double = TypeManager.stringToDouble(latitud);
		Double longitud_double = TypeManager.stringToDouble(longitud);
		
		if(latitud_double < -90 || latitud_double > 90 || longitud_double < -180 ||
			longitud_double > 180)
		{
			return "La latitud debe tener un valor entre -90 y 90. La longitud " +
					"debe tener un valor entre -180 y 180";
		}
		
		
		return null;
	}
	
	
	private String parametrosFechaValidos(String horaSalida, String fechaSalida,
		String horaLlegada, String fechaLlegada, String fechaLimiteApuntarse)
	{
		boolean fechasValidas = ValidadorParametros.isParamsStringValid
			(horaSalida, fechaSalida, horaLlegada, fechaLlegada,
			fechaLimiteApuntarse);
		
		
		if(!fechasValidas)
		{
			return "Falta indicar algún dato sobre el lugar de llegada";
		}
		
		Date fechaSalida_Date = TypeManager.stringToDate(fechaSalida, horaSalida);
		Date fechaLlegada_Date = TypeManager.stringToDate(fechaLlegada, horaLlegada);
		Date fechaLimite_Date = TypeManager.stringToDate(fechaLimiteApuntarse);
		
		if(fechaSalida_Date == null || fechaLlegada_Date == null || fechaLimite_Date == null)
		{
			return "Las fechas o las indicadas tienen un formato incorrecto";
		}
		
		
		if(! fechaLimite_Date.after(new Date()) ||
			!fechaLimite_Date.before(fechaSalida_Date) ||
			!fechaSalida_Date.before(fechaLlegada_Date))
		{
			return "Las fechas indicada no son válidas. La fecha de salida tiene que ser posterior"
				+ " a la fecha límite para apuntarse, y la fecha de salida también tiene que ser"
				+ " previa a la fecha de llegada. Además, la fecha límite para apuntarse tiene"
				+ " que ser posterior a la fecha de hoy.";
		}
		
		
		return null;
	}
	
	
	private String parametroCosteTotalValido(String costeTotalValido)
	{
		if(!ValidadorParametros.isParamsStringValid(costeTotalValido))
		{
			return "Tiene que indicar el coste total del viaje";
		}
		
		
		if(!ValidadorParametros.canBeParsedToDouble(costeTotalValido))
		{
			return "El coste indicado no es válido";
		}
		
		
		return null;
	}
	
	
	private String parametrosPlazasViajeValidos(String numeroPlazasLibres, String maxPlazas)
	{
		if (!ValidadorParametros.isParamsStringValid(numeroPlazasLibres, maxPlazas))
		{
			return "Falta indicar el numero de plazas libres o el número de plazas máximo";
		}
		
		
		if (!ValidadorParametros.canBeParsedToInteger(numeroPlazasLibres, maxPlazas))
		{
			return "El número de plazas libres o el número de plazas máximas no son válidas";
		}
		
		
		Integer numPlazasLibresInteger = TypeManager.stringToInteger(numeroPlazasLibres);
		Integer maxPlazasInteger = TypeManager.stringToInteger(numeroPlazasLibres);
		
		if (numPlazasLibresInteger < 0 || numPlazasLibresInteger > maxPlazasInteger ||
			maxPlazasInteger == 0)
		{
			return "El número de plazas libres tiene que ser mayor o igual a 0, el " +
					"número de plazas libres tiene que ser menor o igual que el número " +
					"máximo de plazasm, y el número máximo de plazas tiene que ser mayor que 0.";
		}
		
		
		return null;
	}
	
	
	@Override
	public String toString()
	{
		return getClass().getName();
	}
}