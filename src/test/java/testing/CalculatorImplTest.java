package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.demo1.CalculatorImpl;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для калькулятора
 */
class CalculatorImplTest {

    private CalculatorImpl calculator;

    //для каждого теста будет создан свой калькулятор
    @BeforeEach
    void setUp() {
        calculator = new CalculatorImpl();

    }

    @Test
    public void zero_test() {
        double result = calculator.calculate("0");
        assertEquals(0, result, 1e-9); //Это 1*10^(-9). Точность 0.000_000_001
    }

    @Test
    public void floating_point_test() {
        double result = calculator.calculate("-3e3"); //(-3)*10^3 или -3*1000
        assertEquals(-3000, result, 1e-9);
    }

    //Этот тест не выполнится. Нужно писать реализацию для парсинга "2+2".
    @Test
    public void addition_test() {
        double result = calculator.calculate("2+2");
        assertEquals(4, result, 1e-9);
    }
}