# README.md for Henk (like Encoder but more Dutch)

## Introduction

CliApp is a command-line interface (CLI) utility for encoding and decoding files. It supports various encoding and decoding algorithms, specified at runtime via command-line options. Built on the Java platform, it uses the picocli library to handle command-line arguments and options.

## Installation

Compile the Java classes and package them into a JAR file. Make sure you include the picocli library in your classpath.

## Usage

The general format for running the CliApp is as follows:

```
java -jar <path-to-jar> <input-file-path> <output-file-path> [--type <type>] [--decode]
```

### Parameters

- `<input-file-path>`: The path to the file you want to encode or decode.
- `<output-file-path>`: The path where the encoded or decoded file will be saved.

### Options

- `--type <type>`: The type of encoding or decoding to use (e.g., "base64", "rot13").
- `--decode`: Use this flag if you want to decode the input file.

## Examples

### Encoding a File with Base64

```sh
java -jar CliApp.jar input.txt output.txt --type base64
```

### Decoding a File with Base64

```sh
java -jar CliApp.jar input.txt output.txt --type base64 --decode
```

## Return Codes

- `0`: Success
- `1`: Failure (e.g., invalid type specified)

## Contributing and Support

For any issues, please refer to the source code or contact the development team.

Feel free to extend the supported algorithms by modifying the `DataProcessorFactory` class and implementing new `IDataProcessor` classes.