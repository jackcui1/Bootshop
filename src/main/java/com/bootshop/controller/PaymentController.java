package com.bootshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import com.bootshop.PaypalPaymentIntent;
import com.bootshop.PaypalPaymentMethod;
import com.bootshop.URLUtils;
import com.bootshop.model.CustomerOrder;
import com.bootshop.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/pay")
public class PaymentController {

	public static final String PAYPAL_SUCCESS_URL = "pay/paidsuccess";
	public static final String PAYPAL_CANCEL_URL = "pay/cancelpayment";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

/*	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		
		return "pay";
	}*/

	@RequestMapping(method = RequestMethod.POST, value = "/pay")
	public String toPaypal(HttpServletRequest request,RequestContext context,CustomerOrder order) {
		/*
		 * String cancelUrl = URLUtils.getBaseURl(request) + "/" +
		 * PAYPAL_CANCEL_URL; String successUrl = URLUtils.getBaseURl(request) +
		 * "/" + PAYPAL_SUCCESS_URL;
		 */
		// generate Spring Webflow return URL
//		RequestContext rc = RequestContextHolder.getRequestContext();
		//CustomerOrder customerOrder=(CustomerOrder) context.getConversationScope().get("order");
		String webflowUrl=context.getFlowExecutionUrl();
		String url = request.getRequestURL() + ";jsessionid="
				+ request.getSession().getId() + "?" + request.getQueryString();
		String paypalCancelUrl = url + "&_eventId=cancel";
		String paypalApprovedUrl = url + "&_eventId=approved";
		//double amount=customerOrder.getCart().getGrandTotal();

		try {
			Payment payment = paypalService.createPayment(10.00, "USD",
					PaypalPaymentMethod.paypal, PaypalPaymentIntent.sale,
					"payment description", paypalCancelUrl, paypalApprovedUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay() {
		return "cancelpayment";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId,
			@RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
}