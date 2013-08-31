package com.iba.tachonet.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.iba.tachonet.utils.KeyUtils;

/**
 * @author Vladimir Radchuk
 * 
 */
public class CardCertificate {

    // Certification profile identifier
    private byte CPI = 01;

    // Certification Authority reference 8 bytes length
    private byte[] CAR;

    // Certificate holder authorisation 7 bytes length
    private byte[] CHA;

    // Certificate end of validity
    private Date EOV;

    // Certificate holder reference 8 bytes length
    private byte[] CHR;

    // public key modulus 128 bytes
    private byte[] n;

    // public key exponent 8 bytes
    private byte[] e;

    /**
     * Default constructor
     */
    public CardCertificate() {
        super();
    }

    /**
     * @return the n
     */
    public byte[] getN() {
        return n;
    }

    /**
     * @return n as HEX string
     */
    public String getNasHexString() {
        return KeyUtils.getHexStringFromBytes(n);
    }

    /**
     * @return the e
     */
    public byte[] getE() {
        return e;
    }

    /**
     * @return the e as HEX string
     */
    public String getEasHexString() {
        return KeyUtils.getHexStringFromBytes(e);
    }

    /**
     * Sets the N and E
     * 
     * @param key
     * @return
     */
    public void setKey(byte[] key) {
        if (key.length != (128 + 8))
            throw new RuntimeException("Invalid key size");
        this.n = new byte[128];
        this.e = new byte[8];
        System.arraycopy(key, 0, n, 0, 128);
        System.arraycopy(key, 128, e, 0, 8);
    }

    /**
     * @param key
     * @return
     */
    public void setKeyAsHexString(String key) {
        setKey(KeyUtils.getBytesFromHexString(key));
    }

    /**
     * @return the cPI
     */
    public byte getCPI() {
        return CPI;
    }

    /**
     * @return the CAR
     */
    public byte[] getCAR() {
        return CAR;
    }

    /**
     * @return the cAR
     */
    public String getCARasHexString() {
        return KeyUtils.getHexStringFromBytes(CAR);
    }

    /**
     * @param CAR
     */
    public void setCAR(byte[] CAR) {
        if (CAR.length != 8)
            throw new RuntimeException("CAR should have 8 bytes");
        this.CAR = CAR;
    }

    /**
     * @param CAR
     */
    public void setCARasHexString(String CAR) {
        setCAR(KeyUtils.getBytesFromHexString(CAR));
    }

    /**
     * @return the CHA
     */
    public byte[] getCHA() {
        return CHA;
    }

    /**
     * @return the cHA
     */
    public String getCHAasHexString() {
        return KeyUtils.getHexStringFromBytes(CHA);
    }

    /**
     * @param CHA
     */
    public void setCHA(byte[] CHA) {
        if (CHA.length != 7)
            throw new RuntimeException("CHA should have 7 bytes");
        this.CHA = CHA;
    }

    /**
     * @param CHA
     */
    public void setCHAasHexString(String CHA) {
        setCHA(KeyUtils.getBytesFromHexString(CHA));
    }

    /**
     * @return the eOV
     */
    public Date getEOV() {
        return EOV;
    }

    /**
     * @param EOV
     */
    public void setEOV(Date EOV) {
        this.EOV = EOV;
    }

    /**
     * @param seconds
     */
    public void setEOV(int seconds) {
        this.EOV = new Date(1000L * seconds);
    }

    /**
     * @return the CHR
     */
    public byte[] getCHR() {
        return CHR;
    }

    /**
     * @return the cHR
     */
    public String getCHRasHexString() {
        return KeyUtils.getHexStringFromBytes(CHR);
    }

    /**
     * @param CHR
     */
    public void setCHR(byte[] CHR) {
        if (CHR.length != 8)
            throw new RuntimeException("CHR should have 8 bytes");
        this.CHR = CHR;
    }

    /**
     * @param CHR
     */
    public void setCHRasHexString(String CHR) {
        setCHR(KeyUtils.getBytesFromHexString(CHR));
    }

    /**
     * Converts to real certificate
     * 
     * @throws UnsupportedEncodingException
     */
    public byte[] asBytes() {
        byte[] bytes = new byte[164];
        bytes[0] = CPI;
        CAR = CAR == null ? new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 }
                : CAR;
        CHA = CHA == null ? new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 } : CHA;
        CHR = CHR == null ? new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 }
                : CHR;
        System.arraycopy(CAR, 0, bytes, 1, 8);
        System.arraycopy(CHA, 0, bytes, 1 + 8, 7);
        System.arraycopy(EOV == null ? new byte[] { (byte) 0xff, (byte) 0xff,
                (byte) 0xff, (byte) 0xff } : KeyUtils.intToByteArray((int) (EOV
                .getTime() / 1000L)), 0, bytes, 1 + 8 + 7, 4);
        System.arraycopy(CHR, 0, bytes, 1 + 8 + 7 + 4, 8);
        System.arraycopy(n, 0, bytes, 1 + 8 + 7 + 4 + 8, 128);
        System.arraycopy(e, 0, bytes, 1 + 8 + 7 + 4 + 8 + 128, 8);

        return bytes;
    }
}
