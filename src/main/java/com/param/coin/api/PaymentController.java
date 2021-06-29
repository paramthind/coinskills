package com.param.coin.api;

import com.param.coin.dto.PaymentDto;
import com.param.coin.dto.PaymentPostDto;
import com.param.coin.model.Payment;
import com.param.coin.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for Payments related REST APIs
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping(produces = "application/json")
	public Mono<PaymentDto> createPayment(@RequestBody PaymentPostDto paymentDto) {
		return Mono.just(convert(
				paymentService.createPayment(paymentDto.getAmount(), paymentDto.getDca(), paymentDto.getType())));
	}

	private PaymentDto convert(Payment p){
		return new PaymentDto(p.getId(), p.getAmount(), p.getInvoice().getDca(), p.getType());
	}
}
