<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="foundProduct">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h4>最新使用者</h4>
						</div>
						<table class="table table-striped">
							<thead class="thead-dark">
								<tr>
									<th>商品圖片</th>
									<th>商品名稱</th>
									<th>商品價錢</th>
									<th>商品描述</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="" items="">
									<!-- construct an "update" link with customer id -->
									<c:url var="" value="">
										<c:param name="" value="" />
									</c:url>

									<!-- construct an "delete" link with customer id -->
									<c:url var="" value="">
										<c:param name="" value="" />
									</c:url>
									<tr>
										<td>${product.ProductImg}</td>
										<td>${product.ProductName}</td>
										<td>${product.ProductPrice}</td>
										<td>${product.ProductDescription}</td>
										<%-- <td><a href="${}" class="">
												<i class=""></i> 修改
										</a></td> --%>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>