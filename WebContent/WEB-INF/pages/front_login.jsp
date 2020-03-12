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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css"
        integrity="sha256-HAaDW5o2+LelybUhfuk0Zh2Vdk8Y2W2UeKmbaXhalfA=" crossorigin="anonymous" />
    <style>
        <%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <title>Farmville | 登入頁</title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>

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
                        <h1>歡迎登入FarmVille</h1>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- LOGIN SECTION -->
    <section class="py-3">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card p-4">
                        <div class="card-body">
                            <form action="<jstl:url value="/userSignIn" />" method="post">
                                <h3 class="text-center">請輸入帳號密碼</h3>
                                <hr>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="email">電子信箱</label>
                                            <input
                                            	type="email"
         										class="form-control"
         										placeholder="請輸入電子信箱 Email"
         										name="userEmail"
         										id="userEmail"
												value="${cookie.UserEmailCookie.getValue()}">
											<span style="color: red;" id="emailErrorSpan">${errors.emailError}</span>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="password">密碼</label>
                                            <input 
                                            	type="password" 
                                            	class="form-control"
                                            	placeholder="請輸入密碼 Password"
                                            	name="userPwd"
                                            	id="userPwd"
												value="${cookie.UserPasswordCookie.getValue()}">
											<span style="color: red;" id="pwdErrorSpan">${errors.pwdError}</span>
                                        </div>
                                    </div>
                                    <div class="col-md-6 pl-4">
                                        <div class="form-group">
                                            <input
                                            	class="form-check-input" 
												type="checkbox"
												id="inlineFormCheck"
												name="rememberMe" /> 
											<label
												class="form-check-label"
												for="FormCheck">記住帳號密碼
											</label>
											<span style="color: red;" id="notFoundErrorSpan">${errors.notFoundError}</span>	
											<br>
                                        </div>
                                    </div>
                                    <div class="col-md-6 text-right">
                                        <div class="form-group">
                                            <a href="<jstl:url value="/directForgotPassword" />">忘記密碼</a>
                                        </div>
                                    </div>
                                </div>
								<!-- SUBMIT LOGIN FORM BUTTON -->
								<div class="row d-flex justify-content-end">
									<div class="col-md-2">
										<div class="form-group">
												<input type="submit" value="登入" class="btn btn-success text-white btn-block">	 
										</div>
									</div>
									
								<!-- OEPN SIGN UP MODAL BUTTON -->
									<div class="col-md-2">
										<div class="form-group">
											<input
												type="button" 
												value="註冊" 
												class="btn btn-secondary btn-block text-white" 
												data-toggle="modal" 
												data-target="#signUpModal"/>
										</div>
									</div>
								</div>
							</form>
							<!-- GOOGLE RECAPTCHA -->
							<div class="col-md-6 mx-auto mt-2">
								<form action="?" method="POST" >
     								<div style="resize: horizontal;" class="g-recaptcha" data-sitekey="6LdDltsUAAAAACnxxFD8oacBBWlWdgPE7X-2VyCp" data-theme="light" data-size="normal"></div>
									<div class="mx-auto col-md-8">
									<br>
										<input onclick="autofillSignIn()" type="button" value="登入 Demo" class="btn btn-secondary text-white btn-block" id="submit">
										<input onclick="autofillSignIn2()" type="button" value="注冊後　Demo" class="btn btn-secondary text-white btn-block" id="submit">
									</div>
								</form>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!-- SIGN UP MODAL -->
	<section>
		<div class="modal" id="signUpModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">註冊</h5>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form 
							class="form" 
							name="signupForm"
							action="<jstl:url value="/userSignUp" />" 
							method="post"
							id="signupForm">
							<div class="form-group">
								<label for="email">電子信箱</label>
								<input
									type="email"
									placeholder="請輸入電子信箱 Email"
									class="form-control" 
									name="nEmail"
									id="newEmail">
							</div>
							<div class="form-group">
								<label for="password">密碼</label>
								<input
									name="nPwd"
									type="password"
									placeholder="請輸入密碼 Password" 
									class="form-control" 
									id="newPwd">
							</div>
							<div class="form-group">
								<label for="password2">確認密碼</label> 
								<input 
									type="password"
									placeholder="請再輸入密碼 Password" 
									class="form-control" 
									name="rPwd"
									id="confirmPwd">
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-success text-white" value="送出">送出</button>
							</div>
						</form>
						<br>
						<div class="col-md-6 mx-auto mt-2">
							<input onclick="autofillSignUp()" type="button" value="一鍵輸入" class="btn btn-secondary btn-block" id="submit">
						</div>
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
                    <p>
                        版權所有 &copy;
                        <span id="year"> FarmVille</span>
                    </p>
                </div>
            </div>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
    <script>
        // Get the current year for copyright
        $("#year").text(new Date().getFullYear());
        
        $(function() {
    		$("#userEmail").keyup(function() {
    			var userEmail = $("#userEmail").val();
    			if (userEmail != "") {
    				var userPwd = $.cookie(userEmail);
    				if (userPwd != null) {
    					$("#userPwd").val(userPwd);
    					$("#rememberMe").attr("checked", true);
    				}
    			}
    		})
    	})
    	
    	function autofillSignIn() {
			var userEmail = document.getElementById("userEmail");
			var userPwd = document.getElementById("userPwd");
			userEmail.value = "leon123@yahoo.com.tw"
			userPwd.value = "Qq22222!";
		};
		
		function autofillSignIn2() {
			var userEmail = document.getElementById("userEmail");
			var userPwd = document.getElementById("userPwd");
			userEmail.value = "demouserEEIT111@gmail.com"
			userPwd.value = "Qq22222!";
		};
		
		function autofillSignUp() {
			var userEmail = document.getElementById("newEmail");
			userEmail.value = "demouserEEIT111@gmail.com"
			var userPwd = document.getElementById("newPwd");
			userPwd.value = "Qq22222!";
			var confirmPwd = document.getElementById("confirmPwd");
			confirmPwd.value = "Qq22222!"
			
		};
    </script>
</body>

</html>