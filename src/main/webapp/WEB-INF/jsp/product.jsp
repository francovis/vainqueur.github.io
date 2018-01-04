<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:forEach var="product" items="${products}">
            <form:form
                    class="buttonCategory"
                    method="post"
                    action="/siteVente/products/choosenProduct"
                    modelAttribute="productRedirection">
            <div class="product">
                <form:input path="id" value="${product.id}" type="hidden"/>
                <form:button><img src="<spring:url value='${product.product.image}'/>"></form:button>
                <p> <c:out value="${product.name}"/> </p>
                <p> <c:out value="${product.product.price}"/> </p>
            </div>
            </form:form>
        </c:forEach>
    </body>
</html>