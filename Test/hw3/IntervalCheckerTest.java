package hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class IntervalCheckerTest {
    @ParameterizedTest
    @ValueSource(ints = {26, 99})
    void testNumberInIntervalPositive(int number){
        IntervalChecker checker = new IntervalChecker();
        assertTrue(checker.numberInInterval(number), number + "должно быть в интервале (25;100)");
    }

    @ParameterizedTest
    @ValueSource (ints = {25, 100, 0, 101})
    void testNumberInIntervalNegative(int number){
        IntervalChecker checker = new IntervalChecker();
        assertFalse(checker.numberInInterval(number), number + "не должно быть в интервале (25;100)");
    }

}
