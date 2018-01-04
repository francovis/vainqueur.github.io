<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    </head>
    <body>
            <c:forEach var="category" items="${categories}">
                <form:form
                        class="buttonCategory"
                        method="post"
                        action="/siteVente/catalog/choosenCategory"
                        modelAttribute="categoryRedirection">
                    <form:input path="id" type="hidden" value="${category.category.id}"/>
                    <form:button><c:out value="${category.name}"/></form:button>
                </form:form>
            </c:forEach>
    </body>
</html>