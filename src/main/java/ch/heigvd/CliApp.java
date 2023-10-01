package ch.heigvd;

import ch.heigvd.Processor.IDataProcessor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.util.concurrent.Callable;

/**
 * CLI class for encoding/decoding files.
 * Uses the @Command annotation from the picocli library to define command-line options and parameters.
 */
@Command(description = "File encoder/decoder", mixinStandardHelpOptions = true)
public class CliApp implements Callable<Integer> {

    /**
     * Path to the input file.
     */
    @Parameters(index = "0", description = "Input file path")
    private String inputFile;

    /**
     * Path to the output file.
     */
    @Parameters(index = "1", description = "Output file path")
    private String outputFile;

    /**
     * Type of encoding/decoding to use.
     */
    @Option(names = "--type", description = "Type of encoding/decoding")
    private String type;

    /**
     * Indicates if the action is to decode.
     */
    @Option(names = "--decode", description = "decode action")
    private boolean isDecoder;

    /**
     * Key used to encode or decode the types depends on the algorithms used.
     */
    @Option(names = "--key", description = "key used to encode or decode the types depends on the algorithms used")
    private String key;

    /**
     * Method called to execute the encoding or decoding.
     *
     * @return 0 on success, 1 on failure.
     */
    @Override
    public Integer call() {
        IDataProcessor dataProcessor = getDataProcessor();
        if (dataProcessor == null) return 1;

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

    private IDataProcessor getDataProcessor() {
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
