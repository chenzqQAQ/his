<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>查看--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/style.css"/>
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>


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
            $('#backid').click(function () {
                window.location.href = "index.jsp";
            });
        });
    </script>
</head>
<body>
<form action="index.jsp" method="post" class="definewidth m20">

    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td>${register.registerName}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件类型</td>
            <td>${p1[register.identifierType]}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件号</td>
            <td>${register.identifierNum}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">挂号费</td>
            <td>5元</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">社保号</td>
            <td>${register.socialSecurityNum}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">联系电话</td>
            <td>${register.phoneNum}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">是否自费</td>
            <td>${p2[register.expenseFlag]}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">性别</td>
            <td>${p3[register.sex]}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">年龄</td>
            <td>${register.age}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">职业</td>
            <td>${register.profession}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">初复诊</td>
            <td>${p4[register.czFlag]}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">所挂科室</td>
            <td>${depName}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">指定医生</td>
            <td>${docName}</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">备注</td>
            <td>${register.remark}</td>
        </tr>
        <tr>
            <td colspan="2">
                <center>
                    <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
                </center>
            </td>
        </tr>
    </table>
</form>
</body>
</html>