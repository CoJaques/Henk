package ch.heigvd;

import java.util.Base64;

public class Base64Decryptor implements IDataProcessor {
    public byte[] process(byte[] input) {
        return Base64.getDecoder().decode(input);
    }

    public String getSuccessMessage() {
        return "File decoded using base 64 and written to: ";
    }
}
