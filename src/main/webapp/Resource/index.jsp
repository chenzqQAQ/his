<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <% String path = request.getContextPath();
    %>
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
    <script type="text/javascript">
        function totalPage() {
            $("#pageNo").val(1);
            $("input[name='action']").val('findAll');
            $("#form1").attr("action", "/his/resourceAction").submit();
            return false;
        }

        function up() {
            var a = $("#pageNo");
            var k = parseInt(a.val());
            a.val(k > 1 ? k - 1 : 1);
            $("input[name='action']").val('findAll');
            $("#form1").attr("action", "/his/resourceAction").submit();
            return false;
        }

        function down() {
            var a = $("#pageNo");
            var k = parseInt(a.val());
            var k1 = parseInt($("#totalPage").val());
            a.val(k < k1 ? k + 1 : k1);
            $("input[name='action']").val('findAll');
            $("#form1").attr("action", "/his/resourceAction").submit();
            return false;
        }

        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("input[name='action']").val('findAll');
            $("#form1").attr("action", "/his/resourceAction").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/Resource/add.jsp";
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

        //清空输入框
        function clearA() {
            $('input[name="resName"]').val("");
        }

        //清空页面信息
        function clearB() {
            $("#pageNo").val("");
        }
    </script>
    <script type="text/javascript">
        $(function () {
            if (${sessionScope.RoleMessage!=null}) {
                alert(${sessionScope.RoleMessage});
                <%session.removeAttribute("RoleMessage");%>
            }
        })
    </script>
</head>
<body>
<form id="form1" class="form-inline definewidth m20" action="/his/resourceAction" method="POST">
    <input type="hidden" name="action" value="findAll">
    资源(菜单)名称：
    <input type="text" name="resName" id="resName" class="abc input-default" placeholder="" value="${res.resName}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary" onclick="clearB()">查询</button>
    <button type="submit" class="btn btn-primary" onclick="clearA();return false">清空</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>资源名称</th>
        <th>路径Url</th>
        <th>是否有效</th>
        <% String[] p = {"无效", "有效"};
            request.setAttribute("p", p);
        %>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach items="${resources}" var="resource">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${resource.resID}"></td>
            <td>${resource.resName}</td>
            <td>${resource.resUrl}</td>
            <td>${p[resource.status]}</td>
            <td>
                <a href="/his/resourceAction?action=find&resID=${resource.resID}">编辑</a>&nbsp;&nbsp;&nbsp;<a
                    href="/his/resourceAction?action=del&resID=${resource.resID}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <th colspan="5">
            <div class="inline pull-right page">
                <input type="hidden" form="form1" value="${page.pageNo}" id="pageNo" name="pageNo">
                <input type="hidden" value="${page.totalPage}" id="totalPage" name="totalPage">
                <a href='#' onclick="totalPage();return false">第一页</a>
                <a href='#' onclick="up();return false">上一页</a>
                <span class='current'>${page.pageNo}</span>
                <a href='#' onclick="down();return false">下一页</a>
                <a href='#' onclick="lastPage();return false">最后一页</a>
                ${page.totalCount} 条记录 ${page.pageNo} /${page.totalPage} 页
            </div>
            <div>
                <button type="button" class="btn btn-success" id="newNav">添加资源</button>&nbsp;&nbsp;&nbsp;<button
                    type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中
            </button>
            </div>
        </th>
    </tr>
</table>
</body>
</html>