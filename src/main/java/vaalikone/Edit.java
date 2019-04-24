package vaalikone;

import static java.lang.Integer.parseInt;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Answer;
import persist.Vastaukset;
import persist.VastauksetPK;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    response.setContentType("text/HTML");
	    response.setCharacterEncoding("UTF-8");
	    
		//entity manager        
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	response.getWriter().println("EMF+EM EI Onnistu");
          	
          	e.printStackTrace(response.getWriter());
          	
          	return;
        }
        
        try {
        			 		
        // get the values for the "answer" entity
        int questionID = parseInt(request.getParameter("q"));
        int answer1 = parseInt(request.getParameter("answer"));
        int candidateID = parseInt(request.getParameter("candidateid"));
        String comment = request.getParameter("comment");
                    		
        // new entity            
        VastauksetPK answerPK = new VastauksetPK(candidateID,questionID);
        Vastaukset answer = em.find(Vastaukset.class, answerPK);
        
        // update values to database
        em.getTransaction().begin();
		answer.setVastaus(answer1);
		answer.setKommentti(comment);
        em.getTransaction().commit();
        
        response.getWriter().println("You changed answer from candidate " + candidateID + " to question nro " + questionID +  " to: " + answer1 + " and the comment to: " + comment);
        response.getWriter().println("<li><a class=\"active\" href=\"index.html\">Go back to start</a></li>");
        
        } catch (Exception e) {
        	response.getWriter().println(e);
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
