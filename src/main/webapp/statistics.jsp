<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cinema</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="req" value="${pageContext.request}"/>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<body>
<div class=" font-sans text-lg bg-gray-100 my-10 flex flex-col w-1/2 m-auto">
    <h2 class=" font-semibold text-2xl mt-5 mb-1 mx-auto">Tickets sold to each movie:</h2>
    <c:forEach var="item" items="${map}">
        <span class="mx-5 my-1">${item.getKey()}: ${item.getValue()}</span>
    </c:forEach>
    <div class="mt-5"></div>
</div>
</body>
</html>
