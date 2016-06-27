import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;

import IOHelper.HexUtil;

/**
 * Mã hóa và giải mã RSA công cụ, cung cấp mặc định trên nền tảng Android thuật toán
 * mã hóa RSA được sử dụng là "org.bouncycastle.jce.provider.BouncyCastleProvider"
 */
public class RSAUtil {
    private static final String ALGORITHOM = "RSA";
    // Algorithm name
    private static final int KEY_SIZE = 1024;
    //Key Size
    private static KeyPairGenerator keyPairGen = null;
    private static KeyFactory keyFactory = null;
    /**
     * Cache key pair.
     */
    private static KeyPair oneKeyPair = null;

    static {
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM);
            keyFactory = KeyFactory.getInstance(ALGORITHOM);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Generate and return RSA key pair.
     * @return Generate and return RSA key pair.。
     */
    private static synchronized KeyPair generateKeyPair() {
        try {
            keyPairGen.initialize(KEY_SIZE, new SecureRandom(new SimpleDateFormat("yyyyMMdd").format(new Date()).getBytes()));
            oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        } catch (InvalidParameterException ex) {
            System.out.println("KeyPairGenerator does not support a key length of " + KEY_SIZE + ".");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("RSAUtils#KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance.");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Returns RSA key pair。
     *
     * @return Returns RSA key pair.
     */
    public static KeyPair getKeyPair() {
        if (oneKeyPair != null) {
            return oneKeyPair;
        }
        return generateKeyPair();
    }

    /**
     * RSA public key object-specific coefficients and the given special index structure.
     *
     * @param modulus        Factor.
     * @param publicExponent Special index.
     * @return RSA dedicated public objects.
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus),
                new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException ex) {
            System.out.println("RSAPublicKeySpec is unavailable.");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * According to the given coefficient and a special index structure dedicated RSA private key object.
     *
     * @param modulus         Factor
     * @param privateExponent Chỉ số đặc biệt.
     * @return RSA dedicated private objects。
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus),
                new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException ex) {
            System.out.println("RSAPrivateKeySpec is unavailable.");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * RSA private key object of the given special hexadecimal string index structure coefficient and dedicated.
     *
     * @param hexModulus         Factor。
     * @param hexPrivateExponent Special index。
     * @return RSA dedicated private objects.
     */
    public static RSAPrivateKey getRSAPrivateKey(String hexModulus, String hexPrivateExponent) {
        if (isBlank(hexModulus) || isBlank(hexPrivateExponent)) {
            System.out.println("hexModulus and hexPrivateExponent cannot be empty. RSAPrivateKey value is null to return.");
            return null;
        }
        byte[] modulus = null;
        byte[] privateExponent = null;
        try {
            modulus = HexUtil.hex2Bytes(hexModulus);
            privateExponent = HexUtil.hex2Bytes(hexPrivateExponent);
        } catch (Exception ex) {
            System.out.println("hexModulus or hexPrivateExponent value is invalid. return null(RSAPrivateKey).");
            ex.printStackTrace();
        }
        if (modulus != null && privateExponent != null) {
            return generateRSAPrivateKey(modulus, privateExponent);
        }
        return null;
    }

    /**
     * According to the given hexadecimal string index coefficients and construct a dedicated private RSA public key object.
     *
     * @param hexModulus        Factor。
     * @param hexPublicExponent Special index。
     * @return RSA dedicated public objects.
     */
    public static RSAPublicKey getRSAPublicKey(String hexModulus, String hexPublicExponent) {
        if (isBlank(hexModulus) || isBlank(hexPublicExponent)) {
            System.out.println("hexModulus and hexPublicExponent cannot be empty. return null(RSAPublicKey).");
            return null;
        }
        byte[] modulus = null;
        byte[] publicExponent = null;
        try {
            modulus = HexUtil.hex2Bytes(hexModulus);
            publicExponent = HexUtil.hex2Bytes(hexPublicExponent);
        } catch (Exception ex) {
            System.out.println("hexModulus or hexPublicExponent value is invalid. return null(RSAPublicKey).");
            ex.printStackTrace();
        }
        if (modulus != null && publicExponent != null) {
            return generateRSAPublicKey(modulus, publicExponent);
        }
        return null;
    }

    /**
     * Using the specified public key to encrypt data.
     *
     * @param publicKey Given public key.
     * @param data      Data to be encrypted.
     * @return Encrypted data.
     */

    public static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }


    /**
     * Using the specified private key to decrypt the data.
     *
     * @param privateKey Given private key.
     * @param data       To decrypt data.
     * @return Original data。
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }

    /**
     * Given public key encryption to use for a given string.
     *
     * @param publicKey Given public key.
     * @param plaintext String.
     * @return Ciphertext given string.
     */
    public static String encryptString(PublicKey publicKey, String plaintext) {
        if (publicKey == null || plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        try {
            byte[] en_data = encrypt(publicKey, data);
            return new String(HexUtil.bytes2Hex(en_data));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * The default public key encryption given strings.
     *
     * @param plaintext String
     * @return Given the string of ciphertext
     */
    public static String encryptString(String plaintext) {
        if (plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        KeyPair keyPair = getKeyPair();
        try {
            byte[] en_data = encrypt((RSAPublicKey) keyPair.getPublic(), data);
            return new String(HexUtil.bytes2Hex(en_data));
        } catch (NullPointerException ex) {
            System.out.println("keyPair cannot be null.");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * RSA encryption is generated by JS string.
     *
     * @param publicKey Public Key
     * @param plaintext Original string
     * @return Encrypted string
     */
    public static String encryptStringByJs(PublicKey publicKey, String plaintext) {
        if (plaintext == null) {
            return null;
        }
        String text = encryptString(publicKey, reverse(plaintext));

        return text;
    }

    /**
     * JS RSA encryption by the string generated by default public.。
     * @param plaintext Original string
     * @return Encrypted string
     */
    public static String encryptStringByJs(String plaintext) {
        if (plaintext == null) {
            return null;
        }
        String text = encryptString(reverse(plaintext));

        return text;
    }

    /**
     * Given private key to decrypt the use of a given string。
     *
     * If the private key is {code null}, or {code encrypttext} is {code null} or an empty string is returned {code null}.
     * When the private key does not match, returns {code null}.
     *
     * @param privateKey  Given private key.
     * @param encrypttext Ciphertext。
     * @return Original string。
     */
    public static String decryptString(PrivateKey privateKey, String encrypttext) {
        if (privateKey == null || isBlank(encrypttext)) {
            return null;
        }
        try {
            byte[] en_data = HexUtil.hex2Bytes(encrypttext);
            byte[] data = decrypt(privateKey, en_data);
            return new String(data);
        } catch (Exception ex) {
            System.out.println(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getCause().getMessage()));

        }
        return null;
    }

    /**
     * Use the default private key to decrypt a given string。
     *
     * @param encrypttext Ciphertext。
     * @return Original string。
     */
    public static String decryptString(String encrypttext) {
        if (isBlank(encrypttext)) {
            return null;
        }
        KeyPair keyPair = getKeyPair();
        try {
            byte[] en_data = HexUtil.hex2Bytes(encrypttext);
            byte[] data = decrypt((RSAPrivateKey) keyPair.getPrivate(), en_data);
            return new String(data);
        } catch (NullPointerException ex) {
            System.out.println("keyPair cannot be null.");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getMessage()));
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Using the specified private key to decrypt the encrypted string JS.
     *
     * @param privateKey  Private
     * @param encrypttext Ciphertext
     * @return {@code encrypttext} The original string
     */
    public static String decryptStringByJs(PrivateKey privateKey, String encrypttext) {
        String text = decryptString(privateKey, encrypttext);
        if (text == null) {
            return null;
        }
        return reverse(text);
    }

    public static String decryptStringByJs(String encrypttext) {
        String text = decryptString(encrypttext);
        if (text == null) {
            return null;
        }
        return reverse(text);
    }


    /**
     * Returns the default public initialized。
     * @return Returns the default public has been initialized.
     */
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = getKeyPair();
        if (keyPair != null) {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }

    /**
     * Returns the default key initialized。
     * @return Returns the default key and initialized.
     */
    public static RSAPrivateKey getDefaultPrivateKey() {
        KeyPair keyPair = getKeyPair();
        if (keyPair != null) {
            return (RSAPrivateKey) keyPair.getPrivate();
        }
        return null;
    }

    /**
     * Reversal string
     * @param str The string to be reversed
     * @return After the reversal of a string
     */
    private static String reverse(final String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Analyzing non-empty string
     * @param cs CharSequence sequence to be judged
     * @return It is a non-empty
     */
    private static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
