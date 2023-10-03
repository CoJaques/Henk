package ch.heigvd;

import ch.heigvd.Processor.IDataProcessor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;

/**
 * CLI class for encoding/decoding files.
 * Uses the @Command annotation from the picocli library to define command-line options and parameters.
 */
public class CliApp {

    /**
     * Path to the input file.
     */
    @Parameters(index = "0", description = "Input file path")
    public String inputFile;

    /**
     * Path to the output file.
     */
    @Parameters(index = "1", description = "Output file path")
    public String outputFile;

    /**
     * Type of encoding/decoding to use.
     */
    @Parameters(index = "2", description = "Type of encoding/decoding")
    public String type;

    /**
     * Key used to encode or decode the types depends on the algorithms used.
     */
    @Option(names = "--key", description = "key used to encode or decode the types depends on the algorithms used")
    public String key;

    /**
     * Decode command.
     *
     * @return 0 on success, 1 on failure.
     */
    @Command(name = "Decode", mixinStandardHelpOptions = true, version = "Decode 1.0",
            description = "Decode files using different algorithms.")
    public Integer Decode() {
        if (inputFile == null || outputFile == null) {
            System.out.println("Input or output can't be null");
            return 1;
        }

        IDataProcessor dataProcessor = getDataProcessor(true);
        if (dataProcessor == null) return 1;

        return process(dataProcessor);
    }

    /**
     * Encode command.
     *
     * @return 0 on success, 1 on failure.
     */
    @Command(name = "Encode", mixinStandardHelpOptions = true, version = "Encode 1.0",
            description = "Encode files using different algorithms.")
    public Integer Encode() {
        if (inputFile == null || outputFile == null) {
            System.out.println("Input or output can't be null");
            return 1;
        }

        IDataProcessor dataProcessor = getDataProcessor(false);
        if (dataProcessor == null) return 1;

        return process(dataProcessor);
    }

    private int process(IDataProcessor dataProcessor) {
        byte[] inputBytes = getInputBytes();
        if (inputBytes == null) return 1;

        byte[] outputBytes = processByte(dataProcessor, inputBytes);
        if (outputBytes == null) return 1;

        return writeOutputByte(outputBytes, dataProcessor);
    }

    private static byte[] processByte(IDataProcessor dataProcessor, byte[] inputBytes) {
        byte[] outputBytes;
        try {
            outputBytes = dataProcessor.process(inputBytes);
        } catch (Exception e) {
            System.out.println("Error processing file" + e.getMessage());
            return null;
        }
        return outputBytes;
    }

    private IDataProcessor getDataProcessor(boolean isDecoder) {
        IDataProcessor dataProcessor;

        try {
            dataProcessor = DataProcessorFactory.getProcessor(type, isDecoder, key);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during processor generation" + e.getMessage());
            return null;
        }
        return dataProcessor;
    }

    private Integer writeOutputByte(byte[] outputBytes, IDataProcessor dataProcessor) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            bos.write(outputBytes);
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found" + e.getMessage());
            return 1;
        } catch (IOException e) {
            System.out.println("Error writing output file : " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.out.println("Error processing file" + e.getMessage());
            return 1;
        }

        System.out.println(dataProcessor.getSuccessMessage() + outputFile);
        return 0;
    }

    private byte[] getInputBytes() {
        byte[] inputBytes;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile))) {
            inputBytes = bis.readAllBytes();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found" + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Error reading input file" + e.getMessage());
            return null;
        }
        return inputBytes;
    }
}
