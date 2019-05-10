package rest;

import static java.lang.Integer.parseInt;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.GenericServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persist.Ehdokkaat;
import persist.Vastaukset;

@WebServlet("/DeleteCandidate")
@Path("/deletecandidate")
public class DeleteCandidate extends HttpServlet {
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Ehdokkaat delete(Ehdokkaat ehdokas// @PathParam("candidateidd") String candidateid, 
			//@QueryParam("candidateidd") int candidateidd 
			) 
	{
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		try {
			Ehdokkaat ehdokasPK = new Ehdokkaat(ehdokas.getEhdokasId());
			ehdokas.setEhdokasId(ehdokas.getEhdokasId());
			ehdokas.setSukunimi(ehdokas.getSukunimi());
			System.out.println(ehdokas);
		//	ehdokasPK.setEhdokasId(request);
			Ehdokkaat ehdokas1 = em.find(Ehdokkaat.class, ehdokas.getEhdokasId());
			System.out.println(ehdokas.getEhdokasId());
			
			em.getTransaction().begin();
			em.remove(ehdokas1);
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {
			//return ehdokas;
		}
		
		listaaEhdokkaat();
		
		return ehdokas;
		
	}
	
	public List<Ehdokkaat> listaaEhdokkaat() {
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("vaalikones");
		EntityManager em1 = emf1.createEntityManager();
		Query q = em1.createQuery("select a from Ehdokkaat a");
		
		List<Ehdokkaat> lista = q.getResultList();
		
		System.out.println(lista);
		
		return lista;
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//forward to .jsp
	
	EntityManagerFactory emf = null;
	EntityManager em = null;
	emf = Persistence.createEntityManagerFactory("vaalikones");
	em = emf.createEntityManager();
		
	String idstring = request.getParameter("candidateid");
	int id = Integer.parseInt(idstring.trim());
	
	int ehdokasid = parseInt(request.getParameter("candidateid"));
	Ehdokkaat ehdokas = em.find(Ehdokkaat.class, ehdokasid);
	
	Ehdokkaat ehdokas1 = new Ehdokkaat(id);
	ehdokas1.setEhdokasId(Integer.parseInt(request.getParameter("candidateid")));
	ehdokas1.setSukunimi(request.getParameter("candidatelname"));
	ehdokas1.setEtunimi(request.getParameter("candidatefname"));
	ehdokas1.setPuolue(request.getParameter("party"));
	ehdokas1.setKotipaikkakunta(request.getParameter("homecity"));
	ehdokas1.setIka(Integer.parseInt(request.getParameter("age")));
	ehdokas1.setMiksiEduskuntaan(request.getParameter("whytoparliament"));
	ehdokas1.setMitaAsioitaHaluatEdistaa(request.getParameter("whatarethethingsyouwanttoaffect"));
	ehdokas.setAmmatti(request.getParameter("profession"));
	System.out.println(ehdokas1);
	
	request.setAttribute("candidateid", id);
	request.setAttribute("candidateid", ehdokas);
		
	String nextJSP = "/DeleteCandidate.jsp";
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	dispatcher.forward(request, response);
	
	PrintWriter out = response.getWriter();
	
	Query q = em.createQuery("select a from Ehdokkaat a");
	
	List<Ehdokkaat> lista = q.getResultList();
	
	System.out.println(lista);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


