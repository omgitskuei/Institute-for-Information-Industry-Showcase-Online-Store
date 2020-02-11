<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib
	prefix="jstl"
	uri="http://java.sun.com/jsp/jstl/core"
%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
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
    <title>Order Details List | 管理者訂單明細頁</title>
    <!-- Stylesheet for HTML5 backward compatibility; normalize.css -->

    <!-- Dictate which Stylesheets to use for this webpage -->
    <style>
    	<%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>


</head>

<body>
<!-- NAVBAR -->
<%@include file="/WEB-INF/pages/AdminNavbar.jsp" %>
  
<!-- HEADER -->
<header id="main-header" class="py-2 bg-success text-white">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <h1><i class="fas fa-pencil-alt"> 訂單管理明細</i></h1>
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
            <input type="text" class="form-control" placeholder="搜尋訂單...">
            <div class="input-group-append">
                <button class="btn btn-success">搜尋</button>
            </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- ORDERS -->
<section id="ordres">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header">
            <h4>訂單明細</h4>
          </div>
          <table class="table table-striped">
            <thead class="thead-dark">
              <tr>
                <th>產品名稱</th>
                <th>單價</th>
                <th>數量</th>
                <th>總價</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <jstl:forEach var="orderDetailsList" items="${detailsList}">
              <!-- construct an "update" link with customer id -->
			<c:url var="updateLink" value="/AdminOrder/updateForm">
						<c:param name="orderID" value="${orderDetailsList.orderDetailID}" />
					</c:url>

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/AdminOrder/delete">
						<c:param name="orderID" value="${orderDetailsList.orderDetailID}" />
					</c:url>
              <tr>
				<td>${orderDetailsList.productName}</td>	
				<td>${orderDetailsList.productPrice}</td>	
				<td>${orderDetailsList.productCount}</td>
				<td>${orderDetailsList.productPrice*orderDetailsList.productCount}</td>
				
				<td>
                  <a href="#" class="btn btn-secondary">
                    <i class="fas fa-angle-double-right"></i> 修改
                  </a>
                </td>
                	 <td>
                  <a href="#" class="btn btn-danger">
                    <i class="fas fa-angle-double-right"></i> 刪除
                  </a>
                </td>
              </tr>
             </jstl:forEach>
            </tbody>
          </table>

          <!-- PAGINATION -->
          <nav class="ml-4">
              <ul class="pagination">
                  <li class="page-item disabled">
                      <a href="#" class="page-link">上一頁</a>
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
                    <a href="#" class="page-link">下一頁</a>
                </li>
              </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- FOOTER -->
<%@include file="/WEB-INF/pages/AdminFooter2.jsp" %>

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