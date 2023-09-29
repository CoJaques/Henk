package ch.heigvd;

import ch.heigvd.Processor.Base64.Base64Decoder;
import ch.heigvd.Processor.Base64.Base64Encoder;
import ch.heigvd.Processor.IDataProcessor;
import ch.heigvd.Processor.ROT13.ROT13Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for creating data processor instances based on type and operation.
 */
public class DataProcessorFactory {

    private static final Map<String, IDataProcessor> decoders = new HashMap<>();
    private static final Map<String, IDataProcessor> encoders = new HashMap<>();

    static {
        decoders.put("base64", new Base64Decoder());
        encoders.put("base64", new Base64Encoder());
        decoders.put("rot13", new ROT13Processor());
        encoders.put("rot13", new ROT13Processor());
    }

    /**
     * Retrieves an IDataProcessor instance based on the type and operation specified.
     *
     * @param type      The type of encoding/decoding, e.g., "base64", "rot13".
     * @param isDecoder Specifies whether to get a decoder or an encoder.
     * @return An IDataProcessor instance corresponding to the specified type and operation,
     * or null if the type is not recognized.
     */
    public static IDataProcessor getProcessor(String type, boolean isDecoder) {
        return isDecoder ? decoders.get(type) : encoders.get(type);
    }
}
