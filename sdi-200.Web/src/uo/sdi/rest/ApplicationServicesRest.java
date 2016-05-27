package uo.sdi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import uo.sdi.model.Application;

@Path("/ApplicationServiceRs")
public interface ApplicationServicesRest {

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void confirmarPasajeros(Long idUser, Application application)
	    throws Exception;
}