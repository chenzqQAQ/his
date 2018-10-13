<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>门诊医生---2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <%--<script type="text/javascript" src="/his/Js/jquery.sorted.js"></script>--%>
    <script type="text/javascript" src="/his/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/his/Js/ckform.js"></script>
    <script type="text/javascript" src="/his/Js/common.js"></script>
    <script type="text/javascript" src="/his/Js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/his/Js/messages_zh.js"></script>

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
            $("#form1").attr("action", "/his/doctorFindAllAction");
            $("#form1").submit();
            return false;
        }

        function up() {
            var k = parseInt($("#pageNo").val());
            $("#pageNo").val(k > 1 ? k - 1 : 1);
            $("#form1").attr("action", "/his/doctorFindAllAction");
            $("#form1").submit();
            return false;
        }

        function down() {
            var k = parseInt($("#pageNo").val());
            var k1 = parseInt($("#totalPage").val());
            $("#pageNo").val(k < k1 ? k + 1 : k1);
            $("#form1").attr("action", "/his/doctorFindAllAction");
            $("#form1").submit();
            return false;
        }

        function lastPage() {
            $("#pageNo").val($("#totalPage").val());
            $("#form1").attr("action", "/his/doctorFindAllAction");
            $("#form1").submit();
            return false;
        }

    </script>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/his/doctor/add.jsp";
            });
            $('#exlOutAll').click(function () {
                //全部数据导出
                $.ajax({
                    url: "/his/docExlOut",
                    type: "GET",
                    success: function (msg) {
                        var k = eval("(" + msg + ")");
                        console.log(k['message']);
                        if (parseInt(k['message']) === 1) {
                            alert("Excel导出成功");
                            var form = $('#form2');
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

        function clearA() {
            $("input[name='docId']").val("");
            $("input[name='docName']").val("");
            $("input[name='depName']").val("");
        }
        //清空页面信息
        function clearB() {
            $("#pageNo").val("");
        }
    </script>
    <script type="text/javascript">
        /**
         * 校验规则
         */
        var rules = {
            docId: {
                number:true,
                maxlength:5
            }

        };
        /**
         * 错误提示信息
         */
        var messages = {
            docid: {
                number: "请输入数字",
                maxlength:"请输入合理医生编号"
            }
        };
        $(function () {
            $("#form1").validate(
                {
                    // "debug": true,
                    //失去焦点验证
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    "rules": rules,
                    "messages": messages,
                    errorPlacement: function (error, element) {
                        element.after(error);
                        // error.appendTo(element);
                    }
                }
            );
            $.validator.setDefaults({
                submitHandler: function () {
                    alert("提交事件");
                }
            });
        })
    </script>
</head>
<body>
<form id="form2" action="/his/down" method="post" hidden>
    <input type="hidden" name="url" value="">
</form>
<form id="form1" action="/his/doctorFindAllAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">医生编号：</td>
            <td><input type="text" name="docId" value="${requestScope.docId}"/></td>

            <td width="10%" class="tableleft">医生姓名：</td>
            <td><input type="text" name="docName" value="${requestScope.docName}"/></td>

            <td width="10%" class="tableleft">科室：</td>
            <td><input type="text" name="depName" value="${requestScope.depName}"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <center>
                    <button type="submit" class="btn btn-primary" onclick="clearB()">查询</button>
                    <button type="reset" class="btn btn-primary" onclick="clearA();return false">清空</button>
                </center>
            </td>
        </tr>
    </table>
</form>

<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>入院时间</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${doctors}" var="doctor">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>
            <td style="vertical-align:middle;">
                <c:out value="${doctor.id}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${doctor.doctorName}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:out value="${doctor.docDate}"/>
            </td>
            <td style="vertical-align:middle;">
                <c:set value="${doctor.depId+0}" var="bb"/>
                <c:out value="${dep[bb]}"/>
            </td>
            <td style="vertical-align:middle;"><a href="/his/doctorFindAction?id=${doctor.id}">详情>>></a>&nbsp;&nbsp;&nbsp;
                <a href="/his/doctorFindAction?id=${doctor.id}">更改</a>&nbsp;&nbsp;&nbsp;
                <a href="/his/doctorDelAction?id=${doctor.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;">1103</td>--%>
    <%--<td style="vertical-align:middle;">程俊</td>--%>
    <%--<td style="vertical-align:middle;">2015-09-09 12：12：12</td>--%>
    <%--<td style="vertical-align:middle;">血液科</td>--%>
    <%--<td style="vertical-align:middle;"><a href="look.jsp">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="edit.jsp">更改</a></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;">1104</td>--%>
    <%--<td style="vertical-align:middle;">王博</td>--%>
    <%--<td style="vertical-align:middle;">2015-12-09 12：12：12</td>--%>
    <%--<td style="vertical-align:middle;">骨科</td>--%>
    <%--<td style="vertical-align:middle;"><a href="look.jsp">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="edit.jsp">更改</a></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;">1105</td>--%>
    <%--<td style="vertical-align:middle;">沈青川</td>--%>
    <%--<td style="vertical-align:middle;">2015-02-04 15：11：12</td>--%>
    <%--<td style="vertical-align:middle;">外科</td>--%>
    <%--<td style="vertical-align:middle;"><a href="look.jsp">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="edit.jsp">更改</a></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;">1106</td>--%>
    <%--<td style="vertical-align:middle;">欧阳雨露</td>--%>
    <%--<td style="vertical-align:middle;">2014-09-05 12：12：12</td>--%>
    <%--<td style="vertical-align:middle;">急诊科</td>--%>
    <%--<td style="vertical-align:middle;"><a href="look.jsp">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="edit.jsp">更改</a></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>--%>
    <%--<td style="vertical-align:middle;">1107</td>--%>
    <%--<td style="vertical-align:middle;">艾小天</td>--%>
    <%--<td style="vertical-align:middle;">2014-02-09 12：12：11</td>--%>
    <%--<td style="vertical-align:middle;">妇科</td>--%>
    <%--<td style="vertical-align:middle;"><a href="look.jsp">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="edit.jsp">更改</a></td>--%>
    <%--</tr>--%>
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
                <button type="button" class="btn btn-success" id="newNav">添加新医生</button>
                <button type="button" class="btn btn-success" id="exlOutAll">导出Excel</button>
            </div>

        </th>
    </tr>
</table>

</body>
</html>
