package uo.sdi.acciones;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/ApplicationServiceRs")
public interface ApplicationServicesRest {


    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void confirmarPasajeros(Long idUser, Long idTrip);
}
