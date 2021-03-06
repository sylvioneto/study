<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Casa do Codigo - Cadastro de Produtos</title>
</head>
<body>
	<h1>Cadastro de Produtos</h1>
	<form:form action="/casadocodigo/produtos" method="POST" 
				modelAttribute="produto" enctype="multipart/form-data"> 	
		<div>
			<label>Titulo</label>
			<form:input  path="titulo" />
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descricao</label>
			<form:textarea path="descricao" rows="10" cols="20" ></form:textarea>
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Paginas</label> 
			<form:input  path="paginas" />
			<form:errors path="paginas" />
		</div>
		<div>
		    <label>Data de Lançamento</label>
		    <form:input  path="dataLancamento" />
		    <form:errors path="dataLancamento" />
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				 <form:input path="precos[${status.index}].valor" />
				 <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
			</div>
		</c:forEach>
		<div>
			<label>Sumário</label>
			<input name="sumario" type="file"/>
		</div>
		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>