<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="profil"/></h1>
<form:form class="registration"
           method="POST"
           action="/siteVente/userInscription/sendUpDate"
           modelAttribute="upDateProfil"
>
    <form:label path="name"><spring:message code="id"/></form:label>
    <br>
    <form:input path="name" value="${name}"/>
    <br>
    <form:label path="password"><spring:message code="old"/><spring:message code="password"/></form:label>
    <br>
    <input type="password"/>
    <br>
    <form:label path="password"><spring:message code="new"/><spring:message code="password"/></form:label>
    <br>
    <form:input type="password" path="password"/>
    <br>
    <form:button><spring:message code="saveProfil"/></form:button>
</form:form>
<div class="vide"></div>
</body>
</html>