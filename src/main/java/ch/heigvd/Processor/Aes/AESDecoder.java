package ch.heigvd.Processor.Aes;

import ch.heigvd.Processor.IKeyedDataProcessor;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of the IKeyedDataProcessor interface for AES decryption.
 * @see <a href="https://en.wikipedia.org/wiki/Advanced_Encryption_Standard">Wikipedia page on AES</a>.
 */
public class AESDecoder implements IKeyedDataProcessor {

    private String key;

    /**
     * Sets the key for AES decryption.
     *
     * @param key The encryption key as a String.
     */
    @Override
    public void setKey(String key) {
        this.key = key;
    }

    private static final String ALGO = "AES/ECB/PKCS5Padding";

    /**
     * Decrypts the given byte array using AES.
     *
     * @param input The byte array to decrypt.
     * @return The decrypted byte array.
     * @throws Exception if decryption fails.
     */
    public byte[] process(byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(input);
    }

    /**
     * Returns a success message after decryption.
     *
     * @return Success message as a String.
     */
    public String getSuccessMessage() {
        return "File decoded using AES and written to: ";
    }
}