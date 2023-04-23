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
            <c:set var="errors" value="${requestScope.INSERTERROR}"/>
            <c:if test="${ not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>

            Password:
            <input type="password" name="txtPassword" value="" />
            (6-30 chars)<br/>
            <c:if test="${ not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>

            Confirm:
            <input type="password" name="txtConfirm" value="" />
            (6-30 chars)<br/>
            <c:if test="${ not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br/>
            </c:if>

            Lastname:
            <input type="text" name="txtLastname" value="<%= request.getParameter("txtLastname")%>" />
            (2-40 chars)<br/>
            <c:if test="${ not empty errors.lastnameLengthErr}">
                <font color="red">
                ${errors.lastnameLengthErr}
                </font><br/>
            </c:if>

            <input type="submit" value="Register" name="btAction" />
            <input type="reset" value="Reset"/>
        </form>
        <c:if test="${ not empty errors.usernameIsExisted}">
            <font color="red">
            ${errors.usernameIsExisted}
            </font><br/>
        </c:if>
    </body>
</html>
