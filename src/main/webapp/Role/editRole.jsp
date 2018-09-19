<%@ page import="com.youma.vo.Role" %>
<%@ page import="com.youma.vo.Resources" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/his/Css/style.css"/>
    <script type="text/javascript" src="/his/Js/jquery.js"></script>
    <script type="text/javascript" src="/his/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/his/Js/ckform.js"></script>
    <script type="text/javascript" src="/his/Js/common.js"></script>

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
    <%
        //将传来的list转为array,以便js解析
        Role role = (Role) request.getAttribute("role");
        List<Resources> list = role.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            Resources rs = list.get(i);
            String str = String.format("{resID:%d}", rs.getResID());
            if (i != 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        sb.append("]");
        request.setAttribute("sb", sb);
    %>
    <script type="text/javascript">
        $(function () {
            //异步请求全部权限信息
            $.ajax({
                url: "/his/resourceAjaxAction",
                success: function (msg) {
                    var k = eval("(" + msg + ")");
                    console.log(k);
                    $.each(k, function (index, i) {
                        var check = $('<input type="checkbox" name="resources"/>');
                        var ul = $('<ul></ul>');
                        var label = $('<label class="checkbox inline"></label>');
                        label.text(i['resName']);
                        check.val(i['resID']).appendTo(label);
                        label.appendTo(ul);
                        ul.appendTo($('#resources'));
                    });
                    var list = eval(${sb});
                    $.each(list, function (index, i) {
                        var oo = i["resID"];
                        console.log(oo);
                        $("input[name='resources'][value=\'" + oo + "\']").prop("checked", true);
                    });
                }
            });
        });
    </script>
</head>
<body>
<form action="roleAddAction?action=edit" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <input name="roleId" value="${role.roleID}" type="hidden">
        <tr>
            <td width="10%" class="tableleft">角色名称</td>
            <td><input type="text" name="roleName" value="${role.roleName}"/></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="status" value="1"/> 启用
                <input type="radio" name="status" value="0"/> 禁用
            </td>
            <script>
                $('input[name="status"][value=${role.status}]').prop("checked", true);
            </script>
        </tr>
        <tr>
            <td class="tableleft">权限</td>
            <td id="resources">
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">更新</button> &nbsp;&nbsp;<button
                    type="button" class="btn btn-success" name="backid" id="backid">返回列表
            </button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

        $('#backid').click(function () {
            window.location.href = "/his/roleFindAction";
        });

    });
</script>