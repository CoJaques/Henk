# README.md for Henk

## Introduction

CliApp is a command-line interface (CLI) utility for encoding and decoding files. It supports various encoding and decoding algorithms, specified at runtime via command-line options. Built on the Java platform, it uses the picocli library to handle command-line arguments and options.

## Installation

Compile the Java classes and package them into a JAR file. Make sure you include the picocli library in your classpath.

## Usage

The general format for running the CliApp is as follows:

```
java -jar <path-to-jar> <input-file-path> <output-file-path> [--type <type>] [--decode] [--key <key>]
```

### Parameters

- `<input-file-path>`: The path to the file you want to encode or decode.
- `<output-file-path>`: The path where the encoded or decoded file will be saved.

### Options

- `--type <type>`: Specifies the type of encoding or decoding to use. Available types are "base64", "rot13", and "aes".
- `--decode`: Use this flag if you want to decode the input file.
- `--key <key>`: Specifies the key to use for encoding or decoding types that depend on cryptographic keys (e.g., "aes").

## Examples

### Encoding a File with Base64

```sh
java -jar CliApp.jar input.txt output.txt --type base64
```

### Decoding a File with Base64

```sh
java -jar CliApp.jar input.txt output.txt --type base64 --decode
```

### Encoding a File with AES

```sh
java -jar CliApp.jar input.txt output.txt --type aes --key yourKeyHere
```

### Decoding a File with AES

```sh
java -jar CliApp.jar input.txt output.txt --type aes --decode --key yourKeyHere
```

## Supported Processors

- Base64 Encoder
- Base64 Decoder
- ROT13 Processor (encoder and decoder)
- AES Encoder
- AES Decoder

## Return Codes

- `0`: Success
- `1`: Failure (e.g., invalid type specified)

## Contributing and Support

For any issues, please refer to the source code and submit a pull request

Feel free to extend the supported algorithms by modifying the `DataProcessorFactory` class and implementing new `IDataProcessor` classes.

