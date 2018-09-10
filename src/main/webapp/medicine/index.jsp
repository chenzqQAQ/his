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
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/medicine/add.jsp";
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

<form action="/his/drugFindNameAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">药品名称：</td>
            <td><input type="text" name="drugName" value=""/></td>

            <td width="10%" class="tableleft">药品类型：</td>
            <td><select name="drugType" id="select">
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
                    document.getElementById('select').value =${drugType};
                    console.log(${drugType});
                </script>
            </td>
        </tr>
        <tr>


            <td colspan="4">
                <center>
                    <button type="submit" class="btn btn-primary" type="button">查询</button>
                    <button type="reset" class="btn btn-primary" type="button">清空</button>
                    <a href="/his/drugFindAllAction">全部</a>
                </center>
            </td>
        </tr>
    </table>
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
    <%--<%--%>

    <%--List<Drug> list = (List<Drug>) request.getAttribute("drugs");--%>
    <%--String str;--%>
    <%--if (null != list) {--%>
    <%--for (int i = 0; i < list.size(); i++) {--%>
    <%--str="/his/drugFindAction?drugid="+list.get(i).getDrugID();--%>
    <%--request.setAttribute("str",str);--%>
    <%--// user1 = list.get(i);--%>
    <%--%>--%>
    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;"><%=list.get(i).getDrugID() %>--%>
    <%--</td>--%>
    <%--<td style="vertical-align:middle;"><%=list.get(i).getDrugName() %>--%>
    <%--</td>--%>
    <%--<td style="vertical-align:middle;"><%=type[list.get(i).getDrugType()] %>--%>
    <%--</td>--%>
    <%--<td style="vertical-align:middle;"><%=list.get(i).getDescription() %>--%>
    <%--</td>--%>
    <%--<td style="vertical-align:middle;"><%=(0 == list.get(i).getFlag()) ? "销售中" : "售完" %>--%>
    <%--</td>--%>
    <%--<td style="vertical-align:middle;"><%=list.get(i).getInventory() %>--%>
    <%--</td>--%>
    <%--<td style="vertical-align:middle;"><a href="${str}">更改</a>&nbsp;&nbsp;&nbsp;<a href="look.jsp">详情>>></a>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--<%--%>
    <%--}--%>
    <%--}--%>
    <%--%>--%>

    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;">J1121</td>--%>
    <%--<td style="vertical-align:middle;">感冒药</td>--%>
    <%--<td style="vertical-align:middle;">处方</td>--%>
    <%--<td style="vertical-align:middle;">用于普通感冒</td>--%>
    <%--<td style="vertical-align:middle;">销售中</td>--%>
    <%--<td style="vertical-align:middle;">1000袋</td>--%>
    <%--<td style="vertical-align:middle;"><a href="/his/medicine/edit.jsp">更改</a>&nbsp;&nbsp;&nbsp;<a href="look.jsp">详情>>></a>--%>
    <%--</td>--%>
    <%--</tr>--%>

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
                <button type="button" class="btn btn-success" id="newNav">添加新药</button>
                <button type="button" class="btn btn-success" id="delPro">导出Excel</button>
                <button type="button" class="btn btn-success">导出txt</button>
            </div>

        </th>
    </tr>
</table>

</body>
</html>
