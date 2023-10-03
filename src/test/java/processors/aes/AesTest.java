package processors.aes;

import ch.heigvd.Processor.Aes.AESDecoder;
import ch.heigvd.Processor.Aes.AESEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AesTest {

    private AESDecoder decoder;
    private AESEncoder encoder;

    @BeforeEach
    public void setup() {
        decoder = new AESDecoder();
        encoder = new AESEncoder();

        String key = "12345678123456781234567812345678";
        decoder.setKey(key);
        encoder.setKey(key);
    }

    @Test
    public void testAESEncodeDecode() throws GeneralSecurityException {
        byte[] original = "Hello, World!".getBytes();

        byte[] encrypted = encoder.process(original);
        byte[] decrypted = decoder.process(encrypted);

        assertArrayEquals(original, decrypted);
    }

    @Test
    public void testAESExceptionOnWrongKeyLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.setKey("wrong-key");
        });
    }
}
