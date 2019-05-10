package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.ws.rs.core.MediaType;

import javax.xml.bind.annotation.XmlElement;

import com.google.api.services.discovery.model.RestMethod.Request;

import persist.Ehdokkaat;


@Path("/addcandidaterest")
public class AddCandidateRest {
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
//	public String add(@QueryParam("EHDOKAS_ID2") int candidateid,
//			          @QueryParam("candidatelname") String candidatelname, 
//			          @QueryParam("candidatefname") String candidatefname, 
//			          @QueryParam("party") String party, 
//			          @QueryParam("homecity") String homecity, 
//			          @QueryParam("age") int age, 
//			          @QueryParam("whytoparliament") String whytoparliament, 
//			          @QueryParam("whatarethethingsyouwanttoaffect") String whatarethethingsyouwanttoaffect, 
//			          @QueryParam("profession") String profession) {
	public Ehdokkaat add(Ehdokkaat ehdokas) {
		System.out.println("Adding candidate");
		EntityManagerFactory emf = null;
		EntityManager em = null;
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		
		try {
			em.getTransaction().begin();
			em.persist(ehdokas);
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			//return ehdokas;
		}
		return ehdokas;
//		 
//		return "You have added a candidate with id of " + candidateid + ", last name of " + candidatelname + 
//				", first name of " + candidatefname + ", party of " + party + ", home city of " + homecity + 
//				", age of " + age + ", why the candidate wants to get to the parliament being " + whytoparliament + 
//				", the things that the candidate wants to affect being " + whatarethethingsyouwanttoaffect + 
//				" and a profession of " + profession;
	}
	
	/* public Ehdokkaat lisääEhdokas(Ehdokkaat e) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		Ehdokkaat ehdokasPK = new Ehdokkaat(e.getEhdokasId());
		Ehdokkaat ehdokas = em.find(Ehdokkaat.class, ehdokasPK);
		
		em.getTransaction().begin();
		em.persist(ehdokas);
		em.getTransaction().commit();
		em.close();
		
		return e;
	}
*/
}
