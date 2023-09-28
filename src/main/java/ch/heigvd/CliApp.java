package ch.heigvd;

import ch.heigvd.Processor.IDataProcessor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;


@Command(description = "File encoder/decoder", mixinStandardHelpOptions = true)
public class CliApp implements Callable<Integer> {

    @Parameters(index = "0", description = "Input file path")
    private String inputFile;

    @Parameters(index = "1", description = "Output file path")
    private String outputFile;

    @Option(names = "--type", description = "Type of encoding/decoding")
    private String type;

    @Option(names = "--decode", description = "decode action")
    private boolean isDecoder;

    @Override
    public Integer call() throws Exception {
        IDataProcessor dataProcessor = DataProcessorFactory.getProcessor(type, isDecoder);

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