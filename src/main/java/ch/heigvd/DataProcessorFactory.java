package ch.heigvd;

import ch.heigvd.Processor.IDataProcessor;
import ch.heigvd.Processor.IKeyedDataProcessor;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Factory class for creating data processor instances based on type and operation.
 */
public class DataProcessorFactory {

    private static final Map<String, IDataProcessor> decoders = new HashMap<>();
    private static final Map<String, IDataProcessor> encoders = new HashMap<>();

    public static void addDecoder(String type, IDataProcessor decoder) {
        decoders.put(type, decoder);
    }

    public static void addEncoder(String type, IDataProcessor encoder) {
        encoders.put(type, encoder);
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
        if (decoders.isEmpty() || encoders.isEmpty())
            registerProcessors();

        IDataProcessor processor = isDecoder ? decoders.get(type) : encoders.get(type);

        if (processor instanceof IKeyedDataProcessor)
            ((IKeyedDataProcessor) processor).setKey(key);

        if (processor == null)
            throw new IllegalArgumentException("Unknown type: " + type);

        return processor;
    }

    private static void registerProcessors() {
        Reflections reflections = new Reflections("ch.heigvd.Processor");
        Set<Class<? extends IDataProcessor>> processors = reflections.getSubTypesOf(IDataProcessor.class);

        for (Class<? extends IDataProcessor> processor : processors) {
            try {
                IDataProcessor instance = processor.getDeclaredConstructor().newInstance();
                Method method = processor.getDeclaredMethod("register");
                method.invoke(instance);
            } catch (Exception e) {
                System.out.println("Error while registering processor: " + e.getMessage());
            }
        }
    }
}
