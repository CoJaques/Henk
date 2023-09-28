package ch.heigvd.Processor.Base64;

import ch.heigvd.Processor.IDataProcessor;

import java.util.Base64;

public class Base64Encryptor implements IDataProcessor {
    public byte[] process(byte[] input) {
        return Base64.getEncoder().encode(input);
    }

    public String getSuccessMessage() {
        return "File encoded using base 64 and written to: ";
    }
}