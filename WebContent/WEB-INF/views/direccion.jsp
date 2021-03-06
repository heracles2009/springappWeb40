<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Direccion.jsp</title>
<script type="text/javascript" src='<c:url value="/res/js/jquery-1.11.2.min.js"/>'></script>
<script type="text/javascript">
	
</script>
</head>
<body>

	<h3>Direccion.jsp</h3>
	
	<sf:form action="${pageContext.request.contextPath}/direccion/save" method="post" commandName="direccion">
	<table> 
		<tr>
			<td>Calle</td>
			<td><sf:input path="calle" type="text"/></td>
		</tr>
		<tr>
			<td>C.P.</td>
			<td><sf:input path="cp" type="text"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Guardar cambios"></td>
		</tr>
	</table>
	</sf:form>
	
	<br/>
	<c:out value="${resultado}"></c:out>
	
	
</body>
</html>

<!-- <c:forEach items="${admins}" var="admin">
		<c:out value="${admin}"/> 
		<a href='<c:url value="/admin/${admin.idAd}/update"/>'>Actualizar</a>
		<a class="confirm" href='<c:url value="/admin/${admin.idAd}/delete"/>'>Eliminar</a>
		<br/>
	</c:forEach>
	-->