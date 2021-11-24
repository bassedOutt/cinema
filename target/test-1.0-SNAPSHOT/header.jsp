<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 23.11.2021
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Header</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="req" value="${pageContext.request}" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<nav class="flex items-center justify-between flex-wrap bg-blue-300 p-6">
    <div class="flex items-center flex-shrink-0 text-white mr-6">
        <a href="${req.contextPath}/index" class="inline-block text-md px-4 leading-none text-2xl text-black border-white hover:border-transparent hover:text-teal mt-4 lg:mt-0">CinemaApp</a>
    </div>
    <div class="w-full block flex-grow lg:flex lg:items-center lg:w-auto">
        <div class="text-sm lg:flex-grow">
            <a href="#responsive-header" class="block mt-4 lg:inline-block pl-6  text-xl lg:mt-0 hover:text-white mr-4">Movies</a>
            <a href="#responsive-header" class="block mt-4 lg:inline-block pl-4  text-xl lg:mt-0 hover:text-white mr-4">My Tickets</a>
            <select class="bg-transparent text-xl h-12 pl-4 hover:bg-blue-100" id ="language">
                <option value="eng">ENG</option>
                <option value="ua">UA</option>
            </select>
        </div>
        <div>
            <c:if test="${sessionScope.user!=null}">
                    <a href="${req.contextPath}/logout" class="inline-block text-sm px-4 py-2 leading-none border rounded border-white hover:border-transparent hover:text-white mt-4 lg:mt-0">Logout</a>
            </c:if>
            <c:if test="${sessionScope.user==null}">
                <a href="${req.contextPath}/login.jsp" class="inline-block text-sm px-4 py-2 leading-none border rounded border-white hover:border-transparent hover:text-white mt-4 lg:mt-0">Log in</a>
                <a href="${req.contextPath}/sign_up.jsp" class="inline-block text-sm px-4 py-2 leading-none border rounded border-white hover:border-transparent hover:text-white mt-4 lg:mt-0">Sign up</a>
            </c:if>

        </div>
    </div>
</nav>

</body>
</html>
