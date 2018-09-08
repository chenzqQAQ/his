<%@ page import="com.youma.vo.Users" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/30
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/his/jquery-3.3.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="./Js/My97DatePicker/WdatePicker.js"></script>
    <script>
        // alert();
        var datepicker1 = {
            lang: 'zh-cn',
            dateFmt: 'yyyy-MM-dd',
            minDate: '1949-10-1',
            maxDate: '#F{$dp.$D(\'end\')}' || new Date(),
            startDate: '1990-1-1',
            readOnly: true,
            highLineWeekDay: true,
            isShowWeek: true
        };
        var datepicker2 = {
            lang: 'zh-cn',
            dateFmt: 'yyyy-MM-dd',
            minDate: '#F{$dp.$D(\'start\')}',
            startDate: '#F{$dp.$D(\'start\')}',
            maxDate: new Date(),
            readOnly: true,
            highLineWeekDay: true,
            isShowWeek: true
        };
        $(function () {
            // function a() {
            //     alert('bug');
            // }
            $('#bu').click(function () {
                $.ajax({
                    url: "ajaxAction",
                    data: {
                        "action": "del",
                        "userId": $('#name').val()
                    },
                    success: function (msg) {
                        console.log("成功");
                    },
                    error: function () {
                        console.log("失败");
                    }

                })
                return false;
            });
            // console.log(document.getElementsByTagName('p'));
        });
    </script>
</head>
<body>
<form id="form1">
    <input id="name" value="123"/>
    <button name="bu" id="bu">点我</button>
</form>
<p>1</p>
<p>2</p>
<p>3</p>
<p>4</p>
<p>5</p>
<p>6</p>
<form>
    开始时间<input type="text" id="start" class="Wdate" autocomplete="off" onfocus="WdatePicker(datepicker1)"><br/>
    结束时间<input type="text" id="end" class="Wdate" autocomplete="off" onfocus="WdatePicker(datepicker2)"><br/>
</form>
</body>
</html>
