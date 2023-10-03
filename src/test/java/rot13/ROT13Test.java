package rot13;

import ch.heigvd.Processor.ROT13.ROT13Processor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ROT13Test {

    private static ROT13Processor rot13Processor;

    @BeforeAll
    public static void setUp() {
        rot13Processor = new ROT13Processor();
    }

    @Test
    public void testProcessWithLowerCaseLetters() {
        byte[] input = "abc".getBytes();
        byte[] expected = "nop".getBytes();
        byte[] actual = rot13Processor.process(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testProcessWithUpperCaseLetters() {
        byte[] input = "ABC".getBytes();
        byte[] expected = "NOP".getBytes();
        byte[] actual = rot13Processor.process(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testProcessWithMixedCaseLetters() {
        byte[] input = "AbC".getBytes();
        byte[] expected = "NoP".getBytes();
        byte[] actual = rot13Processor.process(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testProcessWithNonAlphabeticCharacters() {
        byte[] input = "A1b!".getBytes();
        byte[] expected = "N1o!".getBytes();
        byte[] actual = rot13Processor.process(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testProcessWithEmptyInput() {
        byte[] input = "".getBytes();
        byte[] expected = "".getBytes();
        byte[] actual = rot13Processor.process(input);
        assertArrayEquals(expected, actual);
    }
}
