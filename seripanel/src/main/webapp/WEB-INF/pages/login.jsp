<%--
  Created by IntelliJ IDEA.
  User: puneet
  Date: 30/03/16
  Time: 8:14 AM
  To change this template use File | Settings | File Templates.
--%>

<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SERI :: LOGIN FORM</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/login/css/style.css" />
    <script src="${pageContext.request.contextPath}/resources/login/js/modernizr.custom.63321.js"></script>
    <!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
</head>
<body>
<div class="container">
    <header>

        <h1> <strong>Login Form</strong> </h1>


    </header>

    <section class="main">
        <form class="form-1" name="f" action="/j_spring_security_check" method="POST">
            <p class="field">
                <input type='text' name='j_username' value='' placeholder="Username">
                <i class="icon-user icon-large"></i>
            </p>
            <p class="field">
                <input type='password' name='j_password' placeholder="Password"/>
                <i class="icon-lock icon-large"></i>
            </p>
            <p class="submit">
                <button type="submit" name="submit"><i class="icon-arrow-right icon-large"></i></button>
            </p>
        </form>
    </section>
</div>
</body>
</html>