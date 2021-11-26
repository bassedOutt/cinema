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
<nav class="flex items-center justify-between flex-wrap bg-blue-300 p-3">
    <div class="flex items-center text-white mr-6">
        <a href="${req.contextPath}/index" class="inline-block text-md px-4 leading-none text-2xl text-black border-white hover:border-transparent hover:text-teal lg:mt-0">CinemaApp</a>
    </div>
    <div class="w-full items-center flex-grow flex lg:w-auto">
        <div class="lg:flex-grow flex items-center">
            <a href="${req.contextPath}/movies" class="block lg:inline-block pl-6 text-xl hover:text-white mr-4">Movies</a>
            <a href="#responsive-header" class="block lg:inline-block pl-4  text-xl  hover:text-white mr-4">My Tickets</a>
            <div class="pl-2 flex mt-4 items-center text-lg">
                <form method="get" action="${req.contextPath}/changelang">
                    <select class="bg-transparent h-12 pl-4 mr-5 hover:bg-blue-100" name="language" id ="language">
                        <option value="en">eng</option>
                        <option value="ua">ua</option>
                    </select>
                    <button class="hover:text-white" type="submit">Submit</button>
                </form>
            </div>
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
