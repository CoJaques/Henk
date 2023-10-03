package ch.heigvd;

import ch.heigvd.Processor.Aes.AESDecoder;
import ch.heigvd.Processor.Aes.AESEncoder;
import ch.heigvd.Processor.Base64.Base64Decoder;
import ch.heigvd.Processor.Base64.Base64Encoder;
import ch.heigvd.Processor.IDataProcessor;
import ch.heigvd.Processor.IKeyedDataProcessor;
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
        decoders.put("aes", new AESDecoder());
        encoders.put("aes", new AESEncoder());
    }

    /**
     * Get a data processor instance based on type and operation.
     *
     * @param type      The type of the data processor.
     * @param isDecoder Whether the processor is a decoder or an encoder.
     * @return The data processor instance.
     * @throws IllegalArgumentException If the type is unknown.
     */
    public static IDataProcessor getProcessor(String type, boolean isDecoder, String key) throws IllegalArgumentException {
        IDataProcessor processor = isDecoder ? decoders.get(type) : encoders.get(type);

        if (processor instanceof IKeyedDataProcessor)
            ((IKeyedDataProcessor) processor).setKey(key);

        if (processor == null)
            throw new IllegalArgumentException("Unknown type: " + type);

        return processor;
    }
}
