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

import model.order.OrderBeanService;
import model.orderDetails.OrderDetailsBeanService;
import model.product.ProductBeanService;
import model.user.UserBeanService;

public class StripePaymentController {
	// fields
	private UserBeanService uService;
	private ProductBeanService pService;
	private OrderBeanService oService;
	private OrderDetailsBeanService odService;
	// constructors
	public StripePaymentController() {
		System.out.println("BEGIN: StripePaymentController");
		
	}
	// executable
	public static void main(String args[]) {
		
	}
	
	// methods
	public HashMap<String,Object> makeOrderItem (String name, String description, int amount, String currency, int quantity) {

		HashMap<String, Object> orderItem = new HashMap<String, Object>();
		
		orderItem.put("name", name);
		orderItem.put("description", description);
		orderItem.put("amount", amount);
		orderItem.put("currency", currency);
		orderItem.put("quantity", quantity);
		
		return orderItem;
	}
	
	public void callStripePay() throws StripeException {
		// Set your secret key. Remember to switch to your live secret key in production!
		// See your keys here: https://dashboard.stripe.com/account/apikeys
		Stripe.setMaxNetworkRetries(5);
		
		System.out.println("Stripe API Version: "+Stripe.API_VERSION);
		
		Stripe.apiKey = "sk_test_s56neoj7TwIIkY5oFr45aZHd00cvXIHSQo";

		Map<String, Object> params = new HashMap<String, Object>();
		
		ArrayList<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");
		params.put("payment_method_types", paymentMethodTypes);
		
		ArrayList< HashMap<String, Object> > allOrderItems = new ArrayList<>();
		
		HashMap<String, Object> orderItem = new StripePaymentController().makeOrderItem("T-Shirt", "100% cotton shirt", 500, "TWD", 1);
		
		allOrderItems.add(orderItem);
		
		
		
		
		params.put("line_items", allOrderItems);

		params.put("success_url", "https://example.com/success?session_id={CHECKOUT_SESSION_ID}");
		params.put("cancel_url", "https://example.com/cancel");
		
		
		
		params.put("amount", 1000);
		
		params.put("currency", "usd");
		
		params.put("receipt_email", "kueifengtungchris@gmail.com");
		
		params.put("statement_descriptor", "Purchase at Farmville");
		
		
		PaymentIntent.create(params);
	}
}
