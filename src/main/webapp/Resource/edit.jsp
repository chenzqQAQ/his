<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    </style>
</head>
<body>
<form action="/his/resourceAction" method="post" class="definewidth m20">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="resID" value="${resources.resID}"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">资源名称</td>
            <td><input type="text" name="resName" value="${resources.resName}"/></td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" name="resUrl" value="${resources.resUrl}"/></td>
        </tr>
        <tr>
            <td class="tableleft">是否有效</td>
            <td>
                <input type="radio" name="status" value="1"/> 有效
                <input type="radio" name="status" value="0"/> 无效
            </td>
            <script>
                $("input[name='status'][value=${resources.status}]").prop('checked', true);
            </script>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">更新</button>&nbsp;&nbsp;<button type="button"
                                                                                                           class="btn btn-success"
                                                                                                           name="backid"
                                                                                                           id="backid">
                返回列表
            </button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {
        $('#backid').click(function () {
            window.location.href = "index.html";
        });
    });
</script>