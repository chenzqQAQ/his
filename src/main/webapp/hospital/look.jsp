<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>查看--中软高科-2015</title>
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
                window.location.href = "/his/inpFindAction?action=findAll";
            });
            var p1 = {
                '0': "身份证",
                '1': "护照",
                '2': "军人证"
            };
            var p2 = {
                '0': "否",
                '1': "是"
            };
            var p3 = {
                '0': "女",
                '1': "男"
            };
            var p4 = {
                '0': "初诊",
                '1': "复诊"
            };
            $.ajax({
                url: "/his/registerAjaxAction",
                method: "POST",
                data: {
                    "medicalNum": ${inp.medicalNum}
                },
                success: function (msg) {
                    var k = eval("(" + msg + ")");
                    // console.log(k);
                    //将挂号信息显示到页面上
                    $("#registerName").next().text(k['registerName']);
                    $("#identifierType").next().text(p1[k['identifierType']]);
                    $("#identifierNum").next().text(k['identifierNum']);
                    $("#socialSecurityNum").next().text(k['socialSecurityNum']);
                    $("#phoneNum").next().text(k['phoneNum']);
                    $("#expenseFlag").next().text(p2[k['expenseFlag']]);
                    $("#sex").next().text(p3[k['sex']]);
                    $("#age").next().text(k['age']);
                    $("#czFlag").next().text(p4[k['czFlag']]);
                    $("#remark").next().text(k['remark']);
                    $("#docName").next().text(k['docName']);
                    $("#depName").next().text(k['depName']);
                    $("#profession").next().text(k['profession']);
                }
            })
        });
    </script>
</head>
<body>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td>${inp.medicalNum}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="registerName">姓名</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="identifierType">证件类型</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="identifierNum">证件号</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td>5元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="socialSecurityNum">社保号</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="phoneNum">联系电话</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="expenseFlag">是否自费</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="sex">性别</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="age">年龄</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="profession">职业</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="czFlag">初复诊</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="depName">所挂科室</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="docName">指定医生</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" id="remark">备注</td>
        <td></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">护理</td>
        <td>${inp.nurse}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">床位号</td>
        <td>${inp.bedNum}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">缴费押金</td>
        <td>${inp.deposit}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">病情</td>
        <td>${inp.illness}</td>
    </tr>
    <tr>
        <td colspan="2">
            <center>
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </center>
        </td>
    </tr>
</table>
</body>
</html>