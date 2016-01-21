package se.groupjcnr.projecti.dao;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.groupjcnr.projecti.model.User;

public interface UserDAO extends CrudDAO<User>{
	
	@GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") Long userId);

	@GET
    @Path("/allUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser(Long userId);
}
