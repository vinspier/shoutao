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
    <title>订单详情</title>
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
    </style>
</head>

<body>

<%@include file="head.jsp" %>

<div class="container">
    <div class="row">

        <div style="margin:0 auto;margin-top:10px;width:950px;">
            <strong>商品详情：</strong>
            <hr/>
            <table class="table table-bordered">
                <tbody>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>金额</th>
                </tr>
                    <tr class="active">
                        <td width="60" width="40%">
                            <a target="_blank" href="/getByPid?pid=${product.pid}">
                                    <%--<input type="hidden" name="id" value="22">--%>
                                <img src="${pageContext.request.contextPath}/${product.pimage}" width="70"
                                     height="60">
                            </a>
                        </td>
                        <td width="30%">
                            <a target="_blank" href="/getByPid?pid=${product.pid}">${product.pname}</a>
                        </td>
                        <td width="20%">
                            ￥${product.shop_price}
                        </td>
                        <td width="10%">
                                ${count}
                        </td>
                        <td width="15%">
                            <span class="subtotal">￥${totalPay}</span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div style="margin:0 auto;margin-top:10px;width:950px;">
        <hr/>
        <strong>请填写联系信息：</strong>
        <form class="form-horizontal" id="form-horizontal" action="/createDirectBuyOrder" method="post"
              style="margin-top:5px;margin-left:150px;">

            <input type="hidden" name="pid" value="${product.pid}">
            <input type="hidden" name="count" value="${count}">

            <div class="form-group">
                <label for="address" class="col-sm-1 control-label">地址</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="address" id="address" value="${user.receivedAddress}"
                           placeholder="请输入收货地址">
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-sm-1 control-label">收货人</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="contactname" id="username" value="${user.realname}"
                           placeholder="请输收货人">
                </div>
            </div>
            <div class="form-group">
                <label for="telephone" class="col-sm-1 control-label">电话</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="telephone" id="telephone" value="${user.telephone}"
                           placeholder="请输入联系方式">
                </div>
            </div>
        </form>

        <hr/>

    </div>

    <div style="margin-right:130px;">
        <div style="text-align:right;">
            付款金额: <strong style="color:#ff6600;">￥<span class="total" id="total">${totalPay}</span>元</strong>
        </div>
        <div style="text-align:right;margin-top:10px;margin-bottom:10px;">
            <a href="javascript:void(0)" onclick="payItemSubmit()">
                <input type="button" width="100" value="确认提交" name="submit" border="0"
                       style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                               height:35px;width:100px;color:white;">
            </a>
        </div>
    </div>

</div>

<div style="margin-top:50px;">
    <img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势"/>
</div>

<div style="text-align: center;margin-top: 5px;">
    <ul class="list-inline">
        <li><a>关于我们</a></li>
        <li><a>联系我们</a></li>
        <li><a>招贤纳士</a></li>
        <li><a>法律声明</a></li>
        <li><a>友情链接</a></li>
        <li><a target="_blank">支付方式</a></li>
        <li><a target="_blank">配送方式</a></li>
        <li><a>服务声明</a></li>
        <li><a>广告声明</a></li>
    </ul>
</div>

</body>
<script type="text/javascript">
    function payItemSubmit() {
        document.getElementById("form-horizontal").submit();
    }
</script>

</html>