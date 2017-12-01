<%@ include file="../include/importTags.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-esquiv="Content-Type" content="type=text/html; charset=utf-8">
    <link rel="shortcut icon" href="<spring:url value='/images/localeEn.jpg'/>" type="image/jpg" />
    <title>${title}</title>
    <spring:url var="localeFr" value="">
        <spring:param name="locale" value="fr"/>
    </spring:url>
    <spring:url var="localeEn" value="">
        <spring:param name="locale" value="en"/>
    </spring:url>
    <spring:message code="${firstMenu}" var="menu2"/>
    <spring:message code="menu3" var="menu3" />
    <spring:message code="menu4" var="menu4" />
    <spring:message code="menu5" var="menu5" />
</head>
<body background="<spring:url value='/images/prairies.jpeg'/>">
<header>
    <a href="/siteVente"><p>ISHISH</p></a>
</header>
<div id="scrollWait"></div>
<div id="underHeader">
    <form class="menu"
        method="POST"
        action="/siteVente/menu">
        <input class="underHeaderP" type="submit" name="actionMenu" value="${menu2}"/>
        <input class="underHeaderP" type="submit" name="actionMenu" value="${menu3}"/>
        <input class="underHeaderP" type="submit" name="actionMenu" value="${menu4}"/>
        <input class="underHeaderP" type="submit" name="actionMenu" value="${menu5}"/>
    </form>
    <button id="loupe"><img src='<spring:url value="/images/loupe.png"/>'/></button>
</div>
<p id="flag">
    <a href="${localeFr}"><img src='<spring:url value="/images/frFlag.png"/>'/></a>
    <a href="${localeEn}"><img src='<spring:url value="/images/enFlag.png"/>'/></a>
</p>
<div>
    <tiles:insertAttribute name = "main-content"/>

    <link type="text/css" href="<spring:url value='/css/general.css'/>" rel="Stylesheet">
    <script src="<spring:url value='/javaScript/cssTransformation.js'/>"></script>
</div>
<footer>
    <spring:message code="footer"/>
</footer>
</body>
</html>