package ch.heigvd.Processor.ROT13;

import ch.heigvd.Processor.IDataProcessor;

/**
 * ROT13 processor class for encoding/decoding data using ROT13 algorithm.
 * @see <a href="https://en.wikipedia.org/wiki/ROT13">ROT13</a>
 */
public class ROT13Processor implements IDataProcessor {

    /**
     * Process the input data using ROT13 algorithm.
     * As ROT13 is symmetric (i.e., encoding and decoding are the same operation), this method can be used for both.
     */
    public byte[] process(byte[] input) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            byte c = input[i];
            if (c >= 'a' && c <= 'z') output[i] = (byte) ((c - 'a' + 13) % 26 + 'a');
            else if (c >= 'A' && c <= 'Z') output[i] = (byte) ((c - 'A' + 13) % 26 + 'A');
            else output[i] = c;
        }
        return output;
    }

    public String getSuccessMessage() {
        return "File encoded/decoded using ROT13 ";
    }
}
