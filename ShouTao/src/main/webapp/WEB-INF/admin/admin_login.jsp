<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    <title>管理员登录</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"/>
    <script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
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
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right: 17px;
        }
        #footer_bottom{
            position: fixed;
            top: auto;
            bottom: 0px;
        }
    </style>
    <script type="text/javascript">
        window.onload = function () {
            createVerificationCode();
            var val = "${cookie.saveName.value}";
            if(val != ""){
                document.getElementById("adminName").value = decodeURI(val);
            }
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
            var adminName = $("#adminName").val();
            var inputPassword = $("#password").val();
            var inputVerificationCode = $("#inputVerificationCode").val().toUpperCase();
            var verificationCode = $("#verificationCode").val().toUpperCase();
            if(adminName == ""){
                submitAction = false;
                alert("请输入管理员名称");
                $("#adminName").focus();
            }else{
                if(inputPassword == ""){
                    submitAction = false;
                    alert("请输入登录密码");
                    $("#password").focus();
                }else{
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
                }
            }
            if(submitAction){
                loginFormSubmit();
            }
        }

        function loginFormSubmit() {
            document.getElementById("loginForm").submit();
        }
    </script>
</head>
<body>
<div class="container"
     style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/images/loginbg.jpg') no-repeat;">
    <div class="row">
        <div class="col-md-7">
            <img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="" title="">
        </div>

        <div class="col-md-5">
            <div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
               <strong>管理员登录</strong>&nbsp;&nbsp;&nbsp;&nbsp;<font>${msg}</font>

                <div>&nbsp;</div>
                <form class="form-horizontal" id="loginForm" action="/admin_login" method="post">
                    <div class="form-group">
                        <label for="adminName" class="col-sm-2 control-label">管理员名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="adminName" placeholder="请输入管理员名称" name="adminName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password" placeholder="请输入密码"
                                   name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputVerificationCode" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="inputVerificationCode" placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" readonly="readonly" class="form-control" id="verificationCode" onclick="createVerificationCode()">
                        </div>
                    </div>
                </form>
                <form class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="button" onclick="validateVerificationCode()" width="100" value="登录" name="submit" border="0"
                                   style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                           height:35px;width:100px;color:white;">
                        </div>
                    </div>
                </form>
            </div>
        </div>
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