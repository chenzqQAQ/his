<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>发药--中软高科-2015</title>
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

        ul {
            list-style: none;
            margin: 0px;
        }

        li {
            margin: 0px;
            cursor: pointer;
        }

        .mydiv {
            background-color: white;
            border: 1px black solid;
            width: 210px;
        }

        .red {
            background-color: grey;
        }

    </style>
    <script type="text/javascript">
        $(function () {
            $('#backid').click(function () {
                window.location.href = "/his/dispenedFindAction";
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
                    //将input文本值设置为下拉列表首个选项，初始化
                    $('#drugName1').val($('option:eq(0)').text());
                }
            });
            //实现类似百度输入显示相似内容
            $('#drugName1').keyup(function () {
                if (this.value != "") {
                    //异步传输书写内容
                    $.ajax({
                        url: "/his/drugAjaxAction",
                        //后台模糊查询,like %%
                        data: {"name": this.value},
                        success: function (msg) {
                            //获取input文本框
                            var dn = $('#drugName1');
                            //清空提示信息
                            dn.next("div").html("");
                            //创建提示列表
                            var ul = $("<ul></ul>");
                            //解析返回的json格式数组
                            var k = eval("(" + msg + ")");
                            if (k.length != 0) {
                                //有提示信息时，显示提示框
                                dn.next("div").show();
                            }
                            //向提示块中添加信息
                            $.each(k, function (index, a) {
                                var str = a["drugName"];
                                var key = a["drugID"];
                                var li = $("<li></li>");
                                li.text(str).click(function () {
                                    //选中提示信息时,对文本框和下拉框同时赋值
                                    dn.val(str);
                                    $("#drugName").val(key);
                                    //选择完提示信息时,隐藏提示块
                                    dn.next("div").hide();
                                }).mouseover(function () {
                                    //鼠标移动到提示信息时,背景变为灰色
                                    li.attr("class", "red");
                                })
                                    .mouseout(function () {
                                        //鼠标移出时,去掉背景颜色
                                        li.removeAttr("class");
                                    })
                                    //单个提示信息li添加到ul中
                                    .appendTo(ul);
                            });
                            ul.appendTo(dn.next("div"))
                        }
                    });
                }
                else {
                    //当输入框清空时,隐藏提示信息
                    $('#drugName1').next("div").hide();
                }
            })
            $('div.alldiv').blur(function () {
                //整个文本框和下拉框的包含块失去焦点时，隐藏提示信息
                $('#drugName1').next("div").hide();
                //文本框强制恢复下拉列表选中值(避免没触发提示信息点击事件，导致文本框和下拉列表值不对应)
                var value = $('#drugName').find('option:selected').text();
                $('#drugName1').val(value);

            }).mouseleave(function () {
                //鼠标移开，给药品名称块获取焦点,以便触发失去焦点事件(块需要配合tabindex才能有焦点事件)
                $('div.alldiv').focus();
            })
            //下拉列表值改变时,更新文本框值
            $('#drugName').change(function () {
                var value = $('#drugName').find('option:selected').text();
                $('#drugName1').val(value);
            })
        });
    </script>
</head>
<body>
<form action="/his/dispenedAddAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">病历号</td>
            <td><input name="medicalNum" value="${param.medicalNum}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td><input name="rName" value="${param.rName}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">药品名称</td>
            <td>
                <%-- 块增加tabindex 以便触发焦点的相关事件--%>
                <div class="alldiv" tabindex="1" style="outline: none;">
                    <select id="drugName" name="drugId"
                            style="width:210px;height:30px;border-radius: 3px;margin-bottom: 0px">

                    </select>
                    <%-- 禁止文本框自动完成(影响提示信息显示)--%>
                    <input id="drugName1" name="drugId1" autocomplete="off"
                           style="width:150px;height: 26px;margin-left: -212px;border:none;outline: none;"/>
                    <div class="mydiv" hidden></div>
                </div>
            </td>
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