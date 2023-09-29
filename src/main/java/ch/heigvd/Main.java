package ch.heigvd;

import picocli.CommandLine;

/**
 * Main class for the CLI application
 */
public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CliApp()).execute(args);
        System.exit(exitCode);
    }
}