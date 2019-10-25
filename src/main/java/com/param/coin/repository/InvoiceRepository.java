package com.param.coin.repository;

import com.param.coin.model.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paramveer Singh on 2019-10-23.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
