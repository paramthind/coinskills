package com.param.coin.repository;

import com.param.coin.model.Invoice;
import org.springframework.data.repository.CrudRepository;


public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
