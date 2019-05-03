package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persist.Ehdokkaat;

@Path("/deletecandidate")
public class DeleteCandidate {
	
	@GET
	//@Path("{candidateidd}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("candidateidd") String candidateid, 
			@QueryParam("candidateidd") int candidateidd 
			) 
	{
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		try {
			//Ehdokkaat ehdokasPK = new Ehdokkaat(candidateidd);
			Ehdokkaat ehdokas = em.find(Ehdokkaat.class, candidateidd);
			
			em.getTransaction().begin();
			em.remove(ehdokas);
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {
			
		}
		
		return "You have removed candidate with id of " + candidateidd + ".";
		
	}

}
