package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import uo.sdi.model.Application;
import uo.sdi.persistence.util.GenericDao;


public interface ApplicationDao extends GenericDao<Application, Long[]>
{
	List<Application> findByUserId( Long userId );
	
	List<Application> findByTripId( Long tripId );
	
	
	List<Application> findByPromoterIdBeforeDepartureDate
			(Long userId, Date fechaActual);
}