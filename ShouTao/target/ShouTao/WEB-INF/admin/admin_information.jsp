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
    <title>会员信息</title>
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
    <script type="text/javascript">
        var empty ="";
        function checkItems() {
            if($("#username").val() == null || $("#username").val() == ""){
                empty = "no";
                alert("用户名修改后不应为空");
                $("#username").focus();
            }else{
                if($("#inputEmail3").val() == "" || $("#inputEmail3").val() == null){
                    empty = "no";
                    alert("用户邮箱修改后不应为空");
                    $("#inputEmail3").focus();
                }
            }
            if(empty == ""){
                submitModifyForm();
            }
        }
        function submitModifyForm() {
            document.getElementById("form-horizontal").submit();
        }
    </script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="container" style="width:100%;background:url('../image/register.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <div align="center"><font>管理员信息</font>ADMINISTRATOR INFORMATION</div>
            <br/>
            <form class="form-horizontal" id="form-horizontal" style="margin-top:5px;align-self: center" method="post" action="/modifyUserInformation?uid=${user.uid}" >
                <input type="hidden" name="method" value="regist">
                <div  class="form-group">
                    <label for="adminId" class="col-sm-2 control-label">管理员编号</label>
                    <div class="col-sm-6">
                        <input type="text" readonly="readonly" class="form-control" id="adminId" value="${admin.adminId}" name="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="adminName"  class="col-sm-2 control-label">管理员名称</label>
                    <div class="col-sm-6">
                        <input type="text" readonly="readonly" class="form-control" id="adminName" value="${admin.adminName}" name="realname">
                    </div>
                </div>
                <div class="form-group">
                    <label for="adminRole" class="col-sm-2 control-label">管理员角色</label>
                    <div class="col-sm-6">
                        <input type="text" readonly="readonly" class="form-control" id="adminRole" value="${roleName}"
                               name="receivedAddress">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

<div class="container-fluid" id="footer_bottom">
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




