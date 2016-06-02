package uo.sdi.util;

import uo.sdi.model.Trip;
import alb.util.date.DateUtil;

public class ShowTrip {
    public static String show(Trip trip) {
	StringBuilder sb = new StringBuilder();

	sb.append("Viaje (id = ").append(trip.getId()).append("):\n");

	// ===========================
	// Lugares de llegada y salida
	// ===========================

	sb.append("\t- Lugar de salida: ").append(trip.getDeparture());
	sb.append("\n");

	sb.append("\t- Lugar de llegada: ").append(trip.getDestination());
	sb.append("\n");

	// =========================
	// Fecha de llegada y salida
	// =========================

	sb.append("\t- Fecha de llegada: ");
	sb.append(DateUtil.toString(trip.getDepartureDate(), "dd/MM/yyyy HH:mm"));
	sb.append("\n");

	sb.append("\t- Fecha de salida: ");
	sb.append(DateUtil.toString(trip.getArrivalDate(), "dd/MM/yyyy HH:mm"));
	sb.append("\n");

	sb.append("\t- Fecha de cierre: ");
	sb.append(DateUtil.toString(trip.getClosingDate(), "dd/MM/yyyy HH:mm"));
	sb.append("\n");

	// ===========
	// Otros datos
	// ===========

	sb.append("\t- Coste estimado: ").append(trip.getEstimatedCost());
	sb.append("\n");

	sb.append("\t- Estado del viaje: ");
	sb.append(trip.getStatus().toString().toLowerCase());
	sb.append("\n");

	return sb.toString();
    }
}