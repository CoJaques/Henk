package ch.heigvd.Processor.Aes;

public abstract class AESProcessor implements ch.heigvd.Processor.IKeyedDataProcessor {
    protected String key;

    protected static final String ALGO = "AES/ECB/PKCS5Padding";

    /**
     * Sets the key for AES decryption.
     *
     * @param key The encryption key as a String.
     * @throws IllegalArgumentException if the key is not 16/24/32 characters long.
     */
    @Override
    public void setKey(String key) throws IllegalArgumentException {
        if (key.length() != 16 && key.length() != 24 && key.length() != 32)
            throw new IllegalArgumentException("Key must be 16/24/32 characters long");

        this.key = key;
    }

    @Override
    abstract public byte[] process(byte[] input) throws Exception;

    @Override
    abstract public String getSuccessMessage();
}
