<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1><spring:message code="menu2"/></h1>
        <c:choose>
            <c:when test="${!areCorrectsFields}">
                <div class="error">
                    <p style="font-size: 125%"><spring:message code="badFields"/></p>
                    <c:forEach var="erreur" items="${erreurs}">
                        <p>- <spring:message code="${erreur}"/></p>
                    </c:forEach>
                </div>
            </c:when>
        </c:choose>
        <form:form class="registration"
                   method="POST"
                   action="/siteVente/userInscription/sendInscription"
                   modelAttribute="profilInscription"
        >
            <form:label path="name"><spring:message code="id"/>*</form:label>
            <br>
            <form:input path="name"/>
            <br>
            <form:label path="password"><spring:message code="password"/>*</form:label>
            <br>
            <form:input type="password" path="password"/>
            <br>
            <form:label path="firstName"><spring:message code="firstName"/>*</form:label>
            <br>
            <form:input path="firstName"/>
            <br>
            <form:label path="lastName"><spring:message code="lastName"/>*</form:label>
            <br>
            <form:input path="lastName"/>
            <br>
            <form:label path="mail">eMail*</form:label>
            <br>
            <form:input path="mail"/>
            <br>
            <form:label path="locality"><spring:message code="locality"/>*</form:label>
            <br>
            <form:select path="localityString">
                <form:options items="${localities}" itemValue="locality" itemLabel="toString"/>
            </form:select>
            <br>
            <form:label path="deliveryAddress"><spring:message code="deliveryAddress"/>*</form:label>
            <br>
            <form:input path="deliveryAddress"/>
            <br>
            <form:label path="billingAddress"><spring:message code="billingAddress"/></form:label>
            <br>
            <form:input path="billingAddress"/>
            <br>
            <form:label path="phoneNumber"><spring:message code="phoneNumber"/></form:label>
            <br>
            <form:input path="phoneNumber"/>
            <br>
            <form:button><spring:message code="register"/></form:button>
        </form:form>
    </body>
</html>