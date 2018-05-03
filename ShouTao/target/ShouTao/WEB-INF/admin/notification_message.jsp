<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>信息提示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <style type="text/css">
        #footer_bottom{
            position: fixed;
            bottom: 0px;
        }
    </style>
</head>

<body>
<div class="container-fluid">
    <%@include file="head.jsp" %>
    <div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/register.jpg');">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
                <div align="center"><font>信息提示: </font>NOTIFICATION INFORMATION</div>
                <br/>
                <form class="form-horizontal" id="resetPasswordForm" style="margin-top:5px;align-self: center" method="post" action="/resetPassword" >
                    <input type="hidden" name="passwordOrigin" id="passwordOrigin" value="${user.password}">
                    <div class="container-fluid" align="center">
                        <h3>${msg}</h3>
                    </div>
                </form>
            </div>

            <div class="col-md-2"></div>

        </div>
    </div>

</div>
<div class="container-fluid" id="footer_bottom" >
    <div style="text-align: center;margin-top: 5px;" >
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