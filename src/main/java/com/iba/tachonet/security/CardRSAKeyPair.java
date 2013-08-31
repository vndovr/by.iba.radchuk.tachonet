package com.iba.tachonet.security;

import java.io.Serializable;

import com.iba.tachonet.utils.KeyUtils;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class CardRSAKeyPair implements Serializable {

    private static final long serialVersionUID = 1L;

    private final byte[] publicKey;
    private final byte[] privateKey;

    /**
     * Default constructor
     * 
     * @param publicKey
     * @param privateKey
     */
    public CardRSAKeyPair(byte[] publicKey, byte[] privateKey) {
        super();
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Default constructor
     * 
     * @param publicKey
     *            as HEX string
     * @param privateKey
     *            as HEX string
     */
    public CardRSAKeyPair(String publicKey, String privateKey) {
        super();
        this.publicKey = KeyUtils.getBytesFromHexString(publicKey);
        this.privateKey = KeyUtils.getBytesFromHexString(privateKey);
    }

    /**
     * @return the publicKey
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    /**
     * @return the privateKey
     */
    public byte[] getPrivateKey() {
        return privateKey;
    }

    /**
     * @return the publicKey
     */
    public String getPublicKeyAsHexString() {
        return KeyUtils.getHexStringFromBytes(publicKey);
    }

    /**
     * @return the privateKey
     */
    public String getPrivateKeyAsHexString() {
        return KeyUtils.getHexStringFromBytes(privateKey);
    }

}
