<%-- 
    Document   : search
    Created on : Apr 15, 2023, 9:12:58 AM
    Author     : se140372
--%>

<%@page import="java.util.List"%>
<%@page import="sonndh.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search JSP Page</title>
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "";
                for (Cookie cookie : cookies) {
                    String temp = cookie.getName();
                    if (!temp.equals("JSESSIONID")) {
                        username = temp;
                    }
                }
        %>
        <font color="red">Welcome, <%=username%></font>
        <%
            }
        %>
        
        <h1>Search Page</h1>
        <form action="MainController">
            Search Value
            <input type="text" name="txtSearchValue" value="" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "MainController"
                                + "?btAction=Del"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + request.getParameter("txtSearchValue");
                %>
            <form action="MainController">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="ADMIN" value="ON" <% if (dto.isRole()) {%>
                               checked="checked"
                               <% }%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" value="<%= request.getParameter("txtSearchValue")%>" />
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>

    <%
    } else {//else if
    %>
    <h2>No Record is matched</h2>
    <%
            }
        }//end search value

    %>
</body>
</html>
