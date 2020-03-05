<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Noto+Sans+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" />
    <style>
    	<%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
        *{
        	border:2px,solid,black;
        }
    </style>
   <title>結帳</title>
  </head>
  <body>
    <!-- START -->
  <%@include file="/WEB-INF/pages/front_navbar.jsp" %>
    <!-- PAGE HEADER -->

    <header id="page-header">
      <div class="dark-overlay-title">
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>結帳</h1>
                    <p>此為您的帳單</p>
                </div>
            </div>
        </div>
      </div>
    </header>

<!-- CHECKOUT SECTION -->
<section class="py-3">
<!-- Heading information - 3-page dog ear tags, Total cost -->
<div class="container" >
	<div class="row mt-3 d-flex justify-content-center">
		<div class="col-md-2">
			<div class="alert alert-success alert-rounded text-center" role="alert">1.輸入訂單資料</div>
 		</div>
		<div class="col-md-2">
			<div class="alert alert-light alert-rounded text-center" role="alert">2.金流付款</div>
		</div>
		<div class="col-md-2">
			<div class="alert alert-light alert-rounded text-center" role="alert">3.訂單完成</div>
		</div>
	</div>
	<div class="row d-flex justify-content-center mt-4">
		<div class="col-md-6">
			<div class="accordion" id="accordionExample">
				<div class="card card-bottom">
					<div class="card-header  d-flex justify-content-between" id="headingOne">
						<div class="h3 d-inline-block mt-3">Total Cost</div>
						<div class="h3 d-inline-block mt-2">
							<strong>$ 1059</strong>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Mailing details -->
<div class="container">
	<div class="row d-flex justify-content-center">
		<div class="col-md-6">
			<div class="card text-center my-5 border-0">
				<div class="card-header border-0">
					<div class="h3 mt-1"> 訂購人資訊 </div>
				</div>
				<form class="needs-validation" novalidate>
					<div class="form-row text-left mt-3">
						<div class="form-group col-md-6">
							<label for="name">姓名</label>
							<input type="text" class="form-control " id="name" placeholder="姓名" required>
							<div class="invalid-feedback">請填寫姓名</div>
						</div>
						<div class="form-group col-md-6">
							<label for="mail">Email</label>
							<input type="email" class="form-control" id="mail" placeholder="Email" required>
							<div class="invalid-feedback">請填寫Email</div>
						</div>
					</div>
				<div class="form-row text-left">
					<div class="form-group col-md-4">
						<label for="section">國家</label>
							<select name="" id="section" class="form-control">
								<option value="">台灣</option>
								<option value="">美國</option>
								<option value="">日本</option>
								<option value="">韓國</option>
								<option value="">菲律賓</option>
								<option value="">習近平</option>
								<option value="">越南</option>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label for="zone">城市</label>
							<select name="" id="zone" class="form-control">
								<option value="">台北市</option>
								<option value="">台中市</option>
								<option value="">高雄市</option>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label for="postal-code">郵遞區號</label>
							<input type="text" class="form-control" id="postal-code" placeholder="" required>
						</div>
					</div>
					<div class="form-row">
						<label for="address">地址</label>
						<input type="text" class="form-control" id="mail" required>
					</div>
					<div class="form-row">
						<label for="shipaddress">送貨地址</label>
						<input type="text" class="form-control" id="mailaddress" required>
					</div>
					

						<div class="mt-3 d-flex justify-content-end">
							<a href="service.html" class="btn btn-secondary mr-2">繼續選購</a>
							<a href="<jstl:url value="/directStripeCheckoutStep2"/>" type="submit" class="btn btn-success text-white">進行下一步</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

    <!-- FOOTER -->
    <footer id="main-footer" class="text-center p-4 bg-dark text-white">
      <div class="container">
        <div class="row">
          <div class="col">
            <p>版權所有 &copy;
              <span id="year"> FarmVille</span>
            </p>
          </div>
        </div>
      </div>
    </footer>



    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
      integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
      integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" integrity="sha256-NXRS8qVcmZ3dOv3LziwznUHPegFhPZ1F/4inU7uC8h0=" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());
      

      
    </script>
    </body>
</html>
