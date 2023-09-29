package ch.heigvd.Processor.Base64;

import ch.heigvd.Processor.IDataProcessor;

import java.util.Base64;

/**
 * Base64 processor class for decoding data using Base64 algorithm.
 * @see <a href="https://en.wikipedia.org/wiki/Base64">Base64</a>
 */
public class Base64Decoder implements IDataProcessor {

    /**
     * Decode the input data using Base64 algorithm.
     * @param input The input data
     * @return The decoded data
     */
    public byte[] process(byte[] input) {
        return Base64.getDecoder().decode(input);
    }

    public String getSuccessMessage() {
        return "File decoded using base 64 and written to: ";
    }
}
