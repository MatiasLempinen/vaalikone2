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

import persist.Vastaukset;
import persist.VastauksetPK;

 
@Path("/deleteanswer")
public class DeleteAnswer {
 
	@GET
	public String delete (
			@QueryParam("candidateidd") int candidateidd,
			@QueryParam("questionidd") int questionidd)						
			{                
      
        EntityManagerFactory emf=null;
        EntityManager em = null;
  	    emf=Persistence.createEntityManagerFactory("vaalikones");
  	    em = emf.createEntityManager();

        try {
        	
        	VastauksetPK answerPK = new VastauksetPK(candidateidd,questionidd);
        	Vastaukset answer = em.find(Vastaukset.class, answerPK);
        	
        	em.getTransaction().begin();
     		em.remove(answer);
     		em.getTransaction().commit();
     		em.close();
     		
     		
             
        } catch (Exception e) {
        	
        }
		return "You have removed candidate " + candidateidd + "'s" +" answer to question " + questionidd;
        

	}
	
	
}
