package ch.heigvd.Processor;

/**
 * Interface for data processors
 */
public interface IDataProcessor {
    /**
     * Process the input data
     *
     * @param input The input data
     * @return The processed data
     */
    byte[] process(byte[] input) throws Exception;

    void register();

    /**
     * Get the success message
     *
     * @return The success message
     */
    String getSuccessMessage();
}
