<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:choose>
            <c:when test="${isWrongID}">
                <h1><spring:message code="connection"/></h1>
            </c:when>
            <c:otherwise>
                <h1><spring:message code="wrongID"/></h1>
            </c:otherwise>
        </c:choose>
        <form:form class="registration"
                   method="POST"
                   action="/siteVente/userConnection/sendConnection"
                   modelAttribute="profilConnection"
        >
            <form:label path="name"><spring:message code="id"/>*</form:label>
            <br>
            <form:input path="name"/>
            <br>
            <form:label path="password"><spring:message code="password"/>*</form:label>
            <br>
            <form:input type="password" path="password"/>
            <br>
            <form:button><spring:message code="logIn"/></form:button>
        </form:form>
    </body>
</html>