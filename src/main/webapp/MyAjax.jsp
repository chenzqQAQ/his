<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/6
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="jquery-3.3.1.min.js"></script>

    <script>

        $(function () {
            $('#but1').click(function () {
                $.ajax({
                    type: "POST",
                    url: "ajaxAction?action=add",
                    success: function (data) {
                        console.log("成功");
                    }
                })
                // alert(1);
                return false;
            });
        });
    </script>
</head>
<body>
<form action="#" method="get" id="form1">
    名字:<input type="text" name="userName" value=""/><br/>
    密码:<input type="text" name="userPassword" value=""/><br/>
    姓名:<input type="text" name="userRealName" value=""/><br/>
    邮箱:<input type="text" name="email" value=""/><br/>
    <button name="button" id="but1">确认</button>
</form>
</body>
</html>
