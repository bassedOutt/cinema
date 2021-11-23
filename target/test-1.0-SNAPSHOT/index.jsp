<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cinema</title>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="req" value="${pageContext.request}" />
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%--<nav class="flex items-center justify-end bg-blue-400 flex-wrap p-6">--%>
<%--        <div>--%>
<%--            <a href="${req.contextPath}" class="inline-block text-md px-4 py-2 leading-none text-black border-white hover:border-transparent hover:text-teal mt-4 lg:mt-0">Cinema App</a>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${sessionScope.user!= null}">--%>
<%--                    <a href="${req.contextPath}/logout" class="inline-block text-sm px-4 py-2 leading-none border rounded text-black border-white hover:border-transparent hover:text-teal hover:bg-white mt-4 lg:mt-0">Sign out</a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <a href="${req.contextPath}/login.jsp" class="inline-block text-sm px-4 py-2 border rounded text-black border-black hover:border-transparent hover:bg-white mt-4 lg:mt-0">Sign in</a>--%>
<%--                    <a href="${req.contextPath}/sign_up.jsp" class="inline-block text-sm px-4 py-2 border rounded text-black border-black hover:border-transparent hover:bg-white mt-4 lg:mt-0">Sign up</a>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<div>
    <ul>
        <c:forEach var="session" items="${sessions}">
            <li class="px-4 py-2"><c:out value="${session.getMovie().getTitle()}" /></li>
        </c:forEach>
        <c:if test="${sessionScope.user.isAdmin() == true}">
            <li class="px-4 py-2"><a class="nderline text-blue-600 hover:text-blue-800 visited:text-purple-600" href = "epam_cinema/add_movie">Add a movie</a></li>
        </c:if>
    </ul>
</div>

</body>
</html>
