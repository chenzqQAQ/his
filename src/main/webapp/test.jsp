<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/31
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    for (int i = 0; i < 10; i++) {
        int j = i;
        request.setAttribute("j", j);
%>
<h1>${j}</h1>
<% } %>
</body>
</html>
