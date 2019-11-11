<%-- 
    Document   : cart
    Created on : Oct 30, 2019, 8:48:12 AM
    Author     : nkt19
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Murach's Java Servlets and JSP</title>
    </head>
    <body>

        <h1>Your cart</h1>

        <table border="1" cellpadding="5">
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>

            <c:forEach var="item" items="${cart.items}">

                <tr valign="top">
                    <td>
                        <form action="<c:url value='/cart' />">
                            <input type="hidden" name="productCode" 
                                   value="${item.product.code}">
                            <input type=text size=2 name="quantity" 
                                   value="${item.quantity}">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>${item.product.description}</td>
                    <td>${item.product.priceCurrencyFormat}</td>
                    <td>${item.totalCurrencyFormat}</td>
                    <td>
                        <form action="<c:url value='/cart' />">
                            <input type="hidden" name="productCode" 
                                   value="${item.product.code}">
                            <input type="hidden" name="quantity" 
                                   value="0">
                            <input type="submit" value="Remove Item">
                        </form>
                    </td>
                </tr>

            </c:forEach>

            <tr>
                <td colspan="3">
                    <p><b>To change the quantity</b>, enter the new 
                        quantity and click on the Update button.</p>
                </td>
            </tr>

        </table>
        <br>

        <form action="<c:url value='/index.jsp' />" method="POST">
            <input type="submit" value="Continue Shopping">
        </form>

        <form action="<c:url value='/checkout.jsp' />" 
              method="POST">
            <input type="submit" value="Checkout">
        </form>

    </body>

</html>
