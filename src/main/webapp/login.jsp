<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>枣阳市第二人民医院管理系统--中软高科2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <script type="text/javascript" src="/his/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/his/Js/ckform.js"></script>
    <script type="text/javascript" src="/his/Js/common.js"></script>
    <script type="text/javascript" src="/his/Js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/his/Js/messages_zh.js"></script>
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
    <script type="text/javascript">
        /**
         * 校验规则
         */
        var rules = {
            username: {
                required: true
            },
            password: {
                required: true
            },
            code: {
                required: true
            }

        };
        /**
         * 错误提示信息
         */
        var messages = {
            username: {
                required: "请输入账号"
            },
            password: {
                required: "请输入密码"
            },
            code: {
                required: "请输入验证码"
            }
        };
        $(function () {
            $("#form1").validate(
                {
                    // "debug": true,
                    //失去焦点验证
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    "rules": rules,
                    "messages": messages,
                    errorPlacement: function (error, element) {
                        // console.log(element.attr("name"));
                        if(element.attr("name")=="code"){
                            element.next().after(error);
                        }
                        else{
                            element.after(error);
                        }
                       // error.appendTo(element);
                    }
                }
            );
            $.validator.setDefaults({
                submitHandler: function () {
                    alert("提交事件");
                }
            });
        })
    </script>
</head>
<body>
<div class="container">

    <form id ="form1" class="form-signin" method="post" action="/his/checkCode">
        <h2 class="form-signin-heading">&nbsp;&nbsp;&nbsp;登录系统</h2>
        <input type="text" name="username" class="input-block-level" placeholder="账号" value="${userName}">
        <input type="password" name="password" class="input-block-level" placeholder="密码" value="${passWord}">
        <input type="text" name="code" class="input-medium" placeholder="验证码" autocomplete="off">
        <img id="img" alt="验证码" src="/his/getCode" onclick="changeImg();return false" title="点击切换"
             style="margin-top: -15px">
        <p style="color: red">${message}</p>
        <p id="k">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-large btn-primary" type="submit">登录
        </button>
        </p>
    </form>

</div>
</body>
</html>