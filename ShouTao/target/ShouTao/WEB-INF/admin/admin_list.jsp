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
    <title>所有管理员</title>
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
        function deleteAdmin(id) {
            if(confirm("确认删除该管理员？")){
                location.href = "/admin_delete?adminId="+id;
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
                        <strong><span class="navbar-brand">当前管理者：</span></strong>
                    </div>
                    <div class="navbar-header">
                        <span class="navbar-brand">${admin.adminName}</span>
                    </div>
                    <div class="navbar-header">
                        <strong><span class="navbar-brand">当前管理角色：</span></strong>
                    </div>
                    <div class="navbar-header">
                        <span class="navbar-brand">${role}</span>
                    </div>
                    <c:if test="${roleBiggest == 1}">

                        <div class="navbar-header">
                            <a href="/admin_addNew">
                            <input type="button"  width="100" value="添加管理员" name="submit" border="0"
                                   style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                           height:35px;width:100px;color:white;">
                            </a>
                        </div>
                    </c:if>
                </div>
            </nav>
            <hr/>
            <c:forEach items="${administrators}" var="admin" varStatus="indexOf">
            <table class="table table-bordered">
                <tbody>
                <tr class="warning">
                    <th style="text-align: center">管理员编号</th>
                    <th style="text-align: center">管理员名称</th>
                    <th style="text-align: center">管理员角色</th>
                    <th style="text-align: center">操作权限</th>
                </tr>
                <tr class="active">
                    <td width="15%" style="text-align: center">
                        ${admin.adminId}
                    </td>
                    <td width="25%" style="text-align: center">
                        ${admin.adminName}
                    </td>
                    <c:if test="${roles[indexOf.count-1].roleId == '1'}">
                    <td width="25%" style="text-align: center">
                        普通管理员
                    </td>
                    </c:if>
                    <c:if test="${roles[indexOf.count-1].roleId == '3'}">
                        <td width="25%" style="text-align: center">
                            最高权限管理员
                        </td>
                    </c:if>
                    <c:if test="${roleBiggest == 1}">
                        <td width="10%" style="text-align: center">
                            <a href="javascript:void(0)" onclick="deleteAdmin('${admin.adminId}')">删除该管理者</a>
                        </td>
                    </c:if>
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