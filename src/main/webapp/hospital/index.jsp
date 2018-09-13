<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.youma.vo.Inpatient" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.youma.util.Czq" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>入院办理--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <%--<script type="text/javascript" src="/his/Js/jquery.sorted.js"></script>--%>
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

        <% Field field=null;
        try {
            field = Inpatient.class.getDeclaredField("flag");
        }
            catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(field.getDeclaredAnnotation(Czq.class)!=null)
            {
        String []flag=field.getDeclaredAnnotation(Czq.class).value();
        request.setAttribute("flag",flag);
            }

        %>
    </style>
    <script type="text/javascript">
        function totalPage() {
            $("#pageNo").val(1);
            $("#form1").attr("action", "/his/inpFindAction").submit();
            return false;
        }

        function up() {
            var p = $("#pageNo");
            var k = parseInt(p.val());
            p.val(k > 1 ? k - 1 : 1);
            $("#form1").attr("action", "/his/inpFindAction").submit();
            return false;
        }

        function down() {
            var p = $("#pageNo");
            var k = parseInt(p.val());
            var k1 = parseInt($("#totalPage").val());
            p.val(k < k1 ? k + 1 : k1);
            $("#form1").attr("action", "/his/inpFindAction").submit();
            return false;
        }

        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("#form1").attr("action", "/his/inpFindAction").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/hospital/add.jsp";
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
                if (confirm("确认操作?")) {
                    alert("成功!");
                }
            } else {
                alert("请选中要操作的项");
            }
        }
    </script>
</head>
<body>

<form id="form1" action="index.jsp" method="post" class="definewidth m20">
    <input type="hidden" value="findAll" name="action"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">病例号：</td>
            <td><input type="text" name="medicalNum" value=""/></td>

            <td width="10%" class="tableleft">主治医生：</td>
            <td><input type="text" name="pname" value=""/></td>

            <td width="10%" class="tableleft">科室：</td>
            <td><input type="text" name="pname" value=""/></td>
        </tr>
        <tr>

            <td width="10%" class="tableleft">住院时间：</td>

            <td colspan="5">
                <input type="text" name="pname" value=""/>&nbsp;至&nbsp;<input type="text" name="pname" value=""/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="submit" class="btn btn-primary" type="button">查询</button>
                <button type="submit" class="btn btn-primary" type="button">清空</button>

            </td>
        </tr>
    </table>
</form>

<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>病历号</th>
        <th>姓名</th>
        <th>床位号</th>
        <th>联系电话</th>
        <th>押金</th>
        <th>主治医生</th>
        <th>入院时间</th>
        <th>科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${inps}" var="inp">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${inp.medicalNum}"></td>
            <td style="vertical-align:middle;">${inp.medicalNum}</td>
            <td style="vertical-align:middle;">${inp.name}</td>
            <td style="vertical-align:middle;">${inp.bedNum}</td>
            <td style="vertical-align:middle;">${inp.phone}</td>
            <td style="vertical-align:middle;">${inp.deposit}元</td>
            <td style="vertical-align:middle;">${inp.doctor}</td>
            <td style="vertical-align:middle;">${inp.inpTime}</td>
            <td style="vertical-align:middle;">${inp.depName}</td>
            <td style="vertical-align:middle;">${flag[inp.flag]}</td>
            <td style="vertical-align:middle;"><a href="look.html">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="edit.jsp">更改</a>&nbsp;&nbsp;&nbsp;<a
                    href="javascript:alert('退院成功！');">退院</a>&nbsp;&nbsp;&nbsp;<a
                    href="javascript:alert('出院成功！');">出院</a>
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
                <button type="button" class="btn btn-success" id="newNav">录入住院信息</button>&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" id="delPro" onClick="delAll();">出院</button>&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" id="delPro1" onClick="delAll();">退院</button>&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" id="delPro13">导出Excel</button>
                <button type="button" class="btn btn-success" id="delPro2">打印</button>

            </div>

        </th>
    </tr>
</table>

</body>
</html>
