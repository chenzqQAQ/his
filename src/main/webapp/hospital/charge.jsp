<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>收费项目管理--中软高科-2015</title>
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
        function totalPage() {
            $("#pageNo").val(1);
            $("#form1").attr("action", "/his/pJFindAction");
            $("#form1").submit();
            return false;
        }

        function up() {
            var k = parseInt($("#pageNo").val());
            $("#pageNo").val(k > 1 ? k - 1 : 1);
            $("#form1").attr("action", "/his/pJFindAction");
            $("#form1").submit();
            return false;
        }

        function down() {
            var k = parseInt($("#pageNo").val());
            var k1 = parseInt($("#totalPage").val());
            $("#pageNo").val(k < k1 ? k + 1 : k1);
            $("#form1").attr("action", "/his/pJFindAction");
            $("#form1").submit();
            return false;
        }

        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("#form1").attr("action", "/his/pJFindAction");
            $("#form1").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/hospital/charge-new.jsp";
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

        $(function () {
            $('#backid').click(function () {
                window.location.href = "dispensing.jsp";
            });
            $("input[name='name']").change(function () {
                $("#pageNo").val("")
            });
            $("input[name='medicalNum']").change(function () {
                $("#pageNo").val("")
            });
        });

        //清空输入框
        function clearA() {
            $("input[name='name']").val("");
            $("input[name='medicalNum']").val("");
        }
        //清空页面信息
        function clearB() {
            $("#pageNo").val("");
        }
    </script>
</head>
<body>

<form id="form1" action="/his/pJFindAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">病历号：</td>
            <td>
                <c:set value="${payManager.medicalNum==0?'':payManager.medicalNum}" var="a" scope="page"/>
                <input type="text" name="medicalNum" value="${a}"/>
            </td>

            <td width="10%" class="tableleft">姓名：</td>
            <td><input type="text" name="name" value="${payManager.name}"/></td>
        </tr>
        <tr>


            <td colspan="4">
                <center>
                    <button type="submit" class="btn btn-primary"  onclick="clearB()">查询</button>
                    <button type="submit" class="btn btn-primary" onclick="clearA();return false">清空</button>
                </center>
            </td>
        </tr>
    </table>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>收费项目</th>
        <th>收费金额</th>
        <th>收费日期</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${pjs}" var="pj">
        <tr>
            <td style="vertical-align:middle;">${pj.medicalNum}</td>
            <td style="vertical-align:middle;">${pj.name}</td>
            <td style="vertical-align:middle;">${pj.payName}</td>
            <td style="vertical-align:middle;">${pj.chargeAmount}</td>
            <td style="vertical-align:middle;">${pj.payDate}</td>
            <td style="vertical-align:middle;"><a href="/his/hospital/charge-new.jsp?medicalNum=${pj.medicalNum}&name=${pj.name}">添加收费项目</a></td>
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
                <button type="button" class="btn btn-success" id="newNav">添加收费项目</button>
            </div>

        </th>
    </tr>
</table>

</body>
</html>
