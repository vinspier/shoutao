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
    <title>商品分类表信息</title>
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

        #footer_bottom {
            position: fixed;
            bottom: 0px;
        }

    </style>
    <script type="text/javascript">
        function category_delete(cid, cname) {
            if (confirm("确认要删除  " + cname + "  分类吗")) {
                location.href = "/category_delete?cid=" + cid;
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
                        <strong><span class="navbar-brand">所有分类信息：${categories.size()}个</span></strong>
                        <span>
                            <a href="/category_add_form">
                                 <input type="button" width="100" value="添加分类" name="add" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                            </a>
                        </span>
                    </div>
                </div>
            </nav>
            <hr/>
            <c:forEach items="${categories}" var="cat" varStatus="indexOf">
            <table class="table table-bordered">
                <tbody>
                <tr class="warning">
                    <th style="text-align: center">分类编号</th>
                    <th style="text-align: center">分类名称</th>
                    <th style="text-align: center">操作</th>
                </tr>
                <tr class="active">
                    <td width="25%" style="text-align: center">
                            ${cat.cid}
                    </td>
                    <td width="25%" style="text-align: center">
                        <a href="getByPage?pageNumber=1&cid=${cat.cid}">
                                ${cat.cname}
                        </a>
                    </td>
                    <td width="25%" style="text-align: center">
                        <a href="javascript:void(0)" onclick="category_delete('${cat.cid}','${cat.cname}')">删除</a>
                        <a href="javascript:void(0)" onclick="">修改</a>
                    </td>
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