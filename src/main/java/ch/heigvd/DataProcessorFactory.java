package ch.heigvd;

import java.util.HashMap;
import java.util.Map;

public class DataProcessorFactory {
    private static final Map<String, IDataProcessor> decoders = new HashMap<>();
    private static final Map<String, IDataProcessor> encoders = new HashMap<>();

    static {
        decoders.put("base64", new Base64Decryptor());
        encoders.put("base64", new Base64Encryptor());
    }

    public static IDataProcessor getProcessor(String type, boolean isDecoder) {
        return isDecoder ? decoders.get(type) : encoders.get(type);
    }
}
