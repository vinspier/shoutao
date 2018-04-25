<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的订单</title>
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

        .container .row div {
            /* position:relative;
            float:left; */
        }

        font {
            color: #3164af;
            font-size: 18px;
            font-weight: normal;
            padding: 0 10px;
        }

        #footer_bottom{
            position: fixed;
            bottom: 0px;
        }

    </style>
    <script type="text/javascript">
        function deleteOrderItem(oid) {
            if(confirm("确定取消该订单吗？")){
                location.href = "/deleteOrderByOid?oid=" + oid;
            }
        }
        function deleteOrderItem1(oid) {
            if(confirm("确定删除该订单吗？")){
                location.href = "/deleteOrderByOid?oid=" + oid;
            }
        }
    </script>
</head>

<body>

<%@include file="head.jsp" %>

<div class="container">
    <div class="row">
        <div style="margin:0 auto; margin-top:10px;width:950px;">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <span class="navbar-brand">订单信息</span>
                    </div>
                    <div>
                        <ul class="nav navbar-nav">
                            <li><a href="/order_list">所有订单(${allCounts})</a></li>
                            <li><a href="/getOrdersByState?uid=${user.uid}&state=0">待付款(${unPay})</a></li>
                            <li><a href="/getOrdersByState?uid=${user.uid}&state=1">待发货(${unDelivery})</a></li>
                            <li><a href="/getOrdersByState?uid=${user.uid}&state=2">已发货(${alreadyDelivery})</a></li>
                            <li><a href="/getOrdersByState?uid=${user.uid}&state=3">已完成(${alreadyDone})</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <hr/>
            <c:forEach items="${orders}" var="order">
            <table class="table table-bordered">
                <tbody>
                <tr class="success">
                    <th colspan="2">订单编号:${order.oid}</th>
                    <th colspan="1">
                        <c:if test="${order.state == 0}">
                            状态：<a href="/getOrderById?oid=${order.oid}">未付款</a>
                        </c:if>
                        <c:if test="${order.state == 1}">状态：待发货</c:if>
                        <c:if test="${order.state == 2}">状态：已发货</c:if>
                        <c:if test="${order.state == 3}">状态：已完成</c:if>
                    </th>
                    <c:if test="${order.state == 2 || order.state == 3}">
                        <th>发货单号:${order.deliveryNumber}</th>
                    </c:if>
                    <th colspan="2">订单时间:<fmt:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd HH:mm;ss"/></th>
                    <c:if test="${order.state == 0 || order.state == 1}">
                        <th></th>
                    </c:if>
                </tr>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>
                        <c:if test="${order.state == 1}"><a href="javascript:void (0)">提醒发货</a> </c:if>
                        <c:if test="${order.state == 0}">
                            <a href="/getOrderById?oid=${order.oid}">付款</a>
                            <a href="javascript:void(0);" onclick="deleteOrderItem('${order.oid}')">取消订单</a>
                        </c:if>
                        <c:if test="${order.state == 3}">
                            <a href="javascript:void(0);" onclick="deleteOrderItem1('${order.oid}')">删除订单</a>
                        </c:if>
                        <c:if test="${order.state == 2}">
                            <a href="/#">确认收货</a>
                        </c:if>
                    </th>
                </tr>
                <c:forEach items="${order.orderItems}" var="orderItem">
                <tr class="active">

                    <td width="60" width="40%">
                        <a href="/getByPid?pid=${orderItem.product.pid}">
                                <%--<input type="hidden" name="id" value="22">--%>
                            <img src="${pageContext.request.contextPath}/${orderItem.product.pimage}" width="70"
                                 height="60">
                        </a>
                    </td>

                    <td width="30%">
                        <a target="_blank"
                           href="/getByPid?pid=${orderItem.product.pid}">${orderItem.product.pname}</a>
                    </td>

                    <td width="20%">
                        ￥${orderItem.product.shop_price}
                    </td>

                    <td width="10%">
                            ${orderItem.counts}
                    </td>

                    <td width="15%">
                        <span class="subtotal">￥${orderItem.subtotal}</span>
                    </td>
                    <td></td>
                    </c:forEach>
                </tr>
                </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<div class="container-fluid" id="footer_bottom">
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

</body>

</html>