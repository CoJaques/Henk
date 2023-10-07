package ch.heigvd.Processor.Aes;

import ch.heigvd.DataProcessorFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * Implementation of the IKeyedDataProcessor interface for AES decryption.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Advanced_Encryption_Standard">Wikipedia page on AES</a>.
 */
public class AESDecoder extends AESProcessor {

    /**
     * Decrypts the given byte array using AES.
     *
     * @param input The byte array to decrypt.
     * @return The decrypted byte array.
     * @throws GeneralSecurityException if decryption fails.
     */
    public byte[] process(byte[] input) throws GeneralSecurityException {
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

    @Override
    public void register() {
        DataProcessorFactory.addDecoder("aes", this);
    }
}