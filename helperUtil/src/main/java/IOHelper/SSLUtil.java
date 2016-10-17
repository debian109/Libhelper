package IOHelper;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Using the command keytool -printcert -rfc -file srca.cer export the certificate to a string, and then convert the string
 to an input stream, if you are using okhttp can use new Buffer (). WriteUtf8 (s) .inputStream ()
 */
public class SSLUtil {

    /**
     Returns SSLSocketFactory
     *
     * @param certificates Enter the certificate of stream
     * @return SSLSocketFactory
     */
    public static SSLSocketFactory getSSLSocketFactory(InputStream... certificates) {
        return getSSLSocketFactory(null,certificates);
    }

    /**
     * Mutual authentication
     * @param keyManagers KeyManager[]
     * @param certificates Enter the certificate of stream
     * @return SSLSocketFactory
     */
    public static SSLSocketFactory getSSLSocketFactory(KeyManager[] keyManagers, InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(keyManagers, trustManagerFactory.getTrustManagers(), new SecureRandom());
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            return socketFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtain parameters required for mutual authentication
     * @param bks Enter jks certificates stream
     * @param keystorePass Keys
     * @return KeyManager[] Object
     */
    public static KeyManager[] getKeyManagers(InputStream bks, String keystorePass) {
        KeyStore clientKeyStore = null;
        try {
            clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(bks, keystorePass.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, keystorePass.toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
            return keyManagers;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
