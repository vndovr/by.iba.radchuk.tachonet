package com.iba.tachonet.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.iba.tachonet.utils.KeyUtils;

/**
 * @author Vladimir Radchuk
 * 
 */
public class CardCryptoFacade {

    // Security provider
    private static final String JCEP = "BC";

    private static final String RSA = "RSA";
    private static final String RSACipher = "RSA/ECB/NoPadding";

    private static final String AES = "AES";
    private static final String AESCipher = "AES/ECB/PKCS7Padding";

    private static final String SHA1 = "SHA1";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Default constructor
     */
    public CardCryptoFacade() {
        super();
    }

    /**
     * Generates RSA 1024 key pair
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static CardRSAKeyPair generateRSAKeyPair()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA, JCEP);
        keyGen.initialize(1024);
        KeyPair keypair = keyGen.genKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keypair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keypair.getPublic();

        byte[] prvKeyModulus = KeyUtils.toFixedLenByteArray(privateKey
                .getModulus(), 128);
        byte[] prvKeyExponent = KeyUtils.toFixedLenByteArray(privateKey
                .getPrivateExponent(), 128);
        byte[] pubKeyModulus = KeyUtils.toFixedLenByteArray(publicKey
                .getModulus(), 128);
        byte[] pubKeyExponent = KeyUtils.toFixedLenByteArray(publicKey
                .getPublicExponent(), 8);

        byte[] bsPrivateKey = new byte[256];
        byte[] bsPublicKey = new byte[136];

        System.arraycopy(prvKeyModulus, 0, bsPrivateKey, 0, 128);
        System.arraycopy(prvKeyExponent, 0, bsPrivateKey, 128, 128);
        System.arraycopy(pubKeyModulus, 0, bsPublicKey, 0, 128);
        System.arraycopy(pubKeyExponent, 0, bsPublicKey, 128, 8);

        return new CardRSAKeyPair(bsPublicKey, bsPrivateKey);
    }

    /**
     * Returns the signature for specified certificate
     * 
     * @param cc
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchProviderException
     */
    public static final byte[] sign(CardCertificate cc, byte[] key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, NoSuchProviderException {
        byte[] bcc = cc.asBytes();
        byte[] hash = getSHA1Digest(bcc);
        byte[] certificate = new byte[194];
        byte[] lcs = new byte[1 + 106 + hash.length + 1];
        lcs[0] = (byte) 0x6a;
        System.arraycopy(bcc, 0, lcs, 1, 106);
        System.arraycopy(hash, 0, lcs, 1 + 106, hash.length);
        lcs[lcs.length - 1] = (byte) 0xbc;
        byte[] crypted = encryptWithRSAKey(key, lcs);
        System.arraycopy(crypted, 0, certificate, 0, 128);
        System.arraycopy(bcc, 106, certificate, 128, 58);
        System.arraycopy(cc.getCAR(), 0, certificate, 128 + 58, 8);
        return certificate;
    }

    /**
     * Encrypts the message by RSA public key
     * 
     * @param privateKey
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchProviderException
     */
    public static byte[] encryptWithRSAKey(byte[] key, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException,
            NoSuchProviderException {

        byte[] bsModulus = new byte[128];
        byte[] bsExponent = new byte[key.length == 256 ? 128 : 8];

        System.arraycopy(key, 0, bsModulus, 0, 128);
        System.arraycopy(key, 128, bsExponent, 0, key.length - 128);

        Key pk = key.length == 256 ? (RSAPrivateKey) KeyFactory.getInstance(
                RSA, JCEP).generatePrivate(
                new RSAPrivateKeySpec(new BigInteger(1, bsModulus),
                        new BigInteger(1, bsExponent)))
                : (RSAPublicKey) KeyFactory.getInstance(RSA, JCEP)
                        .generatePublic(
                                new RSAPublicKeySpec(new BigInteger(1,
                                        bsModulus), new BigInteger(1,
                                        bsExponent)));

        Cipher cipher = Cipher.getInstance(RSACipher, JCEP);
        cipher.init(Cipher.ENCRYPT_MODE, pk);
        return cipher.doFinal(message);
    }

    /**
     * Decrypts the message by RSA private key
     * 
     * @param key
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws UnsupportedEncodingException
     */
    public static byte[] decryptWithRSAKey(byte[] key, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, IOException,
            NoSuchProviderException {

        byte[] bsModulus = new byte[128];
        byte[] bsExponent = new byte[key.length == 256 ? 128 : 8];

        System.arraycopy(key, 0, bsModulus, 0, 128);
        System.arraycopy(key, 128, bsExponent, 0, key.length - 128);

        Key pk = key.length == 256 ? (RSAPrivateKey) KeyFactory.getInstance(
                RSA, JCEP).generatePrivate(
                new RSAPrivateKeySpec(new BigInteger(1, bsModulus),
                        new BigInteger(1, bsExponent)))
                : (RSAPublicKey) KeyFactory.getInstance(RSA, JCEP)
                        .generatePublic(
                                new RSAPublicKeySpec(new BigInteger(1,
                                        bsModulus), new BigInteger(1,
                                        bsExponent)));

        Cipher cipher = Cipher.getInstance(RSACipher, JCEP);
        cipher.init(Cipher.DECRYPT_MODE, pk);
        return cipher.doFinal(message);
    }

    /**
     * Encrypts the message by AES key
     * 
     * @param key
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchProviderException
     */
    public static byte[] encryptWithAESKey(byte[] key, byte[] message)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchProviderException {
        SecretKeySpec skeySpec = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AESCipher, JCEP);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(message);

    }

    /**
     * Decrypts the message by AES key
     * 
     * @param key
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchProviderException
     */
    public static byte[] decryptWithAESKey(byte[] key, byte[] message)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchProviderException {
        SecretKeySpec skeySpec = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AESCipher, JCEP);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(message);

    }

    /**
     * Computes the SHA1 digest for specified string
     * 
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    static final byte[] getSHA1Digest(byte[] source)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        MessageDigest sha1 = MessageDigest.getInstance(SHA1, JCEP);
        return sha1.digest(source);

    }

    /**
     * Returns the signature for specified certificate
     * 
     * @param cc
     *            as HEX string
     * @param key
     *            as HEX string
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchProviderException
     */
    public static final String sign(CardCertificate cc, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, NoSuchProviderException {
        return KeyUtils.getHexStringFromBytes(sign(cc, KeyUtils
                .getBytesFromHexString(key)));
    }

    /**
     * Encrypts the message by RSA public key
     * 
     * @param privateKey
     *            as HEX string
     * @param message
     *            as HEX string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchProviderException
     */
    public static String encryptWithRSAKey(String key, String message)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException,
            NoSuchProviderException {
        return KeyUtils.getHexStringFromBytes(encryptWithRSAKey(KeyUtils
                .getBytesFromHexString(key), KeyUtils
                .getBytesFromHexString(message)));
    }

    /**
     * Decrypts the message by RSA private key
     * 
     * @param key
     *            as HEX string
     * @param message
     *            as HEX string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws UnsupportedEncodingException
     */
    public static String decryptWithRSAKey(String key, String message)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, IOException,
            NoSuchProviderException {
        return KeyUtils.getHexStringFromBytes(decryptWithRSAKey(KeyUtils
                .getBytesFromHexString(key), KeyUtils
                .getBytesFromHexString(message)));
    }

    /**
     * Encrypts the message by AES key
     * 
     * @param key
     *            as HEX string
     * @param message
     *            as HEX string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchProviderException
     */
    public static String encryptWithAESKey(String key, String message)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchProviderException {
        return KeyUtils.getHexStringFromBytes(encryptWithAESKey(KeyUtils
                .getBytesFromHexString(key), KeyUtils
                .getBytesFromHexString(message)));
    }

    /**
     * Decrypts the message by AES key
     * 
     * @param key
     *            as HEX string
     * @param message
     *            as HEX string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchProviderException
     */
    public static String decryptWithAESKey(String key, String message)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchProviderException {
        return KeyUtils.getHexStringFromBytes(decryptWithAESKey(KeyUtils
                .getBytesFromHexString(key), KeyUtils
                .getBytesFromHexString(message)));
    }

}
