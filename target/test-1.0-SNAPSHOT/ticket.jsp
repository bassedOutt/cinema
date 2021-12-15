<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 26.11.2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="req" value="${pageContext.request}"/>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="grid grid-cols-4 gap-2 p-5 w-full">
    <c:forEach var="ticket" items="${tickets}">
        <div class="px-5 py-5 flex align-middle text-center items-center place-content-center hover:bg-gray-100 border-4 border-light-blue-500 border-opacity-25">
            <div class="flex flex-col align-middle items-center">
                <div class="m-2 font-medium text-lg ">Row: ${ticket.getSeat().getRow()}</div>
                <div class="m-2 font-medium text-lg ">Seat # ${ticket.getSeat().getSeatNumber()}</div>
                <div class="m-2 font-medium text-lg ">Price: ${ticket.getPrice()}</div>
                <div class="p-2 m-2 w-4/6 bg-blue-100 border-rounded-md word-break">
                    Movie: ${ticket.getSession().getMovie().getTitle()}</div>
                <div class="p-2 m-2 w-4/6 bg-blue-100 border-rounded-md word-break">
                    Date: ${ticket.getSession().getDate()}</div>
                <div class="p-2 m-2 w-4/6 bg-blue-100 border-rounded-md word-break">
                    Time: ${ticket.getSession().getStartTime()} - ${ticket.getSession().getEndTime()}</div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
