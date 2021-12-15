<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Compound Interest Calculator</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="req" value="${pageContext.request}"/>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<div>
    <div class="bg-blue-400 mx-auto text-center w-1/3 py-5 shadow-xl rounded-3xl max-w-2xl">
        <h2 class="text-4xl font-semibold border-b pb-2 mx-6">Login page</h2>
        <!-- If the error attribute is present in the forwarded request, display it -->
        <h3 class="bg-red-300 text-red-900 font-semibold text-xl w-80 rounded-lg my-2 mx-auto">${error}</h3>
        <div>
            <form action="${req.contextPath}/login" method="post">
                <div class="grid grid-cols-2 gap-2 my-5 mx-8">

                    <label for="email" class="text-xl flex items-center">Email:</label>
                    <input type="email" id="email" name="email" placeholder="ivanov@mail.com"
                           class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2">

                    <label for="password" class="text-xl flex items-center">Password:</label>
                    <input type="password" id="password" name="password" min="1" max="100"
                           placeholder="secure_pass1234!"
                           class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg  focus:outline-none focus:ring-2">

                </div>

                <button type="submit"
                        class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-800 hover:text-white">
                    Login
                </button>
                <a href="sign_up.jsp" type="submit"
                   class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-800 hover:text-white">Sign
                    up</a>

            </form>
        </div>
    </div>
</div>


</body>
</html>