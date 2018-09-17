<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>发药---2015</title>
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
                window.location.href = "dispensing.jsp";
            });
            //获取数据库里的全部药信息
            $.ajax({
                url: "/his/drugFindAllAction?action=ajax",
                success: function (msg) {
                    var k = eval("(" + msg + ")");
                    var g = $('#drugName');
                    $.each(k, function (index, i) {
                        var pp = $('<option></option>');
                        pp.val(i['drugID']).text(i['drugName']).appendTo(g);
                    })
                }
            });
            $('input[name="medicalNum"]').blur(function () {
                $.ajax({
                    url: "/his/nameAjaxAction",
                    data: {
                        medicalNum: $('input[name="medicalNum"]').val()
                    },
                    type: "POST",
                    success: function (msg) {
                        // console.log(1);
                        $('#name').text("");
                        var k = eval("(" + msg + ")");
                        $.each(k, function (index, i) {
                            var str;
                            if (index != 0) {
                                str = $('#name').text() + ',' + i['registerName'];
                            }
                            else {
                                str = $('#name').text() + i['registerName'];
                            }
                            $('#name').text(str);
                        })

                    }
                })
            })
        });
    </script>
</head>
<body>
<form action="/his/dispenedAddAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">病历号</td>
            <td><input name="medicalNum"></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td id="name"></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">药品名称</td>
            <td><select id="drugName" name="drugId">

            </select></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">发药数量</td>
            <td><input type="text" name="TotalQuantity" value=""/></td>
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