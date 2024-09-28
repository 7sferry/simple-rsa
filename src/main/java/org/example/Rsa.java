package org.example;

/************************
 * Made by [MR Ferry™]  *
 * on September 2024    *
 ************************/

import java.math.BigInteger;

public class Rsa{

	// Function to generate a key pair
	private static KeyPair generateKeyPair() {
		// Choose prime numbers
		BigInteger p = new BigInteger("17");
		BigInteger q = new BigInteger("7");

		// Calculate modulus
		BigInteger modulo = p.multiply(q);

		// Calculate Euler's totient function
//		BigInteger phiN = BigInteger.valueOf(10);
		BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

		// Choose a random integer for the public exponent
		BigInteger publicKey = new BigInteger("71"); // Ensure it's relatively prime to phiN

		// Calculate the private exponent using the extended Euclidean algorithm
		BigInteger privateKey = publicKey.modInverse(phiN);

		return new KeyPair(modulo, publicKey, privateKey);
	}

	// Encrypt a message
	public static BigInteger encryptMessage(BigInteger message, BigInteger e, BigInteger n) {
		return message.modPow(e, n);
	}

	// Decrypt a message
	public static BigInteger decryptMessage(BigInteger ciphertext, BigInteger d, BigInteger n) {
		return ciphertext.modPow(d, n);
	}

	public static void main(String[] args) {
		KeyPair keyPair = generateKeyPair();

		BigInteger publicKey = keyPair.publicKey;
		BigInteger privateKey = keyPair.privateKey;
		BigInteger n = keyPair.modulus;
		System.out.println("keyPair = " + keyPair);

		// Example usage
		BigInteger message = new BigInteger("10");
		BigInteger encryptedMessage = encryptMessage(message, publicKey, n);
		BigInteger decryptedMessage = decryptMessage(encryptedMessage, privateKey, n);

		System.out.println("Encrypted Message: " + encryptedMessage);
		System.out.println("Decrypted Message: " + decryptedMessage);

		if (decryptedMessage.equals(message)) {
			System.out.println("Message verified successfully.");
		} else {
			System.out.println("Message verification failed.");
		}
	}

	// Inner class to hold key pair
		record KeyPair(BigInteger modulus, BigInteger publicKey, BigInteger privateKey){
	}
}
