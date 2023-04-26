<%-- 
    Document   : search
    Created on : Apr 15, 2023, 9:12:58 AM
    Author     : se140372
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:set var="txtSearchValue" value="${param.txtSearchValue}"/>
    <c:if test="${not empty searchValue}">
        <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
        <c:if test="${not empty result}">
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
                <c:forEach var="dto" items="${result}" varStatus="counter" >
                    <form action="MainController">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>/
                                ${dto.lastname}
                            </td>
                            <td>
                                <input type="checkbox" name="ADMIN" value="ON" 
                                <c:if test="${dto.role}">checked="checked"</c:if>/>
                            </td>
                            <td>
                            <c:url var="urlRewriting" value="delLink">
                                <c:param name="btAction" value="Del"/>
                                <c:param name="pk" value="${dto.username}"/>
                                <c:param name="lastSearchValue" value="${searchValue}"/>
                            </c:url>
                            <a href="${delLink}">Delete</a>
                            </td>
                            <td>
                                <input type="hidden" name="txtSearchValue" value="${name}" />
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="lastSearchValue" value="" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty info}">
            <h2>No Record is matched</h2>
        </c:if>
    </c:if>
</body>
</html>
