import ch.heigvd.CliApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CLIAppTest {
    private CliApp app;

    @BeforeEach
    public void setUp() {
        app = new CliApp();
    }

    @Test
    public void testSuccessfulCall() throws Exception {
        app.inputFile = "src/test/resources/input.txt";
        app.outputFile = "src/test/resources/output.txt";
        app.type = "base64";
        app.key = "key";

        try (OutputStream os = new FileOutputStream("src/test/resources/input.txt")) {
            os.write(new byte[]{55, 56, 57});
        }

        Integer result = app.Encode();

        assertEquals(0, result.intValue());

        // Lire le fichier de sortie et vérifier son contenu
        byte[] outputBytes;
        try (InputStream is = new FileInputStream("src/test/resources/output.txt")) {
            outputBytes = is.readAllBytes();
        }

        assertArrayEquals(new byte[]{78, 122, 103, 53}, outputBytes);
    }

    @Test
    public void errorWithBadType() {
        app.inputFile = "src/test/resources/input.txt";
        app.outputFile = "src/test/resources/output.txt";
        app.type = "mockType";
        app.key = "key";

        Integer result = app.Decode();

        assertEquals(1, result.intValue());
    }

    @Test
    public void errorWithFileNotFound() {
        app.inputFile = "notFound";
        app.outputFile = "src/test/resources/output.txt";
        app.type = "rot13";
        app.key = "key";

        Integer result = app.Decode();

        assertEquals(1, result.intValue());
    }

    @Test
    public void errorWithNoKey() {
        app.inputFile = "src/test/resources/input.txt";
        app.outputFile = "src/test/resources/output.txt";
        app.type = "aes";

        Integer result = app.Decode();

        assertEquals(1, result.intValue());
    }

    @Test
    public void errorWithNoType() {
        app.inputFile = "src/test/resources/input.txt";
        app.outputFile = "src/test/resources/output.txt";
        app.key = "key";

        Integer result = app.Decode();

        assertEquals(1, result.intValue());
    }

    @Test
    public void errorWithNoFile() {
        app.type = "aes";
        app.key = "12345678123456781234567812345678";

        Integer result = app.Decode();

        assertEquals(1, result.intValue());
    }
}
