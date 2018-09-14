<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加收费项目--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
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
            $('#backid').click(function () {
                window.location.href = "charge.html";
            });
            $.ajax({
                url: "/his/projectAjaxAction",
                success: function (msg) {
                    var k = eval("(" + msg + ")");
                    console.log("找到全部收费项目");
                    console.log(k);
                    $.each(k, function (index, i) {
                        var o = $("<option></option>");
                        o.val(i['id']).text(i['projectName']).attr("amount", i['amount'])
                            .appendTo($('#pm'));
                    })
                }

            });
        });
        function add() {
            var table = $('pms');
            if ($('#pm').val() != null && $('#pm').val() != "") {
                var value = $('#pm').val();
                var k = $('option[value="value"]');
                var tr = $("<tr></tr>");
                table.append(tr);
                var td1 = $("<td></td>");
                td1.text(k.val())
                var ip = $("<input type='hidden'></input>");
                var td2 = $("<td></td>");
                td2.text(k.text())
                var td3 = $("<td></td>");
                td3.text(k.attr("amount"))
                tr.append(td1).append(td2).append(td3);
            }
        }
    </script>
</head>
<body>
<form action="charge.html" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">病历号</td>
            <td>1103</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td>黄飞鸿</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">收费项目</td>
            <td><select id="pm">
            </select>
                <a href="#" onclick="add();return false">添加</a>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">收费项目</td>
            <td><input type="text" name="pname" value=""/>输入自动匹配出来</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">收费金额</td>
            <td><input type="text" name="pname" value=""/></td>
        </tr>
        <tr>
            <td>
                <table id="pms">

                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <center>
                    <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button
                        type="button" class="btn btn-success" name="backid" id="backid">返回列表
                </button>
                </center>
            </td>
        </tr>
    </table>
</form>
</body>
</html>