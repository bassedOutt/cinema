<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cinema</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="req" value="${pageContext.request}"/>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <%--    <%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>--%>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="flex">
    <div class="grid grid-cols-4 gap-2 p-5 w-full">
        <c:forEach var="movie" items="${movies}">
            <div class="px-5 py-5 flex align-middle text-center items-center place-content-center hover:bg-gray-100 border-4 border-light-blue-500 border-opacity-25">
                <div class = "flex flex-col align-middle items-center">
                    <div class="m-2 font-medium text-lg ">${movie.getTitle()}</div>
                    <img src="${movie.getImageUrl()}" class="m-2 w-52 h-52">
                    <div class="p-2 m-2 w-4/6 bg-blue-100 border-rounded-md word-break">${movie.getDescription()}</div>
                    <div class="p-2 m-2 ">
                        <form method="get" action="${req.contextPath}/get_session">
                            <input type="hidden" name="movie_id" value="${movie.getId()}">
                            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                                View sessions
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
