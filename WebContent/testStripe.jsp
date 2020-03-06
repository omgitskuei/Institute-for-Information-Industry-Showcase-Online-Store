<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>模板</title>

	<script src="https://js.stripe.com/v3/">
	var stripe = Stripe('pk_test_Duy0yIyahW97FmFzVqBDG0wh00Pwl5FMks');
	</script>
	
	
	
</head>


<body>

        <div class="container text-center">
            
			<form action=<jstl:url value="stripeCheckout"/> method="post" id="payment-form">
 				<div class="form-row">
					<label for="card-element">
						Credit or debit card
					</label>
					<div id="card-element">
					
					<label for="cars">Choose a currency:</label>

						<select name="currency">
						  <option value="TWD">TWD</option>
						  <option value="USD">USD</option>
						</select>
					
					<input type="text" name="totalAmount" placeholder="amount"></input>
					<input type="text" name="email" placeholder="email"></input>

					</div>

					<!-- Used to display form errors. -->
					<div id="card-errors" role="alert"></div>
				</div>
				<br>
				<br>
				Send $500 to stripe
  				<input type="submit" value="Submit!" class="btn btn-success text-white btn-block">
			</form>
            
</div>

</body>
</html>