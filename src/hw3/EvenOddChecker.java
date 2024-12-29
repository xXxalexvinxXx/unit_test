package hw3;

/**
 * Класс для проверки четности или нечетности числа.
 */
public class EvenOddChecker {
    /**
     * Проверяет, является ли переданное число четным.
     *
     * @param n Число для проверки.
     * @return true, если число четное, иначе false.
     */
    public boolean evenOddNumber(int n) {
        return n % 2 == 0;
    }
}
