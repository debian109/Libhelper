package IOHelper;

/**
 */
public class HexUtil {
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * Hexadecimal byte array transfer
     * @param data Hexadecimal string
     * @return byte array
     * @throws Exception Abnormal conversion failed
     */
    public static byte[] hex2Bytes(final String data) throws Exception {
        final int len = data.length();

        if ((len & 0x01) != 0) {
            throw new Exception("Odd number of characters.");
        }

        final byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data.charAt(j), j) << 4;
            j++;
            f = f | toDigit(data.charAt(j), j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    /**
     * String array of hexadecimal bytes transferred
     * @param data an array of bytes
     * @return Conversion Results
     */
    public static String bytes2Hex(final byte[] data) {
        return bytes2Hex(data, true);
    }

    /**
     * String array of hexadecimal bytes transferred
     * @param data an array of bytes
     * @param toLowerCase Are lowercase
     * @return Conversion Results
     */
    public static String bytes2Hex(final byte[] data, final boolean toLowerCase) {
        return bytes2Hex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }


    /**
     * String array of hexadecimal bytes transferred
     * @param data an array of bytes
     * @param toDigits DIGITS_LOWER或DIGITS_UPPER
     * @return Conversion Results
     */
    private static String bytes2Hex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }
    /**
     * 16 is converted to digital
     * @param ch Hex
     * @param index index
     * @return Conversion Results
     * @throws Exception Conversion failed abnormalities
     */
    private static int toDigit(final char ch, final int index)
            throws Exception {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new Exception("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }

}
