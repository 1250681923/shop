<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">+</a> | <a href="javascript: d.closeAll();">-</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'Liste de la gestion');
		d.add('0102','01','Category','','','mainFrame');
		d.add('010201','0102','Liste','${pageContext.request.contextPath}/adminCategory?method=findAll','','mainFrame');
		d.add('010202','0102','Ajouter','${pageContext.request.contextPath}/adminCategory?method=addUI','','mainFrame');
		d.add('0104','01','Produit');
		d.add('010401','0104','Produits cotés','${pageContext.request.contextPath}/adminProduct?method=findAll','','mainFrame');
		d.add('010402','0104','Ajouter','${pageContext.request.contextPath}/adminProduct?method=addUI','','mainFrame');
		d.add('0105','01','Order');
		d.add('010501','0105','Liste','${pageContext.request.contextPath}/adminOrder?method=findAllByState','','mainFrame');
		d.add('010502','0105','A payer','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=0','','mainFrame');
		d.add('010503','0105','Payé','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=1','','mainFrame');
		d.add('010504','0105','Expédiée','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=2','','mainFrame');
		d.add('010505','0105','Fini','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=3','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
