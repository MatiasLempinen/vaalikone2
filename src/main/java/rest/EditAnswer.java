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

import persist.Answer;
import persist.Vastaukset;
import persist.VastauksetPK;

@Path("/editanswer")
public class EditAnswer {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Answer muokkaaVastaus(Answer a)					
			{                
      
        EntityManagerFactory emf=null;
        EntityManager em = null;
  	    emf=Persistence.createEntityManagerFactory("vaalikones");
  	    em = emf.createEntityManager();

        try {
        	
        	VastauksetPK vastausPK = new VastauksetPK(a.getEhdokasid(),a.getKysymysid());
        	Vastaukset vastaus = em.find(Vastaukset.class, vastausPK);
        	
            em.getTransaction().begin();
    		vastaus.setVastaus(a.getVastaus());
    		vastaus.setKommentti(a.getKommentti());
            em.getTransaction().commit();
     		
     		
             
        } catch (Exception e) {
        	
        }
		//return "<p>" + "You changed answer from candidate " + candidateID + " to question nro " + questionID +  " to: " + answer + " and the comment to: " + comment + "</p>"
				//+ "<a href=\"http://localhost:8080/index.html\">Go back to start</a>";
		return a;
        

	}

}

