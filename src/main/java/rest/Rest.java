package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

 
@Path("/restitesti")
public class Rest {
 
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String restitesti() {
		String output = "Tää tulee restistä jee";
		return output;
	}
	
	
}