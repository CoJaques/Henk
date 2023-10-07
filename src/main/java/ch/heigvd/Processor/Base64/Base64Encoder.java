package ch.heigvd.Processor.Base64;

import ch.heigvd.DataProcessorFactory;
import ch.heigvd.Processor.IDataProcessor;

import java.util.Base64;

/**
 * Base64 processor class for encoding data using Base64 algorithm.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Base64">Base64</a>
 */
public class Base64Encoder implements IDataProcessor {

    /**
     * Encode the input data using Base64 algorithm.
     *
     * @param input The input data
     * @return The encoded data
     */
    public byte[] process(byte[] input) {
        return Base64.getEncoder().encode(input);
    }

    public String getSuccessMessage() {
        return "File encoded using base 64";
    }

    @Override
    public void register() {
        DataProcessorFactory.addEncoder("base64", this);
    }
}