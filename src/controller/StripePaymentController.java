package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentMethodCreateParams;

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
	public HttpServletRequest request;
	// constructors
	@Autowired
	public StripePaymentController(HttpServletRequest request, UserBeanService uService, ProductBeanService pService, OrderBeanService oService, OrderDetailsBeanService odService) {
		System.out.println("BEGIN: StripePaymentController");
		this.uService = uService;
		this.pService = pService;
		this.oService = oService;
		this.odService = odService;
		this.request = request;	
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
	
//	private Customer createNewCustomer (String email) throws StripeException {
//		System.out.println("	BEGIN: createNewCustomer(String)");
//		// Create new Stripe Customer
//		Map<String, Object> customerParam = new HashMap<String, Object>();
//		customerParam.put("email", email);
//		System.out.println("Customer"+customerParam);
//		
//		Customer newCustomer = Customer.create(customerParam);
//		System.out.println("New created customer ID: " + newCustomer.getId());
//
//		// Print out all customer info
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		System.out.println(gson.toJson(customerParam));
//		System.out.println("	FINISH: createNewCustomer(String)");
//		return newCustomer;
//	}
	
	@RequestMapping(path="/stripeCheckout", method = RequestMethod.POST)
	public void callStripePay(
			//@RequestParam(name = "currency") String currency, 
			//@RequestParam(name = "totalAmount") int totalAmount, 
			//@RequestParam(name = "email") String receiptEmail, 
			Model nextPage) {
		
		try {
			System.out.println("	BEGIN: /stripeCheckout");
			
			// Stripe business ID for farmvilletaiwan@gmail.com
			Stripe.apiKey = "sk_test_s56neoj7TwIIkY5oFr45aZHd00cvXIHSQo";
			
//			Customer newCustomer = createNewCustomer("kueifengtung@yahoo.com");

			// Retrieving Stripe Customer
			Customer retrievedCustomer = Customer.retrieve("cus_GquXSUfYCXaNAF");
			System.out.println(""+retrievedCustomer);
			

			Map<String, Object> params = new HashMap<String, Object>();

			HashMap<String, Object> paymentIntentData = new HashMap<String, Object>();
			paymentIntentData.put("setup_future_usage", "off_session");
			params.put("payment_intent_data", paymentIntentData);
			
			params.put("customer_email", "kueifengtung@yahoo.com");
			
			ArrayList<String> paymentMethodTypes = new ArrayList<>();
			paymentMethodTypes.add("card");
			params.put("payment_method_types", paymentMethodTypes);

			ArrayList<HashMap<String, Object>> lineItems = new ArrayList<>();
			HashMap<String, Object> lineItem = new HashMap<String, Object>();
			lineItem.put("name", "T-shirt");
			lineItem.put("description", "Comfortable cotton t-shirt");
			lineItem.put("amount", 50000);
			lineItem.put("currency", "usd");
			lineItem.put("quantity", 1);
			lineItems.add(lineItem);
			params.put("line_items", lineItems);
			HttpSession thisSession = request.getSession(false);
			params.put("success_url", "https://420b76e4.ngrok.io/EEIT111FinalProject/directCheckoutSuccess?session_id="+thisSession.getId());
			params.put("cancel_url", "https://420b76e4.ngrok.io/EEIT111FinalProject/directshoppingcart");
			Session session = Session.create(params);
			
			
//			retrievedCustomer.setPhone("0963-798-118");
//			System.out.println("Finished setting phone number");
//			
			// Add a new card to this retrieved customer
//			Map<String, Object> cardParam = new HashMap<String, Object>();  // Adds <card info>
//			cardParam.put("number","4242424242424242");
//			cardParam.put("exp_month", "8");
//			cardParam.put("exp_year", "2021");
//			cardParam.put("last4", "1234");
//			
//			Map <String, Object> tokenParam = new HashMap<String, Object>(); // put <card info> into <token> to securely send <card info>
//			tokenParam.put("card", cardParam);
//			Token token = Token.create(tokenParam);
//			
//			Map <String, Object> source = new HashMap<String, Object>();	// puts the <token> into <source>
//			source.put("source", token.getId());
//			
//			retrievedCustomer.getSources().create(source); // Add <card> to this customer by adding <source> to <customer>
//			
//			// Print out all customer info
//			Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
//			System.out.println(gson1.toJson(retrievedCustomer));
//			
//			
//			
//			// Charge an amount to the card
//			Map <String, Object> chargeParam = new HashMap<String, Object>();
//			chargeParam.put("amount", "3000");
//			chargeParam.put("currency", "USD");
//			chargeParam.put("customer", retrievedCustomer.getId());
//			
//			Charge.create(chargeParam);
//			
//			// Print out all customer info
//			System.out.println(retrievedCustomer);
//			Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
//			System.out.println(gson2.toJson(retrievedCustomer));
//			
//			
			
//			Stripe.setMaxNetworkRetries(5);
//			
//			System.out.println("Stripe API Version: "+Stripe.API_VERSION);
//			// var stripe = Stripe('pk_test_Duy0yIyahW97FmFzVqBDG0wh00Pwl5FMks');
//			Stripe.apiKey = "sk_test_s56neoj7TwIIkY5oFr45aZHd00cvXIHSQo";
//
//			Map<String, Object> params = new HashMap<String, Object>();
//			
//			ArrayList<String> paymentMethodTypes = new ArrayList<>();
//			paymentMethodTypes.add("card");
//			params.put("payment_method_types", paymentMethodTypes);
//			
//			ArrayList< HashMap<String, Object> > allOrderItems = new ArrayList<>();
//			
//			HashMap<String, Object> orderItem = makeOrderItem("T-Shirt", "100% cotton shirt", 500, "TWD", 1);
//			
//			allOrderItems.add(orderItem);
//			
//			
//			
//			// add all order items of the bill to the params
//			//params.put("line_items", allOrderItems);
//			// 
//			
//			
//			// total amount of bill
//			params.put("amount", totalAmount);
//			
//			params.put("currency", currency);
//			
//			//params.put("receipt_email", receiptEmail);
//			
//			//params.put("statement_descriptor", "Purchase at Farmville");
//			
//			// After the PaymentIntent is created, attach a payment method and confirm to continue the payment
//			PaymentIntent.create(params);
//			
//			Session session = Session.create(params);
//
//			params.put("success_url", "http://420b76e4.ngrok.io/EEIT111FinalProject/UserProfile/showTheUserOrer?userID="+uService.selectUserIDByEmail(receiptEmail)+"?session_id="+session);
//			// the URL link given if user cancels, put the checkout link prior to redirecting to STRIPE
//			params.put("cancel_url", "http://420b76e4.ngrok.io/EEIT111FinalProject/directshoppingcart");												// TO DO <------------------------
//			
//			nextPage.addAttribute("sessionID", session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
