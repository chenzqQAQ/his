<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/29
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>
            欢迎用户<%=request.getParameter("userName") %>添加<%=request.getAttribute("flag") %>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/web/User.html">回到add</a>
        </td>
    </tr>
</table>
</body>
</html>
