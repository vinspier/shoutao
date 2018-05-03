<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/30 0030
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <script language="javascript">
        $().ready(function () {
            //发送ajax 查询所有分类
            $.post("/findCategory", function (obj) {
                //alert(obj);
                //遍历json列表,获取每一个分类,包装成li标签,插入到ul内部
                //$.each($(obj),function(){});
                $(obj).each(function () {
                    $("#category_list").append("<li><a href='/getByPage?pageNumber=1&cid=" + this.cid + "'>" + this.cname + "</a></li>");
                });
            }, "json");
        });
    </script>
</head>
<body>
<!--
    时间：2015-12-30
    描述：菜单栏
-->
<div class="container-fluid">
    <div class="col-md-4">
        <%--        <img src="${pageContext.request.contextPath}/img/logo2.png" />--%>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty user }">
                <li><a href="/login">亲，登录</a></li>
                <li><a href="/register">免费注册</a></li>
            </c:if>
            <c:if test="${not empty user }">
                <li>欢迎:<a href="/userInformation?uid=${user.uid}">${user.username }</a></li>
                <li><a href="/order_list">我的订单</a></li>
                <li><a href="/cart">我的购物车</a></li>
                <li><div class="dropdown">
                    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">设置
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="/userInformation?uid=${user.uid}">查看个人信息</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="/changePassword">修改密码</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="/check_balance?uid=${user.uid}">查询余额</a>
                        </li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="/logout">退出</a>
                        </li>
                    </ul>
                </div></li>
            </c:if>
        </ol>
    </div>
</div>
<!--
    时间：2015-12-30
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/index">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="category_list">
                </ul>
                <form class="navbar-form navbar-right" id="searchForm" role="search" method="post" action="/searchByPage?pageNumber=1">
                    <div class="form-group">
                        <input type="text" class="form-control" id="searchContent" name="searchContent" placeholder="Search">
                    </div>
                    <input type="button" onclick="checkSearchContent()" class="btn btn-default" value="Submit">Submit</input>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
<script type="text/javascript">
    function checkSearchContent() {
        if($("#searchContent").val() == ""){
            $("#searchContent").focus();
        }else{
            searchFormSubmit()
        }
    }
    function searchFormSubmit() {
        $("#searchForm").submit();
    }
</script>
</body>
</html>
