# README.md for Henk

## Introduction

Henk is a Command-Line Interface (CLI) utility designed for encoding and decoding files. The program supports a range of encoding and decoding algorithms and offers two specific commands: `Encode` and `Decode`. Built on the Java platform, it uses the picocli library for command-line argument and option handling.

## Installation

Compile the Java classes and package them into a JAR file. Make sure to include the picocli library in your classpath.

## Usage

### Command for Encoding a File

```sh
java -jar <path-to-jar> <input-file-path> <output-file-path> <type> [--key <key>] Encode
```

### Command for Decoding a File

```sh
java -jar <path-to-jar> <input-file-path> <output-file-path> <type> [--key <key>] Decode
```

### Parameters

- ` <input-file-path>`: Path to the file you want to encode or decode.
- ` <output-file-path>`: Path where the encoded or decoded file will be saved.
- ` <type>`: Specifies the type of encoding or decoding to use. Available types are "base64," "rot13," and "aes."

### Options
- `--key <key>`: [Optional] Specifies the key for encoding or decoding types that rely on cryptographic keys (e.g., "aes").

## Examples

### Encoding a File with Base64

```sh
java -jar Henk.jar inputFile output.txt base64 Encode
```

### Decoding a File with Base64

```sh
java -jar Henk.jar input.txt output.txt base64 Decode
```

## Supported Processors

- Base64 Encoder
- Base64 Decoder
- ROT13 Processor (encoder and decoder)
- AES Encoder [Key of 16, 24, 32 bytes]
- AES Decoder [Key of 16, 24, 32 bytes]

## Return Codes

- `0`: Success
- `1`: Failure (e.g., invalid type specified)

## Contributing and Support

For any issues, please refer to the source code and submit a pull request. Feel free to extend the supported algorithms by modifying the `DataProcessorFactory` class and implementing new `IDataProcessor` classes.