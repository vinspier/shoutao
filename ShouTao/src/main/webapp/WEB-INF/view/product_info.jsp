<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
        #footer_bottom{
            position: fixed;
            bottom: 0px;
        }
    </style>
</head>
<body>

<%@include file="head.jsp" %>

<div class="container">
    <div class="row">
        <div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
            <a href="/index">首页&nbsp;&nbsp;&gt;</a>
            <a href="/getByPage?pageNumber=1&cid=${category.cid}">${category.cname}&nbsp;&nbsp;&gt;</a>
            <a>${fn:substring(product.pname,0 ,10 )}···</a>
        </div>

        <div style="margin:0 auto;width:950px;">
            <div class="col-md-6">
                <img style="opacity: 1;width:400px;height:350px;" title="" class="medium" src="../${product.pimage}">
            </div>

            <div class="col-md-6">
                <div><strong>${product.pname}</strong></div>
                <div style="border-bottom: 1px dotted #dddddd;width:350px;margin:10px 0 10px 0;">
                    <div>编号：${product.pid}</div>
                </div>

                <div style="margin:10px 0 10px 0;">商城价: <strong
                        style="color:#ef0101;">￥：${product.shop_price}元/份</strong> 市场价：
                    <del>￥${product.market_price}元/份</del>
                </div>

                <div style="margin:10px 0 10px 0;">促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
                                                          style="background-color: #f07373;">限时抢购</a></div>

                <div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">
                    <div style="margin:5px 0 10px 0;">白色</div>

                    <form action="/addToCart" method="post" id="cartMessage">
                        <input type="hidden" name="pid" value="${product.pid}">
                        <div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">购买数量:
                            <input id="quantity" name="count" value="1" maxlength="4" size="10" type="text">
                            <input type="button" id="delete" onclick="cutDown()" name="delete" value="-" style="width: 30px">
                            <input type="button" id="add" onclick="addUp()" name="add" value="+" style="width: 30px">
                        </div>
                    </form>
                    <form action="/makeSureDirectBuy" method="post" id="directBuyForm">
                        <input type="hidden" name="pid" value="${product.pid}">
                    </form>

                    <div style="margin:20px 0 10px 0;;text-align: center;">
                        <a href="javascript:void(0)" onclick="subForm()">
                            <input style="background: url('${pageContext.request.contextPath}/images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
                                   value="加入购物车" type="button">
                        </a>
                        <a href="javascript:void(0)" onclick="subDirectBuyForm()">
                            <input style="background: url('${pageContext.request.contextPath}/images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
                                   value="直接购买" type="button">
                        </a>
                    </div>

                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div style="width:950px;margin:0 auto;">
            <div style="background-color:#d3d3d3;width:400px;padding:10px 10px;margin:10px 0 10px 0;">
                <strong>商品简介</strong>
            </div>

            <div>
                <img src="../${product.pimage}">
                <th>${product.pname}</th>
            </div>

            <div style="background-color:#d3d3d3;width:400px;padding:10px 10px;margin:10px 0 10px 0;">
                <strong>商品详情</strong>
            </div>
            <div style="width: 700px">
                <strong>${product.pdesc}</strong>
            </div>

        </div>
    </div>

    <div class="container-fluid" id="footer_bottom">
        <div style="margin-top:50px;">
        </div>

        <div style="text-align: center;margin-top: 5px;">
            <ul class="list-inline">
                <li><a href="info.html">关于我们</a></li>
                <li><a>联系我们</a></li>
                <li><a>招贤纳士</a></li>
                <li><a>法律声明</a></li>
                <li><a>友情链接</a></li>
                <li><a>支付方式</a></li>
                <li><a>配送方式</a></li>
                <li><a>服务声明</a></li>
                <li><a>广告声明</a></li>
            </ul>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    function subForm() {
        document.getElementById("cartMessage").submit();
    }
    function getCount() {
        var directBuyForm = document.getElementById("directBuyForm");
        var count = $("#quantity").val();
        var inputElement = document.createElement("input");
        inputElement.setAttribute("type", "hidden");
        inputElement.setAttribute("name", "count");
        inputElement.setAttribute("value", count);
        directBuyForm.appendChild(inputElement);
    }
    function subDirectBuyForm() {
        getCount();
        document.getElementById("directBuyForm").submit();
    }

    function addUp() {
        let count = $("#quantity").val();
        console.log(count);
        count++;
        console.log(count);
        $("#quantity").val(count);
    }
    function cutDown() {
        let count = $("#quantity").val();
        if(count<=1){
            $("#quantity").focus();
        }else{
            count--;
            console.log(count);
            $("#quantity").val(count);
        }
    }
</script>

</html>