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


@Path("/addcandidaterest")
public class AddCandidateRest {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String add(@QueryParam("candidateid") int candidateid,
			          @QueryParam("candidatelname") String candidatelname, 
			          @QueryParam("candidatefname") String candidatefname, 
			          @QueryParam("party") String party, 
			          @QueryParam("homecity") String homecity, 
			          @QueryParam("age") int age, 
			          @QueryParam("whytoparliament") String whytoparliament, 
			          @QueryParam("whatarethethingsyouwanttoaffect") String whatarethethingsyouwanttoaffect, 
			          @QueryParam("profession") String profession) {
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		try {
			Ehdokkaat ehdokas = em.find(Ehdokkaat.class, candidateid);
			
			em.getTransaction().begin();
			em.persist(ehdokas);
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {
			
		}
		 
		return "You have added a candidate with id of " + candidateid + ", last name of " + candidatelname + 
				", first name of " + candidatefname + ", party of " + party + ", home city of " + homecity + 
				", age of " + age + ", why the candidate wants to get to the parliament being " + whytoparliament + 
				", the things that the candidate wants to affect being " + whatarethethingsyouwanttoaffect + 
				" and a profession of " + profession;
	}

}
