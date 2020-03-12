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
							<strong>${sumTotal}</strong>
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
				<form action="<jstl:url value="/directStripeCheckoutStep2"/>" method=POST class="needs-validation">
				<!-- ROW 1, 2 groups so md-6 -->
					<div class="form-row text-left mt-2">
					<!-- Group: Name -->
						<div class="form-group col-md-6">
							<label for="fullname">姓名</label>
							<input type="text" class="form-control " name="fullname" id="fullname" placeholder="姓名" value="${userData.fullname}" required>
							<div class="invalid-feedback">請填寫姓名</div>
						</div>
					<!-- Group: Email -->
						<div class="form-group col-md-6">
							<label for="email">Email</label>
							<input type="email" class="form-control" name="email" id="email" placeholder="Email" value="${userData.email}" required>
							<div class="invalid-feedback">請填寫Email</div>
						</div>
					</div>
				<!-- ROW 2, 3 groups so md-4 -->
					<div class="form-row text-left">
					<!-- Group: Country -->
						<div class="form-group col-md-4">
							<label for="country">國家</label>
							<select name="country" id="country" name="country" class="form-control">
								<option value="">挑選國家</option>
								<option value="Taiwan">台灣</option>
 								<option value="United States">美國</option>
	 							<option value="Japan">日本</option>
							</select>
						</div>
					<!-- Group: City -->
						<div class="form-group col-md-4">
							<label for="city">城市</label>
							<select name="city" id="city" name="city" class="form-control" >
								<option value="">挑選城市</option>
								<option value="Taipei">台北市</option>
								<option value="Taichung">台中市</option>
								<option value="Kaohsiung">高雄市</option>
							</select>
						</div>
					<!-- Group: ZipCode -->
						<div class="form-group col-md-4">
							<label for="zipcode">郵遞區號</label>
							<input type="text" class="form-control" name="zipcode" id="zipcode" placeholder="000" value="" required>
						</div>
					</div>
				<!-- ROW 3, 1 group so md-12 -->
					<div class="form-row text-left">
					<!-- Group: Address -->
						<div class="form-group col-md-12">
							<label for="address">地址</label>
							<input type="text" class="form-control" name="address" id="address" value="${userData.address}" required>
						</div>
					</div>
				<!-- ROW 4, 1 group so md-12  -->
					<div class="form-row text-left">
					<!-- Group: ShipAddress -->
						<div class="form-group col-md-12">
							<label for="shipAddress">送貨地址</label>
							<input type="text" class="form-control" name="shipAddress" id="shipAddress" value="" required>
						</div>
					</div>
				<!-- ROW 5, 1 group so md-12  -->
					<div class="form-row text-left">
					<!-- Group: Buttons -->
						<div class="mx-auto col-md-4">
							<a href=<jstl:url value="/directservices"/> class="btn btn-secondary mr-3 btn-block">繼續選購</a>
						</div>
						<div class="mx-auto col-md-4">
							<a href="<jstl:url value="/directshoppingcart"/>" type="submit" class="btn btn-warning text-white mr-3 btn-block">回上一頁</a>
						</div>
						<div class="mx-auto col-md-4">
							<input type="submit" value="進下一步" class="btn btn-success text-white mr-3 btn-block">	 
						</div>
					</div>
						
						
					</form>
				</div>
			</div>
		</div>
						<div class="col-md-6 mx-auto md-12">
							<input onclick="autofill()" type="button" value="一鍵輸入" class="btn btn-secondary btn-block" id="submit">
						</div>
						<script>
							function autofill() {
								var fullname = document.getElementById("fullname");
								var email = document.getElementById("email");
								var country = document.getElementById("country");
								var city = document.getElementById("city");
								var zipcode = document.getElementById("zipcode");
								var address = document.getElementById("address");
								var shipaddress = document.getElementById("shipAddress");
								fullname.value = "Leon Chen";
								email.value = "leon123@yahoo.com.tw";
								country.value = "Taiwan";
								city.value = "Taipei";
								zipcode.value = "105";
								address.value = "No. 110號, Section 3, Minquan East Road, Songshan District";
								shipaddress.value = "No. 110號, Section 3, Minquan East Road, Songshan District";
							};
						</script>
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
