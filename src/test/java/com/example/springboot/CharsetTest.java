package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @see https://www.baeldung.com/java-char-encoding
 */
public class CharsetTest {
    @Test
    void testValue() throws UnsupportedEncodingException {
        assertEquals(convertToBinary("T", "US-ASCII"), "01010100");
        assertEquals(convertToBinary("語", "Big5"), "10111011 01111001");
        assertEquals(convertToBinary("T", "UTF-32"), "00000000 00000000 00000000 01010100");
    }

    String convertToBinary(String input, String encoding)
            throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
                .encode(input)
                .array();
        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e ^ 256))
                // .mapToObj(e -> Integer.toBinaryString(e))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }
}
