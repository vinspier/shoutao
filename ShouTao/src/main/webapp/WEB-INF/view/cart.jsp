<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>购物车</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <script type="text/javascript">
        function checkedCount() {
            let count = 0;
            var items = $(".cartItems");
            for (var j = 0; j < items.length; j++) {
                if (items[j].checked) {
                    count++;
                }
            }
            if (count > 0) {
                checkedPost()
            } else {
                alert("请至少勾选一项商品");
            }
        }

        function checkedPost() {
            var formItemIds = document.getElementById("itemCheckId");
            var items = $(".cartItems");
            for (var i = 0; i < items.length; i++) {
                if (items[i].checked) {
                    var itemId = (document.getElementById("itemId" + items[i].value).value) + "";
                    var inputElement = document.createElement("input");
                    inputElement.setAttribute("type", "text");
                    inputElement.setAttribute("name", "itemId");
                    inputElement.setAttribute("value", itemId);
                    formItemIds.appendChild(inputElement);
                }
            }
            document.getElementById("itemCheckId").submit();
        }
    </script>
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
</head>

<body>

<%@include file="head.jsp" %>
<div class="container">
    <div class="row">

        <c:if test="${empty cartList}">
            <h3>购物车空空如也,亲,请先去<a href="/index">逛逛</a>吧~~~~~~~~~~~ </h3>
        </c:if>
        <c:if test="${not empty cartList}">
        <div style="margin:0 auto; margin-top:10px;width:950px;">
            <strong style="font-size:16px;margin:5px 0;">购物车详情:</strong>

            <div style="margin:0 auto; margin-top:10px;width:950px;">
                <input type="checkbox" class="allSelected" id="allSelected" onclick="allSelected(this)">
                <span>全选</span>
            </div>

            <form id="itemCheckId" action="/makeSureOrders" method="post">

            </form>
            <c:forEach items="${cartList}" var="cis">
            <input type="hidden" value="${cartList.size()}" id="items">
            <table class="table table-bordered">
                <tbody>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>操作</th>
                    <th></th>
                </tr>
                <tr class="active">
                    <td width="60" width="40%">
                        <a target="_blank" href="/getByPid?pid=${cis.product.pid}">
                            <img src="${pageContext.request.contextPath}/${cis.product.pimage}" width="70" height="60">
                        </a>
                    </td>
                    <td width="30%">
                        <a target="_blank" href="/getByPid?pid=${cis.product.pid}">${cis.product.pname}</a>
                    </td>
                    <td width="20%">
                        ￥${cis.product.shop_price}
                    </td>
                    <td width="10%">
                        <input type="hidden" name="itemId" id="itemId${fn:substring(cis.itemId,0,5)}"
                               value="${cis.itemId}">
                        <form action="/makeSureOrder" method="post" id="pay">
                            <input type="hidden" name="pid" id="pid${fn:substring(cis.itemId,0,5)}"
                                   value="${cis.product.pid}">
                            <input type="hidden" name="itemId" id="itemId${fn:substring(cis.itemId,0,5)}"
                                   value="${cis.itemId}">
                            <input type="hidden" name="subtotal" id="subtotal${fn:substring(cis.itemId,0,5)}"
                                   value="${cis.subtotal}">
                            <input type="text" readonly="readonly" name="count"
                                   id="count${fn:substring(cis.itemId,0,5)}" value="${cis.counts}" maxlength="4"
                                   size="10" style="text-align: center">
                        </form>
                    </td>
                    <td width="15%">
                        <span class="subtotal">￥${cis.subtotal}</span>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="deleteFromCart('${cis.itemId}')" class="delete">删除</a>
                        <br/>
                        <a href="javascript:void(0)" onclick="orderCreate()">付款
                        </a>
                    </td>
                    <td>
                        <input type="checkbox" class="cartItems" onclick="isChecked()"
                               value="${fn:substring(cis.itemId,0,5)}">
                    </td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
        </div>
    </div>

    <div style="margin-right:130px;">
        <div style="text-align:right;">
            商品金额: <strong style="color:#ff6600;">￥<input type="text" readonly="readonly" id="totalMoney" value="0"
                                                         style="width: 80px;text-align: center"> 元</strong>
        </div>
        <div style="text-align:right;margin-top:10px;margin-bottom:10px;">
            <a href="/cleanCart" id="clear" class="clear">清空购物车</a>

            <input type="button" width="100" onclick="checkedCount()" value="提交订单" name="submit" border="0"
                   style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                           height:35px;width:100px;color:white;">

        </div>
    </div>
    </c:if>

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
</div>

</body>

<script type="text/javascript">
    function deleteFromCart(itemId) {
        if (confirm("你确认要删除该产品吗？")) {
            location.href = "/deleteFromCart?itemId=" + itemId;
        }
    }

    function orderCreate() {
        document.getElementById("pay").submit();
    }

    function isChecked() {
        totalPay();
    }

    function totalPay() {
        var j = 0;
        let total = 0;
        var items = $(".cartItems");

        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            if (item.checked) {
                j++;
                total += parseFloat(document.getElementById("subtotal" + item.value).value);
            }
        }
        if (j == items.length) {
            // console.log(j);
            document.getElementById("allSelected").checked = "checked";
        } else {
            // console.log(j);
            document.getElementById("allSelected").checked = null;
        }
        $("#totalMoney").val(total);
    }


    function allSelected() {
        let total = 0.0;
        var itemElements = $(".cartItems");
        for (var i = 0; i < itemElements.length; i++) {
            var itemElement = itemElements[i];
            if (document.getElementById("allSelected").checked) {
                itemElement.checked = "checked";
                total += parseFloat(document.getElementById("subtotal" + itemElement.value).value);

            } else {
                itemElement.checked = null;
                total = 0;
            }
        }
        $("#totalMoney").val(total);
    }

</script>
</html>