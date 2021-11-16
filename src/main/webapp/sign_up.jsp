<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Compound Interest Calculator</title>

    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class>

<div>
    <div class="bg-blue-400 mx-auto text-center w-1/3 py-5 shadow-xl rounded-3xl max-w-2xl">
        <h2 class="text-4xl font-semibold border-b pb-2 mx-6">User registration form</h2>
        <!-- If the error attribute is present in the forwarded request, display it -->
        <h3 class="bg-red-300 text-red-900 font-semibold text-xl w-80 rounded-lg my-2 mx-auto">${error}</h3>
        <div>
            <form action="/epam_cinema/sign_up" method="post">

                <div class="grid grid-cols-2 gap-2 my-5 mx-8">

                    <label for="name" class="text-xl flex items-center">Name:</label>
                    <input type="text" id="name" name="name" placeholder="ivanov" class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2">

                    <label for="surname" class="text-xl flex items-center">Surname:</label>
                    <input type="text" id="surname" name="surname" placeholder="ivanov" class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2">

                    <label for="email" class="text-xl flex items-center">Email:</label>
                    <input type="text" id="email" name="email" placeholder="ivanov" class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2">

                    <label for="password" class="text-xl flex items-center">Password:</label>
                    <input type="text" id="password" name="password" min="1" max="100" placeholder="secure_pass1234!" class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg  focus:outline-none focus:ring-2">

                </div>

                <button type="submit" class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-800 hover:text-white">Sign up</button>

            </form>
        </div>
    </div>
</div>


</body>
</html>