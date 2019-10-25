package com.param.coin.repository;

import com.param.coin.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Paramveer Singh on 2019-10-24.
 */
public interface PaymentRepository extends CrudRepository<Payment, String> {

	List<Payment> findPaymentsByInvoiceDca(@Param("dca") String dca);

}
