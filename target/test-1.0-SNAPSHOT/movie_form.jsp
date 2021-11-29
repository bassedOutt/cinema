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
    <div class="w-1/2 mx-auto px-6 sm:px-6 lg:px-8">
        <div class="bg-white w-full shadow rounded p-8 sm:p-12">
            <p class="text-3xl font-bold leading-7 text-center">Edit Movie</p>

            <form action="${req.contextPath}/movie_submitted" method="get">

                <input type="hidden" name="id" value="${requestScope.movie_en.id}">
                <div class="md:flex items-center mt-8">
                    <div class="w-1/2 ml-6 flex flex-col">
                        <label class="font-semibold leading-none">Image Url</label>
                        <input value="${requestScope.movie_ua.imageUrl}" name="url" type="url"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                    <div class="w-1/4 flex ml-6 flex-col">
                        <label class="font-semibold leading-none">Price</label>
                        <input value="${requestScope.movie_en.price}" name="price"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                    <div class="w-1/4 flex ml-6 flex-col">
                        <label class="font-semibold leading-none">Duration</label>
                        <input value="${requestScope.movie_en.duration}" name="duration"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                </div>

                <div class="md:flex items-center mt-8">
                    <div class="w-1/2 ml-6 flex flex-col">
                        <label class="font-semibold leading-none">Назва фільму</label>
                        <input value="${requestScope.movie_ua.title}" name="title_ua"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                    <div class="w-1/2 flex ml-6 flex-col">
                        <label class="font-semibold leading-none">Title</label>
                        <input value="${requestScope.movie_en.title}" name="title_en"
                               class="leading-none text-gray-900 p-3 focus:outline-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200"/>
                    </div>
                </div>


                <div class="md:flex items-center">
                    <div class="w-1/2 ml-6 flex flex-col mt-8">
                        <label class="font-semibold leading-none">Опис</label>
                        <textarea type="text" name="description_ua"
                                  class="h-40 text-base leading-none text-gray-900 p-3 focus:oultine-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200">${requestScope.movie_ua.description}
                        </textarea>
                    </div>
                    <div class="w-1/2 ml-6 flex flex-col mt-8">
                        <label class="font-semibold leading-none">Description</label>
                        <textarea type="text" name="description_en"
                                  class="h-40 text-base leading-none text-gray-900 p-3 focus:oultine-none focus:border-blue-700 mt-4 bg-gray-100 border rounded border-gray-200">${requestScope.movie_en.description}
                        </textarea>
                    </div>
                </div>
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
