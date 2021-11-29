<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>${movieSession.movie.title}</title>
    <c:set var="req" value="${pageContext.request}"/>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="flex flex-col align-middle items-center">
    <div class="px-5 m-5 w-1/3 self-center py-5 flex align-middle text-center items-center place-content-center border-4 border-light-blue-500 border-opacity-25">
        <div class="flex flex-col align-middle items-center">
            <div class="m-2 font-medium text-lg ">${movieSession.movie.title}</div>
            <img src="${movieSession.movie.imageUrl}" class="m-2 w-52 h-52">
            <div class="m-2 font-medium text-lg ">${movieSession.date}</div>
            <div class="m-2 font-medium text-lg ">${movieSession.startTime}
                - ${movieSession.endTime}</div>
            <div class="m-2 font-medium text-lg ">standard seat: ${standardPrice}
                <div class="m-2 font-medium text-lg ">vip seat: ${vipPrice}

                    <form method="get" action="${req.contextPath}/buy_tickets">
                        <div class="grid grid-cols-10">
                            <c:forEach var="seat" items="${movieSession.seats}">
                                <c:if test="${seat.taken}">
                                    <div class="flex">
                                        <input type="checkbox" class="w-6 m-1 h-6" disabled>
                                        <img class="w-6 h-6 m-1"
                                             src="https://e1.pngegg.com/pngimages/314/988/png-clipart-symbolize-x.png">
                                    </div>
                                </c:if>
                                <c:if test="${!seat.taken}">
                                    <div class="flex">
                                        <input type="checkbox" id="ticket${seat.getSeat()}"
                                               name="ticket${seat.getSeat()}"
                                               class="w-6 m-1 h-6 text-center bg-blue-100 hover:bg-blue-200">
                                        <c:if test="${seat.isVip()}">
                                            <span class="w-6 m-1 h-6 bg-red-100">${seat.getSeat()}</span>
                                        </c:if>
                                        <c:if test="${!seat.isVip()}">
                                            <span class="w-6 m-1 h-6 bg-blue-100">${seat.getSeat()}</span>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                        <button type="submit"
                                class="bg-blue-500 text-center m-2 w-24 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                            Submit
                        </button>
                        <div class="bg-red-200">${errormessage}</div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
