<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>查看---2015</title>
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
		$('#backid').click(function(){
				window.location.href="index.jsp";
		 });
    });
    </script>
</head>
<body>
<form action="index.jsp" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>程俊</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td>护照</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td>534534534</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td>13124345432</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">座机</td>
        <td>01068765432</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>男</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>28</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td>1981-09-04</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td>chengjun@msn.com.cn</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>急诊科</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td>本科</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>工作热情积极表现非常的优异</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>