package com.param.coin.currency;

import org.bitcoinj.core.Base58;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

/**
 * Bitcoin Address generator
 *
 * Created by Paramveer Singh on 2019-10-23.
 */
public class BitCoinCurrencyEngine implements CurrencyEngine{

	private static final Logger log = LoggerFactory.getLogger(BitCoinCurrencyEngine.class);
	private static final String SPEC = "secp256k1";

	@Override
	public String generateAddress() {
		log.trace("Going to generate Bitcoin wallet address");
		try {
			return generate();
		} catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException("Address generation exception", e);
		}

	}

	/**
	 * Generates Bitcoin address
	 *
	 * @return String address
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 */
	private String generate() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
		KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
		g.initialize(ecSpec, new SecureRandom());
		KeyPair keypair = g.generateKeyPair();
		PublicKey publicKey = keypair.getPublic();
		PrivateKey privateKey = keypair.getPrivate();

		ECPublicKey epub = (ECPublicKey) publicKey;
		ECPoint pt = epub.getW();
		byte[] bcPub = new byte[33];
		bcPub[0] = 2;
		System.arraycopy(pt.getAffineX().toByteArray(), 0, bcPub, 1, 32);

		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] s1 = sha.digest(bcPub);

		byte[] ripeMD = Ripemd160.getHash(s1);

		//add 0x00
		byte[] ripeMDPadded = new byte[ripeMD.length + 1];
		ripeMDPadded[0] = 0;

		System.arraycopy(ripeMD, 0, ripeMDPadded, 1, 1);

		byte[] shaFinal = sha.digest(sha.digest(ripeMDPadded));

		//append ripeMDPadded + shaFinal = sumBytes
		byte[] sumBytes = new byte[25];
		System.arraycopy(ripeMDPadded, 0, sumBytes, 0, 21);
		System.arraycopy(shaFinal, 0, sumBytes, 21, 4);

		//base 58 encode
		String addr =  Base58.encode(sumBytes);
		log.info("Bitcoin Address generated: {}", addr);
		return addr;
	}
}
