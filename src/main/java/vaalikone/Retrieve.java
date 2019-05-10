package vaalikone;

import static java.lang.Integer.parseInt;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Kysymykset;
import persist.Vastaukset;
import persist.VastauksetPK;

/**
 * Servlet implementation class Retrieve
 */
@WebServlet("/Retrieve")
public class Retrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Retrieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idstring = request.getParameter("candidateid");                
        int id = Integer.parseInt(idstring.trim());
        
        String idstring1 = request.getParameter("questionid");                
        int id1 = Integer.parseInt(idstring1.trim());
        
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
        	
            //"question" entity
        	int questionid = parseInt(request.getParameter("questionid"));
        	Kysymykset kysymys = em.find(Kysymykset.class, questionid); 
        	
        	
        	//new entity for answer
        	VastauksetPK answerPK = new VastauksetPK(id,id1);
        	Vastaukset answer = em.find(Vastaukset.class, answerPK);
        	
        	//get stuff from DB and set it to attributes so we can print them in .jsp
        	request.setAttribute("answer", answer.getVastaus());
        	request.setAttribute("comment", answer.getKommentti());
        	request.setAttribute("q", request.getParameter("questionid"));
        	request.setAttribute("candidateid", id);
        	request.setAttribute("question", kysymys.getKysymys());
        	//forward to .jsp
        	String nextJSP = "/Retrieve.jsp";
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        	dispatcher.forward(request,response);

       	

    		
            
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
