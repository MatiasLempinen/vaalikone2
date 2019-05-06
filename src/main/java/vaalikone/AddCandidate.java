package vaalikone;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;

/**
 * Servlet implementation class SaveCandidate
 */
@WebServlet(
	    name = "AddCandidate",
	    urlPatterns = {"/AddCandidate"}
	)
public class AddCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String idstring = request.getParameter("candidateid");
		int idc = Integer.parseInt(idstring.trim());
				
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
		
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		} catch (Exception e) {
			response.getWriter().println("EMF + EM ei onnistu.");
		}
			
	
		try {
			Ehdokkaat ehdokas = new Ehdokkaat(idc);
			ehdokas.setEhdokasId(Integer.parseInt(request.getParameter("candidateid")));
			ehdokas.setSukunimi(request.getParameter("candidatelname"));
			ehdokas.setEtunimi(request.getParameter("candidatefname"));
			ehdokas.setPuolue(request.getParameter("party"));
			ehdokas.setKotipaikkakunta(request.getParameter("homecity"));
			ehdokas.setIka(Integer.parseInt(request.getParameter("age")));
			ehdokas.setMiksiEduskuntaan(request.getParameter("whytoparliament"));
			ehdokas.setMitaAsioitaHaluatEdistaa(request.getParameter("whatarethethingsyouwanttoaffect"));
			ehdokas.setAmmatti(request.getParameter("profession"));

			
			em.getTransaction().begin();
			em.persist(ehdokas);
			em.getTransaction().commit();
			em.close();
			
			response.getWriter().println("Candidate added to database!");
		} catch (Exception e) {
			response.getWriter().println();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
