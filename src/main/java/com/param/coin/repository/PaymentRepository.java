package com.param.coin.repository;

import com.param.coin.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaymentRepository extends CrudRepository<Payment, String> {

}
