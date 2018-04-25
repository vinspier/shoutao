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
    <title>用户信息</title>
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
        function admin_activeUser(uid,username) {
            if(confirm("确认要激活  "+username+"  用户吗")){
                location.href = "/admin_activeUser?uid="+uid;
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
                        <c:if test="${state == 0}">
                        <strong><span class="navbar-brand">未激活状态的用户：${users.size()}</span></strong>
                        </c:if>
                        <c:if test="${state == 1}">
                            <strong><span class="navbar-brand">已激活状态的用户：${users.size()}</span></strong>
                        </c:if>
                        <c:if test="${state == 2}">
                            <strong><span class="navbar-brand">所有状态的用户：${users.size()}</span></strong>
                        </c:if>
                    </div>
                </div>
            </nav>
            <hr/>
            <c:forEach items="${users}" var="user" varStatus="indexOf">
            <table class="table table-bordered">
                <tbody>
                <tr class="warning">
                    <th style="text-align: center">用户编号</th>
                    <th style="text-align: center">用户名称</th>
                    <th style="text-align: center">用户密码</th>
                    <th style="text-align: center">用户状态</th>
                    <th style="text-align: center">用户余额</th>
                </tr>
                <tr class="active">
                    <td width="15%" style="text-align: center">
                       ${user.uid}
                    </td>
                    <td width="25%" style="text-align: center">
                        <a href="/admin_userInformation?uid=${user.uid}">${user.username}</a>
                    </td>
                    <td width="25%" style="text-align: center">
                            ${user.password}
                    </td>
                    <td width="25%" style="text-align: center">
                        <c:if test="${user.state == 0}">
                            <a href="javascript:void(0)" onclick="admin_activeUser('${user.uid}','${user.username}')">未激活</a>
                        </c:if>
                        <c:if test="${user.state == 1}"><a>已激活</a></c:if>
                    </td>
                    <td width="25%" style="text-align: center">
                            <a href="/check_balance?uid=${user.uid}">${user.balance}</a>
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