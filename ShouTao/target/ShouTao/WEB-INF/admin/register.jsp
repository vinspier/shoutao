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
    <title>会员注册</title>
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
        window.onload = function () {
            createVerificationCode();
        }
        function createVerificationCode(){
            var createVerificationCode = "";
            var codeLength = 6;
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
            for(var i = 0; i < codeLength; i++){
                var charNum = Math.floor(Math.random() * 52);
                createVerificationCode += codeChars[charNum];
            }
            console.log(createVerificationCode);
            $("#verificationCode").val(createVerificationCode);
        }

        function validateVerificationCode(){
            var submitAction = true;
            var inputVerificationCode = $("#inputVerificationCode").val().toUpperCase();
            var verificationCode = $("#verificationCode").val().toUpperCase();
            if (inputVerificationCode == ""){
                submitAction = false;
                alert("请输入验证码");
                $("#inputVerificationCode").focus();
            }else {
                if(inputVerificationCode != verificationCode){
                    submitAction = false;
                    alert("验证码输入错误");
                    createVerificationCode();
                    $("#inputVerificationCode").focus();
                }
            }
            if(submitAction){
                submitForm();
            }
        }
        function checkedItems() {
            var empty = "";
            var passwordSet = $("#inputPassword3").val();
            var passwordConfirm = $("#confirmpwd").val();
            if($("#username").val()==null || $("#username").val()==""){
                empty += "no";
                alert("用户名不允许为空");
                $("#username").focus();
            }else {
                if(passwordSet == null || passwordSet == ""){
                    alert("用户密码不为空");
                    empty += "no";
                    $("#inputPassword3").focus();
                }else {
                    if(passwordSet!=passwordConfirm){
                        alert("确认密码与设置的密码不匹配");
                        empty += "no";
                        $("#confirmpwd").focus();
                    }else {
                        if($("#inputEmail3").val()==null || $("#inputEmail3").val()==""){
                            empty += "no";
                            alert("邮箱不允许为空");
                            $("#inputEmail3").focus();
                        }
                    }
                }
            }

            if(empty == ""){
                validateVerificationCode();
            }

        }
        function submitForm() {
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
            <font>会员注册</font>USER REGISTER
            <form class="form-horizontal" id="form-horizontal" style="margin-top:5px;" method="post" action="/Register">
                <input type="hidden" name="method" value="regist">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码"
                               name="password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-6">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="usercaption" placeholder="请输入联系姓名" name="realname">
                    </div>
                </div>
                <div class="form-group">
                    <label for="telephone" class="col-sm-2 control-label">联系电话</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="telephone" placeholder="请输入联系方式" name="telephone">
                    </div>
                </div>
                <div class="form-group">
                    <label for="receivedAddress" class="col-sm-2 control-label">收货地址</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="receivedAddress" placeholder="请输入收货地址"
                               name="receivedAddress">
                    </div>
                </div>
                <div class="form-group opt">
                    <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineRadio1" value="男"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineRadio2" value="女"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" name="birthday">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputVerificationCode" class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="inputVerificationCode">
                    </div>
                    <div class="col-sm-3">
                        <input type="text" readonly="readonly" class="form-control" id="verificationCode">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="button" onclick="checkedItems()" width="100" value="注册" name="register" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

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
</body>
</html>




