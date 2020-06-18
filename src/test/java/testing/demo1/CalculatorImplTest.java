package testing.demo1;

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

    //тестим 0
    @Test
    public void zero_test() throws Exception {
        double result = calculator.calculate("0");
        assertEquals(0, result, 1e-9); //Это 1*10^(-9). Точность 0.000_000_001
    }

    //тестим вычисления с плавующей точкой
    @Test
    public void floating_point_test() throws Exception {
        double result = calculator.calculate("-3e3"); //(-3)*10^3 или -3*1000
        assertEquals(-3000, result, 1e-9);
    }

    //тестим выражения
    @Test
    public void addition_test() throws Exception {
        double result = calculator.calculate("2+2");
        assertEquals(4, result, 1e-9);
    }

    //тестим работу с комплексными числами
    @Test
    public void complex_expression_test() throws Exception {
        double result = calculator.calculate("(2+2)*1.5/10-444");
        assertEquals(-443.4, result, 1e-9);
    }

    //тестим вычисление функций
    @Test
    public void function_test() throws Exception {
        double result = calculator.calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        assertEquals(1, result, 1e-9);
    }

}