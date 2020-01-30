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
    <title>Admin's Inventory | 管理者庫存頁</title>
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
        <h1><i class="fas fa-pencil-alt"> 庫存管理</i></h1>
      </div>
    </div>
  </div>
</header>

<!-- SEARCH -->
<section id="search" class="py-4 mb-4 bg-light">
  <div class="container">
    <div class="row">
      <div class="col-md-6 ml-auto">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="搜尋庫存商品...">
            <div class="input-group-append">
                <button class="btn btn-primary">搜尋</button>
            </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- INVENTORY -->
<section id="inventory">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
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

          <!-- PAGINATION -->
          <nav class="ml-4">
              <ul class="pagination">
                  <li class="page-item disabled">
                      <a href="#" class="page-link">Previous</a>
                  </li>
                  <li class="page-item active">
                      <a href="#" class="page-link">1</a>
                  </li>
                  <li class="page-item">
                    <a href="#" class="page-link">2</a>
                </li>
                    <li class="page-item">
                    <a href="#" class="page-link">3</a>
                 </li>
                <li class="page-item">
                    <a href="#" class="page-link">Next</a>
                </li>
              </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</section>

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

  <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>

  <script>
    // Get the current year for the copyright
    $('#year').text(new Date().getFullYear());

  </script>
</body>


</html>