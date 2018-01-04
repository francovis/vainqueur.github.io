<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="product">
            <img src="<spring:url value='${imgProduct}'/>">
            <p>${productName}</p>
            <p>${productDescription}</p>
            <p>${productPrice}</p>
        </div>
        <form:form
            style="width:30%;opacity:1;"
            method="post"
            action="/siteVente/products/addToBasket"
            modelAttribute="productsToBasket">
            <form:input path="realPrice" type="hidden" value="${productPrice}"/>
            <form:label path="quantity"><spring:message code="quantity"/></form:label>
            <form:input path="quantity" type="number"/>
            <form:button><spring:message code="addToBasket"/></form:button>
        </form:form>
    </body>
</html>