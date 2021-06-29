package com.param.coin.api;

import com.param.coin.dto.InvoiceDto;
import com.param.coin.dto.PaymentDto;
import com.param.coin.model.Invoice;
import com.param.coin.model.Payment;
import com.param.coin.service.InvoiceService;
import com.param.coin.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for Invoice related REST APIs
 */
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private PaymentService paymentService;

	@PostMapping(value = {"/{amount}"}, produces = "application/json")
	public Mono<InvoiceDto> createInvoice(@PathVariable BigDecimal amount) {
		return Mono.just(convert(invoiceService.generateInvoice(amount)));
	}

	@GetMapping(value = {"/{dca}"}, produces = "application/json")
	public Mono<InvoiceDto> createInvoice(@PathVariable String dca) {
		return Mono.just(convert(invoiceService.getInvoice(dca)));
	}


	private PaymentDto convertPayment(Payment p){
		return new PaymentDto(p.getId(), p.getAmount(), p.getInvoice().getDca(), p.getType());
	}

	private InvoiceDto convert(Invoice i){
		return new InvoiceDto(i.getDca(), i.getAmount(), i.getPaidAmount(), i.getStatus(), i.getCreationTime());
	}
}
