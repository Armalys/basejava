<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Contacts:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.name()}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>

        <h3>Sections: </h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <dl>
                <dt>${type.name()}</dt>

                <dd><input type="text" name="${type.name()}" size="100" value="${resume.getSection(type)}"></dd>

                <%--<c:choose>--%>
                    <%--<c:when test="${type.equals(SectionType.OBJECTIVE) || type.equals(SectionType.PERSONAL)}">--%>
                        <%--<dd><input type="text" name="${type.name()}" size="100" value="${resume.getSection(type)}"></dd>--%>
                    <%--</c:when>--%>

                    <%--<c:when test="${type.equals(SectionType.ACHIEVEMENT) || type.equals(SectionType.QUALIFICATIONS)}">--%>
                        <%--<c:forEach var="item" items="${resume.getSection(type)}">--%>
                                <%--<dd><input type="text" size="30" value="${item}"></dd>--%>
                        <%--</c:forEach>--%>
                    <%--</c:when>--%>
                <%--</c:choose>--%>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>