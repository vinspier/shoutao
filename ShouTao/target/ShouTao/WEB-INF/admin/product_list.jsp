<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品展示</title>
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
            width: 100%;
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

<div class="row" style="width:1210px;margin:0 auto;">
    <%--			<div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><a href="/index">首页</a></li>
                    </ol>
                </div>--%>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <strong><span class="navbar-brand">
                        <c:if test="${not empty flag}">
                            <c:if test="${flag == 2}">所有</c:if>
                            <c:if test="${flag == 0}">所有上架的</c:if>
                            <c:if test="${flag == 1}">所有下架的</c:if>商品：${productPage.totalRecord}个
                        </c:if>
                        <c:if test="${not empty category}" >
                            所有 ${category.cname} 商品：${productPage.totalRecord}个
                        </c:if>
                    </span></strong>
                </div>
            </div>
        </nav>

    <c:forEach items="${productPage.data}" var="p">
        <div class="col-md-2">
            <a href="/admin_getByPid?pid=${p.pid}">
                <img src="../${p.pimage}" width="120" height="120" style="display: inline-block;">
            </a>
            <p><a href="/admin_getByPid?pid=${p.pid}" style='color:green'>${fn:substring(p.pname,0,10)}..</a></p>
            <p><font color="#FF0000">商城价：&yen;${p.shop_price}</font></p>
        </div>
    </c:forEach>

</div>

<!--分页 -->
<div style="width:380px;margin:0 auto;margin-top:50px;">
    <ul class="pagination" style="text-align:center; margin-top:10px;">

        <!-- 判断是否是第一页 -->
        <c:if test="${productPage.pageNumber == 1 }">
            <li class="disabled">
                <a href="javascript:void(0)" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <%-- 不是第一页 --%>
        <c:if test="${productPage.pageNumber != 1 }">
            <li>
                <c:if test="${not empty isState}">
                    <a href="/getProductToPage?pageNumber=${productPage.pageNumber-1}&flag=${flag}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </c:if>
                <c:if test="${not empty isCategory}">
                    <a href="/admin_getByPage?pageNumber=${productPage.pageNumber-1}&cid=${category.cid}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </c:if>
                <c:if test="${not empty isSearch}">
                    <a href="/admin_searchByPage?pageNumber=${productPage.pageNumber-1}&searchContent=${searchContent}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </c:if>
            </li>
        </c:if>

        <%-- 展示全部--%>
        <c:forEach begin="1" end="${productPage.totalPage}" var="n">
            <c:if test="${productPage.pageNumber == n}">
                <li class="active">
                    <a href="javascript:void(0)">${n}</a>
                </li>
            </c:if>
            <c:if test="${productPage.pageNumber != n}">
                <li>
                    <c:if test="${not empty isState}">
                         <a href="/getProductToPage?pageNumber=${n}&flag=${flag}">${n}</a>
                    </c:if>
                    <c:if test="${not empty isCategory}">
                        <a href="/admin_getByPage?pageNumber=${n}&cid=${category.cid}">${n}</a>
                    </c:if>
                    <c:if test="${not empty isSearch}">
                        <a href="/admin_searchByPage?pageNumber=${n}&searchContent=${searchContent}">${n}</a>
                    </c:if>
                </li>
            </c:if>
        </c:forEach>

        <!-- 判断是否是最后一页 -->
        <c:if test="${productPage.pageNumber == productPage.totalPage }">
            <li class="disabled">
                <a href="javascript:void(0)" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>

        <%-- 不是最后一页 --%>
        <c:if test="${productPage.pageNumber != productPage.totalPage }">
            <li>
                <c:if test="${not empty isState}">
                    <a href="/getProductToPage?pageNumber=${productPage.pageNumber+1}&flag=${flag}" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
                </c:if>
                <c:if test="${not empty isCategory}">
                    <a href="/admin_getByPage?pageNumber=${productPage.pageNumber+1}&cid=${category.cid}" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
                </c:if>
                <c:if test="${not empty isSearch}">
                    <a href="/admin_searchByPage?pageNumber=${productPage.pageNumber+1}&searchContent=${searchContent}" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
                </c:if>
            </li>
        </c:if>

    </ul>
</div>
<!-- 分页结束=======================        -->

<!--
       商品浏览记录:
-->
<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

    <h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
    <div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
    <div style="clear: both;"></div>

    <div style="overflow: hidden;">

        <ul style="list-style: none;">
            <li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;">
                <img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="130px" height="130px"/></li>
        </ul>

    </div>
</div>
<div class="container-fluid" id="footer_bottom">
    <div style="margin-top:50px;">
    </div>

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