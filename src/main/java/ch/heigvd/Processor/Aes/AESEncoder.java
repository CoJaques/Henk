package ch.heigvd.Processor.Aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * Implementation of the IKeyedDataProcessor interface for AES Encryption.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Advanced_Encryption_Standard">Wikipedia page on AES</a>.
 */
public class AESEncoder extends AESProcessor {

    /**
     * Encrypts the input byte array using AES encryption algorithm.
     *
     * @param input The byte array to be encrypted.
     * @return Encrypted byte array.
     * @throws GeneralSecurityException If encryption fails.
     */
    public byte[] process(byte[] input) throws GeneralSecurityException {
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
