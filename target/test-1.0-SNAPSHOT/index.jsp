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
    <div class="h-screen bg-menu w-60 pr-5 pl-5 border-r-2">
        <div class="flex font-sans mt-10 mb-10 text-xl align-center justify-center">
            <p>Filters</p>
        </div>
        <div class="flex flex-col font-sans sm:text-lg text-xl justify-center">
            <form method="get" action="${req.contextPath}/filter">
                <div class="flex flex-col h-5 sm:h-7 md:h-10 lg:h-10 2xl:h-20 md:mt-1 lg:mt-5 mb-5 bg-menu_item hover:bg-menu_item_hover border-t-2 justify-center border-b-2 ">
                    <select name="range" class="bg-white h-12 hover:bg-gray-100">
                        <option value="today">Today</option>
                        <option value="tomorrow">Tomorrow</option>
                        <option value="week">Week</option>
                    </select>
                </div>

                <div class="flex flex-col h-56 md:mt-1 lg:mt-5 mb-5 bg-menu_item hover:bg-menu_item_hover border-t-2 justify-center border-b-2 ">
                    <div class="p-1">Sort by:</div>
                    <div class="p-1">
                        <input type="radio" id="name"
                               name="filter" value="name">
                        <label for="name">Name</label>
                    </div>

                    <div class="p-1">
                        <input type="radio" id="time"
                               name="filter" value="time">
                        <label for="time">Time</label>
                    </div>

                    <div class="p-1">
                        <input type="radio" id="seats"
                               name="filter" value="seats">
                        <label for="seats">Available seats</label>
                    </div>

                    <div class="p-1 flex flex-row">
                        <input type="radio" id="movies"
                               name="filter" value = "movies"class="px-2">
                        <label class="px-2" for="movies">Filter by movies</label>
                    </div>

                </div>
                <div class="flex flex-col h-20 md:mt-1 lg: bg-menu_item hover:bg-menu_item_hover border-t-2 border-b-2 justify-center ">
                    <button type = "submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="grid grid-cols-4 gap-2 p-5 w-full">
        <c:forEach var="session" items="${sessions}">
            <div class="px-4 py-2 flex flex-col align-middle items-center">
                <img src="${session.getMovie().getImageUrl()}" class="w-52 h-52">
                <div class="pt-1">${session.getMovie().getTitle()}</div>
                <div class="pt-1">${session.getDate()}</div>
                <div class="pt-1">${session.getStartTime()} - ${session.getEndTime()}</div>
                <div class="pt-1">
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        Buy ticket
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
