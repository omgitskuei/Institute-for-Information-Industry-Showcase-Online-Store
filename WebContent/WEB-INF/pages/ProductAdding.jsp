<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="command" class="model.product.ProductBean" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>庫存資料內容修改</title>
</head>
<body>
<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>
<div class="container">
	<h1>庫存資料內容修改</h1>

	<form:form method="POST" action="saveProduct" modelAttribute="product">
		
		<div class="form-group">
				<form:hidden path="productID" />
				</div>
				
				<div class="form-group">
				<label>產品名稱</label>
				<form:input cssClass="form-control" path="productName" />
				</div>
		
		<div class="form-group">
				<label>產品單價</label>
				<form:input cssClass="form-control" path="productPrice" />
				</div>
				
				<div class="form-group">
				<label>產品庫存</label>
				<form:input cssClass="form-control" path="productStock" />
				</div>
				
			<%-- 
				 <div class="form-group">
            <label for="image">上傳圖片</label>
            <div class="custom-file">
              <form:input type="file" name="file" class="custom-file-input" id="imgInput" path="productImg"  />
              <label for="imgInput" class="custom-file-label">選擇檔案</label>
              <img id="output" width="100px" src="#" alt="your image" />
            </div>
            <small class="form-text text-muted">檔案勿超過3MB</small>
          </div>
			--%>		
				
				<div class="form-group">
				<label hidden="true">產品圖片</label>
				<form:hidden cssClass="form-control" path="productImg"/>
				</div>
				
				<div class="form-group">
				<label>上架時間</label>
				<form:input cssClass="form-control" path="productTimestamp" readonly="true" />
				</div>
				
				<div class="form-group">
				<label>產品類別</label>
				<form:select cssClass="form-control" path="productCategory">
				<form:option value="蔥類">蔥類</form:option>
				<form:option value="根菜類">根菜類</form:option>
				<form:option value="莖菜類">莖菜類</form:option>
				<form:option value="瓜果類">瓜果類</form:option>
				</form:select>
				</div>
				
				<div class="form-group">
				<label>產品敘述</label>
				<form:textarea cssClass="form-control" path="productDescription" rows="5" />
				</div>
		
			<form:button class="btn btn-info" type="submit"
							 value="儲存"
								>儲存</form:button>
			</form:form>
			  </div>