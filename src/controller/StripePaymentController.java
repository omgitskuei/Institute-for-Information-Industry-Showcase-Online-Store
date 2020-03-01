package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import model.order.OrderBeanService;
import model.orderDetails.OrderDetailsBeanService;
import model.product.ProductBeanService;
import model.user.UserBeanService;
@Controller
public class StripePaymentController {
	// fields
	private UserBeanService uService;
	private ProductBeanService pService;
	private OrderBeanService oService;
	private OrderDetailsBeanService odService;
	// constructors
	@Autowired
	public StripePaymentController(UserBeanService uService, ProductBeanService pService, OrderBeanService oService, OrderDetailsBeanService odService) {
		System.out.println("BEGIN: StripePaymentController");
		this.uService = uService;
		this.pService = pService;
		this.oService = oService;
		this.odService = odService;
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
	
	@RequestMapping(path="/stripeCheckout", method = RequestMethod.POST)
	public void callStripePay(
			@RequestParam(name = "currency") String currency, 
			@RequestParam(name = "totalAmount") int totalAmount, 
			@RequestParam(name = "email") String receiptEmail, 
			Model nextPage) throws StripeException {
		currency = "TWD";
		totalAmount = 1000;
		receiptEmail = "kueifengtungchris@gmail.com";
		
		Stripe.setMaxNetworkRetries(5);
		
		System.out.println("Stripe API Version: "+Stripe.API_VERSION);
		// var stripe = Stripe('pk_test_Duy0yIyahW97FmFzVqBDG0wh00Pwl5FMks');
		Stripe.apiKey = "sk_test_s56neoj7TwIIkY5oFr45aZHd00cvXIHSQo";

		Map<String, Object> params = new HashMap<String, Object>();
		
		ArrayList<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");
		params.put("payment_method_types", paymentMethodTypes);
		
		ArrayList< HashMap<String, Object> > allOrderItems = new ArrayList<>();
		
		HashMap<String, Object> orderItem = makeOrderItem("T-Shirt", "100% cotton shirt", 500, "TWD", 1);
		
		allOrderItems.add(orderItem);
		
		
		
		// add all order items of the bill to the params
		params.put("line_items", allOrderItems);
		// 
		
		
		// total amount of bill
		params.put("amount", totalAmount);
		
		params.put("currency", currency);
		
		params.put("receipt_email", receiptEmail);
		
		params.put("statement_descriptor", "Purchase at Farmville");
		
		// After the PaymentIntent is created, attach a payment method and confirm to continue the payment
		PaymentIntent.create(params);
		
		PaymentMethod.
		Session session = Session.create(params);

		params.put("success_url", "https://example.com/success?session_id="+session);			// TO DO <------------------------
		// the URL link given if user cancels, put the checkout link prior to redirecting to STRIPE
		params.put("cancel_url", "https://example.com/cancel");												// TO DO <------------------------
		
		nextPage.addAttribute("sessionID", session);
		
	}
}
