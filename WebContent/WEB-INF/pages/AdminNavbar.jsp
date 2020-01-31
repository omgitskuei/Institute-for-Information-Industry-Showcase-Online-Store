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
    <title>Admin's Index Navbar | 管理者Navbar</title>
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
        <form class="form"  name="DirectInventoryForm" action=<jstl:url value="/controller.AdminRedirectController" />
        method="post" >
        	<input type="submit" style="background-color: transparent;" class="nav-brand btn btn-lg mt-1 text-white" title="Admin Index Button" name="adminIndexButton" value="管理者">
        	</button>
        </form>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav">
            <li class="nav-item px-2">
             <form class="form nav-link"  name="DirectInventoryForm" action=<jstl:url value="/controller.AdminInventoryController" />
        method="post" >
        	<input type="submit" style="background-color: transparent;" class="btn btn-sm mt-1 text-white" title="Admin Inventory Button" name="adminInventoryButton" value="庫存管理">
        	</form>
            </li>
            <form class="form nav-link" name="DirectOrderForm" action=<jstl:url value="/controller.AdminOrderController" />
        method="post" >
        	<input type="submit" class="btn btn-sm mt-1 text-white" style="background-color: transparent;" title="Admin Order Button" name="adminOrderButton" value="訂單管理">
        	</form>
            <li class="nav-item px-2">
              <form class="form nav-link" name="DirectUserForm" action=<jstl:url value="/controller.AdminUserController" />
        method="post" >
        	<input type="submit" style="background-color: transparent;" class="btn btn-sm mt-1 text-white" title="Admin User Button" name="adminUserButton" value="使用者管理">
            	</form>
            </li>
          </ul>
  
          <ul class="navbar-nav ml-auto">
            <li class="nav-item mr-3 mt-2">
             <span class="nav-link">
                <i class="fas fa-user"></i> Welcome ${userEmail}
              </span>
            </li>
            <li class="nav-item">
			<form class="form nav-link" name="Logout" action=<jstl:url value="/controller.AdminLogoutController" />
        method="post" >
        	<input type="submit" style="background-color: transparent;" class="btn btn-sm mt-1 text-white" title="Admin Logout Button" name="Logout" value="登出">
            	</form>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  

<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
  <script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>

</body>


</html>