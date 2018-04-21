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
    <title>修改密码</title>
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
        function checkItems() {
            var empty = "";
            var originPassword = $("#passwordOrigin").val();
            var inputPasswordOrigin = $("#inputPasswordOrigin").val();
            var resetPassword = $("#resetPassword").val();
            var checkResetPassword = $("#checkResetPassword").val();
            if(inputPasswordOrigin == ""){
                empty = "no";
                alert("请输入原始密码");
                $("#inputPasswordOrigin").focus();
            }else{
                if(resetPassword == ""){
                    empty = "no";
                    alert("请输入重置密码");
                    $("#resetPassword").focus();
                }else{
                    if(checkResetPassword == ""){
                        empty = "no";
                        alert("请输入校验密码")
                        $("#checkResetPassword").focus();
                    }else{
                        if(originPassword != inputPasswordOrigin){
                            empty = "no";
                            alert("原始密码输入错误，请重新输入");
                            $("#inputPasswordOrigin").focus();
                        }else{
                            if(resetPassword != checkResetPassword){
                                empty = "no";
                                alert("重置密码与校验密码不一致，请重新输入重置密码和校验密码");
                                $("#checkResetPassword").focus();
                            }
                        }
                    }
                }
            }
            if(empty == ""){
                submitResetForm();
            }
        }
        function submitResetForm() {
            document.getElementById("resetPasswordForm").submit();
        }
    </script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/register.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <br/>
            <form class="form-horizontal" id="resetPasswordForm" style="margin-top:5px;align-self: center" method="post" action="/resetPassword" >
                <input type="hidden" name="passwordOrigin" id="passwordOrigin" value="${user.password}">
                <div  class="form-group">
                    <label for="inputPasswordOrigin" class="col-sm-2 control-label">原始密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="inputPasswordOrigin" name="inputPasswordOrigin" value="" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="resetPassword" class="col-sm-2 control-label">重置密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="resetPassword" name="resetPassword" value="" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="checkResetPassword" class="col-sm-2 control-label">校验重置密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="checkResetPassword" name="checkResetPassword" value="" >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="button"  onclick="checkItems()" width="100" value="确认修改" name="modify" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

<div class="container-fluid" id="footer_bottom" >
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




