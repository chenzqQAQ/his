<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加药品---2015</title>
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
    <script type="text/javascript" src="/his/Js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../Js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../Js/messages_zh.js"></script>


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

        img {
            width: 200px;
            height: 150px;
            float: left;
        }

        #imgdiv:after {
            clear: both;
            display: block;
            content: "";
            width: 0;
            height: 0;
            visibility: hidden;
        }

        #imgdiv {
            position: relative;
            overflow: hidden;
        }
    </style>
    <script type="text/javascript">
        var datepicker1 = {
            lang: 'zh-cn',
            dateFmt: 'yyyy-MM-dd',
            minDate: '2000-1-1',
            maxDate: '#F{$dp.$D(\'overdueDate\')||\'%y-%M-%d\'}',
            startDate: '2000-1-1',
            readOnly: true,
            highLineWeekDay: true,
            isShowWeek: true
        };
        var datepicker2 = {
            lang: 'zh-cn',
            dateFmt: 'yyyy-MM-dd',
            minDate: '#F{$dp.$D(\'productionDate\')||\'%y-%M-%d\'}',
            startDate: new Date() || '#F{$dp.$D(\'productionDate\')}',
            readOnly: true,
            highLineWeekDay: true,
            isShowWeek: true
        };
    </script>
    <script type="text/javascript">
        $(function () {
            $('#backid').click(function () {
                window.location.href = "/his/drugFindNameAction";
            });
            $('input[name="qualityLife"]').mouseover(function () {
                var date1 = $('#productionDate').val();
                var date2 = $('#overdueDate').val();
                if (date1 !== '' && date2 !== '') {
                    console.log(date1);
                    console.log(date2);
                    var long = new Date(date2).getTime() - new Date(date1).getTime();
                    var day = Math.floor(long / (1000 * 60 * 60 * 24 * 30));
                    console.log(day);
                    $('input[name="qualityLife"]').val(day);
                }
            })
            $('#img').hide();
        });

        function readFile() {
            var img = $('#imgfile')[0].files[0];
            if (window.FileReader) {
                var fileReader = new FileReader();
                fileReader.onload = function () {
                    $('#img').prop("src", fileReader.result);
                    $('#img').show();
                };
                fileReader.readAsDataURL(img);
            }

        }

        function oo() {
            $('#imgfile').click();
        }

        /**
         * 校验规则
         */
        var rules = {
            drugID: {
                required: true
            },
            imgfile: {
                required: true
            },
            purchasePrice: {
                required: true,
                number: true
            }, sellingPrice: {
                required: true,
                number: true
            }, drugName: {
                required: true
            },
            drugType: {
                required: true
            },
            productionDate: {
                required: true
            },
            overdueDate: {
                required: true
            },
            description: {
                required: true
            },
            totalVolume: {
                required: true
            }

        };
        /**
         * 错误提示信息
         */
        var messages = {
            drugID: {
                required: "请输入药品编号"
            },
            imgfile: {
                required: "请选择药品图片"
            },
            purchasePrice: {
                required: "请输入药品进价",
                number: "请填数字"
            }, sellingPrice: {
                required: "请输入药品售价",
                number: "请填数字"
            }, drugName: {
                required: "请输入药品名"
            },
            drugType: {
                required: "请选择药品类型"
            },
            productionDate: {
                required: "请选择生产日期"
            },
            overdueDate: {
                required: "请选择过期日期"
            },
            description: {
                required: "请输入简单描述"
            },
            totalVolume: {
                required: "请输入进货量"
            }
        };
        /**
         * 启动校验框架
         */
        $(function () {
            $("#form1").validate(
                {
                    // "debug": true,
                    //失去焦点验证
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    "rules": rules,
                    "messages": messages,
                    errorPlacement: function (error, element) {
                        error.appendTo(element.parent());
                    }
                }
            );
            $.validator.setDefaults({
                submitHandler: function () {
                    alert("提交事件");
                }
            });
        })
    </script>
</head>
<body>
<form action="../drugAddAction" method="post" class="definewidth m20" enctype="multipart/form-data" id="form1">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">药品编号</td>
            <td><input type="text" name="drugID" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">图片</td>
            <td>
                <div id="imgdiv"><img src="" id="img" style=""></div>
                <button onclick="oo();return false">上传图片</button>
                <input id="imgfile" type="file" name="drugUrl" value="" onchange="readFile()" hidden="hidden"/>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">进价</td>
            <td><input type="text" name="purchasePrice" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">售价</td>
            <td><input type="text" name="sellingPrice" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">药品名称</td>
            <td><input type="text" name="drugName" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">药品类型</td>
            <td><input type="radio" name="drugType" value="0" checked/>处方药&nbsp;&nbsp;&nbsp;
                <input type="radio" name="drugType" value="1"/>中药&nbsp;&nbsp;&nbsp;
                <input type="radio" name="drugType" value="2"/>西药&nbsp;&nbsp;&nbsp;
                <input type="radio" name="drugType" value="3"/>非处方药
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">简单描述</td>
            <td><input type="text" name="description" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">生产日期</td>
            <td>
                <input type="text" id="productionDate" name="productionDate" class="Wdate" autocomplete="off"
                       onfocus="WdatePicker(datepicker1)"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">过期日期</td>
            <td>
                <input type="text" id="overdueDate" name="overdueDate" class="Wdate" autocomplete="off"
                       onfocus="WdatePicker(datepicker2)"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">保质期</td>
            <td><input type="text" name="qualityLife" value=""/>月</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">详细描述</td>
            <td><textarea name="detailedDes"></textarea>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">生产厂商</td>
            <td><textarea name="manufacturer"></textarea></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">服用说明</td>
            <td><input type="text" name="takingDes" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">总的进货量</td>
            <td><input type="text" name="totalVolume" value=""/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">剩余量</td>
            <td><input type="text" name="inventory" value="300"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">备注</td>
            <td><textarea name="remark"></textarea></td>
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