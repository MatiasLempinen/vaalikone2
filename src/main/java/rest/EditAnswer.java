package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static java.lang.Integer.parseInt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persist.Vastaukset;
import persist.VastauksetPK;

@Path("/editanswer")
public class EditAnswer {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String delete (
			@QueryParam("candidateid") int candidateID,
			@QueryParam("answer") int answer,
			@QueryParam("comment") String comment,
			@QueryParam("q") int questionID)						
			{                
      
        EntityManagerFactory emf=null;
        EntityManager em = null;
  	    emf=Persistence.createEntityManagerFactory("vaalikones");
  	    em = emf.createEntityManager();

        try {
        	
        	VastauksetPK answerPK = new VastauksetPK(candidateID,questionID);
        	Vastaukset answer1 = em.find(Vastaukset.class, answerPK);
        	
            em.getTransaction().begin();
    		answer1.setVastaus(answer);
    		answer1.setKommentti(comment);
            em.getTransaction().commit();
     		
     		
             
        } catch (Exception e) {
        	
        }
		return "<p>" + "You changed answer from candidate " + candidateID + " to question nro " + questionID +  " to: " + answer + " and the comment to: " + comment + "</p>"
				+ "<a href=\"http://localhost:8080/index.html\">Go back to start</a>";
        

	}

}

