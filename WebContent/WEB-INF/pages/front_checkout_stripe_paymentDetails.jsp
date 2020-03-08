<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		</style>
    	<script
			src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous">
		</script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous">
		</script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
			integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
			crossorigin="anonymous">
		</script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"
			integrity="sha256-NXRS8qVcmZ3dOv3LziwznUHPegFhPZ1F/4inU7uC8h0="
			crossorigin="anonymous">
		</script>
		<script
			src="https://kit.fontawesome.com/c3dc04dc4d.js"
			crossorigin="anonymous">
		</script>
		<script>
			// Get the current year for copyright
			$("#year").text(new Date().getFullYear());
		</script>
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
	
	<div class="container" >
	<!-- Heading information - 3-page dog ear tags, Total cost -->
		<div class="row mt-3 d-flex justify-content-center">
			<div class="col-md-2">
				<div class="alert alert-light alert-rounded text-center" role="alert">1.輸入訂單資料</div>
 			</div>
			<div class="col-md-2">
				<div class="alert alert-success alert-rounded text-center" role="alert">2.金流付款</div>
			</div>
			<div class="col-md-2">
				<div class="alert alert-light alert-rounded text-center" role="alert">3.訂單完成</div>
			</div>
		</div>
	<!-- TOTAL COST -->
		<div class="row d-flex justify-content-center mt-4">
			<div class="col-md-6">
				<div class="accordion" id="accordionExample">
					<div class="card card-bottom">
						<div class="card-header  d-flex justify-content-between" id="headingOne">
							<div class="h3 d-inline-block mt-3">Total Cost</div>
								<div class="h3 d-inline-block mt-2">
								<div name=sumTotal>
									<strong>${sumTotal}</strong>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
	<!-- Payment Header -->
		<div class="row d-flex justify-content-center">
			<div class="col-md-6">
				<div class="card text-center my-5 border-0">
					<div class="card-header border-0">
						<div class="h3 mt-1"> 付款資訊 </div>
					</div>
					<form action="<jstl:url value="/directCheckoutSuccess"/>" method=POST class="needs-validation">
					<!-- Row 1 -->
						<div class="form-row text-left mt-2">
						<!-- Group 1 - Card number -->
							<div class="form-group col-md-8">
								<label for="cardNum">信用卡卡號</label>
								<input type="text" class="form-control " name="cardNum" id="cardNum" placeholder="Card number">
								<div class="cardNumError"></div>
							</div>
						<!-- Group 2 - Expiration date -->
							<div class="form-group col-md-4">
								<label for="expiry">有效期限</label>
								<input type="text" class="form-control " name="expiry" id="expiry" placeholder="Card expiration Year (MM/YY)" maxlength="5">
								<div class="expDateError"></div>
							</div>
						</div>
					<!-- Row 2 -->
						<div class="form-row text-left">
						<!-- Group 1 - CVC 3-digit-code -->
							<div class="form-group col-md-6">
								<label for="cvCode">卡片背面後3碼 (CVC) </label>
								<input type="text" class="form-control " name="cvCode" id="cvCode" placeholder="Card CVC (###)" maxlength="3">
							</div>
						<!-- Group 2 - Receipt type -->
							<div class="form-group col-md-6">
								<label for="receipt">發票</label>
								<select name="receipt" class="form-control" name="receipt" id="receipt">
									<option value="">發票種類</option>
									<option value="personalReceipt">個人電子發票</option>
									<option value="donateReceipt">捐贈發票</option>
									<option value="companyReceipt">公司戶電子發票</option>
								</select>
							</div>
						</div>
					<!-- Row 3 -->
						<div class="form-row text-left">
						<!-- Group 1 - Coupon -->
							<div class="form-group col-md-8">
								<label for="couponCode">折價卷代碼</label>
								<input type="text" class="form-control" name="couponCode" id="couponCode" placeholder="Coupon Code" maxlength="10">
							</div>
						<!-- Group 2 - wallet -->
							<div class="form-group col-md-4">
								<label for="walletAmount">會員錢包: ${walletAmount}</label>
								<input type="text" class="form-control" name="walletAmount" id="walletAmount" placeholder="0.00" required>
							</div>
						</div>
					<!-- Row 4 -->
						<div class="form-row text-left">
						<!-- Group 1 - buttons -->
							<div class="form-group col-md-12">
								<a href=<jstl:url value="/directservices"/> class="btn btn-secondary mr-3">繼續選購</a>
								<a href="<jstl:url value="/directStripeCheckoutStep1"/>" type="submit" class="btn btn-warning text-white mr-3">回上一頁</a>
								<input type="submit" value="確認付款" class="btn btn-success text-white mr-3">
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
								var cardNum = document.getElementById("cardNum");
								var expiry = document.getElementById("expiry");
								var cvCode = document.getElementById("cvCode");
								var receipt = document.getElementById("receipt");
								var couponCode = document.getElementById("couponCode");
								var walletAmount = document.getElementById("walletAmount");
								cardNum.value = "4242 4242 4242 4242";
								expiry.value = "09/26";
								cvCode.value = "335";
								receipt.value = "personalReceipt";
								couponCode.value = "";
								walletAmount.value = "";
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




    </body>
</html>
