package com.iba.tachonet.utils;

import java.math.BigInteger;

/**
 * @author Vladimir Radchuk
 */
public final class KeyUtils {

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * Private default constructor
     */
    private KeyUtils() {
        super();
    }

    /**
     * Fit (stretch or shrink) the given positive BigInteger into a byte[] of
     * resultByteLen bytes without losing precision.
     * 
     * @trhows IllegalArgumentException If x negative, or won't fit in requested
     *         number of bytes.
     */
    public static final byte[] toFixedLenByteArray(BigInteger x,
            int resultByteLen) {

        if (x.signum() != 1)
            throw new IllegalArgumentException("BigInteger not positive.");

        byte[] xBytes = x.toByteArray();
        int xLen = xBytes.length;

        if (xLen <= 0)
            throw new IllegalArgumentException("BigInteger too small.");

        /*
         * The BigInteger contract specifies that we now have at most one
         * superfluous leading zero byte:
         */
        int xOff = (xBytes[0] == 0) ? 1 : 0;
        xLen -= xOff;

        /*
         * Check whether the BigInteger will fit in the requested byte length.
         */
        if (xLen > resultByteLen)
            throw new IllegalArgumentException("BigInteger too large.");

        /*
         * Now stretch or shrink the encoding to fit in resByteLen bytes.
         */
        byte[] resBytes = new byte[resultByteLen];
        int resOff = resultByteLen - xLen;
        System.arraycopy(xBytes, xOff, resBytes, resOff, xLen);
        return resBytes;
    }

    /**
     * Convert the given byte[] to a String with it's hexadecimal
     * representation.
     * 
     * @param bytes
     * @return
     */
    public static final String getHexStringFromBytes(byte[] bytes) {
        if (bytes == null)
            return null;
        int length = bytes.length;
        char[] buf = new char[length * 2];
        for (int i = 0, j = 0, k; i < length;) {
            k = bytes[i++];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[k & 0x0F];
        }
        return new String(buf);
    }

    /**
     * Convert the given hexadecimal representation to array of bytes.
     * 
     * @param s
     * @return
     */
    public static final byte[] getBytesFromHexString(String s) {
        if (s == null)
            return null;
        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((Character.digit(s.charAt(2 * i), 16) << 4) + Character
                    .digit(s.charAt(2 * i + 1), 16));
        }
        return bytes;
    }

    /**
     * Converts the integer value to array of bytes
     * 
     * @param value
     * @return
     */
    public static final byte[] intToByteArray(int value) {
        return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
                (byte) (value >>> 8), (byte) value };
    }

    /**
     * Converts the array of bytes to integer value
     * 
     * @param b
     * @return
     */
    public static final int byteArrayToInt(byte[] b) {
        return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }

    /**
     * Appends specified symbol in the end of string to adjust the string length
     * 
     * @param s
     * @param length
     * @param symbol
     * @return
     */
    public static final String pad(String s, int length, char symbol) {
        if (s == null)
            s = "";
        while (s.length() < length)
            s += symbol;
        return s;
    }
}
