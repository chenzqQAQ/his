<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.youma.vo.Users" %>
<%@ page import="java.util.List" %>
<%@page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
<form action="usersFindAction" method="get">
    id:<input type="text" name="id"/>
    <button type="submit">提交</button>
    <a href="usersFindAllAction">全部</a>
</form>
<%
    Users user = (Users) request.getAttribute("user");
    Users user1;
    List<Users> list = (List<Users>) request.getAttribute("users");
    if (null != user) {
%>
用户名${user.userName}
<br/>
密码${user.userPassword}
<br/>
修改时间${user.modifyTime}
<br/>
<%} %>
<table>
    <%if (list != null) { %>

    <thead>
    <tr>
        <td>
            用户名
        </td>
        <td>
            密码
        </td>
        <td>
            修改时间
        </td>
    </tr>
    </thead>
    <% for (int i = 0; i < list.size(); i++) {
        // user1 = list.get(i);
    %>
    <tr>
        <td><%=list.get(i).getUserName() %>
        </td>
        <td><%=list.get(i).getUserPassword() %>
        </td>
        <td><%=list.get(i).getModifyTime() %>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>

</body>
</html>
