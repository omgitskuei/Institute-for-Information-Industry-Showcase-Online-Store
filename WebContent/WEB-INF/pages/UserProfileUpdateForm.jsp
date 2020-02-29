<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用者資料</title>
</head>
<body>

<%@include file="/WEB-INF/pages/front_navbar.jsp" %>

  <!-- PAGE HEADER -->
    <header id="page-header">
      <div class="dark-overlay-title"> 
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>會員中心</h1>
                </div>
            </div>
          </div>  
        </div>
    </header>
    <!-- PAGE HEADER -->
    
    <div class="container">
    <br>
    <h2>使用者資料修改</h2>
    <p>${success}</p>
		   <form:form method="POST" action="userUpdateProfile" modelAttribute="profile">
				<div class="form-group">
				<form:hidden path="userID" />
				</div>
				
				<div class="form-group">
				<form:hidden path="profileID" />
				</div>
				
				<div class="form-group">
				<label>姓名</label>
				<form:input cssClass="form-control" path="profileFullName" />
				</div>
				
				<div class="form-group">
				<label>加入日期</label>
				<form:input  cssClass="form-control" path="profileJoinDate" readonly="true" />
				<small><span class="errMsgJS" id="joinDateErrMsg"></span></small>
				<form:errors class="errMsgJS" path="profileJoinDate" />
				</div>
				
				<div class="form-group">
				<label for="birthday">生日</label>
				<form:input type="date" name="birthday" id="birthdayInput" onkeyup="birthdayCheck()" cssClass="form-control" path="profileBirthdate" />
				<small><span class="errMsgJS" id="joinDateErrMsg"></span></small>
				<form:errors class="errMsgJS" path="profileBirthdate" />
				</div>
				
				<div class="form-group">
				<label>性別</label>
				<form:select cssClass="form-control" path="profileSex" >
					<form:option value="f">女性(Female)</form:option>
					<form:option value="m">男性(Male)</form:option>
				</form:select>
				</div>
				
				<div class="form-group">
				<label>電話</label>
				<form:input cssClass="form-control" path="profilePhone" />
				<form:errors class="errMsgJS" path="profilePhone" />
				<small><span class="errMsgJS" id="phoneErrMsg"></span></small>
				</div>
				
				<div class="form-group">
				<label>地址</label>
				<form:input cssClass="form-control" path="profileAddress" />
				<form:errors class="errMsgJS" path="profileAddress" />
				<small><span class="errMsgJS" id="addressErrMsg"></span></small>
				</div>
				
				<div class="form-group">
				<label>VIP Level :</label>
				<form:input cssClass="form-control" path="profileVIP" readonly="true" />
				</div>
				
				<form:button class="btn btn-info" type="submit"
							 value="儲存"
								>儲存</form:button>
			</form:form>
      </div>
 
      
</body>
</html>