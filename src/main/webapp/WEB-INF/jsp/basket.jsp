<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:set var="total" value="0" scope="page" />
        <table class="panier">
            <c:forEach var="orderDetail" items="${basket}">
                <tr>
                     <form:form
                             method="post"
                             action="/siteVente/basket/updateBasket"
                             modelAttribute="changeBasket">
                         <form:input path="product.id" type="hidden" value="${orderDetail.value.product.id}"/>
                         <td>${orderDetail.key}</td>
                         <td><spring:message code="quantity"/>${orderDetail.value.quantity}</td>
                         <td><spring:message code="price"/>${orderDetail.value.realPrice}€</td>
                         <td><spring:message code="total"/>${orderDetail.value.tot}€</td>
                         <td><form:button><spring:message code="update"/></form:button></td>
                         <c:set var="total" value="${total + orderDetail.value.realPrice*orderDetail.value.quantity}" scope="page"/>
                     </form:form>
                </tr>
            </c:forEach>
        </table>
        <c:set var="log" value="${isLogged}" scope="page"/>
        <c:choose>
            <c:when test="${log}">
                <c:set var="formAction" value="https://www.sandbox.paypal.com/cgi-bin/webscr" scope="page"/>
            </c:when>
            <c:otherwise>
                <c:set var="formAction" value="/siteVente/basket/connection" scope="page"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${reduction && total>=20}">
                <c:set var="total" value="${total-10}" scope="page"/>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${total != 0}">
                <form
                            id="formAchat"
                            method="post"
                            action="${formAction}">
                    <input type="hidden" name="cmd" value="_xclick">
                    <input type="hidden" name="return" value="http://localhost:8083/siteVente/basket/buy">
                    <input type="hidden" name="business" value="business.vainqueur@gmail.com">
                    <input type="hidden" name="amount" value="${total}">
                    <input type="hidden" name="cert_id" value="AdNtR5CeUZlHnTwH_yYjm9CYveW2zSjNELNIRGIss743_E1TxT9I8Y0VgTDH9_dcpL0D53ayhqPX1572">
                    <input type="hidden" name="password" value="ENBWc4HBApXxguPg726JRZ-uw1bjZ2jQBO1RJLMVmNyE-zh9eJYo9hqUT8d0T6npkr8_Ofqq9zqTxF57">
                    <input type="hidden" name="currency_code" value="EUR">
                    <p>
                        <spring:message code="totalSum"/>
                        <fmt:formatNumber type = "number" value = "${total}" />€
                        <button><spring:message code="buy"/></button>
                    </p>
                </form>
            </c:when>
        </c:choose>
        <div class="vide"></div>
    </body>
</html>