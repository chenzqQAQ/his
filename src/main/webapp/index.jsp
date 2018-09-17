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
        var config1=[
            {
                id: '1', menu: [
                    {
                        text: '快速通道', items: [
                            {id: '2', text: '挂号信息管理', href: 'registerFindAction'},
                            {id: '3', text: '门诊医生管理', href: 'doctorFindAllAction'},
                            {id: '4', text: '药品管理', href: 'drugFindNameAction'},
                            {id: '5', text: '住院办理', href: 'inpFindAction?action=findAll'},
                            {id: '6', text: '收费项目登记', href: 'pJFindAction'},
                            {id: '7', text: '在院发药', href: 'dispenedFindAction'},
                            {id: '8', text: '住院结算', href: 'hospital/account.html'},
                            {id: '9', text: '月营业额统计', href: ''},
                            {id: '10', text: '年营业额统计', href: ''},

                            {id: '11', text: '用户管理', href: 'usersFindAllAction'},
                            {id: '12', text: '角色管理', href: 'Role/index.html'},
                            {id: '13', text: '资源管理', href: 'resourceAction?action=findAll'},
                            {id: '14', text: '密码设置	', href: 'Resource/index.html'}
                        ]
                    }
                ]
            }
        ];
        var config;
        if (parseInt(${sessionScope.role}) === 0) {
            config = [
                {
                    id: '1', menu: [

                        {
                            text: '快速通道', items: [

                                {id: '2', text: '挂号信息管理', href: 'registerFindAction'},
                                {id: '3', text: '门诊医生管理', href: 'doctorFindAllAction'},
                                {id: '4', text: '药品管理', href: 'drugFindNameAction'},
                                {id: '5', text: '住院办理', href: 'inpFindAction?action=findAll'},
                                {id: '6', text: '收费项目登记', href: 'pJFindAction'},
                                {id: '7', text: '在院发药', href: 'dispenedFindAction'},
                                {id: '8', text: '住院结算', href: 'hospital/account.html'},
                                {id: '9', text: '月营业额统计', href: ''},
                                {id: '10', text: '年营业额统计', href: ''},

                                {id: '11', text: '用户管理', href: 'usersFindAllAction'},
                                {id: '12', text: '角色管理', href: 'Role/index.html'},
                                {id: '13', text: '资源管理', href: 'resourceAction?action=findAll'},
                                {id: '14', text: '密码设置	', href: 'Resource/index.html'}

                            ]
                        }


                    ]
                }

            ];
        }
        else if (parseInt(${sessionScope.role}) === 1) {
            config = [
                {
                    id: '1', menu: [

                        {
                            text: '快速通道', items: [

                                {id: '2', text: '挂号信息管理', href: 'registerFindAction'},
                                {id: '3', text: '门诊医生管理', href: 'doctorFindAllAction'},
                                {id: '4', text: '药品管理', href: 'drugFindAllAction'},
                                {id: '5', text: '住院办理', href: 'inpFindAction?action=findAll'},
                                {id: '6', text: '收费项目登记', href: 'pJFindAction'},
                                {id: '7', text: '在院发药', href: 'dispenedFindAction'},
                                {id: '8', text: '住院结算', href: 'hospital/account.html'},
                                {id: '9', text: '月营业额统计', href: ''},
                                {id: '10', text: '年营业额统计', href: ''},
                            ]
                        }


                    ]
                }

            ];
        }
        else {
            config = [
                {
                    id: '1', menu: [

                        {
                            text: '快速通道', items: [

                                {id: '2', text: '挂号信息管理', href: 'registerFindAction'},
                                {id: '3', text: '门诊医生管理', href: 'doctorFindAllAction'},
                                {id: '4', text: '药品管理', href: 'drugFindAllAction'},
                                {id: '5', text: '住院办理', href: 'inpFindAction?action=findAll'},
                                {id: '6', text: '收费项目登记', href: 'pJFindAction'},
                                {id: '7', text: '在院发药', href: 'dispenedFindAction'},
                                {id: '8', text: '住院结算', href: 'hospital/account.html'},
                            ]
                        }


                    ]
                }

            ];
        }

        new PageUtil.MainPage({
            //直接访问主页面，不用登陆
            //使用时去掉1
            modulesConfig: config1
        });
        $(function () {
            //防止直接访问
            <%--if(${empty sessionScope.realName})--%>
                <%--window.location.href="login.jsp";--%>
        })
    });
</script>
</body>
</html>