<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>意见反馈</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"/>
    <script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../css/style.css" type="text/css"/>

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
<div class="container" style="width:100%;background:url('../image/register.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <font>意见反馈</font>SUGGESTION
            <form class="form-horizontal" id="form-horizontal" style="margin-top:5px;" method="post" action="/suggest">
                <input type="hidden" name="method" value="regist">
                <div class="form-group">
                    <label for="content" class="col-sm-2 control-label">建议内容：</label>
                    <div class="col-sm-6">
                        <textarea class="form-control" id="content" placeholder="请输入反馈内容" name="content"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" width="100" value="提交建议" name="suggest" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>

    </div>

    <div style="margin:0 auto; margin-top:10px;width:950px;">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <c:forEach items="${suggestionList}" var="suggestion">
                <table class="table table-bordered">
                    <tbody>
                    <tr class="success">
                        <th colspan="1">用户名称</th>
                        <th colspan="1">反馈内容</th>
                        <th colspan="1"></th>
                    </tr>
                    <tr class="warning">
                        <th>
                            <c:if test="${suggestion.user.uid.equals(user.uid)}">我：</c:if>
                            <c:if test="${!suggestion.user.uid.equals(user.uid)}">${suggestion.user.username}</c:if>
                        </th>
                        <th>${suggestion.suggest_content}</th>
                        <th>
                            [${suggestion.like_count}]<a href="/thumbsUp?id=${suggestion.id}">赞</a>
                        </th>
                    </tr>
                    </tbody>
                </table>
                </c:forEach>
            </div>
        </nav>
    </div>
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
</body>
</html>




