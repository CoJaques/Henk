package ch.heigvd.Processor.Aes;

import ch.heigvd.Processor.IKeyedDataProcessor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of the IKeyedDataProcessor interface for AES Encryption.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Advanced_Encryption_Standard">Wikipedia page on AES</a>.
 */
public class AESEncoder implements IKeyedDataProcessor {
    private String key;

    /**
     * Sets the encryption key for AES algorithm.
     *
     * @param key The encryption key as a string.
     */
    @Override
    public void setKey(String key) {
        this.key = key;
    }

    private static final String ALGO = "AES/ECB/PKCS5Padding";

    /**
     * Encrypts the input byte array using AES encryption algorithm.
     *
     * @param input The byte array to be encrypted.
     * @return Encrypted byte array.
     * @throws Exception If encryption fails.
     */
    public byte[] process(byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(input);
    }

    /**
     * Provides a success message to indicate completion of the encryption.
     *
     * @return The success message as a string.
     */
    public String getSuccessMessage() {
        return "File encrypted using AES and written to: ";
    }
}
