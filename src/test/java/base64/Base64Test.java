package base64;

import ch.heigvd.Processor.Base64.Base64Decoder;
import ch.heigvd.Processor.Base64.Base64Encoder;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Base64Test {

    @Test
    public void testDecode() {
        Base64Decoder decoder = new Base64Decoder();
        String testString = "Hello, world!";
        byte[] encodedBytes = Base64.getEncoder().encode(testString.getBytes());
        byte[] decodedBytes = decoder.process(encodedBytes);
        assertArrayEquals(testString.getBytes(), decodedBytes);
    }

    @Test
    public void testEncode() {
        Base64Encoder encoder = new Base64Encoder();
        String testString = "Hello, world!";
        byte[] encodedBytes = encoder.process(testString.getBytes());
        byte[] expectedBytes = Base64.getEncoder().encode(testString.getBytes());
        assertArrayEquals(expectedBytes, encodedBytes);
    }
}