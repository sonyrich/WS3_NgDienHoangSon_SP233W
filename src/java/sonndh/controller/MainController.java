package sonndh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private final String LOGINPAGE = "login.html";
    private final String LOGINCONTROLLER = "LoginController";
    private final String CREATENEWACCOUNT = "CreateNewAccountController";
    private final String NULLCONTROLLER = "NullController";
    private final String SEARCHCONTROLLER = "SearchController";
    private final String DELETECONTROLLER = "DeleteController";
    private final String UPDATECONTROLLER = "UpdateController";
    private final String ADDITEMTOCART = "AddItemController";
    private final String VIEWCARTPAGE = "viewCart.jsp";
    private final String REMOVEITEMFORMCART = "RemoveItemController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String button = request.getParameter("btAction");
        String url = LOGINPAGE;

        try {
            if (button == null) {
                url = NULLCONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGINCONTROLLER;
            } else if (button.equals("Register")) {
                url = CREATENEWACCOUNT;
            } else if (button.equals("Search")) {
                url = SEARCHCONTROLLER;
            } else if (button.equals("Del")) {
                url = DELETECONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATECONTROLLER;
            } else if (button.equals("Add to Cart")) {
                url = ADDITEMTOCART;
            } else if (button.equals("View Cart")) {
                url = VIEWCARTPAGE;
            } else if (button.equals("Remove from Cart")) {
                url = REMOVEITEMFORMCART;
            }
        } finally {
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
