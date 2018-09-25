<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>枣阳市第二人民医院管理系统--中软高科2015</title>
    <meta charset="UTF-8">
    <%--<link rel="stylesheet" type="text/css" href="Css/bootstrap.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="Css/style.css"/>--%>
    <%--<script type="text/javascript" src="Js/jquery.js"></script>--%>
    <%--<script type="text/javascript" src="Js/bootstrap.js"></script>--%>
    <%--<script type="text/javascript" src="Js/ckform.js"></script>--%>
    <%--<script type="text/javascript" src="Js/common.js"></script>--%>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

    </style>
    <script type="text/javascript">
        function changeImg() {
            var imgSrc = $('#img');
            var src = imgSrc.attr("src");
            imgSrc.attr("src", chgUrl(src));
        }

        function chgUrl(url) {
            var timestamp = (new Date()).valueOf();
            if (url.indexOf("?") != -1) {
                url = url.substring(0, url.indexOf("?"));
            }
            url += "?timestamp=" + timestamp;
            return url;
        }
    </script>
</head>
<body>
<div class="container">

    <form class="form-signin" method="post" action="/his/checkCode">
        <h2 class="form-signin-heading">&nbsp;&nbsp;&nbsp;登录系统</h2>
        <input type="text" name="username" class="input-block-level" placeholder="账号">
        <input type="password" name="password" class="input-block-level" placeholder="密码">
        <input type="text" name="code" class="input-medium" placeholder="验证码">
        <img id="img" alt="验证码" src="/his/getCode" onclick="changeImg();return false" title="点击切换">
        <p style="color: red">${message}</p>
        <p id="k">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-large btn-primary" type="submit">登录
        </button>
        </p>
    </form>

</div>
</body>
</html>