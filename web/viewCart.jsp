<%-- 
    Document   : viewCart
    Created on : Apr 18, 2023, 9:49:10 AM
    Author     : se140372
--%>

<%@page import="java.util.Map"%>
<%@page import="sonndh.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your current Cart</title>
    </head>
    <body>
        <h1>Your current Cart</h1>
        <%
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getItems() != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <form action="MainController">
                <%
                    Map<String, Integer> items = cart.getItems();
                    int count = 0;
                    for (Map.Entry item : items.entrySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= item.getKey()%>
                    </td>
                    <td>
                        <%= item.getValue()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkItem" value="<%= item.getKey()%>" /> 
                    </td>
                </tr>
                <%
                    }//end for
                %>
                <tr>
                    <td colspan="3">
                        <a href="bookStore.html">Back To Shopping</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove from Cart" name="btAction"/>
                    </td>
                </tr>
            </form>
        </tbody>
    </table>
    <%
                    return;
                }
            }
        }
    %>
    <h2>Cart have not items!</h2>
    <a href="bookStore.html">Back To Shopping</a>
</body>
</html>
