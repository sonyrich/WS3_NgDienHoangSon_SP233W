package sonndh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonndh.registration.RegistrationDAO;

public class UpdateController extends HttpServlet {

    private final String ERRORPAGE = "updateError.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = ERRORPAGE;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String searchValue = request.getParameter("lastSearchValue");
            String isAdmin = request.getParameter("ADMIN");
            boolean role = false;

            if (isAdmin != null) {
                role = true;
            }

            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateRecord(username, password, role);

            if (result) {
                url = "MainController?btAction=Search&txtSearchValue="
                        + searchValue;
            }

            //test output
            //out.println(username + " " + password + " " + searchValue + " " + role);
        } catch (SQLException ex) {
            Logger.getLogger(NullController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(NullController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
