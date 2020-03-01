package controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.datetime.joda.LocalDateParser;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public class StripePaymentController {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void callStripePay() throws StripeException {
		// Set your secret key. Remember to switch to your live secret key in production!
		// See your keys here: https://dashboard.stripe.com/account/apikeys
		Stripe.setMaxNetworkRetries(5);
		System.out.println("Stripe API Version: "+Stripe.API_VERSION);
		Stripe.apiKey = "sk_test_s56neoj7TwIIkY5oFr45aZHd00cvXIHSQo";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", 1000);
		params.put("currency", "usd");
		ArrayList paymentMethodTypes = new ArrayList();
		paymentMethodTypes.add("card");
		params.put("payment_method_types", paymentMethodTypes);
		params.put("receipt_email", "kueifengtungchris@gmail.com");
		params.put("statement_descriptor", "Purchase at Farmville");
		
		
		PaymentIntent.create(params);
	}
}
