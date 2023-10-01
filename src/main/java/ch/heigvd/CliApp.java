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
     * @throws Exception If an exception occurs during processing.
     */
    @Override
    public Integer call() throws Exception {
        IDataProcessor dataProcessor = DataProcessorFactory.getProcessor(type, isDecoder, key);

        if (dataProcessor == null) {
            System.out.println("Invalid type specified");
            return 1;
        }

        byte[] inputBytes;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile))) {
            inputBytes = bis.readAllBytes();
        }

        byte[] outputBytes = dataProcessor.process(inputBytes);

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            bos.write(outputBytes);
        }

        System.out.println(dataProcessor.getSuccessMessage() + outputFile);
        return 0;
    }
}
