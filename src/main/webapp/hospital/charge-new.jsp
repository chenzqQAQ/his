<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
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

        #pms td {
            width: 200px;
        }


    </style>
    <script type="text/javascript">
        $(function () {
            $('#backid').click(function () {
                window.location.href = "/his/pJFindAction";
            });
            $.ajax({
                url: "/his/projectAjaxAction",
                success: function (msg) {
                    var k = eval("(" + msg + ")");
                    // console.log("找到全部收费项目");
                    // console.log(k);
                    $.each(k, function (index, i) {
                        var o = $("<option></option>");
                        o.val(i['id']).text(i['projectName']).attr("amount", i['amount'])
                            .appendTo($('#pm'));
                    })
                }

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
                            $('input[name="registerName"]').val("");
                        }
                        else {
                            // console.log("状态" + parseInt(k['flag']));
                            if (parseInt(k['flag']) === 3) {
                                $('input[name="medicalNum"]').next("span").text("病历号信息失效(已退号)，不能住院");
                                $('input[name="registerName"]').val("");
                            }
                            else {
                                $('input[name="medicalNum"]').next("span").text("病历号存在,自动录入姓名");
                                $('input[name="registerName"]').val(k['registerName']);
                            }
                        }
                    }
                })
            });

        });

        function add() {
            var table = $('#pms');
            if ($('#pm').val() != null && $('#pm').val() != "") {
                var value1 = $('#pm').val();
                var k = $('option[value=' + value1 + ']');
                console.log(k.val());
                var tr = $("<tr></tr>");
                var ip = $("<input type='hidden' name='pid'></input>");
                var ip1 = $("<input type='hidden' name='cost'></input>");
                ip.val(k.val());
                ip1.val(k.attr("amount"));
                var td2 = $("<td></td>");
                td2.text(k.text())
                var td3 = $("<td></td>");
                td3.text(k.attr("amount"));
                var a = $("<td wight='20px'><a href='#' onclick='del(this);return false'>删除</a></td>");
                tr.append(ip).append(ip1).append(td2).append(td3).append(a)
                    .appendTo(table);
            }
        }

        function del(k) {
            // console.log(k);
            var gg = $(k).parents("tr")[0];
            console.log(gg);
            gg.parentNode.removeChild(gg);
        }

        function findPj() {
            $.ajax({
                url: "/his/projectAjaxAction",
                data: {
                    action: "find",
                    id: $('input[name="medicalNum"]').val()
                },
                success: function (msg) {
                    var k = eval("(" + msg + ")");
                    var table = $('#pms');
                    $.each(k, function (index, i) {
                        var tr = $("<tr></tr>");
                        var td2 = $("<td></td>");
                        td2.text(i['payName']);
                        var td3 = $("<td></td>");
                        td3.text(i['chargeAmount']);
                        tr.append(td2).append(td3)
                            .appendTo(table);
                    })
                }
            })
        }
    </script>
</head>
<body>
<form action="/his/pjAddAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">病历号</td>
            <td><input name="medicalNum" value="${param.medicalNum}"><span></span>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td><input name="registerName" value="${param.name}" disabled></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">收费项目</td>
            <td><select id="pm">
            </select>
                <a href="#" onclick="add();return false">添加</a>&nbsp&nbsp&nbsp
                <a href="#" onclick="findPj();return false">导入项目</a>
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
            <td colspan="2">
                <table id="pms">
                    <tr>
                        <td class="tableleft">收费项目</td>
                        <td class="tableleft">收费金额</td>
                    </tr>
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