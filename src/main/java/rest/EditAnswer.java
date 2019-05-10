package rest;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

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
		return a;
        

	}

}

