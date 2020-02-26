<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品</title>
</head>
<body>
<!-- NAVBAR -->
<%@include file="/WEB-INF/pages/AdminNavbar.jsp" %>

<div class="container">
<h1>新增商品</h1>
 <form action="<c:url value="/AdminProduct/addProduct"/>" method="post" enctype="multipart/form-data">
          <div class="form-group">
            <label for="inventoryTitle">商品名稱
            </label>
            <input type="text" name="productName" class="form-control">
          </div>
          <div class="form-group">
            <label for="productCategory">商品類別</label>
            <select class="form-control" name="productCategory">
              <option value="蔬菜">蔬菜</option>
              <option value="水果">水果</option>
            </select>
          </div>
          <div class="form-group">
            <label for="inventoryQuantity">商品數量</label>
            <input type="number" class="form-control" min="0" name="productStock">
          </div>
          <div class="form-group">
            <label for="inventoryPrice">商品單價</label>
            <div class="input-group">
              <div class="input-group-prepend">
                  <span class="input-group-text">NT$</span>
              </div>
              <input class="form-control" type="text" name="productPrice">
              <div class="input-group-append">
                  <span class="input-group-text">元</span>
              </div>
          </div>
          </div>
          <div class="form-group">
            <label for="image">上傳圖片</label>
            <div class="custom-file">
              <input type="file" name="file" class="custom-file-input" id="imgInput" >
              <label for="image" class="custom-file-label">選擇檔案</label>
              <img id="output" width="100px" src="#" alt="your image" />
            </div>
            <small class="form-text text-muted">檔案勿超過3MB</small>
          </div>
          <div class="form-group">
            <label for="inventoryDiscription">商品描述</label>
            <textarea name="productDescription" class="form-control"></textarea>
          </div>
        <input type="submit" class="btn btn-primary" value="送出" data-dismiss="modal" />
        </form>
        </div>
        


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    
	    reader.onload = function(e) {
	      $('#output').attr('src', e.target.result);
	    }
	    
	    reader.readAsDataURL(input.files[0]);
	  }
	}

	$("#imgInput").change(function() {
	  readURL(this);
	});
</script>
</body>
</html>