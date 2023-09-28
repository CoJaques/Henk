package ch.heigvd;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.concurrent.Callable;

@Command(description = "Base64 file encoder", mixinStandardHelpOptions = true)
public class CliApp implements Callable<Integer> {

    @Parameters(index = "0", description = "Input file path")
    private String inputFile;

    @Parameters(index = "1", description = "Output file path")
    private String outputFile;

    @Override
    public Integer call() throws Exception {
        // Read the file into a byte array
        byte[] inputBytes;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile))) {
            inputBytes = bis.readAllBytes();
        }

        // Base64 encode
        byte[] encodedBytes = Base64.getEncoder().encode(inputBytes);

        // Write the encoded bytes to the output file
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            bos.write(encodedBytes);
        }

        System.out.println("File encoded and written to: " + outputFile);
        return 0;
    }
}

