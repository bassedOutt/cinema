<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cinema</title>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<nav class="flex items-center justify-end bg-blue-400 flex-wrap p-6">
    <div class="w-full block flex-grow lg:flex lg:items-center lg:w-auto">
        </div>
        <div>
            <c:choose>
                <c:when test="${sessionScope.user!= null}">
                    <a href="/epam_cinema/sign_out" class="inline-block text-sm px-4 py-2 leading-none border rounded text-black border-white hover:border-transparent hover:text-teal hover:bg-white mt-4 lg:mt-0">Sign out</a>
                </c:when>
                <c:otherwise>
                    <a href="/epam_cinema/login.jsp" class="inline-block text-sm px-4 py-2 border rounded text-black border-black hover:border-transparent hover:bg-white mt-4 lg:mt-0">Sign in</a>
                    <a href="/epam_cinema/sign_up.jsp" class="inline-block text-sm px-4 py-2 border rounded text-black border-black hover:border-transparent hover:bg-white mt-4 lg:mt-0">Sign up</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<c:if test="${sessionScope.user.isAdmin() == true}">
    <div><a href = "epam_cinema/add_movie">Add a movie</a></div>
</c:if>

</body>
</html>
