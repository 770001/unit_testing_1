package testing.demo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testing.demo1.Calculator;
import testing.demo1.CalculatorCli;

import java.io.StringReader;

import static org.mockito.Mockito.*;

/**
 * Класс тестирования коммандного интерфейса калькулятора
 * с использованием моков
 */
public class CalculatorCliTest {

    private Calculator calculatorMock;
    private CalculatorCli calculatorCli;

    //перед каждым тестом свои объекты
    @BeforeEach
    public void setUp() {
        //создаем заглушку или тестовый дублер
        calculatorMock = Mockito.mock(Calculator.class);

        //передаем заглушку в коммандный интерфейс
        calculatorCli = new CalculatorCli(calculatorMock);
    }

    /*
    Тестим что калькулятору передан поток символов,
    он его распрсил и ничего не посчитал.
     */
    @Test
    public void empty_expressions_must_be_skipped() throws Exception {

        //передаем входной поток
        calculatorCli.runInteractiveSession(new StringReader(";\n;    ;;;\t\n;"));

        //проверяем что небыло никаких взаимодействий с заглушкой
        verifyZeroInteractions(calculatorMock);
    }

    /*
    Тестим что калькулятор был вызван три раза и не более
     */
    @Test
    public void each_expression_separated_by_semicolon_must_be_evaluated() throws Exception {
        calculatorCli.runInteractiveSession(new StringReader("1;2;3;"));

        //mock вернет 0.0 для примитивов и null для объектов по умолчанию
        verify(calculatorMock).calculate("1");
        verify(calculatorMock).calculate("2");
        verify(calculatorMock).calculate("3");

        //проверка что больше никаких взаимодействий
        verifyNoMoreInteractions(calculatorMock);
    }

    //Тестим что должен вернуть калькулятор
    @Test
    public void each_expression_separated_by_semicolon_must_be_evaluated_2() throws Exception {

        /*
        Задаем что заглушка будет принимать и что она при этом должна вернуть
        (когда наш калькулятор вызывается с параметром 1 нужно вернуть 1)
         */
        when(calculatorMock.calculate("1")).thenReturn(1d);
        when(calculatorMock.calculate("2")).thenReturn(2d);
        when(calculatorMock.calculate("3")).thenReturn(3d);

        calculatorCli.runInteractiveSession(new StringReader("1;2;3;"));
        verify(calculatorMock).calculate("1");
        verify(calculatorMock).calculate("2");
        verify(calculatorMock).calculate("3");
        verifyNoMoreInteractions(calculatorMock);
    }
}
