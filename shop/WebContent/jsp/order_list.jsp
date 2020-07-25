<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>Mes commandes</strong>
					<table class="table table-bordered">
						<c:forEach items="${pb.data }" var="o">
							<tbody>
								<tr class="success">
									<th colspan="2"></th>
									<th colspan="1">
										<c:if test="${o.state == 0 }"><a href="${pageContext.request.contextPath }/order?method=getById&oid=${o.oid}">A payer</a></c:if>
										<c:if test="${o.state == 1 }">Payé</c:if>
										<c:if test="${o.state == 2 }">Confirmer la réception</c:if>
										<c:if test="${o.state == 3 }">Fini</c:if>
									</th>
									<th colspan="2">Montant total :${o.total } </th>
								</tr>
								<tr class="warning">
								<th>Images</th>
								<th>Produits</th>
								<th>Prix</th>
								<th>Quantite</th>
								<th>Total</th>
								</tr>
								<c:forEach items="${o.items }" var="oi">
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
											${oi.count }
										</td>
										<td width="15%">
											<span class="subtotal">￥${oi.subtotal }</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
						
					</table>
				</div>
			</div>
			<%@include file="/jsp/page.jsp" %>
		</div>

	</body>

</html>