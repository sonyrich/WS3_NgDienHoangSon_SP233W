package sonndh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonndh.registration.RegistrationDAO;
import sonndh.registration.RegistrationInsertError;

public class CreateNewAccountController extends HttpServlet {

    private final String LOGINPAGE = "login.html";
    private final String CREATENEWACCOUNT = "createNewAccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = CREATENEWACCOUNT;
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean bError = false;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String lastname = request.getParameter("txtLastname");

            if (username.trim().length() < 6 || username.trim().length() > 20) {
                bError = true;
                errors.setUsernameLengthErr("Username is required 6 - 20 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                bError = true;
                errors.setPasswordLengthErr("Password is required 6 - 20 chars");
            }
            if (!confirm.equals(password)) {
                bError = true;
                errors.setConfirmNotMatch("Password mismatch");
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 40) {
                bError = true;
                errors.setLastnameLengthErr("Lastname is required 2 - 40 chars");
            }
            if (bError) {
                request.setAttribute("INSERTERROR", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.insertRecord(username, password, lastname, false);

                if (result) {
                    url = LOGINPAGE;
                }
            }
        } catch (SQLException ex) {
            bError = true;
            errors.setUsernameIsExisted("Username is existed");
            request.setAttribute("INSERTERROR", errors);
        } catch (NamingException ex) {
            Logger.getLogger(NullController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
