<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <script type="text/javascript" src="/his/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/his/Js/ckform.js"></script>
    <script type="text/javascript" src="/his/Js/common.js"></script>

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
    <script type="text/javascript">
        //分页函数声明
        function totalPage() {
            $("#pageNo").val(1);
            $("#form1").attr("action", "/his/roleFindAction").submit();
            return false;
        }

        function up() {
            var p = $("#pageNo");
            var k = parseInt(p.val());
            p.val(k > 1 ? k - 1 : 1);
            $("#form1").attr("action", "/his/roleFindAction").submit();
            return false;
        }

        function down() {
            var p = $("#pageNo");
            var k = parseInt(p.val());
            var k1 = parseInt($("#totalPage").val());
            p.val(k < k1 ? k + 1 : k1);
            $("#form1").attr("action", "/his/roleFindAction").submit();
            return false;
        }

        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("#form1").attr("action", "/his/roleFindAction").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/Role/addRole.jsp";
            });
        });


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
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked) {
                    ids.push(alls[i].value);
                }
            }
            if (ids.length > 0) {
                if (confirm("确认删除?")) {
                    alert("删除成功!");
                }
            } else {
                alert("请选中要删除的项");
            }
        }
    </script>

</head>
<body>
<form id="form1" class="form-inline definewidth m20" action="index.jsp" method="get">
    角色名称：
    <input type="text" name="rolename" id="rolename" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>角色名称</th>
        <th>状态</th>
        <%
            String[] status = {"弃用", "启用"};
            request.setAttribute("status", status);

        %>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach items="${roles}" var="role">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${role.roleID}"></td>
            <td>${role.roleName}</td>
            <td>${status[role.status]}</td>
            <td><a href="editRole.jsp">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:alert('删除成功！');">删除</a></td>
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
                <button type="button" class="btn btn-success" id="newNav">添加角色</button>&nbsp;&nbsp;&nbsp;<button
                    type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中
            </button>
            </div>
        </th>
    </tr>
</table>
</body>
</html>