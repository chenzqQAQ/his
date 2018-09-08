<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改医生---2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/style.css"/>
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>


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
                window.location.href = "index.jsp";
            });
        });
    </script>
</head>
<body>
<form action="index.jsp" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td><input type="text" name="pname" value="程俊"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件类型</td>
            <td><select>
                <option>身份证</option>
                <option>护照</option>
                <option>军人证</option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">证件号</td>
            <td><input type="text" name="pname" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">手机</td>
            <td><input type="text" name="pname" value="15343457897"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">座机</td>
            <td><input type="text" name="pname" value="01088586875"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">性别</td>
            <td><input type="radio" name="pname" value="" checked/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="pname"
                                                                                             value=""/>女
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">出生年月</td>
            <td><input type="text" name="pname" value="1986-09-06"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">年龄</td>
            <td>28岁</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">电子邮箱</td>
            <td><input type="text" name="pname" value="chengjun@msn.com.cn"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">所属科室</td>
            <td><select>
                <option>急诊科</option>
                <option>骨科</option>
                <option>血液科</option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">学历</td>
            <td><select>
                <option>专科</option>
                <option>本科</option>
                <option>博士</option>
                <option>博士后</option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">备注</td>
            <td><textarea>此人表现优异工作非常的积极！</textarea></td>
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