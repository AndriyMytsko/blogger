<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" type="text/css" href="/style/login.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body>
<div class="login">
    <h1>Реєстрація</h1>
    <form action="registration" method="post" enctype="multipart/form-data">
        <input type="text" name="username" placeholder="Username" required="required" />
        <input type="password" name="password" placeholder="Password" required="required" />
        <input type="text" name="email" placeholder="Email" required="required" />
        <input type="file" name="file" class="btn btn-primary btn-block btn-large" value="Додати зображення"/>
        <br>
        <button type="submit" class="btn btn-primary btn-block btn-large">Зберегти</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

<script src="js/index.js"></script>

</body>
</html>
