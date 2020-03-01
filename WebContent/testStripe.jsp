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
		// Create a Stripe client.
		var stripe = Stripe('pk_test_Duy0yIyahW97FmFzVqBDG0wh00Pwl5FMks');

		// Create an instance of Elements.
		var elements = stripe.elements();

		// Custom styling can be passed to options when creating an Element.
		// (Note that this demo uses a wider set of styles than the guide below.)
		(function() {
  'use strict';

  var elements = stripe.elements({
    fonts: [
      {
        cssSrc: 'https://fonts.googleapis.com/css?family=Source+Code+Pro',
      },
    ],
    // Stripe's examples are localized to specific languages, but if
    // you wish to have Elements automatically detect your user's locale,
    // use `locale: 'auto'` instead.
    locale: window.__exampleLocale
  });

  // Floating labels
  var inputs = document.querySelectorAll('.cell.example.example2 .input');
  Array.prototype.forEach.call(inputs, function(input) {
    input.addEventListener('focus', function() {
      input.classList.add('focused');
    });
    input.addEventListener('blur', function() {
      input.classList.remove('focused');
    });
    input.addEventListener('keyup', function() {
      if (input.value.length === 0) {
        input.classList.add('empty');
      } else {
        input.classList.remove('empty');
      }
    });
  });

  var elementStyles = {
    base: {
      color: '#32325D',
      fontWeight: 500,
      fontFamily: 'Source Code Pro, Consolas, Menlo, monospace',
      fontSize: '16px',
      fontSmoothing: 'antialiased',

      '::placeholder': {
        color: '#CFD7DF',
      },
      ':-webkit-autofill': {
        color: '#e39f48',
      },
    },
    invalid: {
      color: '#E25950',

      '::placeholder': {
        color: '#FFCCA5',
      },
    },
  };

  var elementClasses = {
    focus: 'focused',
    empty: 'empty',
    invalid: 'invalid',
  };

  var cardNumber = elements.create('cardNumber', {
    style: elementStyles,
    classes: elementClasses,
  });
  cardNumber.mount('#example2-card-number');

  var cardExpiry = elements.create('cardExpiry', {
    style: elementStyles,
    classes: elementClasses,
  });
  cardExpiry.mount('#example2-card-expiry');

  var cardCvc = elements.create('cardCvc', {
    style: elementStyles,
    classes: elementClasses,
  });
  cardCvc.mount('#example2-card-cvc');

  registerElements([cardNumber, cardExpiry, cardCvc], 'example2');
})();

	// Submit the form with the token ID.
	function stripeTokenHandler(token) {
	// Insert the token ID into the form so it gets submitted to the server
		var form = document.getElementById('payment-form');
		var hiddenInput = document.createElement('input');
			hiddenInput.setAttribute('type', 'hidden');
			hiddenInput.setAttribute('name', 'stripeToken');
			hiddenInput.setAttribute('value', token.id);
			form.appendChild(hiddenInput);

		// Submit the form
		form.submit();
	}
	</script>
	
	<style>
		.example.example2 {
  background-color: #fff;
}

.example.example2 * {
  font-family: Source Code Pro, Consolas, Menlo, monospace;
  font-size: 16px;
  font-weight: 500;
}

.example.example2 .row {
  display: -ms-flexbox;
  display: flex;
  margin: 0 5px 10px;
}

.example.example2 .field {
  position: relative;
  width: 100%;
  height: 50px;
  margin: 0 10px;
}

.example.example2 .field.half-width {
  width: 50%;
}

.example.example2 .field.quarter-width {
  width: calc(25% - 10px);
}

.example.example2 .baseline {
  position: absolute;
  width: 100%;
  height: 1px;
  left: 0;
  bottom: 0;
  background-color: #cfd7df;
  transition: background-color 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
}

.example.example2 label {
  position: absolute;
  width: 100%;
  left: 0;
  bottom: 8px;
  color: #cfd7df;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  transform-origin: 0 50%;
  cursor: text;
  pointer-events: none;
  transition-property: color, transform;
  transition-duration: 0.3s;
  transition-timing-function: cubic-bezier(0.165, 0.84, 0.44, 1);
}

.example.example2 .input {
  position: absolute;
  width: 100%;
  left: 0;
  bottom: 0;
  padding-bottom: 7px;
  color: #32325d;
  background-color: transparent;
}

.example.example2 .input::-webkit-input-placeholder {
  color: transparent;
  transition: color 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
}

.example.example2 .input::-moz-placeholder {
  color: transparent;
  transition: color 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
}

.example.example2 .input:-ms-input-placeholder {
  color: transparent;
  transition: color 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
}

.example.example2 .input.StripeElement {
  opacity: 0;
  transition: opacity 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
  will-change: opacity;
}

.example.example2 .input.focused,
.example.example2 .input:not(.empty) {
  opacity: 1;
}

.example.example2 .input.focused::-webkit-input-placeholder,
.example.example2 .input:not(.empty)::-webkit-input-placeholder {
  color: #cfd7df;
}

.example.example2 .input.focused::-moz-placeholder,
.example.example2 .input:not(.empty)::-moz-placeholder {
  color: #cfd7df;
}

.example.example2 .input.focused:-ms-input-placeholder,
.example.example2 .input:not(.empty):-ms-input-placeholder {
  color: #cfd7df;
}

.example.example2 .input.focused + label,
.example.example2 .input:not(.empty) + label {
  color: #aab7c4;
  transform: scale(0.85) translateY(-25px);
  cursor: default;
}

.example.example2 .input.focused + label {
  color: #24b47e;
}

.example.example2 .input.invalid + label {
  color: #ffa27b;
}

.example.example2 .input.focused + label + .baseline {
  background-color: #24b47e;
}

.example.example2 .input.focused.invalid + label + .baseline {
  background-color: #e25950;
}

.example.example2 input, .example.example2 button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  outline: none;
  border-style: none;
}

.example.example2 input:-webkit-autofill {
  -webkit-text-fill-color: #e39f48;
  transition: background-color 100000000s;
  -webkit-animation: 1ms void-animation-out;
}

.example.example2 .StripeElement--webkit-autofill {
  background: transparent !important;
}

.example.example2 input, .example.example2 button {
  -webkit-animation: 1ms void-animation-out;
}

.example.example2 button {
  display: block;
  width: calc(100% - 30px);
  height: 40px;
  margin: 40px 15px 0;
  background-color: #24b47e;
  border-radius: 4px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 600;
  cursor: pointer;
}

.example.example2 .error svg {
  margin-top: 0 !important;
}

.example.example2 .error svg .base {
  fill: #e25950;
}

.example.example2 .error svg .glyph {
  fill: #fff;
}

.example.example2 .error .message {
  color: #e25950;
}

.example.example2 .success .icon .border {
  stroke: #abe9d2;
}

.example.example2 .success .icon .checkmark {
  stroke: #24b47e;
}

.example.example2 .success .title {
  color: #32325d;
  font-size: 16px !important;
}

.example.example2 .success .message {
  color: #8898aa;
  font-size: 13px !important;
}

.example.example2 .success .reset path {
  fill: #24b47e;
}
	</style>
	
</head>


<body>

        <div class="container text-center">
            
			<form action="/charge" method="post" id="payment-form">
 				<div class="form-row">
					<label for="card-element">
						Credit or debit card
					</label>
					<div id="card-element">
					<!-- A Stripe Element will be inserted here. -->
					</div>

					<!-- Used to display form errors. -->
					<div id="card-errors" role="alert"></div>
				</div>

  				<button>Submit Payment</button>
			</form>
            
        

    <div class="cell example example2" id="example-2">
        <form>
          <div data-locale-reversible="">
            <div class="row">
              <div class="field">
                <input id="example2-address" data-tid="elements_examples.form.address_placeholder" class="input empty" type="text" placeholder="185 Berry St" required="" autocomplete="address-line1">
                <label for="example2-address" data-tid="elements_examples.form.address_label">Address</label>
                <div class="baseline"></div>
              </div>
            </div>
            <div class="row" data-locale-reversible="">
              <div class="field half-width">
                <input id="example2-city" data-tid="elements_examples.form.city_placeholder" class="input empty" type="text" placeholder="San Francisco" required="" autocomplete="address-level2">
                <label for="example2-city" data-tid="elements_examples.form.city_label">City</label>
                <div class="baseline"></div>
              </div>
              <div class="field quarter-width">
                <input id="example2-state" data-tid="elements_examples.form.state_placeholder" class="input empty" type="text" placeholder="CA" required="" autocomplete="address-level1">
                <label for="example2-state" data-tid="elements_examples.form.state_label">State</label>
                <div class="baseline"></div>
              </div>
              <div class="field quarter-width">
                <input id="example2-zip" data-tid="elements_examples.form.postal_code_placeholder" class="input empty" type="text" placeholder="94107" required="" autocomplete="postal-code">
                <label for="example2-zip" data-tid="elements_examples.form.postal_code_label">ZIP</label>
                <div class="baseline"></div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="field">
              <div id="example2-card-number" class="input empty StripeElement"><div class="__PrivateStripeElement" style="margin: 0px !important; padding: 0px !important; border: none !important; display: block !important; background: transparent !important; position: relative !important; opacity: 1 !important;"><iframe frameborder="0" allowtransparency="true" scrolling="no" name="__privateStripeFrame8" allowpaymentrequest="true" src="https://js.stripe.com/v3/elements-inner-card-76bab651464ea5f1b989a9bad010b28b.html#style[base][color]=%2332325D&amp;style[base][fontWeight]=500&amp;style[base][fontFamily]=Source+Code+Pro%2C+Consolas%2C+Menlo%2C+monospace&amp;style[base][fontSize]=16px&amp;style[base][fontSmoothing]=antialiased&amp;style[base][::placeholder][color]=%23CFD7DF&amp;style[base][:-webkit-autofill][color]=%23e39f48&amp;style[invalid][color]=%23E25950&amp;style[invalid][::placeholder][color]=%23FFCCA5&amp;locale=en&amp;componentName=cardNumber&amp;wait=true&amp;rtl=false&amp;keyMode=test&amp;apiKey=pk_test_6pRNASCoBOKtIshFeQd4XMUh&amp;origin=https%3A%2F%2Fstripe.dev&amp;referrer=https%3A%2F%2Fstripe.dev%2Felements-examples%2F&amp;controllerId=__privateStripeController1" title="Secure payment input frame" style="border: none !important; margin: 0px !important; padding: 0px !important; width: 1px !important; min-width: 100% !important; overflow: hidden !important; display: block !important; user-select: none !important; height: 19.2px;"></iframe><input class="__PrivateStripeElement-input" aria-hidden="true" aria-label=" " autocomplete="false" maxlength="1" style="border: none !important; display: block !important; position: absolute !important; height: 1px !important; top: 0px !important; left: 0px !important; padding: 0px !important; margin: 0px !important; width: 100% !important; opacity: 0 !important; background: transparent !important; pointer-events: none !important; font-size: 16px !important;"></div></div>
              <label for="example2-card-number" data-tid="elements_examples.form.card_number_label">Card number</label>
              <div class="baseline"></div>
            </div>
          </div>
          <div class="row">
            <div class="field half-width">
              <div id="example2-card-expiry" class="input empty StripeElement"><div class="__PrivateStripeElement" style="margin: 0px !important; padding: 0px !important; border: none !important; display: block !important; background: transparent !important; position: relative !important; opacity: 1 !important;"><iframe frameborder="0" allowtransparency="true" scrolling="no" name="__privateStripeFrame9" allowpaymentrequest="true" src="https://js.stripe.com/v3/elements-inner-card-76bab651464ea5f1b989a9bad010b28b.html#style[base][color]=%2332325D&amp;style[base][fontWeight]=500&amp;style[base][fontFamily]=Source+Code+Pro%2C+Consolas%2C+Menlo%2C+monospace&amp;style[base][fontSize]=16px&amp;style[base][fontSmoothing]=antialiased&amp;style[base][::placeholder][color]=%23CFD7DF&amp;style[base][:-webkit-autofill][color]=%23e39f48&amp;style[invalid][color]=%23E25950&amp;style[invalid][::placeholder][color]=%23FFCCA5&amp;locale=en&amp;componentName=cardExpiry&amp;wait=true&amp;rtl=false&amp;keyMode=test&amp;apiKey=pk_test_6pRNASCoBOKtIshFeQd4XMUh&amp;origin=https%3A%2F%2Fstripe.dev&amp;referrer=https%3A%2F%2Fstripe.dev%2Felements-examples%2F&amp;controllerId=__privateStripeController1" title="Secure payment input frame" style="border: none !important; margin: 0px !important; padding: 0px !important; width: 1px !important; min-width: 100% !important; overflow: hidden !important; display: block !important; user-select: none !important; height: 19.2px;"></iframe><input class="__PrivateStripeElement-input" aria-hidden="true" aria-label=" " autocomplete="false" maxlength="1" style="border: none !important; display: block !important; position: absolute !important; height: 1px !important; top: 0px !important; left: 0px !important; padding: 0px !important; margin: 0px !important; width: 100% !important; opacity: 0 !important; background: transparent !important; pointer-events: none !important; font-size: 16px !important;"></div></div>
              <label for="example2-card-expiry" data-tid="elements_examples.form.card_expiry_label">Expiration</label>
              <div class="baseline"></div>
            </div>
            <div class="field half-width">
              <div id="example2-card-cvc" class="input empty StripeElement"><div class="__PrivateStripeElement" style="margin: 0px !important; padding: 0px !important; border: none !important; display: block !important; background: transparent !important; position: relative !important; opacity: 1 !important;"><iframe frameborder="0" allowtransparency="true" scrolling="no" name="__privateStripeFrame10" allowpaymentrequest="true" src="https://js.stripe.com/v3/elements-inner-card-76bab651464ea5f1b989a9bad010b28b.html#style[base][color]=%2332325D&amp;style[base][fontWeight]=500&amp;style[base][fontFamily]=Source+Code+Pro%2C+Consolas%2C+Menlo%2C+monospace&amp;style[base][fontSize]=16px&amp;style[base][fontSmoothing]=antialiased&amp;style[base][::placeholder][color]=%23CFD7DF&amp;style[base][:-webkit-autofill][color]=%23e39f48&amp;style[invalid][color]=%23E25950&amp;style[invalid][::placeholder][color]=%23FFCCA5&amp;locale=en&amp;componentName=cardCvc&amp;wait=true&amp;rtl=false&amp;keyMode=test&amp;apiKey=pk_test_6pRNASCoBOKtIshFeQd4XMUh&amp;origin=https%3A%2F%2Fstripe.dev&amp;referrer=https%3A%2F%2Fstripe.dev%2Felements-examples%2F&amp;controllerId=__privateStripeController1" title="Secure payment input frame" style="border: none !important; margin: 0px !important; padding: 0px !important; width: 1px !important; min-width: 100% !important; overflow: hidden !important; display: block !important; user-select: none !important; height: 19.2px;"></iframe><input class="__PrivateStripeElement-input" aria-hidden="true" aria-label=" " autocomplete="false" maxlength="1" style="border: none !important; display: block !important; position: absolute !important; height: 1px !important; top: 0px !important; left: 0px !important; padding: 0px !important; margin: 0px !important; width: 100% !important; opacity: 0 !important; background: transparent !important; pointer-events: none !important; font-size: 16px !important;"></div></div>
              <label for="example2-card-cvc" data-tid="elements_examples.form.card_cvc_label">CVC</label>
              <div class="baseline"></div>
            </div>
          </div>
        <button type="submit" data-tid="elements_examples.form.pay_button">Pay $25</button>
          <div class="error" role="alert"><svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" viewBox="0 0 17 17">
              <path class="base" fill="#000" d="M8.5,17 C3.80557963,17 0,13.1944204 0,8.5 C0,3.80557963 3.80557963,0 8.5,0 C13.1944204,0 17,3.80557963 17,8.5 C17,13.1944204 13.1944204,17 8.5,17 Z"></path>
              <path class="glyph" fill="#FFF" d="M8.5,7.29791847 L6.12604076,4.92395924 C5.79409512,4.59201359 5.25590488,4.59201359 4.92395924,4.92395924 C4.59201359,5.25590488 4.59201359,5.79409512 4.92395924,6.12604076 L7.29791847,8.5 L4.92395924,10.8739592 C4.59201359,11.2059049 4.59201359,11.7440951 4.92395924,12.0760408 C5.25590488,12.4079864 5.79409512,12.4079864 6.12604076,12.0760408 L8.5,9.70208153 L10.8739592,12.0760408 C11.2059049,12.4079864 11.7440951,12.4079864 12.0760408,12.0760408 C12.4079864,11.7440951 12.4079864,11.2059049 12.0760408,10.8739592 L9.70208153,8.5 L12.0760408,6.12604076 C12.4079864,5.79409512 12.4079864,5.25590488 12.0760408,4.92395924 C11.7440951,4.59201359 11.2059049,4.59201359 10.8739592,4.92395924 L8.5,7.29791847 L8.5,7.29791847 Z"></path>
            </svg>
            <span class="message"></span></div>
        </form>
        <div class="success">
          <div class="icon">
            <svg width="84px" height="84px" viewBox="0 0 84 84" version="1.1" xmlns="http://www.w3.org/2000/svg" xlink="http://www.w3.org/1999/xlink">
              <circle class="border" cx="42" cy="42" r="40" stroke-linecap="round" stroke-width="4" stroke="#000" fill="none"></circle>
              <path class="checkmark" stroke-linecap="round" stroke-linejoin="round" d="M23.375 42.5488281 36.8840688 56.0578969 64.891932 28.0500338" stroke-width="4" stroke="#000" fill="none"></path>
            </svg>
          </div>
          <h3 class="title" data-tid="elements_examples.success.title">Payment successful</h3>
          <p class="message"><span data-tid="elements_examples.success.message">Thanks for trying Stripe Elements. No money was charged, but we generated a token:</span><span class="token">tok_189gMN2eZvKYlo2CwTBv9KKh</span></p>
          <a class="reset" href="#">
            <svg width="32px" height="32px" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xlink="http://www.w3.org/1999/xlink">
              <path fill="#000000" d="M15,7.05492878 C10.5000495,7.55237307 7,11.3674463 7,16 C7,20.9705627 11.0294373,25 16,25 C20.9705627,25 25,20.9705627 25,16 C25,15.3627484 24.4834055,14.8461538 23.8461538,14.8461538 C23.2089022,14.8461538 22.6923077,15.3627484 22.6923077,16 C22.6923077,19.6960595 19.6960595,22.6923077 16,22.6923077 C12.3039405,22.6923077 9.30769231,19.6960595 9.30769231,16 C9.30769231,12.3039405 12.3039405,9.30769231 16,9.30769231 L16,12.0841673 C16,12.1800431 16.0275652,12.2738974 16.0794108,12.354546 C16.2287368,12.5868311 16.5380938,12.6540826 16.7703788,12.5047565 L22.3457501,8.92058924 L22.3457501,8.92058924 C22.4060014,8.88185624 22.4572275,8.83063012 22.4959605,8.7703788 C22.6452866,8.53809377 22.5780351,8.22873685 22.3457501,8.07941076 L22.3457501,8.07941076 L16.7703788,4.49524351 C16.6897301,4.44339794 16.5958758,4.41583275 16.5,4.41583275 C16.2238576,4.41583275 16,4.63969037 16,4.91583275 L16,7 L15,7 L15,7.05492878 Z M16,32 C7.163444,32 0,24.836556 0,16 C0,7.163444 7.163444,0 16,0 C24.836556,0 32,7.163444 32,16 C32,24.836556 24.836556,32 16,32 Z"></path>
            </svg>
          </a>
        </div>

        <div class="caption">
          <span data-tid="elements_examples.caption.no_charge" class="no-charge">Your card won't be charged</span>
          <a class="source" href="https://github.com/stripe/elements-examples/#example-2">
            <svg width="16px" height="10px" viewBox="0 0 16 10" version="1.1" xmlns="http://www.w3.org/2000/svg" xlink="http://www.w3.org/1999/xlink">
              <path d="M1,8 L12,8 C12.5522847,8 13,8.44771525 13,9 C13,9.55228475 12.5522847,10 12,10 L1,10 C0.44771525,10 6.76353751e-17,9.55228475 0,9 C-6.76353751e-17,8.44771525 0.44771525,8 1,8 L1,8 Z M1,4 L8,4 C8.55228475,4 9,4.44771525 9,5 C9,5.55228475 8.55228475,6 8,6 L1,6 C0.44771525,6 6.76353751e-17,5.55228475 0,5 C-6.76353751e-17,4.44771525 0.44771525,4 1,4 L1,4 Z M1,0 L15,0 C15.5522847,-1.01453063e-16 16,0.44771525 16,1 L16,1 C16,1.55228475 15.5522847,2 15,2 L1,2 C0.44771525,2 6.76353751e-17,1.55228475 0,1 L0,1 L0,1 C-6.76353751e-17,0.44771525 0.44771525,1.01453063e-16 1,0 L1,0 Z" fill="#AAB7C4"></path>
            </svg>
            <span data-tid="elements_examples.caption.view_source">View source on GitHub</span>
          </a>
        </div>
      </div>
</div>

</body>
</html>