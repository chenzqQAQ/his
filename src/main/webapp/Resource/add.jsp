<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <% String path = request.getContextPath();
    %>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css"/>
    <script type="text/javascript" src="<%=path%>/Js/jquery.js"></script>
    <%--<script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/common.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/messages_zh.js"></script>


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

        label.error {
            position: absolute;
            left: 400px;
            display: inline;
            color: red;
        }

        td {
            position: relative;
        }


    </style>
    <script type="text/javascript">
        var rule = {
            resName: {required: true},
            resUrl: {required: true},
            status: {required: true}
        };
        var message = {
            resName: {required: "请输入资源名"},
            resUrl: {required: "请输入资源地址"},
            status: {required: "请选择资源状态"}
        };
    </script>
    <script>
        $(function () {
            $('#backid').click(function () {
                window.location.href = "<%=path%>/Resource/index.jsp";
            });
            $('#form1').validate({
                // debug: true,
                rules: rule,
                messages: message,
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                }
            });
            $.validator.setDefaults({
                submitHandler: function () {
                    alert("提交事件");
                }
            })
        });
    </script>
</head>
<body>
<form id="form1" action="/his/resourceAction" method="post" class="definewidth m20">
    <input type="hidden" name="action" value="add"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">资源名称</td>
            <td><input type="text" name="resName"/></td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" name="resUrl"/></td>
        </tr>
        <tr>
            <td class="tableleft">是否有效</td>
            <td>
                <input type="radio" name="status" value="1"/> 有效
                <input type="radio" name="status" value="0"/> 无效
            </td>
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