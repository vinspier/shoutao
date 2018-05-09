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
    <script type="text/javascript">
        function checkSearchContent() {
            if ($("#searchContent").val() == "") {
                $("#searchContent").focus();
            } else {
                searchFormSubmit()
            }
        }
        function searchFormSubmit() {
            $("#searchForm").submit();
        }
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
            <c:if test="${not empty admin }">
                <li>管理员:<strong>${admin.adminName}</strong></li>
                    <li>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown">
                                设置
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_information?adminId=${admin.adminId}">查看信息</a>
                                </li>
                                <c:if test="${adminLevel == 1}">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_list">查看所有管理员</a>
                                </li>
                                </c:if>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_changePassword">修改密码</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_logout">退出</a>
                                </li>
                            </ul>
                        </div>
                    </li>
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
                <a class="navbar-brand" href="/adminIndex">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="category_list">
                    <li>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="dropdownMenu0" data-toggle="dropdown">
                                商品管理
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getProductToPage?pageNumber=1&flag=0">管理上架商品</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getProductToPage?pageNumber=1&flag=1">管理下架商品</a>
                                </li>
                                <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="/product_upload_input">上传商品</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getProductToPage?pageNumber=1&flag=2">管理所有商品</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="dropdownMenu3" data-toggle="dropdown">
                                分类管理
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/category_list">查看分类</a>
                                </li>

                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/category_add_form">添加分类</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="dropdownMenu4" data-toggle="dropdown">
                                订单管理
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getOrderByState?state=0">查看未付款订单</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getOrderByState?state=1">查看待发货订单</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getOrderByState?state=2">查看已发货订单</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getOrderByState?state=3">查看已完成订单</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/getOrderByState?state=4">查看所有订单</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" id="dropdownMenu5" data-toggle="dropdown">
                                用户管理
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_checkAllUsersByState?state=2">查看所有用户</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_checkAllUsersByState?state=1">查看已激活用户</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="/admin_checkAllUsersByState?state=0">查看未激活用户</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" id="searchForm" role="search" method="post"
                      action="/admin_searchByPage?pageNumber=1">
                    <div class="form-group">
                        <input type="text" class="form-control" id="searchContent" name="searchContent"
                               placeholder="Search">
                    </div>
                    <input type="button" onclick="checkSearchContent()" class="btn btn-default"
                           value="Submit">Submit</input>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
</body>
</html>
