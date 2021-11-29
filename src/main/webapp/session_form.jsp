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
<div class="w-full mt-10 flex flex-col items-center align-middle">
    <div class="max-w-5xl mx-auto px-6 sm:px-6 lg:px-8">
        <div class="bg-white w-full shadow rounded p-8 sm:p-12">
            <p class="text-3xl font-bold leading-7 text-center">Edit session</p>
            <form action="${req.contextPath}/movie_edited" method="get">
                <input type="hidden" name="session_id" value="${session1.getId()}">
                <div class="md:flex items-center mt-12">
                    <div class="w-full md:w-1/2 flex flex-col">
                        <label class="font-semibold leading-none">Movie</label>
                        <select name="movie_title"
                                class="block appearance-none hover:bg-gray-200 mt-4 w-full text-gray-700 py-3 px-4 pr-8 rounded">
                            <c:forEach var="movie" items="${requestScope.movies}">
                                <c:choose>
                                    <c:when test="${requestScope.session1.movie.id.equals(movie.id)}">
                                        <option selected value="${movie.title}">${movie.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${movie.title}">${movie.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="w-full md:w-1/2 flex flex-col md:ml-6 md:mt-0 mt-4">
                        <label class="font-semibold leading-none">Pricing</label>
                        <select name="pricing_id"
                                class="block appearance-none hover:bg-gray-200 mt-4 w-full text-gray-700 py-3 px-4 pr-8 rounded">
                            <c:forEach var="pricing" items="${requestScope.pricings}">
                                <c:choose>
                                    <c:when test="${requestScope.session1.pricing.id.equals(pricing.id)}">
                                        <option name="pricing" selected value="${pricing.id}">${pricing.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option name="pricing" value="${pricing.id}">${pricing.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="md:flex items-center mt-8">
                    <div class="w-1/3 flex flex-col">
                        <label class="font-semibold leading-none">Date</label>
                        <input value="${requestScope.session1.date}" name="date" type="date"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                    <div class="w-1/3 ml-6 flex flex-col">
                        <label class="font-semibold leading-none">Start time</label>
                        <input value="${requestScope.session1.startTime}" name="start_time" type="time"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                    <div class="w-1/3 flex ml-6 flex-col">
                        <label class="font-semibold leading-none">End time</label>
                        <input value="${requestScope.session1.endTime}" name="end_time" type="time"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                </div>
                <%--                <div>--%>
                <%--                    <div class="w-full flex flex-col mt-8">--%>
                <%--                        <label class="font-semibold leading-none">Message</label>--%>
                <%--                        <textarea type="text"--%>
                <%--                                  class="h-40 text-base leading-none text-gray-900 p-3 focus:oultine-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"></textarea>--%>
                <%--                    </div>--%>
                <%--                </div>--%>
                <div class="w=1/2 px-3 mt-8 border rounded text-center bg-red-200">${errormessage}</div>
                <div class="flex items-center justify-center w-full">
                    <button class="mt-9 font-semibold leading-none text-white py-4 px-10 bg-blue-700 rounded hover:bg-blue-600 focus:ring-2 focus:ring-offset-2 focus:ring-blue-700 focus:outline-none">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
