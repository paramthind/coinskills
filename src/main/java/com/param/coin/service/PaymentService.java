package com.param.coin.service;

import com.param.coin.enums.PaymentType;
import com.param.coin.model.Invoice;
import com.param.coin.model.Payment;
import com.param.coin.repository.PaymentRepository;
import com.param.coin.utils.BigDecimalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Handles Operations related to Payments
 *
 * Created by Paramveer Singh on 2019-10-23.
 */
@Service
@Transactional
public class PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	InvoiceService invoiceService;

	/**
	 * Create new payment for given amount. This method will allow both +ive and -ive payments
	 * @param amount
	 * @return Payment
	 */
	public Payment createPayment(BigDecimal amount, String dca, PaymentType type) {
		log.trace("Creating new payment with amount: {}", amount);

		//lets first try to pay invoice then save payment record
		Invoice invoice = invoiceService.payInvoice(amount, dca);

		amount = BigDecimalUtils.setScale(amount);
		Payment payment = new Payment(amount, type);
		payment.setInvoice(invoice);
		//save to DB
		paymentRepository.save(payment);

		log.info("Payment with amount {} created for address: {}", amount, dca);
		return payment;
	}

	public List<Payment> getInvoicePayments(String dca) {
		List<Payment> paymentList =  paymentRepository.findPaymentsByInvoiceDca(dca);
		return paymentList;
	}
}
