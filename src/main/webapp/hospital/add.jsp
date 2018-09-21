<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>录入住院信息--中软高科-2015</title>
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
        //清空挂号信息
        function pp() {
            $("#registerName").next().empty();
            $("#identifierType").next().empty();
            $("#identifierNum").next().empty();
            $("#socialSecurityNum").next().empty();
            $("#phoneNum").next().empty();
            $("#expenseFlag").next().empty();
            $("#sex").next().empty();
            $("#age").next().empty();
            $("#czFlag").next().empty();
            $("#remark").next().empty();
            $("#docName").next().empty();
            $("#depName").next().empty();
            $("#profession").next().empty();
        };
        $(function () {
            $('#backid').click(function () {
                window.location.href = "index.jsp";
            });
            $('input[name="medicalNum"]').blur(function () {
                var k = $('input[name=medicalNum]').val();
                var pattern = new RegExp("[`~!@#$^&*=|{}':;',\\[\\]<>《》/?~！@#￥……&*|{}【】‘；：”“'。，、？' ']");
                var reg = /^([0-9]+)$/;//全部为数字
                if (pattern.test(k)) {
                    $('input[name="medicalNum"]').next("span").text("病历号不能为特殊字符");
                    return false;
                } else if (k.indexOf(" ") != -1) {
                    $('input[name="medicalNum"]').next("span").text("病历号不能为空格");
                    return false;
                }
                $.ajax({
                    url: "/his/registerAjaxAction",
                    method: "POST",
                    data: {
                        "medicalNum": k
                    },
                    success: function (msg) {
                        var k = eval("(" + msg + ")");
                        // console.log(k);
                        if (parseInt(k['medicalNum']) === 0) {
                            $('input[name="medicalNum"]').next("span").text("病历号不存在，请输入正确病历号");
                            //清空挂号信息,上次信息可能存在
                            pp();
                        }
                        else {
                            // console.log("状态" + parseInt(k['flag']));
                            if (parseInt(k['flag']) === 3) {
                                $('input[name="medicalNum"]').next("span").text("病历号信息失效(已退号)，不能住院");
                                pp();
                            }
                            else {
                                if (parseInt(k['noFind']) === 1) {
                                    $('input[name="medicalNum"]').next("span").text("病历号已住院");
                                    //清空挂号信息,上次信息可能存在
                                    pp();
                                } else {
                                    //找到了病历号对应的挂号信息
                                    $('input[name="medicalNum"]').next("span").text("病历号存在,自动录入相关信息");
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
                            }
                        }
                    }
                })
            });

        });
    </script>
</head>
<body>
<form action="/his/inpatientAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <input type="hidden" name="action" value="add">
            <td width="10%" class="tableleft">病历号</td>
            <td><input type="text" name="medicalNum" value="" placeholder="输入病历号回车自动带出挂号人信息"/>
                <span>输入病历号回车自动带出挂号人信息</span>
            </td>
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
            <td><input type="text" name="nurse" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">床位号</td>
            <td><input type="text" name="bedNum" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">缴费押金</td>
            <td><input type="text" name="deposit" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">病情</td>
            <td><textarea name="illness"></textarea></td>
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