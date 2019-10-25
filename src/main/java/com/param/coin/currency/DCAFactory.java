package com.param.coin.currency;

/**
 * Created by Paramveer Singh on 2019-10-23.
 */
public class DCAFactory {

	/**
	 * Create BitCoinCurrencyEngine object
	 *
	 * @return CurrencyEngine
	 */
	public static CurrencyEngine getBitcoinCurrencyEngine() {
		return new BitCoinCurrencyEngine();
	}
}
