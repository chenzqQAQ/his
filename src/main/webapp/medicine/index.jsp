<%@ page import="com.youma.vo.Drug" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>药品查询----2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <%--<script type="text/javascript" src="/his/Js/jquery.sorted.js"></script>--%>
    <script type="text/javascript" src="/his/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/his/Js/ckform.js"></script>
    <script type="text/javascript" src="/his/Js/common.js"></script>
    <script type="text/javascript" src="/his/Js/ckeditor/ckeditor.js"></script>


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
            $("#form1").attr("action", "/his/drugFindNameAction").submit();
            return false;
        }

        function up() {
            var p = $("#pageNo");
            var k = parseInt(p.val());
            p.val(k > 1 ? k - 1 : 1);
            $("#form1").attr("action", "/his/drugFindNameAction").submit();
            return false;
        }

        function down() {
            var p = $("#pageNo");
            var k = parseInt(p.val());
            var k1 = parseInt($("#totalPage").val());
            p.val(k < k1 ? k + 1 : k1);
            $("#form1").attr("action", "/his/drugFindNameAction").submit();
            return false;
        }

        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("#form1").attr("action", "/his/drugFindNameAction").submit();
            return false;
        }

        //清空输入框
        function clearA() {
            $("input[name='drugName']").val("");
            $("#select").val(99);
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/medicine/add.jsp";
            });
            $('#exlOutAll').click(function () {
                //全部数据导出
                $.ajax({
                    url: "/his/drugExlOut",
                    type: "GET",
                    success: function (msg) {
                        var k = eval("("+msg+")");
                        console.log(k['message']);
                        if (parseInt(k['message']) === 1) {
                            alert("Excel导出成功");
                            var form=$('#form2');
                            $('input[name=url]').val(k['url']);
                            form.submit();
                        }
                        else {
                            alert("Excel导出失败");
                        }
                    }
                });
                return false;
            })
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

<form id="form1" action="/his/drugFindNameAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">药品名称：</td>
            <td><input type="text" name="drugName" value="${drug.drugName}"/></td>

            <td width="10%" class="tableleft">药品类型：</td>
            <td><select name="drugType" id="select">
                <option value="99">全部</option>
                <option value="0">处方</option>
                <option value="1">中药</option>
                <option value="2">西药</option>
                <option value="3">非处方</option>
                <%
                    String[] type = {"处方", "中药", "西药", "非处方"};
                    String[] flag = {"销售中", "售罄", "进货"};
                    request.setAttribute("type", type);
                    request.setAttribute("flag", flag);
                %>
            </select>
                <script>
                    document.getElementById('select').value =${drug.drugType};
                </script>
            </td>
        </tr>
        <tr>


            <td colspan="4">
                <center>
                    <button type="submit" class="btn btn-primary" type="button">查询</button>
                    <button type="reset" class="btn btn-primary" onclick="clearA();return false">清空</button>
                </center>
            </td>
        </tr>
    </table>
</form>
<form id="form2" action="/his/down" method="post" hidden>
    <input type="hidden" name="url" value="">
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>药品编号</th>
        <th>药品名称</th>
        <th>药品类型</th>
        <th>简单描述</th>
        <th>状态</th>
        <th>剩余量</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${drugs}" var="it">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>
            <td style="vertical-align:middle;"><c:out value="${it.drugID}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${it.drugName}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${type[it.drugType]}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${it.description}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${flag[it.flag]}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${it.inventory}"/>
            </td>
            <td style="vertical-align:middle;"><a href="/his/drugFindAction?drugid=${it.drugID}">更改</a>&nbsp;&nbsp;&nbsp;
                <a href="look.html">详情>>></a>
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
                <button type="button" class="btn btn-success" id="newNav">添加新药</button>
                <button type="button" class="btn btn-success" id="exlOutAll">导出Excel</button>
                <button type="button" class="btn btn-success">导出txt</button>
            </div>

        </th>
    </tr>
</table>

</body>
</html>
