<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Panier</title>
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
		</style>
	</head>

	<body>

		
			<%@include file="/jsp/head.jsp" %>

		<div class="container">
			<div class="row">
				<c:if test="${empty cart || empty cart.cartItems }">
					<h3>Votre panier est vide...</h3>
				</c:if>
				<c:if test="${not empty cart.cartItems}">
				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">Mon panier</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>Images</th>
								<th>Products</th>
								<th>Prix</th>
								<th>Quantite</th>
								<th>Total</th>
								<th>Operation</th>
							</tr>
							<c:forEach items="${cart.cartItems }" var="ci">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${ci.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank">${ci.product.pname}</a>
									</td>
									<td width="20%">
										￥${ci.product.shop_price}
									</td>
									<td width="10%">
										<input type="text" readonly="readonly" name="quantity" value="${ci.count }" maxlength="4" size="10">
									</td>
									<td width="15%">
										<span class="subtotal">￥${ci.subtotal }</span>
									</td>
									<td>
										<a href="javascript:void(0);" onclick="removeFromCart('${ci.product.pid}')" class="delete">Supprimer</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					 Montant total: <strong style="color:#ff6600;">￥${cart.total }</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath }/cart?method=clear" id="clear" class="clear">Vider mon panier</a>
					<a href="${pageContext.request.contextPath }/order?method=save">
						<input type="button" width="100" value="Soumettre" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>
			</c:if>
		</div>


		
		

	</body>
	<script type="text/javascript">
		function removeFromCart(pid){
			if(confirm("Veux-tu m'abandonner？")){
				location.href="${pageContext.request.contextPath}/cart?method=remove&pid="+pid;
			}
		}
	</script>
</html>