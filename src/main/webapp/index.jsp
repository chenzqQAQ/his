<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>xx市第二人民医院信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${sessionScope.realName}</span>

        <a href="login.jsp" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
        </div>
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-home">医院管理系统</div>
            </li>

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.6.js"></script>
<script type="text/javascript" src="assets/js/bui.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>
    BUI.use('common/main', function () {
        //方便测试,不用登陆
        var config;
        $.ajax({
            url: "/his/resAjaxAction?action=kk",
            data: {
                //全部资源，方便测试
                roleId: ${sessionScope.role}
            },
            type: "POST",
            async: false,
            success: function (msg) {
                if(msg=="0")
                {
                    // console.log("可用");
                    aa();
                }
                else{
                    alert("角色被禁用，请联系管理");
                }
            },
            error:function (msg) {
                console.log(msg);
                console.log("失败");
            }

        });
        $(function () {
            //防止直接访问
            <%--if(${empty sessionScope.realName})--%>
            <%--window.location.href="login.jsp";--%>
            // console.log(111);
        })
    });
    //角色启用,才能有目录
    function aa() {
        $.ajax({
            url: "/his/resAjaxAction",
            data: {
                //全部资源，方便测试
                roleId: ${sessionScope.role}
            },
            type: "POST",
            async: false,
            success: function (msg) {
                // console.log(msg);
                var str = eval(msg);
                config = [
                    {
                        id: '1', menu: [{
                            text: '快速通道',
                            items: str
                        }]
                    }
                ];
            },
            error:function (msg) {
                console.log(msg);
                console.log("失败");
            }

        });
        new PageUtil.MainPage({
            modulesConfig: config
        });
    }
</script>
</body>
</html>