package hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class EvenOddCheckerTest {

    @ParameterizedTest
    @CsvSource({
            "2, true, '2 должно быть четным числом.'",
            "3, false, '3 должно быть нечетным числом.'",
            "0, true, '0 должно быть четным числом.'",
            "-4, true, '-4 должно быть четным числом.'",
            "-5, false, '-5 должно быть нечетным числом.'"
    })
    public void testEvenOddNumber(int number, boolean expected, String message) {
        EvenOddChecker checker = new EvenOddChecker();
        assertEquals(expected, checker.evenOddNumber(number), message);
    }
}
