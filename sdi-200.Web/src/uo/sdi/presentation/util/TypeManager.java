package uo.sdi.presentation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TypeManager
{
	private TypeManager() {}
	
	
	/**
	 * Convierte una cadena de texto a una fecha con formato java.util.Date
	 * 
	 * @param fecha   fecha con formato String
	 * @param formato  formato que tienen la fecha (Ejemplo: dd/MM/yyyy HH:mm)
	 * 
	 * @return   Fecha con formato java.util.Date, o null si no se pudo convertir
	 * 
	 */
	public static Date stringToDate(String fecha, String formato)
	{
		Date fechaConvertida = null;
		
		
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			
			fechaConvertida = sdf.parse(fecha);
		}
		
		catch (ParseException excep) { fechaConvertida = null; }
		
		
		return fechaConvertida;
	}
	
	
	/**
	 * Convierte una fecha en formato String y una fecha con formato java.util.Date
	 * 
	 * @param fecha   fecha con formato dia/mes/año (Ejemplo: 01/03/2000)
	 * 
	 * @return   Fecha con formato java.util.Date, o null si no se pudo convertir
	 * 
	 */
	public static Date stringToDate(String fecha)
	{
		Date fechaConvertida = null;
		
		
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			fechaConvertida = sdf.parse(fecha);
		}
		
		catch (ParseException excep) { fechaConvertida = null; }
		
		
		return fechaConvertida;
	}
	
	
	/**
	 * Convierte una cadena de texto en un entero
	 * 
	 * @param cadenaConvertir   cadena que se quiere transformar en un entero
	 * @return   entero resultante de convertir la cadena, o null si no se pudo convertir
	 * 
	 */
	public static Integer stringToInteger(String cadenaConvertir)
	{
		Integer valor = null;
		
		
		try
		{
			valor = Integer.parseInt(cadenaConvertir);
		}
		
		catch (NumberFormatException excep) { valor = null; }
		
		
		return valor;
	}
	
	
	/**
	 * Convierte una cadena de texto en un double
	 * 
	 * @param cadenaConvertir   cadena que se quiere transformar en un double
	 * @return   double resultante de convertir la cadena, o null si no se pudo convertir
	 * 
	 */
	public static Double stringToDouble(String cadenaConvertir)
	{
		Double valor = null;
		
		
		try
		{
			valor = Double.parseDouble(cadenaConvertir);
		}
		
		catch (NumberFormatException excep) { valor = null; }
		
		
		return valor;
	}
	
	
	/**
	 * Convierte una cadena de texto en un Long
	 * 
	 * @param cadenaConvertir   cadena que se quiere transformar en un Long
	 * @return   Long resultante de convertir la cadena, o null si no se pudo convertir
	 * 
	 */
	public static Long stringToLong(String cadenaConvertir)
	{
		Long valor = null;
		
		
		try
		{
			valor = Long.parseLong(cadenaConvertir);
		}
		
		catch (NumberFormatException excep) { valor = null; }
		
		
		return valor;
	}
	
	
	/**
	 * Convierte una fecha java.util.Date y a un string con un formato
	 * que se indica como parámetro
	 * 
	 * @param fecha   fecha con formato java.util.Date
	 * @param formato   formato que se usará para convertir la fecha en un
	 * 					String; por ejemplo: "dd/MM/yyyy HH:mm"
	 * 
	 * @return   fecha en formato String, usando las plantilla que se pasa
	 * como parámetro
	 * 
	 */
	public static String dateToString(Date fecha, String formato)
	{
		String fechaConvertida = null;
		
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			
			fechaConvertida = sdf.format(fecha);
		}
		
		catch(Exception excep)
		{
			return null;
		}
		
		return fechaConvertida;
	}
}