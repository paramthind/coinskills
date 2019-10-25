package com.param.coin.service;

import com.param.coin.currency.DCAFactory;
import com.param.coin.enums.InvoiceStatus;
import com.param.coin.exception.InvalidInputException;
import com.param.coin.exception.InvoiceNotFoundException;
import com.param.coin.exception.PaymentRejectedException;
import com.param.coin.model.Invoice;
import com.param.coin.repository.InvoiceRepository;
import com.param.coin.utils.BigDecimalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Handles operations related to Invoice
 *
 * Created by Paramveer Singh on 2019-10-23.
 */
@Service
@Transactional
public class InvoiceService {

	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

	@Autowired
	InvoiceRepository invoiceRepository;
	/**
	 * Generate Invoice for a amount greater than 1.
	 *
	 * @param amount double
	 * @return Invoice object with a digital currency address
	 */
	public Invoice generateInvoice(BigDecimal amount) {
		log.trace("Generating new invoice for amount: {}", amount);
		if (amount.compareTo(BigDecimal.ZERO)  <= 0) {
			throw new InvalidInputException("Invoice amount is less than 0.01: " + amount);
		}
		amount = BigDecimalUtils.setScale(amount);
		//now generate a new digital currency address
		String address = DCAFactory.getBitcoinCurrencyEngine().generateAddress();

		log.debug("Address for new Invoice is: {}", address);
		Invoice invoice = new Invoice(address, amount);
		//save to DB
		invoiceRepository.save(invoice);
		log.info("Invoice Generated with address: {}", address);
		return invoice;
	}

	/**
	 * Fetch a invoice for a given address
	 *
	 * @param dca Digital currency address
	 * @return Invoice
	 */
	public Invoice getInvoice(String dca) {
		Optional<Invoice> invoice =  invoiceRepository.findById(dca);
		if(invoice.isPresent()){
			log.info("Get invoice for address {}", dca);
			return invoice.get();
		}else{
			throw new InvoiceNotFoundException("Invoice for address " + dca + " not found.");
		}
	}

	/**
	 * This method will add a payment to a given address invoice.
	 * @param amount
	 * @param dca
	 * @return
	 */
	public Invoice payInvoice(BigDecimal amount, String dca) {
		Invoice invoice = getInvoice(dca);
		if(InvoiceStatus.PAID.equals(invoice.getStatus())){
			throw new PaymentRejectedException("Invoice fully paid. No more payment required");
		}

		log.debug("Paying invoice with address {} and payment amount; {}", dca, amount);
		amount = BigDecimalUtils.setScale(amount);

		invoice.setPaidAmount(BigDecimalUtils.add(invoice.getPaidAmount(),amount));
		BigDecimal pendingAmount = BigDecimalUtils.subtract(invoice.getAmount(), invoice.getPaidAmount());

		//now update status
		if(pendingAmount.compareTo(BigDecimal.ZERO) > 0 ){
			invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
			log.trace("Invoice partially paid");
		}else if(pendingAmount.compareTo(BigDecimal.ZERO)  <= 0){
			invoice.setStatus(InvoiceStatus.PAID);
			log.trace("Invoice fully paid");
		}
		invoiceRepository.save(invoice);
		log.info("Invoice with address: {} successfully paid with amount: {}", dca, amount);
		return invoice;
	}
}
