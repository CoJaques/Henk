package ch.heigvd;

public interface IDataProcessor {
    byte[] process(byte[] input);
    String getSuccessMessage();
}
