<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
   	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<meta http-equiv="X-UA-Compatible" content="ie=edge">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
  	crossorigin="anonymous">
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
  	crossorigin="anonymous">
    <title>Farmville | 管理者首頁</title>
    <!-- Stylesheet for HTML5 backward compatibility; normalize.css -->

    <!-- Dictate which Stylesheets to use for this webpage -->

</head>

<body>
<!-- NAVBAR -->
<%@include file="/WEB-INF/pages/AdminNavbar.jsp" %>
<!-- HEADER -->
<header id="main-header" class="py-2 bg-primary text-white">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <h1><i class="fas fa-cog"> 管理者儀表板</i></h1>
      </div>
    </div>
  </div>
</header>

<!-- ACTIONS -->
<section id="actions" class="py-4 bg-light">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#addInventoryModal">
					<i class="fas fa-plus"></i> 新增庫存
 				</a>
			</div>
			<!-- Send Newsletter Button - Opens Modal -->
			<div class="col-md-3">
				<a href="#" class="btn btn-success btn-block" data-toggle="modal" data-target="#sendNewsletterModal">
					<i class="fas fa-plus"></i> 寄送訂閱信
				</a>
			</div>
		</div>
	</div>
</section>

<!-- SEARCH -->
<section id="search" class="py-4 mb-4 bg-light">
  <div class="container">
    <div class="row">
      <div class="col-md-9">
      <!-- FORM URL -->
      <form class="form" name="searchForm" action="<jstl:url value="searchBarProducts" />" method="post">
        <div class="input-group">
        	<!-- SEARCH BAR -->
            <input type="search" class="form-control" placeholder="搜尋..." name="searchBar">
            <!-- SUBMIT BUTTON -->
            <div class="input-group-append">
                <input type="submit" class="btn btn-primary" value="搜尋">
            </div>

            <div class="col-md-3">
     	 	<select name="selectCategory" id="theme" class="form-control">
      			<option selected value="搜尋商品類別">搜尋商品類別</option>
      			<option value="蔥類">蔥類</option>
      			<option value="根菜類">根菜類</option>
      			<option value="莖菜類">莖菜類</option>
      			<option value="瓜果類">瓜果類</option>
      			</select>
   		   </div>
        </div>
      </form>
      </div>
      
     </div>
    </div>
   </section>

<!-- INVENTORY -->
<section id="inventory">
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <div class="card">
          <div class="card-header">
            <h4>最新庫存商品</h4>
          </div>
          <table class="table table-striped">
            <thead class="thead-dark">
              <tr>
                <th>#</th>
                <th>商品名稱</th>
                <th>商品類別</th>
                <th>商品數量（斤）</th>
                <th>商品單價</th>
                <th>商品快照</th>
                <th>商品描述</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
            	<jstl:if test="${not empty SearchResults}">
            		<jstl:forEach var="product" items="${SearchResults}" varStatus="status">
						<!-- construct an "update" link with customer id -->
						<jstl:url var="updateLink" value="/AdminProduct/updateForm">
							<jstl:param name="productID" value="${product.productID}" />
						</jstl:url>
						<!-- construct an "delete" link with customer id -->
						<jstl:url var="deleteLink" value="/AdminProduct/delete">
							<jstl:param name="productID" value="${product.productID}" />
						</jstl:url>
						<tr>
							<td>${product.productID}</td>
 							<td>${product.productName}</td>
 							<td>${product.productCategory}</td>
 							<td>${product.productStock}</td>
							<td>${product.productPrice}</td>
							<td><img src="${product.productImg}" alt="${product.productName}" width="75" /></td>
							<td>${product.productDescription}</td>
<%-- 							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${product.productTimestamp}" /></td> --%>		
							<td>
								<a href="${updateLink}" class="btn btn-secondary">
									<i class="fas fa-angle-double-right"></i> 修改
								</a>
								<a href="${deleteLink}" class="btn btn-danger">
									<i class="fas fa-angle-double-right"></i> 刪除
								</a>
							</td>
						</tr>
					</jstl:forEach>
				</jstl:if>
            </tbody>
          </table>
        </div>
      </div>
      
      
      <div class="col-md-3">
        <div class="card text-center bg-primary text-white mb-3">
          <div class="card-body">
            <h3>庫存商品</h3>
            <h4 class="display-4">
              <i class="fas fa-pencil-alt"></i> ${dataNum.product}
            </h4>
          </div>
        </div>

        <div class="card text-center bg-success text-white mb-3">
          <div class="card-body">
            <h3>訂單</h3>
            <h4 class="display-4">
              <i class="fas fa-folder"></i> ${dataNum.order}
            </h4>
          </div>
        </div>

        <div class="card text-center bg-warning text-white mb-3">
          <div class="card-body">
            <h3>使用者</h3>
            <h4 class="display-4">
              <i class="fas fa-users"></i> ${dataNum.user}
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- FOOTER -->
<%@include file="/WEB-INF/pages/AdminFooter2.jsp" %>
  
  <!-- MODALS -->

<!-- ADD Inventory MODAL -->
<div class="modal fade" id="addInventoryModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title">新增庫存</h5>
        <button class="close" data-dismiss="modal">
          <span>&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="inventoryTitle">商品名稱
            </label>
            <input type="text" class="form-control">
          </div>
          <div class="form-group">
            <label for="inventoryCategory">商品類別</label>
            <select class="form-control">
              <option value="">蔬菜</option>
              <option value="">水果</option>
            </select>
          </div>
          <div class="form-group">
            <label for="inventoryQuantity">商品數量</label>
            <input type="number" class="form-control" min="0">
          </div>
          <div class="form-group">
            <label for="inventoryPrice">商品單價</label>
            <div class="input-group">
              <div class="input-group-prepend">
                  <span class="input-group-text">NT$</span>
              </div>
              <input class="form-control" type="text">
              <div class="input-group-append">
                  <span class="input-group-text">元</span>
              </div>
          </div>
          </div>
          <div class="form-group">
            <label for="image">上傳圖片</label>
            <div class="custom-file">
              <input type="file" class="custom-file-input" id="image">
              <label for="image" class="custom-file-label">選擇檔案</label>
            </div>
            <small class="form-text text-muted">檔案勿超過3MB</small>
          </div>
          <div class="form-group">
            <label for="inventoryDiscription">商品描述</label>
            <textarea name="editor1" class="form-control"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">儲存</button>
      </div>
    </div>
  </div>
</div>

<!-- SEND OUT Newsletter MODAL -->
<section>
	<div class="modal fade" id="sendNewsletterModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Close button -->
				<div class="modal-header bg-success text-white">
					<h5 class="modal-title">送出訂閱信</h5>
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Newsletter email header and email message body form-->
				<div class="modal-body">
					<form 
						class="form"
						name="sendNewsletterForm" 
						action="<jstl:url value="sendNewsletter" />" 
						method="post">
						<div class="form-group">
							<label for="title">信件主旨 - Email Subject</label>
							<input
								name="title"
								type="text"
								placeholder="Enter email subject here"
								class="form-control"
								id="title">
						</div>
						<div class="form-group">
							<label for="messageBody">信件內容 - Message Body</label>
							<textarea
								name="messageBody"
								cols="100"
								rows="10"
								id="messageBody"
								onfocus="if(value=='限定100字元'){value=''}"
								onblur="if (value ==''){value='限定100字元'}"
								class="form-control">
							</textarea>
						</div>
						<!-- Submit button -->
						<div class="modal-footer">
							<input type="submit" value="一鍵送出" class="btn btn-success" />
						</div>
					</form>
					<div class="col-md-6 mx-auto mt-2">
						<input onclick="autofillSendNewsletter()" type="button" value="一鍵輸入" class="btn btn-success text-white btn-block" id="submit">
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
  <script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>

	<script>
		function autofillSendNewsletter() {
		var title = document.getElementById("title");
		var body = document.getElementById("messageBody");
		title.value = "March Sales!"
		body.value = "Celebrate this month with in-season fruits and vegetables: Asparagus, Apples, Artichokes, Avocados, Bananas, Beets, Broccoli, Brussels Sprouts! - FarmVille Produce Curator";
	};
	/* footer年份 */
    // Get the current year for the copyright
    $('#year').text(new Date().getFullYear());

  </script>
</body>


</html>