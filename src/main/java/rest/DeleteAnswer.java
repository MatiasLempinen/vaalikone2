package rest;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;


import persist.Vastaukset;
import persist.VastauksetPK;

 
@Path("/deleteanswer")
public class DeleteAnswer {
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public VastauksetPK poistaVastaus(VastauksetPK v)					
			{                
      
        EntityManagerFactory emf=null;
        EntityManager em = null;
  	    emf=Persistence.createEntityManagerFactory("vaalikones");
  	    em = emf.createEntityManager();

  	  try {
        	
        	VastauksetPK vastausPK = new VastauksetPK(v.getEhdokasId(),v.getKysymysId());
        	Vastaukset vastaus = em.find(Vastaukset.class, vastausPK);
        	
        	em.getTransaction().begin();
     		em.remove(vastaus);
     		em.getTransaction().commit();
     		em.close();
     		
	   } catch (Exception e) {
	        	
	   }
             

		return v;
		
		

	}
	
	
}
