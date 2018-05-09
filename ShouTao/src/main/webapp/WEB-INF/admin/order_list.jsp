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
    <title>所有用户订单</title>
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
        function admin_deleteOrderByOid(name,oid) {
            if(confirm("确认要删除 "+name+"用户 未付款的订单项吗")){
                location.href = "/admin_deleteOrderByOid?oid=" + oid;
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
                        <span class="navbar-brand">
                            <c:if test="${state == 0}">未付款</c:if>
                            <c:if test="${state == 1}">未发货</c:if>
                            <c:if test="${state == 2}">已发货</c:if>
                            <c:if test="${state == 3}">已完成</c:if>
                            <c:if test="${state == 4}">所有</c:if>
                            订单信息:${orderList.size()}条
                        </span>
                    </div>
                </div>
            </nav>
            <hr/>
            <c:forEach items="${orderList}" var="order">
            <table class="table table-bordered">
                <tbody>
                <tr class="success">
                    <th colspan="1">用户名称:${order.user.username}</th>
                    <th colspan="2">订单编号:${order.oid}</th>
                    <th colspan="2">订单时间:<fmt:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd HH:mm;ss"/></th>
                    <th colspan="1">
                        <c:if test="${order.state == 0}">状态：未付款</c:if>
                        <c:if test="${order.state == 1}">状态：待发货</c:if>
                        <c:if test="${order.state == 2}">状态：已发货</c:if>
                        <c:if test="${order.state == 3}">状态：已完成</c:if>
                    </th>
                </tr>
                <tr class="success">
                    <th colspan="1">联系人:${order.contactname}</th>
                    <th colspan="2">联系电话:${order.telephone}</th>
                    <th colspan="2">联系地址:${order.address}</th>
                    <th >总金额：￥${order.total}</th>
                </tr>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>
                        <c:if test="${order.state == 0}">
                            <a href="javascript:void(0)">提醒付款</a>
                            <a href="javascript:void(0)" onclick="admin_deleteOrderByOid('${order.user.username}','${order.oid}')">删除</a>
                        </c:if>
                        <c:if test="${order.state == 1}"><a href="/deliveryWriteNumber?oid=${order.oid}&uid=${order.user.uid}">发货</a> </c:if>
                        <c:if test="${order.state == 2}">
                            <a href="javascript:void (0)">提醒收货</a>
                        </c:if>
                        <c:if test="${order.state == 3}">
                            用户已确认收货
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
            <li><a>关于我们</a></li>
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