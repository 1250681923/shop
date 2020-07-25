<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Mes commandes</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
	<%@include file="/jsp/head.jsp" %>


		<div class="container">
			<div class="row">

				<div style="margin:0 auto;margin-top:10px;width:950px;">
					<strong>Mes commandes</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th colspan="2"></th>
								<th colspan="1">
									<c:if test="${bean.state == 0 }">A payer</c:if>
									<c:if test="${bean.state == 1 }">Payé</c:if>
									<c:if test="${bean.state == 2 }">Confirmer la réception</c:if>
									<c:if test="${bean.state == 3 }">Fini</c:if>
								</th>
								<th colspan="2">Temps: ${bean.ordertime }
								 </th>
							</tr>
							<tr class="warning">
								<th>Images</th>
								<th>Produits</th>
								<th>Prix</th>
								<th>Quantite</th>
								<th>Total</th>
							</tr>
							<c:forEach items="${bean.items }" var="oi">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${oi.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank">${oi.product.pname}</a>
									</td>
									<td width="20%">
										￥${oi.product.shop_price}
									</td>
									<td width="10%">
										${oi.count}
									</td>
									<td width="15%">
										<span class="subtotal">￥${oi.subtotal}</span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div style="text-align:right;margin-right:120px;">
					Montant total: <strong style="color:#ff6600;">￥${bean.total }</strong>
				</div>

			</div>

			<div>
				<hr/>
				<form action="${pageContext.request.contextPath }/order" id="orderForm" method="post" class="form-horizontal" style="margin-top:5px;margin-left:150px;">
					<!-- 提交的方法 -->
					<input type="hidden" name="method" value="pay">
					<!-- 订单号 -->
					<input type="hidden" name="oid" value="${bean.oid }">
					<div class="form-group">
						<label for="username" class="col-sm-1 control-label">Adresse</label>
						<div class="col-sm-5">
							<input type="text" name="address" class="form-control" id="username" placeholder="请输入收货地址">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-1 control-label">Nom</label>
						<div class="col-sm-5">
							<input type="text" name="name" class="form-control" id="inputPassword3" placeholder="请输收货人">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-1 control-label">Tel</label>
						<div class="col-sm-5">
							<input type="text" name="telephone" class="form-control" id="confirmpwd" placeholder="请输入联系方式">
						</div>
					</div>
				

				<hr/>

				<p style="text-align:right;margin-right:100px;">
						<a href="javascript:document.getElementById('orderForm').submit();">
							<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51" border="0" />
						</a>
					</p>
                 </form>
				</div>
			</div>

				
		

		
		
	</body>

</html>