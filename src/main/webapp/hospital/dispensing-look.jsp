<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>发药详情--2015</title>
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
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/dispenedFindAction";
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
                window.location.href = "/his/dispenedFindAction";
            });
        });
    </script>
</head>
<body>

<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>药品名称</th>
        <th>药品数量</th>
        <th>已发数量</th>
        <th>未发数量</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${drugs}" var="drug">
        <tr>
            <td style="vertical-align:middle;">${drug.medicalNum}</td>
            <td style="vertical-align:middle;">${drug.rName}</td>
            <td style="vertical-align:middle;">${drug.drugName}</td>
            <td style="vertical-align:middle;">${drug.totalQuantity}</td>
            <td style="vertical-align:middle;">${drug.dispensedQuantity}</td>
            <td style="vertical-align:middle;">${drug.undispensedQuantity}</td>
            <td style="vertical-align:middle;">
                <c:set value="${drug.undispensedQuantity}" var="a" scope="page"/>
                <c:if test="${a>0}">
                    <a href="/his/disUpdateAction?medicalNum=${drug.medicalNum}&drugID=${drug.id}&dispensedQuantity=${a}">发全</a>
                    <c:if test="${a>0}"><a href="/his/disUpdateAction?medicalNum=${drug.medicalNum}&drugID=${drug.id}&dispensedQuantity=1">发1</a></c:if>
                    <c:if test="${a>2}"><a href="/his/disUpdateAction?medicalNum=${drug.medicalNum}&drugID=${drug.id}&dispensedQuantity=3">发3</a></c:if>
                    <c:if test="${a>4}"><a href="/his/disUpdateAction?medicalNum=${drug.medicalNum}&drugID=${drug.id}&dispensedQuantity=5">发5</a></c:if>
                    <c:if test="${a>29}"><a href="/his/disUpdateAction?medicalNum=${drug.medicalNum}&drugID=${drug.id}&dispensedQuantity=30">发30</a></c:if>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <th colspan="5">
            <div class="inline pull-right page">
                <a href='#'>第一页</a> <a href='#'>上一页</a> <span class='current'>1</span><a href='#'>2</a><a
                    href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a> <a href='#'>下一页</a>
                <a href='#'>最后一页</a>
                &nbsp;&nbsp;&nbsp;共<span class='current'>32</span>条记录<span class='current'> 1/33 </span>页
            </div>
            <div>
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </div>

        </th>
    </tr>
</table>

</body>
</html>
