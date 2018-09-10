<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加医生---2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <%--<script type="text/javascript" src="/his/Js/jquery.sorted.js"></script>--%>
    <script type="text/javascript" src="/his/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/his/Js/ckform.js"></script>
    <script type="text/javascript" src="/his/Js/common.js"></script>

    <script type="text/javascript" src="/his//Js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="/his//Js/My97DatePicker/WdatePicker.js"></script>


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
        function findAllDep() {
            $.ajax({
                url: "/his/departmentAction",
                data: {
                    action: "findAll"
                },
                success: function (msg) {
                    console.log("ajax请求成功，开始执行成功后的回调函数");
                    var deps = eval("(" + msg + ")");
                    $('#depName').empty();
                    $.each(deps, function (item, dep) {
                        console.log(dep['id']);
                        console.log(dep['depName']);
                        var option = $("<option></option>");
                        option.val(dep['id']).text(dep['depName']).appendTo($('#depName'));
                    })
                }

            });
        }

        var datepicker = {
            lang: 'zh-cn',
            dateFmt: 'yyyy-MM-dd',
            minDate: '1949-10-1',
            maxDate: new Date(),
            startDate: '1990-1-1',
            readOnly: true,
            highLineWeekDay: true
        };
        $(function () {
            $('#backid').click(function () {
                window.location.href = "index.jsp";
            });
        });
        $(function () {
            // console.log($('#age').siblings("span")[0]);
            $('#birthday').blur(function () {
                var myyear = $(this).val();
                // console.log(myyear);
                var age = 2018 - parseInt(myyear);
                // console.log(age);
                if (!isNaN(age)) {
                    $('#age').siblings("span").text(age).end()
                        .val(age)
                }
            })
            findAllDep();
        })
    </script>
</head>
<body>
<form action="/his/doctorAddAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td><input type="text" name="doctorName" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件类型</td>
            <td>
                <select name="identifierType">
                    <option value="0">身份证</option>
                    <option value="1">护照</option>
                    <option value="2">军人证</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件号</td>
            <td><input type="text" name="identifierNum" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">手机</td>
            <td><input type="text" name="phoneNum" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">座机</td>
            <td><input type="text" name="setaPhoneNum" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">性别</td>
            <td><input type="radio" name="sex" value="1" checked/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="sex"
                                                                                            value="0"/>女
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">出生年月</td>
            <td><input type="text" autocomplete="off" id="birthday" name="birthday" value="" class="Wdate"
                       onfocus="WdatePicker(datepicker)"/>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">年龄</td>
            <td name="age">
                <input type="hidden" name="age" value="" id="age"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">电子邮箱</td>
            <td><input type="text" name="email" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">所属科室</td>
            <td><select name="depId" id="depName">
                <option value="0">急诊科</option>
                <option value="1">骨科</option>
                <option value="2">血液科</option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">学历</td>
            <td><select name="degree">
                <option value="0">专科</option>
                <option value="1">本科</option>
                <option value="2">博士</option>
                <option value="3">博士后</option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">备注</td>
            <td><textarea name="remark"></textarea></td>
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