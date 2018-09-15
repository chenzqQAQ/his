<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
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
            width: 500px;
            height: 200px;
        }


    </style>

    <script type="text/javascript">
        $(function () {
            $('#backid').click(function () {
                window.location.href = "/his/drugFindNameAction";
            });
        });
    </script>
</head>
<body>
<form action="/his/drugUpdateAction" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">药品编号</td>
            <td>
                <input type="hidden" name="drugID" value="${drug.drugID}"/>
                <input type="text" name="" value="${drug.drugID}" disabled/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">图片</td>

            <td><img src="${drug.drugUrl}" title="药品图片" alt="图片找不到"/>
                <%--<input type="file" name="drugUrl" value="${drug.drugUrl}"/>--%>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">进价</td>
            <td><input type="text" name="purchasePrice" value="${drug.purchasePrice}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">售价</td>
            <td><input type="text" name="sellingPrice" value="${drug.sellingPrice}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">药品名称</td>
            <td><input type="text" name="drugName" value="${drug.drugName}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">药品类型</td>
            <td><input id="type0" type="radio" name="drugType" value="0"/>处方药&nbsp;&nbsp;&nbsp;
                <input id="type1" type="radio" name="drugType" value="1"/>中药&nbsp;&nbsp;&nbsp;
                <input id="type2" type="radio" name="drugType" value="2"/>西药&nbsp;&nbsp;&nbsp;
                <input id="type3" type="radio" name="drugType" value="3"/>非处方药
            </td>
            <script>
                document.getElementById('type' +${drug.drugType}).setAttribute("checked", 'true');
            </script>
        </tr>
        <tr>
            <td width="10%" class="tableleft">简单描述</td>
            <td><input type="text" name="description" value="${drug.description}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">生产日期</td>
            <td><input type="text" name="productionDate" value="${drug.productionDate}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">过期日期</td>
            <td><input type="text" name="overdueDate" value="${drug.overdueDate}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">保质期</td>
            <td><input type="text" name="qualityLife" value="${drug.qualityLife}"/>天</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">详细描述</td>
            <td><textarea name="detailedDes">${drug.detailedDes}</textarea>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">生产厂商</td>
            <td><textarea name="manufacturer">${drug.manufacturer}</textarea></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">服用说明</td>
            <td><input type="text" name="takingDes" value="${drug.takingDes}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">总的进货量</td>
            <td><input type="text" name="totalVolume" value="${drug.totalVolume}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">剩余量</td>
            <td><input type="text" name="inventory" value="${drug.inventory}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">备注</td>
            <td><textarea name="remark">${drug.remark}</textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <center>
                    <button type="submit" class="btn btn-primary" type="button">更新</button> &nbsp;&nbsp;<button
                        type="button" class="btn btn-success" name="backid" id="backid">返回列表
                </button>
                </center>
            </td>
        </tr>
    </table>
</form>
</body>
</html>