package ch.heigvd.Processor;

public interface IDataProcessor {
    byte[] process(byte[] input);
    String getSuccessMessage();
}
