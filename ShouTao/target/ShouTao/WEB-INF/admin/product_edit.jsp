<%@ page import="constant.Constant" %>
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
    <title>商品编辑</title>
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
            if($("#pname").val() == ""){
                alert("请输入商品名称");
                $("#pname").focus();
            }else{
                    var selectedOption = $("#cidSelect option:selected");
                    $("#cid").val(selectedOption.val());
                    submitResetForm();
            }
        }

        function submitResetForm() {
            if(confirm("确认编辑修改并更新？")){
                document.getElementById("productEditForm").submit();
            }
        }
    </script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/register.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <div align="center"><font>商品编辑: </font>PRODUCT EDIT</div>
            <br/>
            <form class="form-horizontal" id="productEditForm" enctype="multipart/form-data" style="margin-top:5px;align-self: center" method="post" action="/product_edit?pid=${product.pid}" >
                <input type="hidden" id="cid" name="cid" value="">
                <div class="form-group">
                    <label for="pname" class="col-sm-2 control-label">商品名称：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-contro" id="pname" name="pname" value="${product.pname}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="market_price" class="col-sm-2 control-label">商品标价：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-contro" id="market_price" name="market_price" value="${product.market_price}">元
                    </div>
                </div>
                <div class="form-group">
                    <label for="shop_price" class="col-sm-2 control-label">商品售价：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-contro" id="shop_price" name="shop_price" value="${product.shop_price}" >元
                    </div>
                </div>
                <div class="form-group">
                    <label for="pdesc" class="col-sm-2 control-label">商品描述：</label>
                    <div class="col-sm-6">
                        <textarea  class="form-contro" id="pdesc" name="pdesc" value="">${product.pdesc}</textarea>
                    </div>
                </div>
                <div  class="form-group">
                    <label for="cidSelect" class="col-sm-2 control-label">选择分类：</label>
                    <div class="col-sm-6">
                        <select  id="cidSelect" name="cidSelect">
                            <c:if test="${not empty categoryList && categoryList.size() > 0 }">
                                <c:forEach items="${categoryList}" var="ca">
                                    <c:if test="${product.cid.equals(ca.cid)}">
                                    <option  value="${ca.cid}" selected="selected">${ca.cname}</option>
                                    </c:if>
                                    <option  value="${ca.cid}">${ca.cname}</option>
                                </c:forEach>
                            </c:if>
                        </select>
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




