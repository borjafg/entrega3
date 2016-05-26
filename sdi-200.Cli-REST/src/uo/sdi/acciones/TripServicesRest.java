package uo.sdi.acciones;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uo.sdi.model.Trip;

@Path("/TripServiceRs")
public interface TripServicesRest {

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<Trip> getViajes();
    
    @GET
    @Path("/findById/{promoter_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<Trip> getViajesUsuario(@PathParam("promoted_id")Long id);

}
