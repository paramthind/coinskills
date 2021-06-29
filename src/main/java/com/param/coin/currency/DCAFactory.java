package com.param.coin.currency;


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
