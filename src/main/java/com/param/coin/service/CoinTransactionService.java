package com.param.coin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * TODO: Add transactions for each action done for a invoice
 * Created by Paramveer Singh on 2019-10-23.
 */
@Service
@Transactional
public class CoinTransactionService {

	private static final Logger log = LoggerFactory.getLogger(CoinTransactionService.class);


}
