package ch.heigvd.Processor;

/**
 * Extends IDataProcessor to include the ability to set an encryption or decryption key.
 * Classes implementing this interface can process data with algorithms that require a key.
 */
public interface IKeyedDataProcessor extends IDataProcessor {

    /**
     * Sets the encryption or decryption key.
     *
     * @param key The key as a string.
     * @throws IllegalArgumentException if the key is not of the correct length.
     */
    void setKey(String key) throws IllegalArgumentException;
}