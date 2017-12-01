<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="welcomeMessage"/></h1>
<div class="floatLeft">
    <c:choose>
    <c:when test="${test}">
    <form:form id="logIn"
               method="POST"
               action="/siteVente/userConnection/sendConnection"
               modelAttribute="profilConnection"
    >
        <form:label path="name"><spring:message code="id"/></form:label>
        <br>
        <form:input path="name"/>
        <br>
        <form:label path="password"><spring:message code="password"/></form:label>
        <br>
        <form:input type="password" path="password"/>
        <br>
        <form:button><spring:message code="logIn"/></form:button>
    </form:form>
    </c:when>
    <c:otherwise>
        <form id="logOut"
                   method="POST"
                   action="/siteVente/disconnection"
        >
        <button><spring:message code="logOut"/></button>
        </form>
    </c:otherwise>
    </c:choose>
    <div class="divLeft">
        <p><spring:message code="leftLayout"/></p>
    </div>
</div>
<div class="divRight">
    <p><spring:message code="rightLayout"/></p>
</div>
<div class="centre"><p><spring:message code="welcomeMainImg"/></p></div>
</body>
</html>