package com.param.coin.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Contains helper methods related to BigDecimal operations.
 *
 * Created by Paramveer Singh on 2019-10-24.
 */
public class BigDecimalUtils {

	public static BigDecimal add(BigDecimal a, BigDecimal b){
		return a.add(b).setScale(2, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal subtract(BigDecimal a, BigDecimal b){
		return a.subtract(b).setScale(2, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal setScale(BigDecimal a){
		return a.setScale(2, RoundingMode.HALF_EVEN);
	}
}
