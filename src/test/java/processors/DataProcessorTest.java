package processors;

import ch.heigvd.DataProcessorFactory;
import ch.heigvd.Processor.Base64.Base64Encoder;
import ch.heigvd.Processor.IDataProcessor;
import ch.heigvd.Processor.IKeyedDataProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataProcessorTest {
    @Test
    public void testGetProcessorForKnownType() {
        IDataProcessor processor = DataProcessorFactory.getProcessor("base64", false, null);
        assertNotNull(processor);
        assertTrue(processor instanceof Base64Encoder);
    }

    @Test
    public void testGetProcessorForUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> DataProcessorFactory.getProcessor("unknown", false, null));
    }

    @Test
    public void testKeyedDataProcessor() {
        IDataProcessor processor = DataProcessorFactory.getProcessor("aes", true, "12345678123456781234567812345678");
        assertNotNull(processor);
        assertTrue(processor instanceof IKeyedDataProcessor);

        IKeyedDataProcessor keyedProcessor = (IKeyedDataProcessor) processor;

        assertThrows(IllegalArgumentException.class, () -> keyedProcessor.setKey("wrong-key"));
        assertDoesNotThrow(() -> keyedProcessor.setKey("123456781234567812345678123456781234567812345678"));
    }

}
