<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Connexion</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>
	
	
			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/img/logo3.png" />
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
						<li><a href="${pageContext.request.contextPath }/user?method=loginUI">Je me connecte</a></li>
				    	<li><a href="${pageContext.request.contextPath }/user?method=registUI">Je m'inscris</a></li>
				    	<li><a href="${pageContext.request.contextPath }">Accueil</a></li>
					</ol>
				</div>
			</div>
			<!--
            	时间：2015-12-30
            	描述：导航条
            -->
           
	<!--		<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
	<!--					<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath }/jsp/index.jsp">Accueil</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
		<!--				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li class="active"><a href="#">手机数码<span class="sr-only">(current)</span></a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
	<!--				</div>
					<!-- /.container-fluid -->
	<!--			</nav>
			</div>
	
	
	-->
	
	
<div class="container"  style="width:95%;height:650px;background:#fffff7 url('${pageContext.request.contextPath}/images/denglu.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
		<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
		<font>会员登录</font>USER LOGIN ${msg }

		<div>&nbsp;</div>
<form class="form-horizontal" action="${pageContext.request.contextPath }/user" method="post">
	<input type="hidden" name="method" value="login">
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">Username</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
    </div>
  </div>
   <div class="form-group">
        <label for="cap" class="col-sm-2 control-label">Captcha</label>
     <div class="col-sm-3">
     <img src="${pageContext.request.contextPath }/captcha.png" onclick="changeImg(this)">
    </div>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="cap" placeholder="请输入验证码" name="cap">
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Connexion automatique
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox" name="savename" value="ok"> SaveName
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit"  width="100" value="登录/login" name="submit" border="0"
    style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    </div>
  </div>
</form>
</div>			
	</div>
</div>
</div>	

	

		
		
<script> 
    function changeImg(img) {
        img.src = "${pageContext.request.contextPath }/captcha.png?t="+new Date().getTime(); 
    }
</script>	
		
</body>
<script type="text/javascript">
          var val = "${cookie.saveName.value}";
          document.getElementById("username").value=decodeURI(val);
</script>
</html>