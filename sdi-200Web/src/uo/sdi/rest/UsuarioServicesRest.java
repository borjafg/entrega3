package uo.sdi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uo.sdi.model.Trip;
import uo.sdi.model.User;

@Path("/UsuarioServiceRs")
public interface UsuarioServicesRest {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    List<User> getUsuarios();
    @GET
    @Path("/findById/{login}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    User getUserByLogin(@PathParam("login") String login);   
}
