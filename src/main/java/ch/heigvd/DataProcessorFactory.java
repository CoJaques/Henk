package ch.heigvd;

import ch.heigvd.Processor.Base64.Base64Decryptor;
import ch.heigvd.Processor.Base64.Base64Encryptor;
import ch.heigvd.Processor.IDataProcessor;
import ch.heigvd.Processor.ROT13.ROT13Processor;

import java.util.HashMap;
import java.util.Map;

public class DataProcessorFactory {
    private static final Map<String, IDataProcessor> decoders = new HashMap<>();
    private static final Map<String, IDataProcessor> encoders = new HashMap<>();

    static {
        decoders.put("base64", new Base64Decryptor());
        encoders.put("base64", new Base64Encryptor());
        decoders.put("rot13", new ROT13Processor());
        encoders.put("rot13", new ROT13Processor());
    }

    public static IDataProcessor getProcessor(String type, boolean isDecoder) {
        return isDecoder ? decoders.get(type) : encoders.get(type);
    }
}
