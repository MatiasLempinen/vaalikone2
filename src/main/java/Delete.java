

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
		
      
        
        
		String idstring = request.getParameter("candidateidd");                
        int idc = Integer.parseInt(idstring.trim());
        
        String idqstring = request.getParameter("questionidd");                
        int idq = Integer.parseInt(idqstring.trim());
        
        
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
        	
        	VastauksetPK answerPK = new VastauksetPK(idc,idq);
        	Vastaukset answer = em.find(Vastaukset.class, answerPK);
        	
        	em.getTransaction().begin();
     		em.remove(answer);
     		em.getTransaction().commit();
     		em.close();
     		
             
        } catch (Exception e) {
        	response.getWriter().println();
        }
        
        out.println("The candidate " + idc + " and question " + idq + " has been deleted");
        out.println("<br><br><a href=\"index.html\">Go Back</a>");
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
