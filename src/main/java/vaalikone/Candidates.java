 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;

import static java.lang.Integer.parseInt;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import persist.Answer;
import persist.Ehdokkaat;
import persist.Kysymykset;
import persist.Vastaukset;

/**
 *
 * @author Jonne
 */
public class Candidates extends HttpServlet {

	
    //hae java logger-instanssi
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kysymys_id;
        
        // get the ID and parse it
        String idstring = request.getParameter("candidateid");                
        int id = Integer.parseInt(idstring.trim());
       
        
        
        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");

        //jos käyttäjä-oliota ei löydy sessiosta, luodaan sinne sellainen
        if (usr == null) {
            usr = new Kayttaja();
            logger.log(Level.FINE, "Luotu uusi k�ytt�j�olio");
            session.setAttribute("usrobj", usr);
        }
        

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

        
        // search the database for a candidate with given id 
        // and set his names to attributes so we can print them in jsp
        try {
        	Ehdokkaat ehdokas = em.find(Ehdokkaat.class, id); 
            
            request.setAttribute("ehdokasFirstName", ehdokas.getEtunimi());
            request.setAttribute("ehdokasLastName", ehdokas.getSukunimi());
            request.setAttribute("candidateid", ehdokas.getEhdokasId());
               
        } catch (Exception e) {
        	request.setAttribute("Error", e);
        }


        try {
        	
        // new entity            
 		Answer answer = new Answer();
 		 		
        // get the values for the "answer" entity
        int questionID = parseInt(request.getParameter("q"));
        int answer1 = parseInt(request.getParameter("vastaus"));
        String comment = request.getParameter("comment");
                
 		// set the values for this entity     		
		answer.setEhdokasid(id);
		answer.setKysymysid(questionID);
		answer.setVastaus(answer1);
		answer.setKommentti(comment);
        
        // save this entity to database
        em.getTransaction().begin();
        em.persist(answer);
        em.getTransaction().commit();
        
        } catch (Exception e) {
        	request.setAttribute("Error", e);
        }
        
        //hae url-parametri func joka määrittää toiminnon mitä halutaan tehdä.
        //func=haeEhdokas: hae tietyn ehdokkaan tiedot ja vertaile niitä käyttäjän vastauksiin
        //Jos ei määritelty, esitetään kysymyksiä.
        String strFunc = request.getParameter("func");

        if (strFunc == null) {

            //hae parametrinä tuotu edellisen kysymyksen nro
            String strKysymys_id = request.getParameter("q");

            //hae parametrina tuotu edellisen kysymyksen vastaus
            String strVastaus = request.getParameter("vastaus");

            // Jos kysymyksen numero (kysId) on asetettu, haetaan tuo kysymys
            // muuten haetaan kysnro 1
            if (strKysymys_id == null) {
                kysymys_id = 1;
            } else {
                kysymys_id = parseInt(strKysymys_id);
                //jos vastaus on asetettu, tallenna se session käyttäjä-olioon
                if (strVastaus != null) {
                    usr.addVastaus(kysymys_id, parseInt(strVastaus));
                }

                //määritä seuraavaksi haettava kysymys
                kysymys_id++;
            }

            //jos kysymyksiä on vielä jäljellä, hae seuraava
            if (kysymys_id < 20) {
                try {
                    //Hae haluttu kysymys tietokannasta
                    Query q = em.createQuery(
                            "SELECT k FROM Kysymykset k WHERE k.kysymysId=?1");
                    q.setParameter(1, kysymys_id);
                    //Lue haluttu kysymys listaan
                    List<Kysymykset> kysymysList = q.getResultList();
                    request.setAttribute("kysymykset", kysymysList);
                    request.getRequestDispatcher("/candidate.jsp")
                            .forward(request, response);

                } finally {
                    // Sulje tietokantayhteys
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    em.close();
                }

                //jos kysymykset loppuvat, lasketaan tulos!
            } else {
                request.getRequestDispatcher("/Answers.jsp")
                .forward(request, response);
            }
            
        }

    }

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
