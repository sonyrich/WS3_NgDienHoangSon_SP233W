<%-- 
    Document   : CreateNewAccount
    Created on : Apr 20, 2023, 7:29:08 AM
    Author     : se140372
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sonndh.registration.RegistrationInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register Page</h1>
        <form action="MainController">
            Username:
            <input type="text" name="txtUsername" value="<%= request.getParameter("txtUsername")%>" />
            (6-20 chars)<br/>
            <font color="red">
            <%
                RegistrationInsertError errors
                        = (RegistrationInsertError) request.getAttribute("INSERTERROR");
                if (errors != null) {
                    if (errors.getUsernameLengthErr() != null) {
            %>
            <%= errors.getUsernameLengthErr()%><br/>
            <%
                    }
                }//end if errors
            %>
            </font>
            <c:set var="errors" value="${requestScope.INSERTERROR}"/>

            Password:
            <input type="password" name="txtPassword" value="" />
            (6-30 chars)<br/>
            <font color="red">
            <%
                if (errors != null) {
                    if (errors.getPasswordLengthErr() != null) {
            %>
            <%= errors.getPasswordLengthErr()%><br/>
            <%
                    }
                }//end if errors
            %>
            </font>
            Confirm:
            <input type="password" name="txtConfirm" value="" />
            (6-30 chars)<br/>
            <font color="red">
            <%
                if (errors != null) {
                    if (errors.getConfirmNotMatch() != null) {
            %>
            <%= errors.getConfirmNotMatch()%><br/>
            <%
                    }
                }//end if errors
%>
            </font>
            Lastname:
            <input type="text" name="txtLastname" value="<%= request.getParameter("txtLastname")%>" />
            (2-40 chars)<br/>
            <font color="red">
            <%
                if (errors != null) {
                    if (errors.getLastnameLengthErr() != null) {
            %>
            <%= errors.getLastnameLengthErr()%><br/>
            <%
                    }
                }//end if errors
            %>
            </font>
            <input type="submit" value="Register" name="btAction" />
            <input type="reset" value="Reset"/>
        </form>
        <font color="red">
        <%
            if (errors != null) {
                if (errors.getUsernameIsExisted() != null) {
        %>
        <%= errors.getUsernameIsExisted()%><br/>
        <%
                }
            }//end if errors
%>
        </font>
    </body>
</html>
