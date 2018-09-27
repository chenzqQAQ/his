<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
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

    <script type="text/javascript" src="/his/Js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="/his/Js/My97DatePicker/WdatePicker.js"></script>
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
        $.validator.addMethod("isBlank", function (value,element) {
            var pattern = new RegExp("[`~!@#$^&*=|{}':;',\\[\\]<>《》/?~！@#￥……&*|{}【】‘；：”“'。，、？' ']");
            var reg = /^([0-9]+)$/;//全部为数字

            if (pattern.test(value)) {
                return false;
            } else if (value.indexOf(" ") != -1) {
                return false;
            } else {
                return true;
            }
        }, '不能包含特殊字符');
        var rule = {
            doctorName: {
                required: true,
                minlength: 2,
                isBlank: true
            },
            identifierType: {
                required: true
            }, identifierNum: {
                required: true,
                minlength: 10,
                digits: true
            }, phoneNum: {
                required: true,
                rangelength: [11, 11],
                digits: true
            }, sex: {
                required: true
            }, birthday: {
                required: true
            }, age: {
                required: true
            }, email: {
                required: true,
                email: true
            }, depId: {
                required: true
            }, degree: {
                required: true,
            }
        };
        var message = {
            doctorName: {
                required: "请输入医生姓名",
                minlength: "名字必须二个字符以上"
            },
            identifierType: {
                required: "请选择证件类型"
            }, identifierNum: {
                required: "请输入证件号",
                minlength: "证件号必须8位及以上",
                digits: "证件号必须是整数"

            }, phoneNum: {
                required: "请输入手机号",
                rangelength: "手机号必须11位",
                digits: "手机号号必须是整数"
            }, sex: {
                required: "请选择性别"
            }, birthday: {
                required: "请选择出生日期"
            }, age: {
                required: "请输入年龄"
            }, email: {
                required: "请输入电子邮箱",
                email: "请输入正确的电子邮箱地址"
            }, depId: {
                required: "请选择科室"
            }, degree: {
                required: "请选择学历"
            }
        };
    </script>
    <script type="text/javascript">
        function findAllDep() {
            $.ajax({
                url: "/his/departmentAction",
                data: {
                    action: "findAll"
                },
                success: function (msg) {
                    // console.log("ajax请求成功，开始执行成功后的回调函数");
                    var deps = eval("(" + msg + ")");
                    $('#depName').empty();
                    $.each(deps, function (item, dep) {
                        // console.log(dep['id']);
                        // console.log(dep['depName']);
                        var option = $('<option></option>');
                        option.val(dep['id']).text(dep['depName']).appendTo($('#depName'));
                    })
                }

            });
        };

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
                window.location.href = "/his/doctorFindAllAction";
            });
            $('#form1').validate({
                rules: rule,
                messages: message,
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                }
            });
            $.validator.setDefaults({
                submitHandler: function () {
                    alert("提交事件");
                }
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
            $('input[name="doctorName"]').blur(function () {
                var str=$(this).val();
                $.ajax({
                    url: "/his/ajaxAction1?docName=" + str,
                    type: "GET",
                    dataType: "text",
                    success: function (msg) {
                        // console.log(msg);
                        var i=parseInt(msg);
                        if (i === 1) {
                            $('#k').text("医生已存在");
                        } else if (i === 0) {
                            $('#k').text("医生可以添加");
                        }
                        else{
                            $('#k').text("医生名不能为空");
                        }
                        // console.log("执行回调函数");
                    }
                })
                return false;
            })
        })
    </script>
</head>
<body>
<form id="form1" action="/his/doctorAddAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td><input type="text" name="doctorName" value=""/>*<span id="k"></span></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件类型</td>
            <td>
                <select name="identifierType">
                    <option value="0">身份证</option>
                    <option value="1">护照</option>
                    <option value="2">军人证</option>
                </select>*
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件号</td>
            <td><input type="text" name="identifierNum" value=""/>*</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">手机</td>
            <td><input type="text" name="phoneNum" value=""/>*</td>
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
                       onfocus="WdatePicker(datepicker)"/>*
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
            <td><input type="text" name="email" value=""/>*</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">所属科室</td>
            <td><select name="depId" id="depName">
            </select>*</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">学历</td>
            <td><select name="degree">
                <option value="0">专科</option>
                <option value="1">本科</option>
                <option value="2">博士</option>
                <option value="3">博士后</option>
            </select>*</td>
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