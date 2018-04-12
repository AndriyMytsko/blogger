<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" type="text/css" href="/style/login.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>


<body>

<div class="login">
    <h1>Вхід</h1>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Username" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">Ввійти</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <div class="w3-card-2 w3-round">
        <div class="w3-white">
            <button type="button" class="btn btn-primary btn-block btn-large" onClick='location.href="/registration"'>
                Зареєструватися
            </button>
        </div>
    </div>


</div>

</body>
</html>