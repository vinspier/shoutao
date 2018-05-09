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
    <title>订单发货</title>
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
        function admin_deliveryOrder(oid) {
            var deliveryNumber = $("#deliveryNumber").val();
            if(confirm("确认发货吗")){
                location.href = "/admin_deliveryOrder?oid=" + oid+"&deliveryNumber="+deliveryNumber;
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
                        <span class="navbar-brand">订单发货
                        </span>
                    </div>
                </div>
            </nav>
            <hr/>
            <table class="table table-bordered">
                <tbody>
                <tr class="success">
                    <th colspan="1">用户名称:${order.user.username}</th>
                    <th colspan="2">订单编号:${order.oid}</th>
                    <th colspan="2">订单时间:<fmt:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd HH:mm;ss"/></th>
                    <th colspan="1">代发货
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
            </table>
        </div>
    </div>
</div>

<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/register.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <br/>
            <form class="form-horizontal" id="deliveryOrder" style="margin-top:5px;align-self: center" method="post" >
                <div  class="form-group">
                    <label for="deliveryNumber" class="col-sm-2 control-label">订单编号</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="deliveryNumber" name="inputPasswordOrigin" placeholder="请输入发货单号" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="button"  onclick="admin_deliveryOrder('${order.oid}')" width="100" value="确认发货" name="modify" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

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