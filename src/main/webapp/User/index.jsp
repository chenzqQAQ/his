<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<% String path = request.getContextPath();
%>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css"/>
    <script type="text/javascript" src="<%=path%>/Js/jquery.js"></script>
    <%--<script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/common.js"></script>


    <style type="text/css">
        body {
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script>
        //首页跳转
        function totalPage() {
            $("#pageNo").val(1);
            $("#form1").attr("action", "/his/usersFindAllAction");
            $("#form1").submit();
            return false;
        }
        //上一页
        function up() {
            var k = parseInt($("#pageNo").val());
            $("#pageNo").val(k > 1 ? k - 1 : 1);
            $("#form1").attr("action", "/his/usersFindAllAction");
            $("#form1").submit();
            return false;
        }
        //下一页
        function down() {
            var k = parseInt($("#pageNo").val());
            var k1 = parseInt($("#totalPage").val());
            $("#pageNo").val(k < k1 ? k + 1 : k1);
            $("#form1").attr("action", "/his/usersFindAllAction");
            $("#form1").submit();
            return false;
        }
        //尾页
        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("#form1").attr("action", "/his/usersFindAllAction");
            $("#form1").submit();
            return false;
        }

        function checkall() {
            var alls = document.getElementsByName("check");
            var ch = document.getElementById("checkall");
            if (ch.checked) {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = true;
                }
            } else {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = false;
                }
            }
        }

        function delAll() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            var str = "/his/usersDelAction?action=delAll";
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked) {
                    ids.push(alls[i].value);
                    str = str + "&drugid=" + alls[i].value;
                }
            }
            if (ids.length > 0) {
                console.log(str);
                if (confirm("确认删除?")) {
                    window.location.href = str;
                    alert("删除成功!");
                }
            } else {
                alert("请选中要删除的项");
            }
        }
        //清空输入框
        function clearA() {
           $('input[name="username"]') .val("");
        }
        //清空页面信息
        function clearB() {
            $("#pageNo").val("");
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/User/addUser.jsp";
            });
        });
    </script>
</head>
<body>
<form id="form1" class="form-inline definewidth m20" action="/his/usersFindAllAction" method="post">
    用户名称：
    <input type="text" name="username" id="username" class="abc input-default" placeholder="" value="${user.userName}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary" onclick="clearB()">查询</button>
    <button type="submit" class="btn btn-primary" onclick="clearA();return false">清空</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>用户账户</th>
        <th>真实姓名</th>
        <th>角色</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user" varStatus="id">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${user.userID}"></td>
            <td style="text-align:center;">
                <c:out value="${user.userName}"></c:out>
            </td>
            <td style="text-align:center;">
                <c:out value="${user.realName}"></c:out>
            </td>
            <td style="text-align:center;">
                <c:out value="${user.roleName}"></c:out>
            </td>
            <td style="text-align:center;">
                <a href="/his/usersFindAction?drugid=${user.userID}">编辑</a>&nbsp;&nbsp;&nbsp;<a
                    href="/his/usersDelAction?drugid=${user.userID}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <th colspan="5">
            <div class="inline pull-right page">
                <input type="hidden" form="form1" value="${page.pageNo}" id="pageNo" name="pageNo">
                <input type="hidden" form="form1" value="${page.totalPage}" id="totalPage" name="totalPage">
                <a href='#' onclick="totalPage();return false">第一页</a>
                <a href='#' onclick="up();return false">上一页</a>
                <span class='current'>${page.pageNo}</span>
                <a href='#' onclick="down();return false">下一页</a>
                <a href='#' onclick="lastPage();return false">最后一页</a>
                ${page.totalCount} 条记录 ${page.pageNo} /${page.totalPage} 页
            </div>
            <div>
                <button type="button" class="btn btn-success" id="newNav">添加用户</button>&nbsp;&nbsp;&nbsp;<button
                    type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中
            </button>
            </div>
        </th>
    </tr>
</table>
</body>
</html>