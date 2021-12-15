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

<div class="flex justify-center items-center font-sans">
    <div class="w-full h-full flex">
        <div class="h-full bg-menu m-auto w-60 pr-5 pl-5 ml-2 my-5 border-4 hover:bg-gray-100 border-light-blue-500 border-opacity-25 rounded-md">
            <div class="flex font-sans mt-10 mb-10 text-xl align-center justify-center">
                <p>Filters</p>
            </div>
            <div class="flex flex-col sm:text-lg text-xl justify-center">
                <form method="get" action="${req.contextPath}/index">
                    <div class="flex flex-col h-5 sm:h-7 md:h-10 lg:h-10 2xl:h-20 md:mt-1 lg:mt-5 mb-5 bg-menu_item hover:bg-menu_item_hover border-t-2 justify-center border-b-2 ">
                        <select name="range" class="bg-white h-12 hover:bg-gray-100">
                            <option value="today">Today</option>
                            <option value="tomorrow">Tomorrow</option>
                            <option selected value="week">Week</option>
                        </select>
                    </div>

                    <div class="flex flex-col h-48 md:mt-1 lg:mt-2 mb-2 bg-menu_item hover:bg-menu_item_hover border-t-2 justify-center border-b-2 ">
                        <div class="p-1">Sort by:</div>
                        <div class="p-1">
                            <input type="radio" id="name"
                                   name="filter" value="name">
                            <label for="name">Name</label>
                        </div>

                        <div class="p-1">
                            <input type="radio" id="time"
                                   name="filter" checked value="time">
                            <label for="time">Time</label>
                        </div>

                        <div class="p-1">
                            <input type="radio" id="seats"
                                   name="filter" value="seats">
                            <label for="seats">Available seats</label>
                        </div>

                    </div>
                    <div class="flex flex-col h-20 md:mt-1 lg: bg-menu_item hover:bg-menu_item_hover border-t-2 border-b-2 justify-center ">
                        <button type="submit"
                                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="flex flex-col items-center align-middle w-full h-screen">
            <div class="grid grid-cols-4 gap-2 p-5 w-full">
                <c:forEach var="session" items="${sessions}">
                    <div class="px-4 py-2 flex flex-col align-middle items-center border-4 hover:bg-gray-100 border-light-blue-500 border-opacity-25 rounded-md">
                        <img src="${session.getMovie().getImageUrl()}" class="w-52 h-52">
                        <div class="m-1 font-medium text-center text-lg">${session.getMovie().getTitle()}</div>
                        <div class="m-1">${session.getDate()}</div>
                        <div class="m-1">${session.getStartTime()} - ${session.getEndTime()}</div>
                        <div class="m-1">Free seats: ${session.getFreeSeats()}</div>
                        <div class="m-1">
                            <form method="get" action="${req.contextPath}/movie_session">
                                <input type="hidden" name="id" value="${session.getId()}">
                                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                                    Buy ticket
                                </button>
                            </form>
                        </div>
                        <c:if test="${sessionScope.user!=null}">
                            <c:if test="${sessionScope.user.isAdmin()==true}">
                                <div class="flex align-middle w-2/3 space-x-2">
                                    <a class=" w-full text-center bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                                       href="${req.contextPath}/edit_session?id=${session.getId()}">Edit</a>
                                    <a class="w-full text-center bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                                       href="${req.contextPath}/delete_session?id=${session.getId()}">Delete</a>
                                </div>
                            </c:if>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
            <div class="flex justify-center mx-auto">

                <div class="flex justify-center">
                    <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                        <div class="h-10 flex items-center px-5 text-gray-600 bg-white border m-1 border-gray-600 hover:bg-gray-100">
                            <a href="employee.do?page=${currentPage - 1}">Previous</a>
                        </div>
                    </c:if>

                    <%--For displaying Page numbers. The when condition does not display a link for the current page--%>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <div class="h-10 flex items-center px-5 text-gray-600 content-center bg-gray-200 border m-1 border-gray-600 hover:bg-gray-100">${i}</div>
                            </c:when>
                            <c:otherwise>
                                <div class="h-10 flex px-5 items-center text-gray-600 bg-white border m-1 border-gray-600 hover:bg-gray-100">
                                    <a href="employee.do?page=${i}">${i}</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <%--For displaying Next link --%>
                    <c:if test="${currentPage lt noOfPages}">
                        <div class="h-10 px-5 flex items-center text-gray-600 bg-white border m-1 border-gray-600 hover:bg-gray-100">
                            <a href="employee.do?page=${currentPage + 1}">Next</a></div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
