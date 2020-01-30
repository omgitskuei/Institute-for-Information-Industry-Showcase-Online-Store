<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib
	prefix="jstl"
	uri="http://java.sun.com/jsp/jstl/core"
%>

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
    <title>Admin's Index | 管理者首頁</title>
    <!-- Stylesheet for HTML5 backward compatibility; normalize.css -->

    <!-- Dictate which Stylesheets to use for this webpage -->
    <style>
    	<%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>


</head>

<body>
   <nav class="navbar navbar-expand-sm navbar-dark bg-dark p-0">
      <div class="container">
        <a href="index.html" class="navbar-brand">管理者</a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav">
            <li class="nav-item px-2">
             <form class="form nav-link" name="DirectInventoryForm" action=<jstl:url value="/controller.AdminInventoryController" />
        method="post" >
        	<input type="submit" class="btn btn-primary btn-sm mt-1" title="Admin Inventory Button" name="adminInventoryButton" value="庫存管理">
        	</form>
            </li>
            <form class="form nav-link" name="DirectOrderForm" action=<jstl:url value="/controller.AdminOrderController" />
        method="post" >
        	<input type="submit" class="btn btn- btn-sm mt-1" title="Admin Order Button" name="adminOrderButton" value="訂單管理">
        	</form>
            <li class="nav-item px-2">
              <form class="form nav-link" name="DirectUserForm" action=<jstl:url value="/controller.AdminUserController" />
        method="post" >
        	<input type="submit" class="btn btn-warning text-black btn-sm mt-1" title="Admin User Button" name="adminUserButton" value="使用者管理">
            </li>
          </ul>
  
          <ul class="navbar-nav ml-auto">
            <li class="nav-item mr-3">
             <span class="nav-link">
                <i class="fas fa-user"></i> Welcome ${userEmail}
              </span>
            </li>
            <li class="nav-item">
             <form class="form" name="logoutForm" action=<jstl:url value="/controller.AdminLogoutController" />
        method="post" >
        	<input type="submit" class="btn btn-danger btn-sm mt-1" title="Admin Logout Button" name="adminLogoutButton" value="登出">
        </form>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  
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
<section id="actions" class="py-4 mb-4 bg-light">
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#addInventoryModal">
          <i class="fas fa-plus"></i> 新增庫存
        </a>
      </div>
      <div class="col-md-3">
        <a href="#" class="btn btn-success btn-block" data-toggle="modal" data-target="#addOrderModal">
          <i class="fas fa-plus"></i> 新增訂單
        </a>
      </div>
      <div class="col-md-3">
        <a href="#" class="btn btn-warning btn-block text-black" data-toggle="modal" data-target="#addUserModal">
          <i class="fas fa-plus"></i> 新增使用者
        </a>
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
              <tr>
                <td>1</td>
                <td>香菜</td>
                <td>蔬菜</td>
                <td>100</td>
                <td>10</td>
                <td>EMPTY</td>
                <td>EMPTY</td>
                <td>
                  <a href="details.html" class="btn btn-secondary">
                    <i class="fas fa-angle-double-right"></i> 詳細資訊
                  </a>
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>高麗菜</td>
                <td>蔬菜</td>
                <td>300</td>
                <td>10</td>
                <td>EMPTY</td>
                <td>EMPTY</td>
                <td>
                  <a href="details.html" class="btn btn-secondary">
                    <i class="fas fa-angle-double-right"></i> 詳細資訊
                  </a>
                </td>
              </tr>
              <tr>
                <td>3</td>
                <td>花椰菜</td>
                <td>蔬菜</td>
                <td>400</td>
                <td>10</td>
                <td>EMPTY</td>
                <td>EMPTY</td>
                <td>
                  <a href="details.html" class="btn btn-secondary">
                    <i class="fas fa-angle-double-right"></i> 詳細資訊
                  </a>
                </td>
              </tr>
              <tr>
                <td>4</td>
                <td>蘋果</td>
                <td>水果</td>
                <td>1</td>
                <td>10</td>
                <td>EMPTY</td>
                <td>EMPTY</td>
                <td>
                  <a href="details.html" class="btn btn-secondary">
                    <i class="fas fa-angle-double-right"></i> 詳細資訊
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="col-md-3">
        <div class="card text-center bg-primary text-white mb-3">
          <div class="card-body">
            <h3>庫存商品</h3>
            <h4 class="display-4">
              <i class="fas fa-pencil-alt"></i> 6
            </h4>
            <a href="inventories.html" class="btn btn-outline-light btn-sm">檢視</a>
          </div>
        </div>

        <div class="card text-center bg-success text-white mb-3">
          <div class="card-body">
            <h3>訂單</h3>
            <h4 class="display-4">
              <i class="fas fa-folder"></i> 4
            </h4>
            <a href="orders.html" class="btn btn-outline-light btn-sm">檢視</a>
          </div>
        </div>

        <div class="card text-center bg-warning text-white mb-3">
          <div class="card-body">
            <h3>使用者</h3>
            <h4 class="display-4">
              <i class="fas fa-users"></i> 4
            </h4>
            <a href="<jstl:url value = "/controller.ProfileController"/>" class="btn btn-outline-light btn-sm" > Show Profile </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>



   <!--  <main>
        <h2>URI: WebContent/AdminIndex</h2>

        <form class="form" name="loginForm" action=<jstl:url value="/controller.AdminIndexController" />
        method="post" >

        	Form buttons
        	<label>Click to Sign in as Admin:</label>
        	<input type="submit" title="Admin Login Button"name="adminSignInButton" value="Go to Admin Login">
        	<span></span>

        </form>

        <h3>Other files under URI: WebContent/...</h3>

        <a href='./form.jsp'>./form.jsp</a><br>
        <a href='./index.jsp'>./index.jsp</a><br>
        <a href='./loginSystem.jsp'>./loginSystem.jsp</a><br>
        <a href='./UploadFile.jsp'>./UploadFile.jsp</a><br>
        <a href='./viewImagePage.jsp'>./viewImagePage.jsp</a><br>


    </main> -->


<!-- FOOTER -->
<footer id="main-footer" class="bg-dark text-white mt-5 p-5">
  <div class="container">
    <div class="row">
      <div class="col">
        <p class="lead text-center">
          Copyright &copy;
          <span id="year"></span>
          FarmVille
        </p>
      </div>
    </div>
  </div>
  </footer>
  
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

<!-- ADD Order MODAL -->
<div class="modal fade" id="addOrderModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title">新增訂單</h5>
        <button class="close" data-dismiss="modal">
          <span>&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
          <label for="orderTitle">商品名稱
            </label>
            <input type="text" class="form-control">
          </div>
          <div class="form-group">
            <label for="orderCategory">商品類別</label>
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
            <label for="orderUserID">買家帳號</label>
            <input type="text" class="form-control">
          </div>
          <div class="form-group">
            <label for="orderTransportMethod">寄送方式</label>
            <select class="form-control">
              <option value="">全家</option>
              <option value="">7-11</option>
              <option value="">郵局</option>
              <option value="">黑貓宅急便</option>
            </select>
          </div>
          <div class="form-group">
            <label for="orderPayMethod">付款方式</label>
            <select class="form-control">
              <option value="">貨到付款</option>
              <option value="">信用卡付款</option>
              <option value="">轉帳</option>
              <option value="">Apple Pay</option>
              <option value="">街口支付</option>
              <option value="">Line Pay</option>
            </select>
          </div>
          <div class="form-group">
            <label for="orderQuote">備註</label>
            <textarea name="editor1" class="form-control"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-success" data-dismiss="modal">儲存</button>
      </div>
    </div>
  </div>
</div>

<!-- ADD User MODAL -->
<div class="modal fade" id="addUserModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-warning text-black">
        <h5 class="modal-title">新增使用者</h5>
        <button class="close" data-dismiss="modal">
          <span>&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="email">電子信箱</label>
            <input type="email" class="form-control">
          </div>
          <div class="form-group">
            <label for="password">密碼</label>
            <input type="password" class="form-control">
          </div>
          <div class="form-group">
            <label for="password2">確認密碼</label>
            <input type="password" class="form-control">
          </div>
          <div class="form-group">
            <label for="orderPayMethod">身份</label>
            <select class="form-control">
              <option value="">賣家</option>
              <option value="">買家</option>
              <option value="">管理者</option>
            </select>
           </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-warning" data-dismiss="modal">儲存</button>
      </div>
    </div>
  </div>
</div>
<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
  <script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>

  <script>
    // Get the current year for the copyright
    $('#year').text(new Date().getFullYear());

    CKEDITOR.replace('editor1');
  </script>
</body>


</html>