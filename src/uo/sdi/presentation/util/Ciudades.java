package uo.sdi.presentation.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ciudades {
	private static Map<String, List<String>> ciudades = inicializar();

	/**
	 * Crea un diccionario en el que las claves son provincias y el valor es una
	 * lista de algunas ciudades que están en la correspondiente provincia
	 * 
	 * @return diccionario con posibles ciudades para cada provincia
	 * 
	 */
	private static Map<String, List<String>> inicializar() {
		Map<String, List<String>> listaCiudades = new HashMap<String, List<String>>();

		return listaCiudades;
	}
	
	/**
	 * Dada una provincia devuelve una lista de ciudades.
	 * 
	 * @param provincia de la que obtener la lista de ciudades
	 * @return lista de algunas ciudades que estan en la provincia indicada
	 * 
	 */
	public static List<String> getCiudades(String provincia) {
		List<String> posiblesCiudades = ciudades.get(provincia);
		
		if(posiblesCiudades != null) {
			return posiblesCiudades;
		}
		
		return new ArrayList<String>();
	}
}